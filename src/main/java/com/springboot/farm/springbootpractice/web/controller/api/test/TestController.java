package com.springboot.farm.springbootpractice.web.controller.api.test;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.farm.springbootpractice.service.ProductService;
import com.springboot.farm.springbootpractice.web.dto.CMRespDto;
import com.springboot.farm.springbootpractice.web.dto.product.ReadProductRespDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/test")
@RequiredArgsConstructor
public class TestController {

	private final ProductService productService;
	
	@GetMapping("/list")
	public ResponseEntity<?> getProductList() {
		List<ReadProductRespDto> productList = null;
		
		try {
			productList = productService.getAllProductList();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new CMRespDto<>(-1, "모든 농산물 리스트로 불러오기 실패", productList));
		}
		
		return ResponseEntity.ok().body(new CMRespDto<>(1, "모든 농산물 리스트로 불러오기 성공", productList));
	}
}
