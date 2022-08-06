package com.springboot.farm.springbootpractice.web.dto.reply;

import com.springboot.farm.springbootpractice.domain.entity.Reply;

import lombok.Data;

@Data
public class CreateReplyReqDto {
	private int boardCode;
	private String reply;
	private int userCode;

	public Reply toEntity() {
		return Reply.builder()
		.board_code(boardCode)
		.reply(reply)
		.user_code(userCode)
		.build();
	}
}