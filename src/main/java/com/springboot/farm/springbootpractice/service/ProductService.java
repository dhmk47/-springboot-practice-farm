package com.springboot.farm.springbootpractice.service;

import com.springboot.farm.springbootpractice.web.dto.product.CreateProductReqDto;
import com.springboot.farm.springbootpractice.web.dto.product.ReadProductRespDto;

public interface ProductService {
	public int insertProduct(CreateProductReqDto createProductReqDto) throws Exception;
	public ReadProductRespDto getProductByProductName(String productName) throws Exception;
}