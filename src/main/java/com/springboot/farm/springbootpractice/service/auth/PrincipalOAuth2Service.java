package com.springboot.farm.springbootpractice.service.auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2AuthorizationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.springboot.farm.springbootpractice.domain.entity.User;
import com.springboot.farm.springbootpractice.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class PrincipalOAuth2Service extends DefaultOAuth2UserService {
	
	private final UserRepository userRepository;
//	private final BCryptPasswordEncoder passwordEncoder; // 왜 오류?

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		String provider = null;
		User user = null;
		Map<String, Object> attributes = new HashMap<String, Object>();
		
		OAuth2User auth2User = super.loadUser(userRequest);
		ClientRegistration clientRegistration =  userRequest.getClientRegistration();
		
		provider = clientRegistration.getClientName(); // Google, Naver
		
		attributes = auth2User.getAttributes();			// Google -> sub, name, email, Naver -> id, name, email, mobile
		
		log.info(">>>>>>> attributes: {}", attributes);
		log.info(">>>>>>> clientRegistration: {}", clientRegistration);
		
		user = oAuth2User(provider, attributes);
		
		return new PrincipalDetails(user, attributes);
		
	}
	
	private User oAuth2User(String provider, Map<String, Object> attributes) throws OAuth2AuthenticationException {
		User user = null;
		String id = null;
		String oAuth_id = null;
		boolean naverFlag = false;
		Map<String, Object> response = null;
		
		if(provider.equalsIgnoreCase("google")) {
			response = attributes;
			id = (String) response.get("sub");
			
		}else if(provider.equalsIgnoreCase("naver")) {
			response = (Map<String, Object>) attributes.get("response");
			id = (String) response.get("id");
			naverFlag = true;
		}
		
		oAuth_id = provider + "_" + id;
		
		try {
			user = userRepository.getUserByOAuth2Id(oAuth_id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new OAuth2AuthenticationException("DATABASE Error");
		}
		
		if(user == null) {
			user = User.builder()
					.name((String) response.get("name"))
					.username(oAuth_id)
					.oauth2_id(oAuth_id)
					.password(new BCryptPasswordEncoder().encode(id))
					.email((String) response.get("email"))
					.roles("ROLE_USER")
					.build();
			
			try {
				userRepository.insertUser(user);
				
				if(naverFlag) {		// 네이버라면 전화번호도 제공해주기 때문에 전화번호도 같이 업데이트
					user.setPhone((String) response.get("mobile"));
					userRepository.updateUserPhone(user);
				}

				user = userRepository.getUserByOAuth2Id(oAuth_id);
			} catch (Exception e) {
				e.printStackTrace();
				throw new OAuth2AuthenticationException("DATABASE Error");
			}
			
		}
		
		return user;
	}
}