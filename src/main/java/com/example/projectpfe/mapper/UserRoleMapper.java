package com.example.projectpfe.mapper;

import com.example.projectpfe.dto.UserRoleDto;
import com.example.projectpfe.pojo.model.UserRole;

//@Mapper
public interface UserRoleMapper {
	//UserRoleMapper INSTANCE = Mappers.getMapper( UserRoleMapper.class );
	//@Mapping(ignore = true, target = "userRoleId")
    UserRoleDto toDto(UserRole entity);
	
	//@Mapping(ignore = true, target = "userRoleId")
    UserRole toEntity(UserRoleDto dto);
}
