package com.springboot.farm.springbootpractice.service;

import com.springboot.farm.springbootpractice.web.dto.product.CreateProductReqDto;
import com.springboot.farm.springbootpractice.web.dto.product.ReadProductRespDto;
import com.springboot.farm.springbootpractice.web.dto.product.UpdateProductReqDto;

public interface ProductService {
	public boolean insertProduct(CreateProductReqDto createProductReqDto) throws Exception;
	public ReadProductRespDto getProductByProductName(String productName) throws Exception;
	public boolean modifyProductInfo(UpdateProductReqDto updateProductReqDto) throws Exception;
}