package com.springboot.farm.springbootpractice.web.dto.reply;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReadReplyRespDto {
	private int boardCode;
	private String reply;
	private String name;
	private String roles;
	private Object time;
	private int totalReplyCount;
	private LocalDateTime createDate;
	private LocalDateTime updateDate;
	
}
