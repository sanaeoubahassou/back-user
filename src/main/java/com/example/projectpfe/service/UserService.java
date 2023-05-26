package com.example.projectpfe.service;


import com.example.projectpfe.dto.UserDto;
import com.example.projectpfe.pojo.emuns.RoleEnum;
import com.example.projectpfe.pojo.req.UserReq;
import com.example.projectpfe.pojo.req.UserUpdateReq;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface UserService {
    UserDto createUser (UserReq userReq);
    UserDto getUserbyIgg(String igg);
    UserDto updateUser(UserUpdateReq userReq,String igg);
    Page<UserDto> allUsers(int size, int page);
    void deleteUser(String igg);
    Set<UserDto>  filter(String igg,String firstName, String lastName, String city,String country,RoleEnum roleEnum);// add roles and user branch
   // Page<UserDto>  filter(String igg, String firstName, String lastName, String city,String country,RoleEnum roleEnum, int pageNumber, int pageSize);// add roles and user branch

}
