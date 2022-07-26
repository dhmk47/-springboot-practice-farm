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
import com.springboot.farm.springbootpractice.web.dto.product.ReadPastAndNowProductInfoDto;
import com.springboot.farm.springbootpractice.web.dto.product.ReadProductReqDto;
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
				/*Util.addProductFlag.forEach((key, value) -> {
					Util.addProductFlag.put(key, true);
				});*/
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok().body(new CMRespDto<>(-1, "새로운 품목 생성 실패", result));
		}
		
		return ResponseEntity.ok().body(new CMRespDto<>(1, "새로운 품목 생성 성공", result));
	}
	
	@GetMapping("/users")
	public ResponseEntity<?> getUserProductByProductName(ReadProductReqDto readProductReqDto) {
		ReadProductRespDto readProductRespDto = null;
		
		try {
			readProductRespDto = productService.getUserProductByProductName(readProductReqDto);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new CMRespDto<>(-1, "사용자 농산물 불러오기 실패", readProductRespDto));
		}
		
		return ResponseEntity.ok().body(new CMRespDto<>(1, "사용자 농산물 불러오기 성공", readProductRespDto));
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
	public ResponseEntity<?> getAllProductToList() {
		List<ReadProductRespDto> productList = null;
		
		try {
			productList = productService.getAllProductList();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new CMRespDto<>(-1, "모든 농산물 리스트로 불러오기 실패", productList));
		}
		
		return ResponseEntity.ok().body(new CMRespDto<>(1, "모든 농산물 리스트로 불러오기 성공", productList));
	}
	
	@GetMapping("/list/{userCode}")
	public ResponseEntity<?> getMyProductList(@PathVariable int userCode) {
		List<ReadProductRespDto> productList = null;
		
		try {
			productList = productService.getMyProductList(userCode);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new CMRespDto<>(-1, "사용자 농산물 리스트 불러오기 실패", productList));
		}
		
		return ResponseEntity.ok().body(new CMRespDto<>(1, "사용자 농산물 리스트 불러오기 성공", productList));
	}
	
	@GetMapping("/new/list")
	public ResponseEntity<?> getRecentlyProductList(/*int userCode*/) {
		List<ReadProductRespDto> productList = null;
		
		if(Util.addProductFlag/*Util.addProductFlag.get(userCode)*/) {
			
			Date now = new Date();
			now.setDate(now.getDate() - 1);
			SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
			
			try {
				productList = productService.getRecentlyProductList(date.format(now) + "");
				/*Util.addProductFlag.replace(userCode, false);*/
				
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.internalServerError().body(new CMRespDto<>(-1, "새롭게 추가된 품목 불러오기 실패", productList));
			}
		}
		return ResponseEntity.ok().body(new CMRespDto<>(1, "새롭게 추가된 품목 불러오기 성공", productList));
	}
	
	@GetMapping("/modify/list")
	public ResponseEntity<?> getRecentlyModifiedProductList() {
		List<ReadPastAndNowProductInfoDto> productList = null;
		
		if(Util.modifyProductFlag) {
			Date now = new Date();
			now.setDate(now.getDate() - 1);
			SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
			
			try {
				productList = productService.getRecentlyModifiedProductList(date.format(now));
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.internalServerError().body(new CMRespDto<>(-1, "최근에 수정된 품목 불러오기 실패", productList));
			}
			
		}
		
		return ResponseEntity.ok().body(new CMRespDto<>(1, "최근에 수정된 품목 불러오기 성공", productList));
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