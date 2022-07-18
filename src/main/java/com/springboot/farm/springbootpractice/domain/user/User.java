package com.springboot.farm.springbootpractice.domain.user;

import java.time.LocalDateTime;

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
	private LocalDateTime create_date;
	private LocalDateTime update_date;
}