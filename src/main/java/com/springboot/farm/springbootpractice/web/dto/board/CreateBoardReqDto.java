package com.springboot.farm.springbootpractice.web.dto.board;

import com.springboot.farm.springbootpractice.domain.entity.Board;

import lombok.Data;

@Data
public class CreateBoardReqDto {
	private String boardTitle;
	private String boardContent;
	private int userCode;
	private int boardType;
	
	public Board toEntity() {
		return Board.builder()
				.board_title(boardTitle)
				.board_content(boardContent)
				.user_code(userCode)
				.board_type(boardType)
				.build();
	}
}