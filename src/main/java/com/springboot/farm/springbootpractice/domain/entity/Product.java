package com.springboot.farm.springbootpractice.domain.entity;

import java.time.LocalDateTime;

import com.springboot.farm.springbootpractice.web.dto.product.ReadProductRespDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {
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
	
	public ReadProductRespDto toReadRespDto() {
		return ReadProductRespDto.builder()
				.product_code(product_code)
				.product_name(product_name)
				.price(price)
				.season(season)
				.grow_day(grow_day)
				.amount(amount)
				.user_code(user_code)
				.purchase_price(purchase_price)
				.create_date(create_date)
				.update_date(update_date)
				.build();
	}
}