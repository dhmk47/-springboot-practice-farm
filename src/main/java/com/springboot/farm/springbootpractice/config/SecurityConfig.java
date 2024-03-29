package com.springboot.farm.springbootpractice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.AntPathMatcher;

import com.springboot.farm.springbootpractice.config.auth.AuthFailureHandler;
import com.springboot.farm.springbootpractice.config.auth.CustomAccessDeniedHandler;
import com.springboot.farm.springbootpractice.config.auth.CustomAuthenticationEntryPoint;
import com.springboot.farm.springbootpractice.service.auth.PrincipalOAuth2Service;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final PrincipalOAuth2Service principalOAuth2Service;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable();
		
		http.authorizeRequests()
			.antMatchers("/notice", "/free", "QnA", "/product/management", "/api/v1/content/reply")
			.authenticated()
			
			.antMatchers("/product/management")
				.access("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
			
			.antMatchers("/api/v1/product/auth/**")
				.access("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
			
				.antMatchers("/notice/**")
				.access("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
			
			.anyRequest()
				.permitAll()
				
			.and()
			
			.exceptionHandling()
				.authenticationEntryPoint(new CustomAuthenticationEntryPoint())
				
				.accessDeniedHandler(new CustomAccessDeniedHandler())
				
			.and()
			
			.formLogin()
				.loginPage("/index")
				.loginProcessingUrl("/auth/signin")
				.failureHandler(new AuthFailureHandler())
				
			.and()
			
			.oauth2Login()
			.userInfoEndpoint()
			.userService(principalOAuth2Service)
			
			.and()
			
			.failureHandler(new AuthFailureHandler())
			.defaultSuccessUrl("/")
			
			.and()
			
			.logout()
			.logoutSuccessUrl("/");
		
		
	}
}