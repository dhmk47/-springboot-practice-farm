package com.springboot.farm.springbootpractice.domain.entity;

import com.springboot.farm.springbootpractice.web.dto.board.BoardFileRespDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardFile {
	private String file_name;
	private int board_code;
	
	public BoardFileRespDto toDto() {
		return BoardFileRespDto.builder()
				.fileName(file_name)
				.build();
	}
}