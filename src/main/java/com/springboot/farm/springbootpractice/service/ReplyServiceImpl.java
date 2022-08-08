package com.springboot.farm.springbootpractice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.springboot.farm.springbootpractice.domain.reply.ReplyRepository;
import com.springboot.farm.springbootpractice.web.dto.reply.CreateReplyReqDto;
import com.springboot.farm.springbootpractice.web.dto.reply.ReadReplyRespDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService{
	
	private final ReplyRepository replyRepository;

	@Override
	public boolean addReply(CreateReplyReqDto createReplyReqDto) throws Exception {
		return replyRepository.save(createReplyReqDto.toEntity()) > 0;
	}

	@Override
	public List<ReadReplyRespDto> getContentReplyListByBoardCode(int boardCode) throws Exception {
		List<ReadReplyRespDto> list = null;
		
		list = replyRepository.getReplyListByBoardCode(boardCode)
				.stream()
				.map(entity -> entity.toDto())
				.collect(Collectors.toCollection(ArrayList::new));
		
		return list;
	}
}