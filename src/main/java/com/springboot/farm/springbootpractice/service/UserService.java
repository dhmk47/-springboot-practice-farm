package com.springboot.farm.springbootpractice.service;

import java.util.List;

import com.springboot.farm.springbootpractice.domain.entity.User;
import com.springboot.farm.springbootpractice.web.dto.auth.CheckUsernameReqDto;
import com.springboot.farm.springbootpractice.web.dto.auth.CreateUserReqDto;

public interface UserService {
	public User getUser(CheckUsernameReqDto checkUsernameReqDto) throws Exception;
	public List<User> getAllUserToList() throws Exception;
	public boolean modifyUser(CreateUserReqDto createUserReqDto) throws Exception;
	public boolean updateUserMoney(int money, int userCode, String type) throws Exception;
	public boolean removeUser(CreateUserReqDto createUserReqDto) throws Exception;
}
