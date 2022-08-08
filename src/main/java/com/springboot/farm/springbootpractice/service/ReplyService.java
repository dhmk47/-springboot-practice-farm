package com.springboot.farm.springbootpractice.service;

import java.util.List;

import com.springboot.farm.springbootpractice.web.dto.reply.CreateReplyReqDto;
import com.springboot.farm.springbootpractice.web.dto.reply.ReadReplyRespDto;

public interface ReplyService {
	public boolean addReply(CreateReplyReqDto createReplyReqDto) throws Exception;
	public List<ReadReplyRespDto> getContentReplyListByBoardCode(int boardCode, int page, int index) throws Exception;
}