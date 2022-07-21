package com.springboot.farm.springbootpractice.web.controller.api.product;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.farm.springbootpractice.service.ProductService;
import com.springboot.farm.springbootpractice.web.dto.CMRespDto;
import com.springboot.farm.springbootpractice.web.dto.product.ReadProductRespDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {
	
	private final ProductService productService;
	
	@GetMapping("/{productName}")
	public ResponseEntity<?> getProductByProductName(@PathVariable String productName) {
		ReadProductRespDto readProductRespDto = null;
		
		try {
			readProductRespDto = productService.getProductByProductName(productName);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok().body(new CMRespDto<>(-1, "품목 불러오기 실패", readProductRespDto));
		}
		
		return ResponseEntity.ok().body(new CMRespDto<>(1, "품목 불러오기 성공", readProductRespDto));
	}
}