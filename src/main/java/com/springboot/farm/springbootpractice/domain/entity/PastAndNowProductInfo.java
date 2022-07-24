package com.springboot.farm.springbootpractice.domain.entity;

import java.time.LocalDateTime;

import com.springboot.farm.springbootpractice.web.dto.product.ReadPastAndNowProductInfoDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PastAndNowProductInfo {
	private String original_name;
	private String updated_name;
	private int original_price;
	private int updated_price;
	private String original_season;
	private String updated_season;
	private int original_grow_day;
	private int updated_grow_day;
	private LocalDateTime create_date;
	private LocalDateTime update_date;
	
	public ReadPastAndNowProductInfoDto toRespDto() {
		return ReadPastAndNowProductInfoDto.builder()
				.originalName(original_name)
				.updatedName(updated_name)
				.originalPrice(original_price)
				.updatedPrice(updated_price)
				.originalSeason(original_season)
				.updatedSeason(updated_season)
				.originalGrowDay(original_grow_day)
				.updatedGrowDay(updated_grow_day)
				.createDate(create_date)
				.updateDate(update_date)
				.build();
	}
}