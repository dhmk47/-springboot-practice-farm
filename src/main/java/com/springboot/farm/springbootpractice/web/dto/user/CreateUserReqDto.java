package com.springboot.farm.springbootpractice.web.dto.user;

import com.springboot.farm.springbootpractice.domain.entity.User;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreateUserReqDto {
	private String name;
	private String username;
	private String password;
	private String email;
	
	public User toEntity() {
		return User.builder()
				.name(name)
				.username(username)
				.password(password)
				.email(email)
				.roles("ROLE_USER")
				.build();
	}
}