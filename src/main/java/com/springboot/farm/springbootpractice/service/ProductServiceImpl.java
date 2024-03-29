package com.springboot.farm.springbootpractice.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.springboot.farm.springbootpractice.domain.entity.Product;
import com.springboot.farm.springbootpractice.domain.product.ProductRepository;
import com.springboot.farm.springbootpractice.web.dto.product.BuyProductDto;
import com.springboot.farm.springbootpractice.web.dto.product.CreateProductListReqDto;
import com.springboot.farm.springbootpractice.web.dto.product.CreateProductReqDto;
import com.springboot.farm.springbootpractice.web.dto.product.ReadDeletedProductRespDto;
import com.springboot.farm.springbootpractice.web.dto.product.ReadPastAndNowProductInfoDto;
import com.springboot.farm.springbootpractice.web.dto.product.ReadProductReqDto;
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
	public boolean addUserProduct(BuyProductDto buyProductDto) throws Exception {
		return productRepository.addUserProduct(buyProductDto.toEntity()) > 0;
	}
	
	@Override
	public int addProductToList(CreateProductListReqDto createProductListReqDto) throws Exception {
		List<Product> productEntityList = new ArrayList<Product>();
		
		productEntityList = createProductListReqDto.toEntity();
		
		return productRepository.saveList(productEntityList);
	}
	
	@Override
	public ReadProductRespDto getUserProductByProductName(ReadProductReqDto readProductReqDto) throws Exception {
		Product product = null;
		
		product = productRepository.readUserProductByProductName(readProductReqDto.toEntity());
		
		return product == null ? null : product.toReadRespDto();
	}
	
	@Override
	public ReadProductRespDto getProductByProductName(String productName, String type) throws Exception {
		Product readProductRespDto = null;
		
		readProductRespDto = productRepository.readProductByProductName(productName, type);
		
		return readProductRespDto == null ? null : readProductRespDto.toReadRespDto();
	}
	
	@Override
	public List<ReadProductRespDto> getAllProductList(String type) throws Exception {
//		List<ReadProductRespDto> productList = null;
//		
//		
//		productList = productRepository.readAllProduct(type)
//				.stream()
//				.map(entity -> entity.toReadRespDto())
//				.collect(Collectors.toCollection(ArrayList::new));
//		
//		return productList;
		
		return toReadProductRestDtoList(productRepository.readAllProduct(type));
	}
	
	@Override
	public List<ReadProductRespDto> getMyProductList(int userCode) throws Exception {
//		List<ReadProductRespDto> productList = null;
//		
//		productList = productRepository.readMyProductList(userCode)
//				.stream()
//				.map(entity -> entity.toReadRespDto())
//				.collect(Collectors.toCollection(ArrayList::new));
//		
//		return productList;
		
		return toReadProductRestDtoList(productRepository.readMyProductList(userCode));
	}
	
	@Override
	public List<ReadProductRespDto> getRecentlyProductList(String date) throws Exception {
//		List<ReadProductRespDto> productList = null;
//		
//		productList = productRepository.readRecentlyProductLIst(date)
//				.stream()
//				.map(entity -> entity.toReadRespDto())
//				.collect(Collectors.toCollection(ArrayList::new));
//		
//		return productList;
		return toReadProductRestDtoList(productRepository.readRecentlyProductLIst(date));
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
	public List<ReadDeletedProductRespDto> getDeletedUsersProductList(int userCode) throws Exception {
		List<ReadDeletedProductRespDto> productList = null;
		
		productList = productRepository.getDeletedProductListByUserCode(userCode)
				.stream()
				.map(entity -> entity.toDto())
				.collect(Collectors.toCollection(ArrayList::new));
		
		return productList;
	}
	
	@Override
	public boolean modifyProductInfo(UpdateProductReqDto updateProductReqDto) throws Exception {
		return productRepository.update(updateProductReqDto.toEntity()) > 0;
	}
	
	@Override
	public boolean updateUsersProduct(UpdateProductReqDto updateProductReqDto) throws Exception {
		return productRepository.updateUserProduct(updateProductReqDto.toEntity()) > 0;
	}

	@Override
	public boolean removeProduct(int productCode) throws Exception {
		return productRepository.delete(productCode) > 0;
	}
	
	@Override
	public boolean removeDeletedUserProduct(int userCode) throws Exception {
		return productRepository.deleteDeletedUserProduct(userCode) > 0;
	}
	

	private List<ReadProductRespDto> toReadProductRestDtoList(List<Product> productEntityList) {
		List<ReadProductRespDto> dtoList = new ArrayList<>();
		dtoList = productEntityList
			.stream()
			.map(entity -> entity.toReadRespDto())
			.collect(Collectors.toCollection(ArrayList::new));
		
		return dtoList;
	}
}