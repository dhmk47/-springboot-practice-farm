package com.springboot.farm.springbootpractice.service;

import com.springboot.farm.springbootpractice.web.dto.reply.CreateReplyReqDto;

public interface ReplyService {
	public boolean addReply(CreateReplyReqDto createReplyReqDto) throws Exception;
}