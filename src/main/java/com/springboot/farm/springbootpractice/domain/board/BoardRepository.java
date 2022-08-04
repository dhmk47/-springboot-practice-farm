package com.springboot.farm.springbootpractice.domain.board;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.springboot.farm.springbootpractice.domain.entity.Board;

@Mapper
public interface BoardRepository {
	public int save(Board board);
	public Board getBoardByBoardCode(int board_code, int board_type);
	public List<Board> getBoardList(Map<String, Object> map);
	public int updateBoardByBoardCode(int board_code);
	public int deleteBoradByBoardCode(int board_code);
}