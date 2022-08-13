package com.springboot.farm.springbootpractice.web.dto.board;

import com.springboot.farm.springbootpractice.domain.entity.Board;

import lombok.Data;

@Data
public class UpdateBoardReqDto {
	private int boardCode;
	private String boardTitle;
	private String boardContent;
	private boolean importanceFlag;
	
	public Board toEntity() {
		return Board.builder()
				.board_code(boardCode)
				.board_title(boardTitle)
				.board_content(boardContent)
				.importance_flag(importanceFlag ? 1 : 0)
				.build();
	}
}