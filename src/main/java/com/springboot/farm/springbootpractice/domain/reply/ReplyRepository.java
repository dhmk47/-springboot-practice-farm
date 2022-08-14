package com.springboot.farm.springbootpractice.domain.reply;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.springboot.farm.springbootpractice.domain.entity.Reply;

@Mapper
public interface ReplyRepository {
	public int save(Reply reply);
	public List<Reply> getReplyListByBoardCode(Map<String, Object> map);
	public int updateReplyByReplyCode(Map<String, Object> map);
	public int delete(int reply_code);
}