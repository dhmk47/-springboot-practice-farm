package com.springboot.farm.springbootpractice.domain.reply;

import org.apache.ibatis.annotations.Mapper;

import com.springboot.farm.springbootpractice.domain.entity.Reply;

@Mapper
public interface ReplyRepository {
	public int save(Reply reply);
}