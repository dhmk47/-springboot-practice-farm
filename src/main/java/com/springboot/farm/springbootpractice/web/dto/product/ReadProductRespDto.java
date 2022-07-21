package com.springboot.farm.springbootpractice.web.dto.product;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReadProductRespDto {
	private int product_code;
	private String product_name;
	private int price;
	private String season;
	private int grow_day;
	private int amount;
	private int user_code;
	private int purchase_price;
	private LocalDateTime create_date;
	private LocalDateTime update_date;
}