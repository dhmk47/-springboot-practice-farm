package com.springboot.farm.springbootpractice.web.controller.api.product;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.farm.springbootpractice.service.ProductService;
import com.springboot.farm.springbootpractice.util.Util;
import com.springboot.farm.springbootpractice.web.dto.CMRespDto;
import com.springboot.farm.springbootpractice.web.dto.product.CreateProductReqDto;
import com.springboot.farm.springbootpractice.web.dto.product.ReadProductRespDto;
import com.springboot.farm.springbootpractice.web.dto.product.UpdateProductReqDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {
	
	private final ProductService productService;
	
	@PostMapping("/new")
	public ResponseEntity<?> addProduct(CreateProductReqDto createProductReqDto) {
		boolean result = false;
		
		try {
			if(productService.insertProduct(createProductReqDto)) {
				result = true;
				Util.addProductFlag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok().body(new CMRespDto<>(-1, "새로운 품목 생성 실패", result));
		}
		
		return ResponseEntity.ok().body(new CMRespDto<>(1, "새로운 품목 생성 성공", result));
	}
	
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
	
	@GetMapping("/list")
	public ResponseEntity<?> getRecentrylProductList() {
		List<ReadProductRespDto> productList = null;
		
		if(Util.addProductFlag) {
			
			Date date = new Date();
			date.setDate(date.getDate() - 1);
			SimpleDateFormat now = new SimpleDateFormat("yyyy-MM-dd");
			
			try {
				productList = productService.getRecentlyProductList(now.format(date) + "");
				
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.internalServerError().body(new CMRespDto<>(-1, "새롭게 추가된 품목 불러오기 실패", productList));
			}
		}
		return ResponseEntity.ok().body(new CMRespDto<>(1, "새롭게 추가된 품목 불러오기 성공", productList));
	}
		
	@PutMapping("/modify")
	public ResponseEntity<?> modifyProduct(UpdateProductReqDto updateProductReqDto) {
		boolean result = false;
		
		try {
			result = productService.modifyProductInfo(updateProductReqDto);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new CMRespDto<>(-1, "품목 수정 실패", result));
		}
		
		return ResponseEntity.ok().body(new CMRespDto<>(1, "품목 수정 성공", result));
	}
	
	@DeleteMapping("/remove")
	public ResponseEntity<?> removeProduct(int productCode) {
		boolean result = false;
		
		try {
			productService.removeProduct(productCode);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new CMRespDto<>(-1, "품목 삭제 실패", result));
		}
		
		return ResponseEntity.ok().body(new CMRespDto<>(1, "품목 삭제 성공", result));
	}
}