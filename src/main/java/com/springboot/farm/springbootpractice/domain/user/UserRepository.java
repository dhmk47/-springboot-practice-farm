package com.springboot.farm.springbootpractice.domain.user;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRepository {
	public int insertUser(User user);
	public User getUserByUsername(String username);
}