package com.springboot.farm.springbootpractice.web.dto.product;

import com.springboot.farm.springbootpractice.domain.entity.Product;

import lombok.Data;

@Data
public class CreateProductReqDto {
	private int productCode;
	private String productName;
	private int price;
	private String season;
	private int growDay;
	
	public Product toEntity() {
		return Product.builder()
				.product_code(productCode)
				.product_name(productName)
				.price(price)
				.season(season)
				.grow_day(growDay)
				.build();
	}
}