package com.example.projectpfe.mapper;

import com.example.projectpfe.dto.UserDto;
import com.example.projectpfe.dto.UserRoleDto;
import com.example.projectpfe.pojo.model.User;
import com.example.projectpfe.pojo.model.UserRole;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DataMapperImp implements  DataMapper{


    @Override
    public UserDto user2dto(User user) {
        if(user == null){
            return null;
        }
        UserDto userDto = new UserDto();
        //userDto.setId(user.getId());
        userDto.setIgg(user.getIgg());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setCountry(user.getCountry());
        userDto.setCity(user.getCity());
        userDto.setEmail(user.getEmail());
        userDto.setUserRoles(Collections.unmodifiableSet(user.getUserRoles()));
        return userDto;

    }

    @Override
    public Set<UserDto> userSet2dto(Set<User> users) {
        if(users==null){
            return null;
        }
        Set<UserDto> set = new HashSet<UserDto>(Math.max((int)(users.size() / .75f)+1,16));
        for(User user: users){
            set.add(user2dto(user));
        }
        return set;
    }

    @Override
    public List<UserDto> userList2dto(List<User> users) {
        if(users == null){
            return  null;
        }
        List<UserDto> list =new ArrayList<UserDto>(users.size());
        for (User user : users){
            list.add(user2dto(user));
        }
        return list;
    }

    @Override
    public UserRoleDto userRole2dto(UserRole userRole) {
        if(userRole == null){
            return null;
        }
        UserRoleDto userRoleDto = new UserRoleDto();
        userRoleDto.setId(userRole.getId());
        userRoleDto.setRole(userRole.getRole());
        userRoleDto.setChecked(userRole.isChecked());

        return userRoleDto;
    }

    @Override
    public Set<UserRoleDto> userRoleSet2dto(Set<UserRole> userRoles) {
        if(userRoles==null){
            return null;
        }
        Set<UserRoleDto> set = new HashSet<UserRoleDto>(Math.max((int)(userRoles.size() / .75f)+1,16));
        for(UserRole userRole: userRoles){
            set.add(userRole2dto(userRole));
        }
        return set;

    }


}
