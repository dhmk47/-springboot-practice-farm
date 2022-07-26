package com.springboot.farm.springbootpractice.domain.user;

import org.apache.ibatis.annotations.Mapper;

import com.springboot.farm.springbootpractice.domain.entity.User;

@Mapper
public interface UserRepository {
	public int insertUser(User user);
	public User getUserByUsername(String username);
	public int updateUserMoney(int money, int userCode);
}