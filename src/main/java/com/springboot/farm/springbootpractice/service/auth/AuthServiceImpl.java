package com.springboot.farm.springbootpractice.service.auth;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.farm.springbootpractice.domain.entity.User;
import com.springboot.farm.springbootpractice.domain.user.UserRepository;
import com.springboot.farm.springbootpractice.web.dto.auth.CheckUsernameReqDto;
import com.springboot.farm.springbootpractice.web.dto.auth.CreateUserReqDto;
import com.springboot.farm.springbootpractice.web.dto.user.CreateUserRespDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
	
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public CreateUserRespDto signupUser(CreateUserReqDto createUserReqDto) throws Exception {
		createUserReqDto.setPassword(bCryptPasswordEncoder.encode(createUserReqDto.getPassword()));
		User user = createUserReqDto.toEntity();
		int result = userRepository.insertUser(user);
		return user.toCreateUserDto();
	}

	@Override
	public boolean readUserByUsername(CheckUsernameReqDto checkUsernameReqDto) throws Exception{
		return userRepository.getUserByUsername(checkUsernameReqDto.getUsername()) == null;
	}
}