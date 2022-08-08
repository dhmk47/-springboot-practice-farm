package com.springboot.farm.springbootpractice.web.controller.api.reply;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.farm.springbootpractice.service.ReplyService;
import com.springboot.farm.springbootpractice.web.dto.CMRespDto;
import com.springboot.farm.springbootpractice.web.dto.reply.CreateReplyReqDto;
import com.springboot.farm.springbootpractice.web.dto.reply.ReadReplyRespDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/content")
public class ReplyController {

	private final ReplyService replyService;
	
	@PostMapping("/reply")
	public ResponseEntity<?> insertReply(@RequestBody CreateReplyReqDto createReplyReqDto) {
		boolean result = false;
		
		try {
			result = replyService.addReply(createReplyReqDto);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new CMRespDto<>(-1, "댓글 작성 실패", result));
		}
		
		return ResponseEntity.ok().body(new CMRespDto<>(1, "댓글 작성 성공", result));
	}
	
	@GetMapping("/reply/{boardCode}")
	public ResponseEntity<?> getContentReply(@PathVariable int boardCode, int page, int index) {
		List<ReadReplyRespDto> replyList = null;
		
		try {
			replyList = replyService.getContentReplyListByBoardCode(boardCode, page, index);
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new CMRespDto<>(-1, "댓글 리스트 불러오기 실패", replyList));
		}
		
		return ResponseEntity.ok().body(new CMRespDto<>(1, "댓글 리스트 불러오기 성공", replyList));
	}
}
