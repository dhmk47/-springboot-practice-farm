package com.springboot.farm.springbootpractice.service.auth;

import com.springboot.farm.springbootpractice.web.dto.auth.CheckUsernameReqDto;
import com.springboot.farm.springbootpractice.web.dto.auth.CreateUserReqDto;
import com.springboot.farm.springbootpractice.web.dto.user.CreateUserRespDto;

public interface AuthService {
	public CreateUserRespDto signupUser(CreateUserReqDto createUserReqDto) throws Exception;
	public boolean readUserByUsername(CheckUsernameReqDto checkUsernameReqDto) throws Exception;
}