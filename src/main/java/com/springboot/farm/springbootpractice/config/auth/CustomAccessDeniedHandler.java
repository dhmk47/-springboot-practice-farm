package com.springboot.farm.springbootpractice.config.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		String uri = request.getRequestURI();
		if(uri.equals("/notice")) {
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html; charset=utf-8");
			response.getWriter().print("<html><head></head><body><script>alert(\"공지사항은 운영자 및 관리자만 작성 할 수 있습니다.\");location.href=\"/board?type=notice&page=1\"</script></body></html>");
		}
		
	}

}