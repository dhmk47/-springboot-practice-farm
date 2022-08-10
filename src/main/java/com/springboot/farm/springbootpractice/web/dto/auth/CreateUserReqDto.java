package com.springboot.farm.springbootpractice.web.dto.auth;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.springboot.farm.springbootpractice.domain.entity.User;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreateUserReqDto {
	@NotBlank
	private String name;
	
	@NotBlank
	@Pattern(regexp = "^[A-Za-z0-9]{4,10}$", message = "5자~10자이내로 영소대문자, 숫자 조합으로 만들어주세요.")
	private String username;
	
	@NotBlank
	@Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$*_])[A-Za-z0-9!@#$*_]{8,16}$", message = "비밀번호는 소문자, 특수문자, 숫자가 하나 이상 포함되어 있어야 합니다.\\n사용가능 특수문자: !@#$*_")
	private String password;
	
	@NotBlank
	@Pattern(regexp = "^[A-Za-z0-9]+@[A-Za-z0-9]+\\.com$", message = "이메일 양식을 지켜주세요.")
	private String email;
	
	public User toEntity() {
		return User.builder()
				.name(name)
				.username(username)
				.password(password)
				.email(email)
				.roles("ROLE_USER")
				.build();
	}
}