package com.springboot.farm.springbootpractice.domain.reply;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.springboot.farm.springbootpractice.domain.entity.Reply;

@Mapper
public interface ReplyRepository {
	public int save(Reply reply);
	public List<Reply> getReplyListByBoardCode(int board_code);
}