package com.example.projectpfe.dto;

import com.example.projectpfe.pojo.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String igg;
    private String firstName;
    private String lastName;
    private  String country;
    private  String city;
    private String email;
    //private String password;
    private Set<UserRole> userRoles;



}
