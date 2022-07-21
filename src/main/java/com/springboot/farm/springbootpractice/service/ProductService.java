package com.springboot.farm.springbootpractice.service;

import com.springboot.farm.springbootpractice.web.dto.product.ReadProductRespDto;

public interface ProductService {
	public ReadProductRespDto getProductByProductName(String productName) throws Exception;
}