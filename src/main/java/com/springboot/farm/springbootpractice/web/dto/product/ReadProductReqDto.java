package com.springboot.farm.springbootpractice.web.dto.product;

import com.springboot.farm.springbootpractice.domain.entity.Product;

import lombok.Data;

@Data
public class ReadProductReqDto {
	private int productCode;
	private String productName;
	private int userCode;
	
	public Product toEntity() {
		return Product.builder()
				.product_code(productCode)
				.product_name(productName)
				.user_code(userCode)
				.build();
	}

}