package com.springboot.farm.springbootpractice.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.springboot.farm.springbootpractice.domain.reply.ReplyRepository;
import com.springboot.farm.springbootpractice.web.dto.reply.CreateReplyReqDto;
import com.springboot.farm.springbootpractice.web.dto.reply.ReadReplyRespDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReplyServiceImpl implements ReplyService{
	
	private final ReplyRepository replyRepository;

	@Override
	public boolean addReply(CreateReplyReqDto createReplyReqDto) throws Exception {
		return replyRepository.save(createReplyReqDto.toEntity()) > 0;
	}

	@Override
	public List<ReadReplyRespDto> getContentReplyListByBoardCode(int boardCode, int page, int index) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		List<ReadReplyRespDto> list = null;
		
		map.put("board_code", boardCode);
		map.put("page", (page -1) * index);
		map.put("index", index);
		
		list = replyRepository.getReplyListByBoardCode(map)
				.stream()
				.map(entity -> entity.toDto())
				.collect(Collectors.toCollection(ArrayList::new));
		
		
		list.forEach(reply -> {
			LocalDateTime now = LocalDateTime.now();
			Object result = null;

			long year = ChronoUnit.YEARS.between(reply.getUpdateDate(), now);
			long month = ChronoUnit.MONTHS.between(reply.getUpdateDate(), now);
			long weeks = ChronoUnit.WEEKS.between(reply.getUpdateDate(), now);
			long days = ChronoUnit.DAYS.between(reply.getUpdateDate(), now);
			long hours = ChronoUnit.HOURS.between(reply.getUpdateDate(), now);
			long minutes = ChronoUnit.MINUTES.between(reply.getUpdateDate(), now);
			long seconds = ChronoUnit.SECONDS.between(reply.getUpdateDate(), now);
			
			if(year > 0) {
				result = year + "년전";
			}else if(month > 0) {
				result = month + "개월전";
			}else if(weeks > 0) {
				result = weeks + "주전";
			}else if(days > 0) {
				result = days + "일전";
			}else if(hours > 0) {
				result = hours + "시간전";
			}else if(minutes > 0) {
				result = minutes + "분전";
			}else {
				result = seconds + "초전";
			}
			
			reply.setTime(result);
//			String localDateTime = reply.getUpdateDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//			int yearResult = now.getYear() - reply.getUpdateDate().getYear();
//			int dayResult = now.getDayOfYear() - reply.getUpdateDate().getDayOfYear();
//			int hourResult = now.getHour() - reply.getUpdateDate().getHour();
//			int minuteResult = now.getMinute() - reply.getUpdateDate().getMinute();
//			int secondResult = now.getSecond() - reply.getUpdateDate().getSecond() + 3;
//			
//			
//			if((yearResult != 0 || dayResult > 6) && dayResult > -358 && (dayResult != 7 || hourResult > -1)) {
////				System.out.println(localDateTime);
//				
//				result = localDateTime;
//			}else if(dayResult != 0 && dayResult < 8 && (dayResult != 1 || hourResult > -1)) {
//				if(dayResult < 0) {
//					
//					int lastYear = now.getYear() - 1;
//					if(lastYear % 4 == 0 && lastYear % 100 != 0 || lastYear % 400 == 0) {	// 윤년이라면 366일 이라서 366 +
//						dayResult += 366;
//					}else {																	// 아니라면 365 +
//						dayResult += 365;
//					}
//					
//				}
////				System.out.println(dayResult + "일 전");
//				result = dayResult + "일 전";
//			}else if(hourResult != 0 && (hourResult != 1 || minuteResult > -1)) {
//				if(hourResult < 0) {
//					hourResult += 24;
//				}
////				System.out.println(hourResult + "시간 전");
//				result = hourResult + "시간 전";
//			}else if(minuteResult != 0 && (minuteResult != 1 || secondResult > -1)) {
//				if(minuteResult < 0) {
//					minuteResult += 60;
//				}
////				System.out.println(minuteResult + "분 전");
//				result = minuteResult + "분 전";
//			}else {
//				if(secondResult < 0) {
//					secondResult += 60;
//				}
////				System.out.println(secondResult + "초 전");
//				result = secondResult + "초 전";
//			}
//			
//			reply.setTime(result);
		});
		
		return list;
	}
	
	@Override
	public boolean updateReply(int replyCode, String reply) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("reply_code", replyCode);
		map.put("reply", reply);
		
		return replyRepository.updateReplyByReplyCode(map) > 0;
	}
	
	@Override
	public boolean deleteReply(int replyCode) throws Exception {
		return replyRepository.delete(replyCode) > 0;
	}
}