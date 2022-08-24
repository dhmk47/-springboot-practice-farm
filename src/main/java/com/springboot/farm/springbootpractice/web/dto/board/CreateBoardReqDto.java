package com.springboot.farm.springbootpractice.web.dto.board;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.springboot.farm.springbootpractice.domain.entity.Board;

import lombok.Data;

@Data
public class CreateBoardReqDto {
	private String boardTitle;
	private String boardContent;
	private int userCode;
	private int boardType;
	private boolean importanceFlag;
	private List<MultipartFile> files;
	
	public Board toEntity() {
		return Board.builder()
				.board_title(boardTitle)
				.board_content(boardContent)
				.user_code(userCode)
				.board_type(boardType)
				.importance_flag(importanceFlag ? 1 : 0)
				.build();
	}
}