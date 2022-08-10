package com.springboot.farm.springbootpractice.web.dto.auth;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class CheckUsernameReqDto {
	@NotBlank
	@Pattern(regexp = "^[A-Za-z0-9]{4,10}$", message = "5자~10자이내로 영소대문자, 숫자 조합으로 만들어주세요.")
	private String username;
}