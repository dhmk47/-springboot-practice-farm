package com.springboot.farm.springbootpractice.web.controller.api.user.auth;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.farm.springbootpractice.handler.aop.annotation.ValidCheck;
import com.springboot.farm.springbootpractice.handler.exception.CustomValidationApiException;
import com.springboot.farm.springbootpractice.service.auth.AuthService;
import com.springboot.farm.springbootpractice.service.auth.PrincipalDetails;
import com.springboot.farm.springbootpractice.util.Util;
import com.springboot.farm.springbootpractice.web.dto.CMRespDto;
import com.springboot.farm.springbootpractice.web.dto.auth.CheckUsernameReqDto;
import com.springboot.farm.springbootpractice.web.dto.auth.CreateUserReqDto;
import com.springboot.farm.springbootpractice.web.dto.user.CreateUserRespDto;
import com.springboot.farm.springbootpractice.web.dto.user.ReadUserRespDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth/user")
@RequiredArgsConstructor
public class AuthUserController {
	
	private final AuthService authService;
	
	@ValidCheck
	@PostMapping("/signup")
	public ResponseEntity<?> signupUser(@RequestBody @Valid CreateUserReqDto createUserReqDto, BindingResult bindingResult) {
		
		CreateUserRespDto createUserRespDto = null;
		
		try {
			createUserRespDto = authService.signupUser(createUserReqDto);
			Util.addProductFlag.put(createUserRespDto.getUsercode(), true);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok().body(new CMRespDto<>(-1, "회원가입 실패", createUserRespDto));
		}
		
		return ResponseEntity.ok().body(new CMRespDto<>(1, "회원가입 성공", createUserRespDto));
	}

	@ValidCheck
	@GetMapping("/signup/validation/username")
	public ResponseEntity<?> checkUser(@Valid CheckUsernameReqDto checkUsernameReqDto, BindingResult bindingResult){
		
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
		ReadUserRespDto readUserRespDto = null;
		if(principalDetails != null) {
			System.out.println(principalDetails.getUser());
			readUserRespDto = principalDetails.getUser().toReadUserDto();
		}
		
		return ResponseEntity.ok().body(new CMRespDto<>(1, "유저 불러오기 성공", readUserRespDto));
	}
}