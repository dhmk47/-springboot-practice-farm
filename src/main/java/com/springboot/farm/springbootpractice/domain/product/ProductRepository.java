package com.springboot.farm.springbootpractice.domain.product;

import org.apache.ibatis.annotations.Mapper;

import com.springboot.farm.springbootpractice.domain.entity.Product;

@Mapper
public interface ProductRepository {
	public int save(Product product);
	public Product readProductByProductName(String productName);
	public int update(Product product);
	public int delete(int productCode);
}