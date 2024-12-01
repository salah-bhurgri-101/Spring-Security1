package com.spring.service.impl;

import com.spring.constant.AppConstants;
import com.spring.dto.AddressDto;
import com.spring.dto.UserDto;
import com.spring.modal.Address;
import com.spring.modal.Role;
import com.spring.modal.User;
import com.spring.repository.RoleRepo;
import com.spring.repository.UserRepo;
import com.spring.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDto addUser(UserDto userDto) {
        List<AddressDto> addresses = userDto.getAddresses();
        for (AddressDto a : addresses){
           a.setUser(userDto);
        }
        User user = modelMapper.map(userDto, User.class);

//--------add two role or many by programmer :
//        Role role = roleRepo.findById(AppConstants.NORMAL_USER).orElseThrow(() -> new RuntimeException("Role Not Found"));
//        Role role1 = roleRepo.findById(AppConstants.ADMIN_USER).orElseThrow(() -> new RuntimeException("Role Not Found"));
//        Set<Role> roles = user.getRoles();
//        roles.add(role);
//        roles.add(role1);

//--------add one role by programmer
//        Role role = roleRepo.findById(AppConstants.NORMAL_USER).orElseThrow(() -> new RuntimeException("Role Not Found"));
//        user.getRoles().add(role);

//______add new role by new user work here
        Set<Role> roles = user.getRoles();
        Set<Role> newRole = new HashSet<>(roleRepo.findAll());

        for (Role role : roles){
            for(Role role1 : newRole){
                if(role.getName().equals(role1.getName())){
                    role.setRole_id(role1.getRole_id());
//                    roles.add(role);  role direct add in user through java that why I comment it
                    break;
                }
            }
        }

//--------tostring() is stack over flow problem that way getter() and setter
//        System.out.println("Updated"+ user);

//      user.setRoles(roles); role direct add in user through java that why I comment it
        user.setUser_id(1L);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User save = userRepo.save(user);


//--------toString()  is stack over flow problem that way getter() and setter
//        UserDto map = modelMapper.map(save, UserDto.class);
//        System.out.println("Updated"+ map);
//        return map;

                return  modelMapper.map(save, UserDto.class);
    }

    @Override
    public UserDto getById(Long id) {
        return null;
    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> all = userRepo.findAll();
        List<UserDto> allUserDto = new ArrayList<>();
        for (User user : all){
            UserDto map = modelMapper.map(user, UserDto.class);
            allUserDto.add(map);
        }
        return allUserDto;
    }

    @Override
    public UserDto updateUser(UserDto userDto, Long userId) {

//        List<AddressDto> addresses = userDto.getAddresses();
//        List<Address> addressess = new ArrayList<>();
//        for (AddressDto a : addresses){
////            a.setUser(userDto);
//            Address map = modelMapper.map(a, Address.class);
//            addressess.add(map);
//        }

        User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User Not Found"));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
//        user.setAddresses(userDto.getAddresses().stream().map((addressDto)->modelMapper.map(addressDto,Address.class)).collect(Collectors.toList()));
//        user.setAddresses(addressess);

//        List<Address> oldAddress = user.getAddresses();
//        for(Address a : oldAddress){
//            for (AddressDto b : userDto.getAddresses()) {
//                a.setState(b.getState());
//                a.setCity(b.getCity());
//                a.setStreet(b.getStreet());
//                a.setZipcode(b.getZipcode());
//            }
//        }

        List<Address> oldAddress = user.getAddresses();
        List<AddressDto> newAddresses = userDto.getAddresses();

//        for (int i = 0; i < oldAddress.size() && i < newAddresses.size(); i++) {
//            Address a = oldAddress.get(i);
//            AddressDto b = newAddresses.get(i);
//
//            a.setState(b.getState());
//            a.setCity(b.getCity());
//            a.setStreet(b.getStreet());
//            a.setZipcode(b.getZipcode());
//        }
        UserServiceImpl.updateAddressList(oldAddress, newAddresses);

        return modelMapper.map(userRepo.save(user),UserDto.class);
    }

    @Override
    public UserDto findByName(String username) {
        return modelMapper.map(userRepo.findByName(username).orElseThrow(() -> new RuntimeException("User Not Found")) , UserDto.class);
    }

    public static void updateAddressList(List<Address> oldAddresses, List<AddressDto> newAddressDtos) {
        // Ensure both lists are of the same size
        if (oldAddresses.size() != newAddressDtos.size()) {
            throw new IllegalArgumentException("Address lists must have the same size.");
        }

        // Update each address with corresponding addressDto
        for (int i = 0; i < oldAddresses.size(); i++) {
            Address oldAddress = oldAddresses.get(i);
            AddressDto newAddressDto = newAddressDtos.get(i);

            // Update the address fields from the DTO
            oldAddress.setState(newAddressDto.getState());
            oldAddress.setCity(newAddressDto.getCity());
            oldAddress.setStreet(newAddressDto.getStreet());
            oldAddress.setZipcode(newAddressDto.getZipcode());
        }
   }
}
