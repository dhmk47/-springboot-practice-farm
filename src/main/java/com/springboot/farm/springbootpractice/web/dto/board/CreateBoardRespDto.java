package com.springboot.farm.springbootpractice.web.dto.board;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateBoardRespDto {
	private int boardCode;
	private String boardTitle;
	private String boardContent;
	private List<BoardFileRespDto> files;
	private int userCode;
	private int boardType;
	private String name;
	private int views;
	private String reply;
	private boolean importanceFlag;
	private LocalDateTime createDate;
	private LocalDateTime updateDate;
}