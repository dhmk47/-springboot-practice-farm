package com.springboot.farm.springbootpractice.web.dto.board;

import java.time.LocalDateTime;

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
	private int userCode;
	private int boardType;
	private String name;
	private LocalDateTime createDate;
	private LocalDateTime updateDate;
}