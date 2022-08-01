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
	
	@GetMapping("/notice")
	public String goNotice() {
		return "/board/notice";
	}
	
	@GetMapping("/test")
	public String goTest() {
		return "test";
	}
}