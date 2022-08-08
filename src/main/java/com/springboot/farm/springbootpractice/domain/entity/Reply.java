package com.springboot.farm.springbootpractice.domain.entity;

import java.time.LocalDateTime;

import com.springboot.farm.springbootpractice.web.dto.reply.ReadReplyRespDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Reply {
	private int board_code;
	private String reply;
	private String name;
	private String roles;
	private int user_code;
	private LocalDateTime create_date;
	private LocalDateTime update_date;
	
	public ReadReplyRespDto toDto() {
		return ReadReplyRespDto.builder()
				.boardCode(board_code)
				.reply(reply)
				.name(name)
				.roles(roles)
				.createDate(create_date)
				.updateDate(update_date)
				.build();
	}
	
}