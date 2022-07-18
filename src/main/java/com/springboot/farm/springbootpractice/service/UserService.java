package com.springboot.farm.springbootpractice.service;

import com.springboot.farm.springbootpractice.web.dto.user.CreateUserReqDto;

public interface UserService {
	public boolean signupUser(CreateUserReqDto createUserReqDto);
//	public User getUser(String username);
	public boolean modifyUser(CreateUserReqDto createUserReqDto);
	public boolean removeUser(CreateUserReqDto createUserReqDto);
}
