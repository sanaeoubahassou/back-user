package com.example.projectpfe.mapper.mapperImpl;

import com.example.projectpfe.dto.UserDto;
import com.example.projectpfe.mapper.UserMapper;
import com.example.projectpfe.pojo.model.User;
import org.springframework.stereotype.Component;
@Component
public class UserMapperImpl implements UserMapper {

	@Override
	public UserDto toDto(User entity) {
		UserDto userDto = new UserDto();
		userDto.setCity(entity.getCity());
		userDto.setCountry(entity.getCountry());
		userDto.setFirstName(entity.getFirstName());
		//userDto.setId(entity.getId());
		userDto.setIgg(entity.getIgg());
		userDto.setLastName(entity.getLastName());
		userDto.setEmail(entity.getEmail());
		return userDto;
	}

	@Override
	public User toEntity(UserDto dto) {
		User entity = new User();
		entity.setCity(dto.getCity());
		entity.setCountry(dto.getCountry());
		entity.setFirstName(dto.getFirstName());
		//entity.setId(dto.getId());
		entity.setIgg(dto.getIgg());
		entity.setLastName(dto.getLastName());
		entity.setEmail(dto.getEmail());
		return entity;
	}

}
