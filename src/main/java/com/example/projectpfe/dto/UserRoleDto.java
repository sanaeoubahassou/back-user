package com.example.projectpfe.dto;

import com.example.projectpfe.pojo.emuns.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleDto {
    private Long id;
    private RoleEnum role;
    private boolean checked;

}
