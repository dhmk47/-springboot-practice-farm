package com.springboot.farm.springbootpractice.domain.product;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.springboot.farm.springbootpractice.domain.entity.PastAndNowProductInfo;
import com.springboot.farm.springbootpractice.domain.entity.Product;

@Mapper
public interface ProductRepository {
	public int save(Product product);
	public int addUserProduct(Product product);
	public Product readUserProductByProductCode(Product product);
	public Product readProductByProductName(String productName);
	public List<Product> readAllProduct();
	public List<Product> readMyProductList(int userCode);
	public List<Product> readRecentlyProductLIst(String date);
	public List<PastAndNowProductInfo> readModifiedProductList(String date);
	public int update(Product product);
	public int delete(int productCode);
}