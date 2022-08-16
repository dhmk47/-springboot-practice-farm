package com.springboot.farm.springbootpractice.domain.user;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.springboot.farm.springbootpractice.domain.entity.User;

@Mapper
public interface UserRepository {
	public int insertUser(User user);
	public User getUserByUsername(String username);
	public List<User> getAllUser();
	public int updateUserMoney(Map<String, Object> map);
}