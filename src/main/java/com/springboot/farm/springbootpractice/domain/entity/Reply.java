package com.springboot.farm.springbootpractice.domain.entity;

import java.time.LocalDateTime;

import lombok.Builder;

@Builder
public class Reply {
	private int board_code;
	private String reply;
	private int user_code;
	private LocalDateTime create_date;
	private LocalDateTime update_date;
	
}