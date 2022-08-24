package com.springboot.farm.springbootpractice.domain.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.springboot.farm.springbootpractice.web.dto.board.ReadBoardRespDto;

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
	private String file_name;
	private int user_code;
	private int board_type;		// 1 -> 공지사항	2 -> 자유게시판		3 -> QnA
	private int total_count;
	private String name;
	private int views;
	private int total_reply;
	private String reply;
	private int importance_flag;
	private LocalDateTime create_date;
	private LocalDateTime update_date;
	
//	public CreateBoardRespDto toCreateBoardRespDto() {
//		return CreateBoardRespDto.builder()
//				.boardCode(board_code)
//				.boardTitle(board_title)
//				.boardContent(board_content)
//				.files(files)
//				.userCode(user_code)
//				.boardType(board_type)
//				.name(name)
//				.views(views)
//				.reply(reply)
//				.importanceFlag(importance_flag == 1 ? true : false)
//				.createDate(create_date)
//				.updateDate(update_date)
//				.build();
//	}
	
	public ReadBoardRespDto toReadBoardRespDto() {
		return ReadBoardRespDto.builder()
				.boardCode(board_code)
				.boardTitle(board_title)
				.boardContent(board_content)
				.files(file_name)
				.userCode(user_code)
				.boardType(board_type)
				.totalCount(total_count)
				.name(name)
				.views(views)
				.totalReply(total_reply)
				.reply(reply)
				.importanceFlag(importance_flag == 1 ? true : false)
				.createDate(create_date)
				.updateDate(update_date)
				.build();
	}
}