package com.springboot.farm.springbootpractice.service;

import com.springboot.farm.springbootpractice.domain.user.User;
import com.springboot.farm.springbootpractice.web.dto.user.CreateUserReqDto;
import com.springboot.farm.springbootpractice.web.dto.user.CreateUserRespDto;
import com.springboot.farm.springbootpractice.web.dto.user.ReadUserRespDto;

public interface UserService {
	public CreateUserRespDto signupUser(CreateUserReqDto createUserReqDto) throws Exception;
	public User getUser(String username) throws Exception;
	public ReadUserRespDto readUserByUsername(String username) throws Exception;
	public boolean modifyUser(CreateUserReqDto createUserReqDto) throws Exception;
	public boolean removeUser(CreateUserReqDto createUserReqDto) throws Exception;
}
