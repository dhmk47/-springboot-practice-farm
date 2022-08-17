package com.springboot.farm.springbootpractice.web.dto.user;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReadUserRespDto {
	private int userCode;
	private String name;
	private String username;
	private String oAuth2_id;
	private String password;
	private String email;
	private String roles;
	private int money;
	private String phone;
	private LocalDateTime create_date;
	private LocalDateTime update_date;
}