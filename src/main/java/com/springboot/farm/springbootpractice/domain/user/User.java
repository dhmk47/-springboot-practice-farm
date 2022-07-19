package com.springboot.farm.springbootpractice.domain.user;

import java.time.LocalDateTime;

import com.springboot.farm.springbootpractice.web.dto.user.CreateUserRespDto;
import com.springboot.farm.springbootpractice.web.dto.user.ReadUserRespDto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
	private int usercode;
	private String name;
	private String username;
	private String password;
	private String email;
	private String roles;
	
	public CreateUserRespDto toCreateUserDto() {
		return CreateUserRespDto.builder()
				.usercode(usercode)
				.name(name)
				.username(username)
				.password(password)
				.email(email)
				.roles(roles)
				.build();
	}
	
	public ReadUserRespDto toReadUserDto() {
		return ReadUserRespDto.builder()
				.usercode(usercode)
				.name(name)
				.username(username)
				.password(password)
				.email(email)
				.roles(roles)
				.build();
	}
}