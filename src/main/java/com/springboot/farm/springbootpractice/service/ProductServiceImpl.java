package com.springboot.farm.springbootpractice.service;

import org.springframework.stereotype.Service;

import com.springboot.farm.springbootpractice.domain.entity.Product;
import com.springboot.farm.springbootpractice.domain.product.ProductRepository;
import com.springboot.farm.springbootpractice.web.dto.product.ReadProductRespDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

	private final ProductRepository productRepository;
	@Override
	public ReadProductRespDto getProductByProductName(String productName) throws Exception {
		Product readProductRespDto = null;
		readProductRespDto = productRepository.readProductByProductName(productName);
		return readProductRespDto == null ? null : readProductRespDto.toReadRespDto();
	}

}