package com.springboot.farm.springbootpractice.config.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		
		String header = request.getHeader("X-Requested-With");
		

        boolean isAjax = "XMLHttpRequest".equals(header);
		
		if(isAjax) {	// 인증이 안 되어있는 상태로 Ajax 통신이 되었을 경우 403 에러
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
		}else {
			String uri = request.getRequestURI();
			if(uri.equals("/notice") || uri.equals("/free") || uri.equals("/QnA")) {
				response.setCharacterEncoding("utf-8");
				response.setContentType("text/html; charset=utf-8");
				response.getWriter().print("<html><head></head><body><script>alert(\"로그인 후 진행해주세요.\");location.href=\"/index\"</script></body></html>");
				
			}
		}
	}

}