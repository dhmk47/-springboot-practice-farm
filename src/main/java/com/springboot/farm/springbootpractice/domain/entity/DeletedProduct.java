package com.springboot.farm.springbootpractice.domain.entity;

import com.springboot.farm.springbootpractice.web.dto.product.ReadDeletedProductRespDto;

import lombok.Data;

@Data
public class DeletedProduct {
	private String product_name;
	private int amount;
	private int purchase_price;
	private int total_amount;
	private int total_price;
	
	public ReadDeletedProductRespDto toDto() {
		return ReadDeletedProductRespDto.builder()
				.productName(product_name)
				.amount(amount)
				.purchasePrice(purchase_price)
				.totalAmount(total_amount)
				.totalPrice(total_price)
				.build();
	}
}