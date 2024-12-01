package com.spring.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtHelper {

    // Token validity period set to 5 hours (5hr * 60min * 60s)
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60; //5 hour active
//    public static final long JWT_TOKEN_VALIDITY = 60; 1 second active

    // Secret key for signing the JWT
    private String secret = "afafasfafafasfasfasfafacasdasfasxASFACASDFACASDFASFASFDAFASFASDAADSCSDFADCVSGCFVADXCcadwavfsfarvf";

    // Retrieve username from JWT token
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    // Retrieve expiration date from JWT token
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    // Retrieve a single claim from JWT token
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    // Retrieve all claims from JWT token using the secret key
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).build().parseClaimsJws(token).getBody();
    }

    // Check if the token has expired
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    // Generate token for user
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername());
    }

    // Generate the token by defining claims, signing with HS512 algorithm, and compacting to URL-safe string
    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000)) // Convert to milliseconds
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    // Validate the token by checking the username and if the token is expired
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}





// package com.codemyth.jwt;

// import io.jsonwebtoken.Claims;
// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.SignatureAlgorithm;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.stereotype.Component;

// import java.util.Date;
// import java.util.HashMap;
// import java.util.Map;
// import java.util.function.Function;

// @Component
// public class JwtHelper {
//     //requirement :
//     public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60; // this is 5hr * 60min * 60 seconds

//     private String secret = "afafasfafafasfasfasfafacasdasfasxASFACASDFACASDFASFASFDAFASFASDAADSCSDFADCVSGCFVADXCcadwavfsfarvf";

//     //retrieve username from jwt token
//     public String getUsernameFromToken(String token) {
//         return getClaimFromToken(token, Claims::getSubject);
//     }

//     //retrieve expiration date from jwt token
//     public Date getExpirationDateFromToken(String token) {
//         return getClaimFromToken(token, Claims::getExpiration);
//     }

//     public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
//         final Claims claims = getAllClaimsFromToken(token);
//         return claimsResolver.apply(claims);
//     }

//     //for retrieving any information from token we will need the secret key
//     @SuppressWarnings("deprecation")
//     private Claims getAllClaimsFromToken(String token) {
//         return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
//     }

//     //check if the token has expired
//     private Boolean isTokenExpired(String token) {
//         final Date expiration = getExpirationDateFromToken(token);
//         return expiration.before(new Date());
//     }

//     //generate token for user
//     public String generateToken(UserDetails userDetails) {
//         Map<String, Object> claims = new HashMap<>();
//         return doGenerateToken(claims, userDetails.getUsername());
//     }

//     //while creating the token -
//     //1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
//     //2. Sign the JWT using the HS512 algorithm and secret key.
//     //3. According to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
//     //   compaction of the JWT to a URL-safe string
//     private String doGenerateToken(Map<String, Object> claims, String subject) {

//         return Jwts.builder()
//                 .setClaims(claims)
//                 .setSubject(subject)
//                 .setIssuedAt(new Date(System.currentTimeMillis()))
//                 .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000)) // converting to milliseconds
//                 .signWith(SignatureAlgorithm.HS512, secret)
//                 .compact();
//     }

//     //validate token
//     public Boolean validateToken(String token, UserDetails userDetails) {
//         final String username = getUsernameFromToken(token);
//         return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
//     }
// }
