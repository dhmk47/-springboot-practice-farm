package com.springboot.farm.springbootpractice.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.springboot.farm.springbootpractice.domain.entity.User;
import com.springboot.farm.springbootpractice.domain.user.UserRepository;
import com.springboot.farm.springbootpractice.web.dto.auth.CheckUsernameReqDto;
import com.springboot.farm.springbootpractice.web.dto.auth.CreateUserReqDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
	
	private final UserRepository userRepository;

	@Override
	public User getUser(CheckUsernameReqDto checkUsernameReqDto) throws Exception {
		return null;
	}
	
	@Override
	public List<User> getAllUserToList() throws Exception {
		return userRepository.getAllUser();
	}

	@Override
	public boolean modifyUser(CreateUserReqDto createUserReqDto) throws Exception{
		return false;
	}
	
	@Override
	public boolean updateUserMoney(int money, int userCode, String type) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("money", money);
		map.put("userCode", userCode);
		map.put("type", type);
		return userRepository.updateUserMoney(map) > 0;
	}

	@Override
	public boolean removeUser(CreateUserReqDto createUserReqDto) throws Exception{
		return false;
	}


	
}