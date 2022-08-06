package com.springboot.farm.springbootpractice.service;

import org.springframework.stereotype.Service;

import com.springboot.farm.springbootpractice.domain.reply.ReplyRepository;
import com.springboot.farm.springbootpractice.web.dto.reply.CreateReplyReqDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService{
	
	private final ReplyRepository replyRepository;

	@Override
	public boolean addReply(CreateReplyReqDto createReplyReqDto) throws Exception {
		return replyRepository.save(createReplyReqDto.toEntity()) > 0;
	}

}