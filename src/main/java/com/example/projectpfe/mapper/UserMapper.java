package com.example.projectpfe.mapper;

import com.example.projectpfe.dto.UserDto;
import com.example.projectpfe.dto.UserRoleDto;
import com.example.projectpfe.pojo.model.User;
import com.example.projectpfe.pojo.model.UserRole;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

//@Mapper(componentModel = "spring")
public interface UserMapper {

//	UserMapper INSTANCE = Mappers.getMapper( UserMapper.class ); 
	
//	@Mapping(source= "id" , target = "id")
//	@Mapping(source= "igg" , target = "igg")
//	@Mapping(source= "firstName" , target = "firstName")
//	@Mapping(source= "lastName" , target = "lastName")
//	@Mapping(source= "country" , target = "country")
//	@Mapping(source= "city" , target = "city")
//	@Mapping(source= "userRoles" , target = "userRoles", qualifiedByName = "mapUserRoleToDTO")
	UserDto toDto(User entity);
	
//	@Mapping(source= "id" , target = "id")
//	@Mapping(source= "igg" , target = "igg")
//	@Mapping(source= "firstName" , target = "firstName")
//	@Mapping(source= "lastName" , target = "lastName")
//	@Mapping(source= "country" , target = "country")
//	@Mapping(source= "city" , target = "city")
//	@Mapping(source= "userRoles" , target = "userRoles", qualifiedByName = "mapUserRoleToEntity")
    User toEntity(final UserDto dto);

	@Named("mapUserRoleToEntity")
    default List<UserRole> mapAddressesDtoToEntity(List<UserRoleDto> userRoleDto) {
        return userRoleDto.stream()
        		.map((UserRoleDto user)->(
						new UserRole(user.getId(),user.getRole(),user.isChecked())
						)).collect(Collectors.toList());
    }
	
	@Named("mapUserRoleToDTO")
    default List<UserRoleDto> mapAddressesEntityToDto(List<UserRole> userRole) {
        return userRole.stream()
        		.map((UserRole user)->(
						new UserRoleDto(user.getId(),user.getRole(),user.isChecked())
						)).collect(Collectors.toList());
    }

}
