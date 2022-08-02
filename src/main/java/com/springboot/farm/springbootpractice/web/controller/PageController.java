package com.springboot.farm.springbootpractice.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PageController {

	@GetMapping({"/", "/index"})
	public String getMainPage() {
		return "index";
	}
	
	@GetMapping("/signup")
	public String goSignupPage() {
		return "signup";
	}
	
	@GetMapping("/product/management")
	public String goAddProductPage() {
		return "managementProduct";
	}
	
	// 공지사항
	
	@GetMapping("/notice")
	public String goNotice() {
		return "/board/notice";
	}
	
	@GetMapping("/notice/write")
	public String goNoticeWrite() {
		return "/board/noticeWrite";
	}

	// 자유게시판
	
	@GetMapping("/board")
	public String goBoard() {
		return "/board/freeBoard";
	}
	
	@GetMapping("/board/write")
	public String goBoardWrite() {
		return "/board/boardWrite";
	}

	// QnA
	
	@GetMapping("/QnA")
	public String goQnA() {
		return "/board/QnA";
	}
	
	@GetMapping("/QnA/write")
	public String goQnAWrite() {
		return "/board/QnAWrite";
	}
	
	
	
	@GetMapping("/test")
	public String goTest() {
		return "test";
	}
}