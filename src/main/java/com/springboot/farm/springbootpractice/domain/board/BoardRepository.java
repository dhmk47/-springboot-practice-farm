package com.springboot.farm.springbootpractice.domain.board;

import org.apache.ibatis.annotations.Mapper;

import com.springboot.farm.springbootpractice.domain.entity.Board;

@Mapper
public interface BoardRepository {
	public int save(Board board);
	public Board getBoardByBoardCode(int board_code);
	public int updateBoardByBoardCode(int board_code);
	public int deleteBoradByBoardCode(int board_code);
}