package com.springboot.farm.springbootpractice.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.farm.springbootpractice.domain.entity.User;
import com.springboot.farm.springbootpractice.domain.user.UserRepository;
import com.springboot.farm.springbootpractice.web.dto.user.CreateUserReqDto;
import com.springboot.farm.springbootpractice.web.dto.user.CreateUserRespDto;
import com.springboot.farm.springbootpractice.web.dto.user.ReadUserRespDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
	
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public CreateUserRespDto signupUser(CreateUserReqDto createUserReqDto) throws Exception{
		createUserReqDto.setPassword(bCryptPasswordEncoder.encode(createUserReqDto.getPassword()));
		User user = createUserReqDto.toEntity();
		int result = userRepository.insertUser(user);
		return user.toCreateUserDto();
	}
	
	@Override
	public User getUser(String username) throws Exception{
		return null;
	}

	@Override
	public ReadUserRespDto readUserByUsername(String username) throws Exception{
		User user = userRepository.getUserByUsername(username);
		return user == null ? null : user.toReadUserDto();
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