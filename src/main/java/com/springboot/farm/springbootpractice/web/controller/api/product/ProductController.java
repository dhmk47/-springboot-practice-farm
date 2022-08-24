package com.springboot.farm.springbootpractice.web.controller.api.product;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.farm.springbootpractice.domain.entity.User;
import com.springboot.farm.springbootpractice.service.ProductService;
import com.springboot.farm.springbootpractice.service.UserService;
import com.springboot.farm.springbootpractice.util.Util;
import com.springboot.farm.springbootpractice.web.dto.CMRespDto;
import com.springboot.farm.springbootpractice.web.dto.product.BuyProductDto;
import com.springboot.farm.springbootpractice.web.dto.product.CreateProductListReqDto;
import com.springboot.farm.springbootpractice.web.dto.product.CreateProductReqDto;
import com.springboot.farm.springbootpractice.web.dto.product.ReadDeletedProductRespDto;
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
	private final UserService userService;
	
	@PostMapping("/deleted/new")
	public ResponseEntity<?> addDeletedProduct(@RequestBody CreateProductListReqDto createProductListReqDto) {
		int result = 0;
		
		try {
			result = productService.addProductToList(createProductListReqDto);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new CMRespDto<>(-1, "삭제된 농산물 다시 등록 실패", result));
		}
		
		return ResponseEntity.ok().body(new CMRespDto<>(1, "삭제된 농산물 다시 등록 성공", result));
	}
	
	@PostMapping("/new")
	public ResponseEntity<?> addProduct(@RequestBody CreateProductReqDto createProductReqDto) {
		boolean result = false;
		
		try {
			result = productService.insertProduct(createProductReqDto);
			if(result) {
				allUsersProductFlagUpdate("new");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok().body(new CMRespDto<>(-1, "새로운 품목 생성 실패", result));
		}
		
		return ResponseEntity.ok().body(new CMRespDto<>(1, "새로운 품목 생성 성공", result));
	}
	
	@PostMapping("/users/new")
	public ResponseEntity<?> addUserProduct(@RequestBody BuyProductDto buyProductDto) {
		boolean result = false;
		
		try {
			result = productService.addUserProduct(buyProductDto);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new CMRespDto<>(-1, "사용자 농산물 추가 실패", result));
		}
		
		return ResponseEntity.ok().body(new CMRespDto<>(1, "사용자 농산물 추가 성공", result));
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
			readProductRespDto = productService.getProductByProductName(productName, "default");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok().body(new CMRespDto<>(-1, "품목 불러오기 실패", readProductRespDto));
		}
		
		return ResponseEntity.ok().body(new CMRespDto<>(1, "품목 불러오기 성공", readProductRespDto));
	}
	
	@GetMapping("/deleted/check/{productName}")
	public ResponseEntity<?> getDeletedProductByProductName(@PathVariable String productName) {
		ReadProductRespDto readProductRespDto = null;
		try {
			readProductRespDto = productService.getProductByProductName(productName, "delete");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok().body(new CMRespDto<>(-1, "삭제된 품목 불러오기 실패", readProductRespDto));
		}
		
		return ResponseEntity.ok().body(new CMRespDto<>(1, "삭제된 품목 불러오기 성공", readProductRespDto));
	}
	
	// type에 따라 모든, 삭제 농산물 리스트 불러옴
	@GetMapping("/list/{type}")
	public ResponseEntity<?> getAllProductToList(@PathVariable String type) {
		List<ReadProductRespDto> productList = null;
		
		try {
			productList = productService.getAllProductList(type);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new CMRespDto<>(-1, "모든 농산물 리스트로 불러오기 실패", productList));
		}
		
		return ResponseEntity.ok().body(new CMRespDto<>(1, "모든 농산물 리스트로 불러오기 성공", productList));
	}
	
	@GetMapping("/list/user/{userCode}")
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
	public ResponseEntity<?> getRecentlyProductList(int userCode) {
		List<ReadProductRespDto> productList = null;
				
		LocalDateTime now = LocalDateTime.now();
		
//		int year = now.getYear();
//		int month = now.getMonthValue();
//		int day = now.getDayOfMonth();
		
//		System.out.println(year);
//		System.out.println(month);
//		System.out.println(day);
//		System.out.println(nowLocalDateTime.minusDays(4));
		
		String dateTimeFormat = now.minusDays(4).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		
		if(Util.addProductFlag.get(userCode) != null) {
			
			if(Util.addProductFlag.get(userCode)) {
				
				System.out.println("if문 새로운 품목 가져오는 db 접근");
				
				
				try {
					productList = productService.getRecentlyProductList(dateTimeFormat);
					
					Util.newProductMap.put(userCode, productList);
					Util.addProductFlag.replace(userCode, false);
					
				} catch (Exception e) {
					e.printStackTrace();
					return ResponseEntity.internalServerError().body(new CMRespDto<>(-1, "새롭게 추가된 품목 불러오기 실패", productList));
				}
			}else {
				System.out.println("else문 새로운 품목 가져오는 db 접근x");
				
				productList = Util.newProductMap.get(userCode)
					.stream()
					.filter(product -> ChronoUnit.DAYS.between(product.getCreate_date(), now) < 4)
					.collect(Collectors.toCollection(ArrayList<ReadProductRespDto>::new));
				
				
				Util.newProductMap.put(userCode, productList);
				
			}
		}else {
			System.out.println("else문 새로운 품목 가져오는 db 접근");
			
			try {
				productList = productService.getRecentlyProductList(dateTimeFormat);
				Util.newProductMap.put(userCode, productList);
				Util.addProductFlag.put(userCode, false);
				
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.internalServerError().body(new CMRespDto<>(-1, "새롭게 추가된 품목 불러오기 실패", productList));
			}
		}
		
		return ResponseEntity.ok().body(new CMRespDto<>(1, "새롭게 추가된 품목 불러오기 성공", productList));
	}
	
	@GetMapping("/modify/list")
	public ResponseEntity<?> getRecentlyModifiedProductList(int userCode) {
		List<ReadPastAndNowProductInfoDto> productList = null;

		LocalDateTime now = LocalDateTime.now();
		
//		int year = now.getYear();
//		int month = now.getMonthValue();
//		int day = now.getDayOfMonth();
		
//		System.out.println(year);
//		System.out.println(month);
//		System.out.println(day);
//		System.out.println(nowLocalDateTime.minusDays(4));
		
		String dateTimeFormat = now.minusDays(4).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		
		if(Util.modifyProductFlag.get(userCode) != null) {
			
			if(Util.modifyProductFlag.get(userCode)) {

				System.out.println("if문 수정된 품목 가져오는 db 접근");
				
				try {
					productList = productService.getRecentlyModifiedProductList(dateTimeFormat);
					Util.modifyProductMap.put(userCode, productList);
					Util.modifyProductFlag.put(userCode, false);
					
				} catch (Exception e) {
					e.printStackTrace();
					return ResponseEntity.internalServerError().body(new CMRespDto<>(-1, "최근에 수정된 품목 불러오기 실패", productList));
				}
				
			}else {

				System.out.println("else문 수정된 품목 가져오는 db 접근x");
				
				productList = Util.modifyProductMap.get(userCode)
						.stream()
						.filter(product -> ChronoUnit.DAYS.between(product.getUpdateDate(), now) < 4)
						.collect(Collectors.toCollection(ArrayList::new));
				
				Util.modifyProductMap.put(userCode, productList);
			}
		}else {
			System.out.println("else문 수정된 품목 가져오는 db 접근");
			
			try {
				productList = productService.getRecentlyModifiedProductList(dateTimeFormat);
				Util.modifyProductMap.put(userCode, productList);
				Util.modifyProductFlag.put(userCode, false);
				
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.internalServerError().body(new CMRespDto<>(-1, "최근에 수정된 품목 불러오기 실패", productList));
			}
			
		}

		return ResponseEntity.ok().body(new CMRespDto<>(1, "최근에 수정된 품목 불러오기 성공", productList));
	}
	
	@GetMapping("/deleted/list/user/{userCode}")
	public ResponseEntity<?> checkDeletedUserProduct(@PathVariable int userCode) {
		List<ReadDeletedProductRespDto> deletedUserProductList = null;
		
		if(Util.deletedFlag) {
			try {
				deletedUserProductList = productService.getDeletedUsersProductList(userCode);
				
				if(deletedUserProductList.size() > 0) {	// 삭제된 품목이 하나라도 있다면
					int money = 0;
					
					for(ReadDeletedProductRespDto product : deletedUserProductList) {
						money += product.getAmount() * product.getPurchasePrice();
					}
					
					deletedUserProductList.get(0).setRewardMoney(money);
					
					if(productService.removeDeletedUserProduct(userCode)) {	// deleted_user_product 테이블의 데이터를 삭제에 성공했다면
						// 그 보상금액을 update에 성공했다면 or 실패했다면
						String type = "reward";
						return userService.updateUserMoney(money, userCode, type) ? ResponseEntity.ok().body(new CMRespDto<>(1, "삭제된 사용자 품목 불러오기(삭제, 금액보상) 성공", deletedUserProductList))
								: ResponseEntity.internalServerError().body(new CMRespDto<>(-1, "삭제된 사용자 품목 불러오기(삭제, 금액보상) 실패", deletedUserProductList));
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.internalServerError().body(new CMRespDto<>(-1, "삭제된 사용자 품목 불러오기 실패", deletedUserProductList));
			}
		}
		
		return ResponseEntity.ok().body(new CMRespDto<>(1, "삭제된 사용자 품목 불러오기 성공", deletedUserProductList));
	}
		
	@PutMapping("/modify/{productCode}")
	public ResponseEntity<?> modifyProduct(@PathVariable int productCode, @RequestBody UpdateProductReqDto updateProductReqDto) {
		boolean result = false;
		
		updateProductReqDto.setProductCode(productCode);
		
		try {
			result = productService.modifyProductInfo(updateProductReqDto);
			if(result) {
				allUsersProductFlagUpdate("modify");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new CMRespDto<>(-1, "품목 수정 실패", result));
		}
		
		return ResponseEntity.ok().body(new CMRespDto<>(1, "품목 수정 성공", result));
	}
	
	@PutMapping("/users/new")
	public ResponseEntity<?> updateUserProduct(UpdateProductReqDto updateProductReqDto) {
		boolean result = false;
		try {
			result = productService.updateUsersProduct(updateProductReqDto);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new CMRespDto<>(-1, "사용자 품목 업데이트 실패", result));
		}
		
		return ResponseEntity.ok().body(new CMRespDto<>(1, "사용자 품목 업데이트 성공", result));
	}
	
	@DeleteMapping("/remove/{productCode}")
	public ResponseEntity<?> removeProduct(@PathVariable int productCode) {
		boolean result = false;
		
		try {
			productService.removeProduct(productCode);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new CMRespDto<>(-1, "품목 삭제 실패", result));
		}
		
		return ResponseEntity.ok().body(new CMRespDto<>(1, "품목 삭제 성공", result));
	}
	
	private void allUsersProductFlagUpdate(String type) {
		try {
			List<User> userList = userService.getAllUserToList();
			
			if(type.equals("new")) {
				userList.forEach(user -> Util.addProductFlag.put(user.getUsercode(), true));
				System.out.println("모두 true로 변경");
				
				Util.addProductFlag.put(0, true);
			}else {
				userList.forEach(user -> Util.modifyProductFlag.put(user.getUsercode(), true));
				System.out.println("모두 true로 변경");
				
				Util.addProductFlag.put(0, true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}