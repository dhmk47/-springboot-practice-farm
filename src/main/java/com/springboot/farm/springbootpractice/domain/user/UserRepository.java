package com.springboot.farm.springbootpractice.domain.user;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.springboot.farm.springbootpractice.domain.entity.User;

@Mapper
public interface UserRepository {
	public int insertUser(User user) throws Exception;
	public User getUserByUsername(String username) throws Exception;
	public User getUserByOAuth2Id(String oAuth2_id) throws Exception;
	public int updateUserPhone(User user) throws Exception;
	public List<User> getAllUser() throws Exception;
	public int updateUserMoney(Map<String, Object> map) throws Exception;
}