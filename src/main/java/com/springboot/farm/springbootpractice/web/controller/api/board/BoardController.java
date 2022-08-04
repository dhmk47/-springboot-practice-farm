package com.springboot.farm.springbootpractice.web.controller.api.board;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.farm.springbootpractice.service.BoardService;
import com.springboot.farm.springbootpractice.web.dto.CMRespDto;
import com.springboot.farm.springbootpractice.web.dto.board.CreateBoardReqDto;
import com.springboot.farm.springbootpractice.web.dto.board.CreateBoardRespDto;
import com.springboot.farm.springbootpractice.web.dto.board.ReadBoardRespDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/board")
@RequiredArgsConstructor
public class BoardController {
	
	private final BoardService boardService;
	
	@PostMapping("/new")
	public ResponseEntity<?> insertBoard(@RequestBody CreateBoardReqDto createBoardReqDto) {
		CreateBoardRespDto createBoardRespDto = null;
		
		try {
			createBoardRespDto = boardService.createBoard(createBoardReqDto);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new CMRespDto<>(-1, "게시글 작성 실패", createBoardRespDto));
		}
		
		return ResponseEntity.ok().body(new CMRespDto<>(1, "게시글 작성 성공", createBoardRespDto));
	}
	
	@GetMapping("/{type}/list/{boardCode}")
	public ResponseEntity<?> getAllBoardList(@PathVariable String type, @PathVariable String boardCode, int page, int totalCount) {
		List<ReadBoardRespDto> boardList = null;
		
		try {
			boardList = boardService.getBoardList(type, boardCode, page, totalCount);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new CMRespDto<>(-1, "게시글 불러오기 실패", boardList));
		}
		
		return ResponseEntity.ok().body(new CMRespDto<>(1, "게시글 불러오기 성공", boardList));
	}
	
	@GetMapping("/{boardType}/{boardCode}")
	public ResponseEntity<?> getBoardByBoardCode(@PathVariable String boardType, @PathVariable int boardCode) {
		ReadBoardRespDto readBoardRespDto = null;
		
		try {
			readBoardRespDto = boardService.getBoardByBoardCode(boardCode, boardType);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new CMRespDto<>(-1, boardType + " " + boardCode + "번 게시글 불러오기 실패", readBoardRespDto));
		}
		
		return ResponseEntity.ok().body(new CMRespDto<>(1, boardType + " " + boardCode + "번 게시글 불러오기 성공", readBoardRespDto));
	}
}