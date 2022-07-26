package com.springboot.farm.springbootpractice.web.dto.product;

import com.springboot.farm.springbootpractice.domain.entity.Product;

import lombok.Data;

@Data
public class BuyProductDto {
	private int productCode;
	private String productName;
	private int price;
	private String season;
	private int amount;
	private int userCode;
	private int purchasePrice;
	
	public Product toEntity() {
		return Product.builder()
				.product_code(productCode)
				.product_name(productName)
				.price(price)
				.season(season)
				.amount(amount)
				.user_code(userCode)
				.purchase_price(purchasePrice)
				.build();
	}
}