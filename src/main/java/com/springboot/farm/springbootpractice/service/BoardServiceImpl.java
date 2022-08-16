package com.springboot.farm.springbootpractice.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import com.springboot.farm.springbootpractice.domain.board.BoardRepository;
import com.springboot.farm.springbootpractice.domain.entity.Board;
import com.springboot.farm.springbootpractice.web.dto.board.CreateBoardReqDto;
import com.springboot.farm.springbootpractice.web.dto.board.CreateBoardRespDto;
import com.springboot.farm.springbootpractice.web.dto.board.ReadBoardRespDto;
import com.springboot.farm.springbootpractice.web.dto.board.UpdateBoardReqDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
	
	private final BoardRepository boardRepository;

	@Override
	public CreateBoardRespDto createBoard(CreateBoardReqDto createBoardReqDto) throws Exception {
		Board board = createBoardReqDto.toEntity();
		
//		String title = board.getBoard_title();
//		String content = board.getBoard_content();
//		for(int i = 0; i < 50; i++) {
//			board.setBoard_title(title + (i + 1));
//			board.setBoard_content(content + (i + 1));
//			boardRepository.save(board);
//		}
		System.out.println(board);
		boardRepository.save(board);
		
		return board != null ? board.toCreateBoardRespDto() : null;
	}

	@Override
	public ReadBoardRespDto getBoardByBoardCode(int boardCode, String board_type) throws Exception {
		Board board = null;
		int boardType = board_type.equals("notice") ? 1 : board_type.equals("free") ? 2 : 3;
		
		board = boardRepository.getBoardByBoardCode(boardCode, boardType);
		
		ReadBoardRespDto readBoardRespDto = board == null ? null : board.toReadBoardRespDto();

		if(readBoardRespDto != null) {
			readBoardRespDto.setTime(readBoardRespDto.getUpdateDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd___HH:mm:ss")));
		}
		
		return readBoardRespDto;
	}
	
	@Override
//	@Cacheable(value = "boardList", key =  "#type + #page")
	public List<ReadBoardRespDto> getBoardList(String type, int page, int totalCount, boolean boardPageFlag) throws Exception {
		List<ReadBoardRespDto> boardList = null;
		Map<String, Object> map = new HashMap<String, Object>();
		
//		System.out.println(page + "번 page 들어와서 service 접근");
		
		map.put("type", type.equals("notice") ? 1 : type.equals("free") ? 2 : type.equals("QnA") ? 3 : 4);
		map.put("boardPage", boardPageFlag);
		map.put("page", (page - 1) * totalCount);
		map.put("totalCount", totalCount);
		
		boardList = boardRepository.getBoardList(map)
				.stream()
				.map(entity -> entity.toReadBoardRespDto())
				.collect(Collectors.toCollection(ArrayList::new));
		
		boardList.forEach(board -> {
			LocalDateTime now = LocalDateTime.now();
			String localDateTime = board.getUpdateDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
			int yearResult = now.getYear() - board.getUpdateDate().getYear();
			int dayResult = now.getDayOfYear() - board.getUpdateDate().getDayOfYear();
			
			Object result = null;
			
			if(dayResult == 0) {
				result = localDateTime.substring(11);
			}else {
				if(yearResult == 0) {
					result = localDateTime.substring(5);
				}else {
					result = localDateTime;
				}
			}
			
			board.setTime(result);
		});
		
		return boardList;
	}

	@Override
	public boolean updateBoardByBoardCode(UpdateBoardReqDto updateBoardReqDto) throws Exception {
		return boardRepository.updateBoardByBoardCode(updateBoardReqDto.toEntity()) > 0;
	}
	
	@Override
	public boolean updateBoardViewsCount(int boardCode) throws Exception {
		return boardRepository.updateBoardViewCount(boardCode) > 0;
	}

	@Override
	public boolean deleteBoardByBoardCode(int boardCode) throws Exception {
		return boardRepository.delete(boardCode) > 0;
	}

}