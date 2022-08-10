package com.springboot.farm.springbootpractice.domain.entity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import com.springboot.farm.springbootpractice.web.dto.user.CreateUserRespDto;
import com.springboot.farm.springbootpractice.web.dto.user.ReadUserRespDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
	private int usercode;
	private String name;
	private String username;
	private String password;
	private String email;
	private String roles;
	private int money;
	private LocalDateTime create_date;
	private LocalDateTime update_date;
	
	public CreateUserRespDto toCreateUserDto() {
		return CreateUserRespDto.builder()
				.usercode(usercode)
				.name(name)
				.username(username)
				.password(password)
				.email(email)
				.roles(roles)
				.money(money)
				.create_date(create_date)
				.update_date(update_date)
				.build();
	}
	
	public ReadUserRespDto toReadUserDto() {
		return ReadUserRespDto.builder()
				.userCode(usercode)
				.name(name)
				.username(username)
				.password(password)
				.email(email)
				.roles(roles)
				.money(money)
				.create_date(create_date)
				.update_date(update_date)
				.build();
	}
	
	public List<String> getRolesToList() {
		return Arrays.asList(roles.replaceAll(" ", "").split(","));
	}
}