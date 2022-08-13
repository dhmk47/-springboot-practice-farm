package com.springboot.farm.springbootpractice.service;

import java.util.List;

import com.springboot.farm.springbootpractice.web.dto.board.CreateBoardReqDto;
import com.springboot.farm.springbootpractice.web.dto.board.CreateBoardRespDto;
import com.springboot.farm.springbootpractice.web.dto.board.ReadBoardRespDto;
import com.springboot.farm.springbootpractice.web.dto.board.UpdateBoardReqDto;

public interface BoardService {
	public CreateBoardRespDto createBoard(CreateBoardReqDto createBoardReqDto) throws Exception;
	public ReadBoardRespDto getBoardByBoardCode(int boardCode, String board_type) throws Exception;
	public List<ReadBoardRespDto> getBoardList(String type, int page, int totalCount, boolean boardPageFlag) throws Exception;
	public boolean updateBoardByBoardCode(UpdateBoardReqDto updateBoardReqDto) throws Exception;
	public boolean deleteBoardByBoardCode(int boardCode) throws Exception;
}