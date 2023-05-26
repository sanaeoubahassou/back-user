package com.example.projectpfe.mapper;

import com.example.projectpfe.dto.UserDto;
import com.example.projectpfe.dto.UserRoleDto;
import com.example.projectpfe.pojo.model.User;
import com.example.projectpfe.pojo.model.UserRole;

import java.util.List;
import java.util.Set;

public interface DataMapper {


    UserDto user2dto(User user);
    Set<UserDto> userSet2dto(Set<User> users);
    List<UserDto> userList2dto(List<User> users);

    UserRoleDto userRole2dto(UserRole userRole);
    Set<UserRoleDto> userRoleSet2dto(Set<UserRole> userRoles);



}
