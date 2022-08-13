package com.springboot.farm.springbootpractice.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.farm.springbootpractice.handler.aop.annotation.Log;

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
	
	@GetMapping("/signin")
	public String goSigninPage() {
		return "signin";
	}
	
	@GetMapping("/product/management")
	public String goAddProductPage() {
		return "managementProduct";
	}
	
	// 게시판
	
	@GetMapping("/board")
	public String goNotice(Model model, @RequestParam String type, @RequestParam int page) {
		model.addAttribute("type", type);
		model.addAttribute("page", page);
		return "/board/board";
	}
	
	@GetMapping("/{boardType}")
	public String goNoticeWrite(@PathVariable String boardType, String type, String number, Model model) {
		System.out.println("들어옴");
		model.addAttribute("boardType", boardType);
		model.addAttribute("type", type);
		model.addAttribute("boardCode", number);
		return "/board/boardWrite";
	}
	
	@GetMapping("/content")
	public String goContent(Model model, @RequestParam String type, @RequestParam int number) {
		model.addAttribute("type", type);
		model.addAttribute("boardCode", number);
		return "/board/content";
	}
	
	@GetMapping("/test")
	public String goTest() {
		return "test";
	}
}