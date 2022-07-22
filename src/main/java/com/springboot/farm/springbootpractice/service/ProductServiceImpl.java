package com.springboot.farm.springbootpractice.service;

import org.springframework.stereotype.Service;

import com.springboot.farm.springbootpractice.domain.entity.Product;
import com.springboot.farm.springbootpractice.domain.product.ProductRepository;
import com.springboot.farm.springbootpractice.web.dto.product.CreateProductReqDto;
import com.springboot.farm.springbootpractice.web.dto.product.ReadProductRespDto;
import com.springboot.farm.springbootpractice.web.dto.product.UpdateProductReqDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

	private final ProductRepository productRepository;
	
	@Override
	public boolean insertProduct(CreateProductReqDto createProductReqDto) throws Exception {
		return productRepository.save(createProductReqDto.toEntity()) > 0;
	}
	@Override
	public ReadProductRespDto getProductByProductName(String productName) throws Exception {
		Product readProductRespDto = null;
		readProductRespDto = productRepository.readProductByProductName(productName);
		return readProductRespDto == null ? null : readProductRespDto.toReadRespDto();
	}
	@Override
	public boolean modifyProductInfo(UpdateProductReqDto updateProductReqDto) throws Exception {
		return productRepository.update(updateProductReqDto.toEntity()) > 0;
	}

	@Override
	public boolean removeProduct(int productCode) throws Exception {
		return productRepository.delete(productCode) > 0;
	}
}