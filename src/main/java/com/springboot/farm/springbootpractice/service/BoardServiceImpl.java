package com.springboot.farm.springbootpractice.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
	public List<ReadBoardRespDto> getBoardList(String type, String boardCode) throws Exception {
		List<ReadBoardRespDto> boardList = null;
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("type", type);
		map.put("boardCode", boardCode);
		
		boardList = boardRepository.getBoardList(map)
				.stream()
				.map(entity -> entity.toReadBoardRespDto())
				.collect(Collectors.toCollection(ArrayList::new));
		
		return boardList;
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