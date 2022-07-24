package com.springboot.farm.springbootpractice.web.dto.product;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ReadPastAndNowProductInfoDto {
	private String originalName;
	private String updatedName;
	private int originalPrice;
	private int updatedPrice;
	private String originalSeason;
	private String updatedSeason;
	private int originalGrowDay;
	private int updatedGrowDay;
	private LocalDateTime createDate;
	private LocalDateTime updateDate;
}
