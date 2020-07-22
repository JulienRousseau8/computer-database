package com.excilys.mapper;

import org.springframework.stereotype.Component;

import com.excilys.dto.MyUserDTO;
import com.excilys.model.MyUser;

@Component
public class UserDTOMapper {

	public MyUser DtoToUser(MyUserDTO userDto) {
		MyUser user = new MyUser.Builder()
				.setId(userDto.getId() == null ? 0 : Long.parseLong(userDto.getId()))
				.setUsername(userDto.getUsername())
				.setPassword(userDto.getPassword())
				.setRole(userDto.getRole())
				.build();
		return user;
	}
	
	public MyUserDTO userToDto(MyUser user) {
		MyUserDTO userDto = new MyUserDTO.Builder()
				.setId(String.valueOf(user.getId()))
				.setUsername(user.getUsername())
				.setPassword(user.getPassword())
				.setRole(user.getRole())
				.build();
		return userDto;
	}
}
