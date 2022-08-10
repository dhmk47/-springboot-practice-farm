package com.springboot.farm.springbootpractice.web.controller.api.user.auth;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.farm.springbootpractice.domain.entity.User;
import com.springboot.farm.springbootpractice.service.UserService;
import com.springboot.farm.springbootpractice.service.auth.AuthService;
import com.springboot.farm.springbootpractice.service.auth.PrincipalDetails;
import com.springboot.farm.springbootpractice.web.dto.CMRespDto;
import com.springboot.farm.springbootpractice.web.dto.auth.CheckUsernameReqDto;
import com.springboot.farm.springbootpractice.web.dto.auth.CreateUserReqDto;
import com.springboot.farm.springbootpractice.web.dto.user.CreateUserRespDto;
import com.springboot.farm.springbootpractice.web.dto.user.ReadUserRespDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth/user")
@RequiredArgsConstructor
public class AuthUserContorller {
	
	private final AuthService authService;
	
	@PostMapping("/signup")
	public ResponseEntity<?> signupUser(@RequestBody @Valid CreateUserReqDto createUserReqDto, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			Map<String, String> errorMap = new HashMap<String, String>();
			
			bindingResult.getFieldErrors().forEach(error -> {
				errorMap.put(error.getField(), error.getDefaultMessage());
			});
			
			return ResponseEntity.badRequest().body(new CMRespDto<>(-1, "유효성 검사 실패", errorMap));
		}
		
		
		CreateUserRespDto createUserRespDto = null;
		
		try {
			createUserRespDto = authService.signupUser(createUserReqDto);
			/*Util.addProductFlag.put(createUserRespDto.getUsercode(), true);*/
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok().body(new CMRespDto<>(-1, "회원가입 실패", createUserRespDto));
		}
		
		return ResponseEntity.ok().body(new CMRespDto<>(1, "회원가입 성공", createUserRespDto));
	}
	
	@GetMapping("/signup/validation/username")
	public ResponseEntity<?> checkUser(@Valid CheckUsernameReqDto checkUsernameReqDto, BindingResult bindingResult){
		
		if(bindingResult.hasErrors()) {
			Map<String, String> errorMap = new HashMap<String, String>();
			
			bindingResult.getFieldErrors().forEach(error -> {
				errorMap.put(error.getField(), error.getDefaultMessage());
			});
			
			return ResponseEntity.badRequest().body(new CMRespDto<>(-1, "유효성 검사 실패", errorMap));
		}
		
		boolean status = false;
		
		try {
			status = authService.readUserByUsername(checkUsernameReqDto);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new CMRespDto<>(-1, "유저불러오기 실패", status));
		}
		
		return ResponseEntity.ok().body(new CMRespDto<>(1, "유저불러오기 성공", status));
	}

	@GetMapping("/principal/load")
	public ResponseEntity<?> checkLoginUser(@AuthenticationPrincipal PrincipalDetails principalDetails) {
		User user = null;
		if(principalDetails != null) {
			user = principalDetails.getUser();
		}
		return ResponseEntity.ok().body(new CMRespDto<>(1, "유저 불러오기 성공", user));
	}
}