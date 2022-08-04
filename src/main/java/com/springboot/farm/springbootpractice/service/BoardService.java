package com.springboot.farm.springbootpractice.service;

import java.util.List;

import com.springboot.farm.springbootpractice.web.dto.board.CreateBoardReqDto;
import com.springboot.farm.springbootpractice.web.dto.board.CreateBoardRespDto;
import com.springboot.farm.springbootpractice.web.dto.board.ReadBoardRespDto;

public interface BoardService {
	public CreateBoardRespDto createBoard(CreateBoardReqDto createBoardReqDto) throws Exception;
	public ReadBoardRespDto getBoardByBoardCode(int boardCode, String board_type) throws Exception;
	public List<ReadBoardRespDto> getBoardList(String type, String boardCode, int page, int totalCount) throws Exception;
	public int updateBoardByBoardCode(int boardCode) throws Exception;
	public int deleteBoardByBoardCode(int boardCode) throws Exception;
}