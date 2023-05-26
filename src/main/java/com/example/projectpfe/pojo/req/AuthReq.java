package com.example.projectpfe.pojo.req;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


@Data
public class AuthReq {
    @NotBlank(message = "Enter the valid egg")
    private String igg;

    @NotBlank(message = "Enter the password")
    private String password;


}
