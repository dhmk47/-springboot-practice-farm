package com.springboot.farm.springbootpractice.domain.board;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.springboot.farm.springbootpractice.domain.entity.Board;
import com.springboot.farm.springbootpractice.domain.entity.BoardFile;

@Mapper
public interface BoardRepository {
	public int saveBoard(Board board);
	public int saveFiles(List<BoardFile> fileList);
	public List<Board> getBoardByBoardCode(int board_code, int board_type);
	public List<Board> getBoardList(Map<String, Object> map);
	public int updateBoardByBoardCode(Board board);
	public int updateBoardViewCount(int board_code);
	public int delete(int board_code);
}