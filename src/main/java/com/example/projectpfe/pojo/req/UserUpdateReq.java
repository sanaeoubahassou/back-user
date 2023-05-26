package com.example.projectpfe.pojo.req;

import com.example.projectpfe.pojo.model.UserRole;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;

@Data
public class UserUpdateReq {
    @Pattern(regexp = "^[a-zA-Z ._-]{2,}$",message = "Entrer juste les characters et min deux")
    private String firstName;
    @Pattern(regexp = "^[a-zA-Z ._-]{2,}$",message = "Entrer juste les characters et min deux")
    private String lastName;
    @Pattern(regexp = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$", message = "Enter l'email")
    private String email;
    @NotBlank(message = "Entrer le pays")
    private  String country;
    @NotBlank(message = "Enter la ville")
    private  String city;
    private Set<UserRole> userRoles = new HashSet<>();


}
