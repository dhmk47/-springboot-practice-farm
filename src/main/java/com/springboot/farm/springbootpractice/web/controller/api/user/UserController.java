package com.springboot.farm.springbootpractice.web.controller.api.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.farm.springbootpractice.service.UserService;
import com.springboot.farm.springbootpractice.web.dto.user.CreateUserReqDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
	
	private final UserService userService;
	
	@PostMapping("/signup")
	public ResponseEntity<?> signupUser(@RequestBody CreateUserReqDto createUserReqDto) {
		
		if(userService.signupUser(createUserReqDto)) {
			return ResponseEntity.ok().body(createUserReqDto);
		}else {
			return ResponseEntity.internalServerError().body(false);
		}
		
	}

}