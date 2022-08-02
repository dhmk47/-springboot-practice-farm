package com.springboot.farm.springbootpractice.service;

import org.springframework.stereotype.Service;

import com.springboot.farm.springbootpractice.domain.board.BoardRepository;
import com.springboot.farm.springbootpractice.domain.entity.Board;
import com.springboot.farm.springbootpractice.web.dto.board.CreateBoardReqDto;
import com.springboot.farm.springbootpractice.web.dto.board.CreateBoardRespDto;
import com.springboot.farm.springbootpractice.web.dto.board.ReadBoardRespDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
	
	private final BoardRepository boardRepository;

	@Override
	public CreateBoardRespDto createBoard(CreateBoardReqDto createBoardReqDto) throws Exception {
		Board board = createBoardReqDto.toEntity();
		
		boardRepository.save(board);
		
		return board != null ? board.toCreateBoardRespDto() : null;
	}

	@Override
	public ReadBoardRespDto getBoardByBoardCode(int boardCode) throws Exception {
		return null;
	}

	@Override
	public int updateBoardByBoardCode(int boardCode) throws Exception {
		return 0;
	}

	@Override
	public int deleteBoardByBoardCode(int boardCode) throws Exception {
		return 0;
	}

}