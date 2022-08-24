package com.springboot.farm.springbootpractice.domain.product;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.springboot.farm.springbootpractice.domain.entity.DeletedProduct;
import com.springboot.farm.springbootpractice.domain.entity.PastAndNowProductInfo;
import com.springboot.farm.springbootpractice.domain.entity.Product;

@Mapper
public interface ProductRepository {
	public int save(Product product);
	public int saveList(List<Product> product);
	public int addUserProduct(Product product);
	public Product readUserProductByProductName(Product product);
	public Product readProductByProductName(String product_name, String type);
	public List<Product> readAllProduct(String type);
	public List<Product> readMyProductList(int userCode);
	public List<Product> readRecentlyProductLIst(String date);
	public List<PastAndNowProductInfo> readModifiedProductList(String date);
	public List<DeletedProduct> getDeletedProductListByUserCode(int userCode);
	public int update(Product product);
	public int updateUserProduct(Product product);
	public int delete(int productCode);
	public int deleteDeletedUserProduct(int userCode);
}