package com.example.projectpfe.mapper.mapperImpl;

import com.example.projectpfe.dto.UserRoleDto;
import com.example.projectpfe.mapper.UserRoleMapper;
import com.example.projectpfe.pojo.model.UserRole;

public class UserRoleMapperImpl implements UserRoleMapper {
    @Override
    public UserRoleDto toDto(UserRole entity) {
        UserRoleDto userRoleDto = new UserRoleDto();
        userRoleDto.setRole(entity.getRole());
        userRoleDto.setChecked(entity.isChecked());
        return userRoleDto;
    }

    @Override
    public UserRole toEntity(UserRoleDto dto) {
        UserRole userRole = new UserRole();
        userRole.setId(userRole.getId());
        userRole.setChecked(dto.isChecked());
        userRole.setRole(dto.getRole());
        return userRole;
    }

}
