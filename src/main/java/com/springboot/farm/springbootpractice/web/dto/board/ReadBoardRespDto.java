package com.springboot.farm.springbootpractice.web.dto.board;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ReadBoardRespDto {
	private int boardCode;
	private String boardTitle;
	private String boardContent;
	private int userCode;
	private int boardType;		// 1 -> 공지사항	2 -> 자유게시판		3 -> QnA
	private int totalCount;
	private String name;
	private LocalDateTime createDate;
	private LocalDateTime updateDate;
}
