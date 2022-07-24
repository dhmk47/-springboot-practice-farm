package com.springboot.farm.springbootpractice.util;

public class Util {
	// 새로운 품목이 들어온다면 true로 대입 후 true면 db에 접근 후 데이터 가져오기
	// false면 db 접근 X
	// 라이브 서버가 아닌 현재 서버가 계속 재실행 되는 과정이기 때문에 임시적으로 true false값으로만 설정
	// ㄴ 서버 재실행을 안 한다면 회원이 회원 가입 할때마다 그 코드를 map의 key 값으로 사용하고 그 키 값마다 고유의 boolean 값을 가진다면
	// ㄴ 회원마다 true 또는 false 값으로 db에 접근할지 안 할지 결정이 됩니다.
	public static boolean addProductFlag = false;
	/* public static Map<Integer, Boolean> addProductFlag = new HashMap<Integer, Boolean>();*/
}