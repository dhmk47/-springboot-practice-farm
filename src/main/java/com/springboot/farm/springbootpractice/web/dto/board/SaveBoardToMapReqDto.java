package com.springboot.farm.springbootpractice.web.dto.board;

import lombok.Data;

@Data
public class SaveBoardToMapReqDto {
	private String boardTitle;
	private String boardContent;
	private String boardType;
	private boolean importanceFlag;
}