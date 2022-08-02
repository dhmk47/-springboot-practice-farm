package com.springboot.farm.springbootpractice.domain.entity;

import java.time.LocalDateTime;

import com.springboot.farm.springbootpractice.web.dto.board.CreateBoardRespDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Board {
	private int board_code;
	private String board_title;
	private String board_content;
	private int user_code;
	private int board_type;		// 1 -> 공지사항	2 -> 자유게시판		3 -> QnA
	private LocalDateTime create_date;
	private LocalDateTime update_date;
	
	public CreateBoardRespDto toCreateBoardRespDto() {
		return CreateBoardRespDto.builder()
				.boardCode(board_code)
				.boardTitle(board_title)
				.boardContent(board_content)
				.userCode(user_code)
				.boardType(board_type)
				.createDate(create_date)
				.updateDate(update_date)
				.build();
	}
}