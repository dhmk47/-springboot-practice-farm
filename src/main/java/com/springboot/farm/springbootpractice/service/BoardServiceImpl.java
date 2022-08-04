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
		
		String title = board.getBoard_title();
		String content = board.getBoard_content();
		for(int i = 0; i < 50; i++) {
			board.setBoard_title(title + (i + 1));
			board.setBoard_content(content + (i + 1));
			boardRepository.save(board);
		}
		
		boardRepository.save(board);
		
		return board != null ? board.toCreateBoardRespDto() : null;
	}

	@Override
	public ReadBoardRespDto getBoardByBoardCode(int boardCode, String board_type) throws Exception {
		Board board = null;
		int boardType = board_type.equals("notice") ? 1 : board_type.equals("freeBoard") ? 2 : 3;
		
		board = boardRepository.getBoardByBoardCode(boardCode, boardType);
		
		return board == null ? null : board.toReadBoardRespDto();
	}
	
	@Override
	public List<ReadBoardRespDto> getBoardList(String type, String boardCode, int page, int totalCount) throws Exception {
		List<ReadBoardRespDto> boardList = null;
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("type", type.equals("notice") ? 1 : type.equals("freeBoard") ? 2 : 3);
		map.put("boardCode", boardCode);
		map.put("page", (page - 1) * totalCount);
		map.put("totalCount", totalCount);
		
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