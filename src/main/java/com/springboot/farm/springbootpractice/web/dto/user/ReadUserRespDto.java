package com.springboot.farm.springbootpractice.web.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReadUserRespDto {
	private int usercode;
	private String name;
	private String username;
	private String password;
	private String email;
	private String roles;
}