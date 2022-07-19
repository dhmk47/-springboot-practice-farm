package com.springboot.farm.springbootpractice.web.controller.api.user;

import javax.websocket.server.PathParam;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.farm.springbootpractice.service.UserService;
import com.springboot.farm.springbootpractice.web.dto.CMRespDto;
import com.springboot.farm.springbootpractice.web.dto.user.CreateUserReqDto;
import com.springboot.farm.springbootpractice.web.dto.user.CreateUserRespDto;
import com.springboot.farm.springbootpractice.web.dto.user.ReadUserRespDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
	
	private final UserService userService;
	
	@PostMapping("/signup")
	public ResponseEntity<?> signupUser(CreateUserReqDto createUserReqDto) {
		CreateUserRespDto createUserRespDto = null;
		
		try {
			createUserRespDto = userService.signupUser(createUserReqDto);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok().body(new CMRespDto<>(-1, "회원가입 실패", createUserRespDto));
		}
		
		return ResponseEntity.ok().body(new CMRespDto<>(1, "회원가입 성공", createUserRespDto));
	}
	
	@GetMapping("/check/{username}")
	public ResponseEntity<?> checkUser(@PathVariable String username){
		ReadUserRespDto readUserRespDto = null;
		
		try {
			readUserRespDto = userService.readUserByUsername(username);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok().body(new CMRespDto<>(-1, "유저불러오기 실패", readUserRespDto));
		}
		
		return ResponseEntity.ok().body(new CMRespDto<>(1, "유저불러오기 성공", readUserRespDto));
	}

}