package com.springboot.farm.springbootpractice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.springboot.farm.springbootpractice.domain.entity.PastAndNowProductInfo;
import com.springboot.farm.springbootpractice.domain.entity.Product;
import com.springboot.farm.springbootpractice.domain.product.ProductRepository;
import com.springboot.farm.springbootpractice.web.dto.product.CreateProductReqDto;
import com.springboot.farm.springbootpractice.web.dto.product.ReadPastAndNowProductInfoDto;
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
	public List<ReadProductRespDto> getRecentlyProductList(String date) throws Exception {
		List<ReadProductRespDto> productList = null;
		
		productList = productRepository.readRecentlyProductLIst(date)
				.stream()
				.map(entity -> entity.toReadRespDto())
				.collect(Collectors.toCollection(ArrayList::new));
		
		return productList;
	}
	
	@Override
	public List<ReadPastAndNowProductInfoDto> getRecentlyModifiedProductList(String date) throws Exception {
		List<ReadPastAndNowProductInfoDto> productList = null;
		
		productList = productRepository.readModifiedProductList(date)
				.stream()
				.map(entity -> entity.toRespDto())
				.collect(Collectors.toCollection(ArrayList::new));
		
		return productList;
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