package com.springboot.farm.springbootpractice.web.dto.product;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ReadDeletedProductRespDto {
	private String productName;
	private int amount;
	private int purchasePrice;
	private int totalAmount;
	private int totalPrice;

}