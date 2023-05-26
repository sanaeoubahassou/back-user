package com.example.projectpfe.service.impl;
import com.example.projectpfe.dto.UserDto;
import com.example.projectpfe.exception.ExceptionApi;
import com.example.projectpfe.exception.PayLoadExceptionItem;
import com.example.projectpfe.mapper.DataMapper;
import com.example.projectpfe.pojo.model.User;
import com.example.projectpfe.pojo.emuns.RoleEnum;
import com.example.projectpfe.pojo.req.UserReq;
import com.example.projectpfe.pojo.req.UserUpdateReq;
import com.example.projectpfe.repository.AuthRepository;
import com.example.projectpfe.repository.UserRepository;
import com.example.projectpfe.repository.UserRoleRepository;
import com.example.projectpfe.service.UserService;
import com.example.projectpfe.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserServiceImp implements UserService {


    @Autowired
    UserRepository userRepository;


    @Autowired
    UserRoleRepository userRoleRepository;
    @Autowired
    Utils utils;

    @Autowired
    DataMapper dataMapper;

    @Autowired
    AuthRepository authRepository;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Autowired
    EmailService emailService;

    @Override
    public UserDto createUser(UserReq userReq) {
        User user = new User();
        user.setIgg(utils.generateIgg());
        userRepository.findByIggAndAndDeletedIsFalse(user.getIgg().trim())
                .ifPresent(
                        e ->{
                            throw  new ExceptionApi(PayLoadExceptionItem.Igg_Already_Used);
                        }
                );

        userRepository.findByEmailAndDeletedIsFalse(userReq.getEmail().trim())
                .ifPresent(
                        e ->{
                            throw  new ExceptionApi(PayLoadExceptionItem.Email_Already_Used);
                        }
                );

        user.setEmail(userReq.getEmail());
        String password = Utils.generatePassword();
        user.setPassword(passwordEncoder.encode(password));
        user.setFirstName(userReq.getFirstName());
        user.setLastName(userReq.getLastName());
        user.setCountry(userReq.getCountry());
        user.setCity(userReq.getCity());
        emailService.sendEmailWithIgg(user.getEmail(),user.getIgg(), password);
        userRepository.save(user);
        user.setUserRoles(userReq.getUserRoles());
        userRepository.save(user);
        return dataMapper.user2dto(user);


    }

    @Override
    public UserDto getUserbyIgg(String igg) {

        return dataMapper.user2dto(userRepository
                .findByIggAndAndDeletedIsFalse(igg).orElseThrow(
                        () -> {
                            throw new ExceptionApi(PayLoadExceptionItem.User_Not_Found);
                        }
                )
        );
    }

    @Override
    public UserDto updateUser(UserUpdateReq userReq, String igg) {
        User user = userRepository.getUserByIgg(igg);
        if (user == null){
            throw new ExceptionApi(PayLoadExceptionItem.User_Not_Found);
        }

        user.setFirstName(userReq.getFirstName());
        user.setLastName(userReq.getLastName());
        user.setCountry(userReq.getCountry());
        user.setCity(userReq.getCity());
        user.setEmail(userReq.getEmail());
        userRepository.save(user);
        user.setUserRoles(userReq.getUserRoles());
        userRepository.save(user);
        return dataMapper.user2dto(user);
    }

    @Override
    public Page<UserDto> allUsers(int size, int page) {
        Page pageUsers = userRepository.allUers(PageRequest.of(page, size));

        List<UserDto> listUsers = dataMapper.userList2dto(pageUsers.getContent());
        return new PageImpl<>(listUsers, pageUsers.getPageable(), pageUsers.getTotalElements());
        //return dataMapper.userSet2dto(users);

    }

    @Override
    public void deleteUser(String igg) {
        userRepository
                .findByIggAndAndDeletedIsFalse(igg)
                .ifPresent(
                        h -> {
                            h.setDeleted(true);
                            userRepository.save(h);
                        }
                );
    }

    @Override
    public Set<UserDto> filter(String igg, String firstName, String lastName, String city, String country, RoleEnum roleEnum) {
       // Pageable pageable = PageRequest.of(page, size, Sort.by("lastName"));
       // Page<User> userPage = userRepository.findAllByDeletedIsFalse(pageable);
        Set<User> users = userRepository.findAllByDeletedIsFalseOrderByLastName();

        if (igg != null) {
            users = users.stream()
                    .filter(p -> p.getIgg().contains(igg))
                    .collect(Collectors.toSet());
        }

        if (firstName != null) {
            String lowercaseFirstName = firstName.toLowerCase();
            users = users.stream()
                    .filter(p -> p.getFirstName().toLowerCase().contains(lowercaseFirstName))
                    .collect(Collectors.toSet());
        }
        if (lastName != null) {
            String lowercaseLastName = lastName.toLowerCase();
            users = users.stream()
                    .filter(p -> p.getLastName().toLowerCase().contains(lowercaseLastName))
                    .collect(Collectors.toSet());
        }

        if (city != null) {
            String lowercaseCity = city.toLowerCase();
            users = users.stream()
                    .filter(p -> p.getCity().toLowerCase().contains(lowercaseCity))
                    .collect(Collectors.toSet());
        }
        if (country != null) {
            String lowercaseCountry = country.toLowerCase();
            users = users.stream()
                    .filter(p -> p.getCountry().toLowerCase().contains(lowercaseCountry))
                    .collect(Collectors.toSet());
        }

        if (roleEnum != null) {
            users = users.stream()
                    .filter(p -> p.getUserRoles().stream().anyMatch(r -> r.getRole().equals(roleEnum)))
                    .collect(Collectors.toSet());
        }


        return dataMapper.userSet2dto(users);
    }

}

