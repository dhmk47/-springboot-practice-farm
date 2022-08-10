package com.springboot.farm.springbootpractice.web.controller.api.user.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.farm.springbootpractice.domain.entity.User;
import com.springboot.farm.springbootpractice.service.auth.PrincipalDetails;
import com.springboot.farm.springbootpractice.web.dto.CMRespDto;

@RestController
@RequestMapping("/api/v1/user/auth")
public class AuthUserContorller {

	@GetMapping("/check")
	public ResponseEntity<?> checkLoginUser(@AuthenticationPrincipal PrincipalDetails principalDetails) {
		User user = null;
		if(principalDetails != null) {
			user = principalDetails.getUser();
		}
		return ResponseEntity.ok().body(new CMRespDto<>(1, "유저 불러오기 성공", user));
	}
}