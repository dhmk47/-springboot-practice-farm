package com.springboot.farm.springbootpractice.web.dto.product;

import java.util.ArrayList;
import java.util.List;

import com.springboot.farm.springbootpractice.domain.entity.Product;

import lombok.Data;

@Data
public class CreateProductListReqDto {
	private List<CreateProductReqDto> objList;
	
	public List<Product> toEntity() {
		List<Product> productEntity = new ArrayList<Product>();
		
		objList.forEach(i -> {
			productEntity.add(i.toEntity());
		});
		
		return productEntity;
	}
}