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
import com.springboot.farm.springbootpractice.util.Util;
import com.springboot.farm.springbootpractice.web.dto.CMRespDto;
import com.springboot.farm.springbootpractice.web.dto.board.CreateBoardReqDto;
import com.springboot.farm.springbootpractice.web.dto.board.CreateBoardRespDto;
import com.springboot.farm.springbootpractice.web.dto.board.ReadBoardRespDto;
import com.springboot.farm.springbootpractice.web.dto.board.SaveBoardToMapReqDto;

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
	
	@PostMapping("/save/map/{boardCode}")
	public ResponseEntity<?> saveToMap(@PathVariable int boardCode, @RequestBody SaveBoardToMapReqDto boardToMapReqDto) {
		boolean status = false;
		
		try {
			Util.boardMap.put(boardCode, boardToMapReqDto);
			
			if(Util.boardMap.get(boardCode) != null) {
				status = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok().body(new CMRespDto<>(1, "저장 실패", status));
		}
		
		return ResponseEntity.ok().body(new CMRespDto<>(1, "저장 성공", status));
	}
	
	@GetMapping("/{type}/list")
	public ResponseEntity<?> getAllBoardList(@PathVariable String type, int page, int totalCount, boolean boardPageFlag) {
		List<ReadBoardRespDto> boardList = null;
		
		try {
			boardList = boardService.getBoardList(type, page, totalCount, boardPageFlag);
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
	
	@GetMapping("/load/map/{boardCoad}")
	public ResponseEntity<?> getBoardFromMap(@PathVariable int boardCode) {
		
		SaveBoardToMapReqDto board = Util.boardMap.get(boardCode);
		
		if(board != null) {
			return ResponseEntity.ok().body(new CMRespDto<>(1, "수정할 게시글 불러오기 성공", board));
		}

		return ResponseEntity.internalServerError().body(new CMRespDto<>(-1, "수정할 게시글 불러오기 실패", board));
	}
}