package com.springboot.farm.springbootpractice.service;

import org.springframework.stereotype.Service;

import com.springboot.farm.springbootpractice.domain.user.UserRepository;
import com.springboot.farm.springbootpractice.web.dto.user.CreateUserReqDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
	
	private final UserRepository userRepository;

	@Override
	public boolean signupUser(CreateUserReqDto createUserReqDto) {
		return userRepository.insertUser(createUserReqDto.toEntity()) > 0;
	}

	@Override
	public boolean modifyUser(CreateUserReqDto createUserReqDto) {
		return false;
	}

	@Override
	public boolean removeUser(CreateUserReqDto createUserReqDto) {
		return false;
	}

}