/*

@Override
public Page<UserDto> filter(String igg, String firstName, String lastName, String city, String country, RoleEnum roleEnum, int size, int page) {
    if (igg == null && firstName == null && lastName == null && city == null && country == null && roleEnum == null) {
        // No filter values provided, call the service method to retrieve all users
        return this.allUsers(size,page);
    }
   //Filter

    Pageable pageable = PageRequest.of(page, size, Sort.by("lastName"));
    Page<User> userPage = userRepository.findAllByDeletedIsFalse(pageable);
    List<User> filteredUsers = userPage.stream()
            .filter(p -> (igg == null || p.getIgg().contains(igg)))
            .filter(p -> (firstName == null || p.getFirstName().contains(firstName)))
            .filter(p -> (lastName == null || p.getLastName().equalsIgnoreCase(lastName)))
            .filter(p -> (city == null || p.getCity().contains(city)))
            .filter(p -> (country == null || p.getCountry().contains(country)))
            .filter(p -> (roleEnum == null || p.getUserRoles().stream().anyMatch(r -> r.getRole().equals(roleEnum))))
            .collect(Collectors.toList());

    List<UserDto> userDtos = dataMapper.userList2dto(filteredUsers);
    return new PageImpl<>(userDtos, pageable, userPage.getTotalElements());
}
*/


/*
    List<User> filteredUsers=new ArrayList<>();

    if(igg != null){
       filteredUsers=userPage.stream()
                .filter(p ->p.getIgg().contains(igg))
                .collect(Collectors.toList());
    }
    if (firstName != null) {
        String lowercaseFirstName = firstName.toLowerCase();
         filteredUsers=userPage.stream()
                .filter(p -> p.getFirstName().toLowerCase().contains(lowercaseFirstName))
                .collect(Collectors.toList());
    }
    if (lastName != null) {
        String lowercaseLastName = lastName.toLowerCase();
         filteredUsers=userPage.stream()
                .filter(p -> p.getLastName().toLowerCase().contains(lowercaseLastName))
                .collect(Collectors.toList());
    }
    if (city != null) {
        String lowercaseCity = city.toLowerCase();
        filteredUsers=userPage.stream()
                .filter(p -> p.getCity().toLowerCase().contains(lowercaseCity))
                .collect(Collectors.toList());
    }
    if (country != null) {
        String lowercaseCountry = country.toLowerCase();
        filteredUsers=userPage.stream()
                .filter(p -> p.getCountry().toLowerCase().contains(lowercaseCountry))
                .collect(Collectors.toList());
    }
    if(roleEnum != null){
        filteredUsers=userPage.stream()
                .filter(p ->p.getUserRoles().stream().anyMatch(r ->r.getRole().equals(roleEnum)))
                .collect(Collectors.toList());
    }

 */

 /*
    List<User> filteredUsers = userPage.stream()
            .filter(user -> {
                boolean isIggMatch = igg == null || user.getIgg().contains(igg);
                boolean isFirstNameMatch = firstName == null || user.getFirstName().toLowerCase().contains(firstName.toLowerCase());
                boolean isLastNameMatch = lastName == null || user.getLastName().toLowerCase().contains(lastName.toLowerCase());
                boolean isCityMatch = city == null || user.getCity().toLowerCase().contains(city.toLowerCase());
                boolean isCountryMatch = country == null || user.getCountry().toLowerCase().contains(country.toLowerCase());
                boolean isRoleMatch = roleEnum == null || user.getUserRoles().stream().anyMatch(r -> r.getRole().equals(roleEnum));
                return isIggMatch && isFirstNameMatch && isLastNameMatch && isCityMatch && isCountryMatch && isRoleMatch;
            })
            .collect(Collectors.toList());
*/