package com.springboot.farm.springbootpractice.service;

import java.util.List;

import com.springboot.farm.springbootpractice.web.dto.product.BuyProductDto;
import com.springboot.farm.springbootpractice.web.dto.product.CreateProductReqDto;
import com.springboot.farm.springbootpractice.web.dto.product.ReadPastAndNowProductInfoDto;
import com.springboot.farm.springbootpractice.web.dto.product.ReadProductReqDto;
import com.springboot.farm.springbootpractice.web.dto.product.ReadProductRespDto;
import com.springboot.farm.springbootpractice.web.dto.product.UpdateProductReqDto;

public interface ProductService {
	public boolean insertProduct(CreateProductReqDto createProductReqDto) throws Exception;
	public boolean addUserProduct(BuyProductDto buyProductDto) throws Exception;
	public ReadProductRespDto getUserProductByProductName(ReadProductReqDto readProductReqDto) throws Exception;
	public ReadProductRespDto getProductByProductName(String productName) throws Exception;
	public List<ReadProductRespDto> getAllProductList() throws Exception;
	public List<ReadProductRespDto> getMyProductList(int userCode) throws Exception;
	public List<ReadProductRespDto> getRecentlyProductList(String date) throws Exception;
	public List<ReadPastAndNowProductInfoDto> getRecentlyModifiedProductList(String date) throws Exception;
	public boolean modifyProductInfo(UpdateProductReqDto updateProductReqDto) throws Exception;
	public boolean updateUsersProduct(UpdateProductReqDto updateProductReqDto) throws Exception;
	public boolean removeProduct(int productCode) throws Exception;
}