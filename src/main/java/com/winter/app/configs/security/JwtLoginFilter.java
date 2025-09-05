package com.winter.app.configs.security;

import java.io.IOException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter{

	private AuthenticationManager authenticationManager;
	//autowired 하면 꼬일까봐 생성자
	
	private JwtTokenManager jwtTokenManager;
	// securityconfig JwtLoginFilter도 new로 만들고있음
	
	
	public JwtLoginFilter(AuthenticationManager authenticationManager, JwtTokenManager jwtTokenManager) {
		// TODO Auto-generated constructor stub
		this.authenticationManager = authenticationManager;
		this.jwtTokenManager = jwtTokenManager;
		this.setFilterProcessesUrl("/api/member/login");
	}
	
	
	// 로그인 처리
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		// 1. username, password
		System.out.println("로그인 시도");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		System.out.println(username);
		System.out.println(password);
		
		UsernamePasswordAuthenticationToken au = new UsernamePasswordAuthenticationToken(username, password);
		
		return authenticationManager.authenticate(au);
		
	}
	
	// 로그인이 성공했을 때 실행 메서드
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.out.println("로그인 성공");
		String accessToken = jwtTokenManager.createAccessToken(authResult);
		String refreshToken = jwtTokenManager.createRefreshToken(authResult); // db 저장
		
		// 토큰 전달시, 서버가 달라서 쿠키저장은 안됨
		
		/**
		 * 개발시 포트번호가 다르기 때문에 쿠키가 저장되지 않는다.
		 * Boot에 같이 빌드해서 배포하면 쿠키가 저장됨
		 */
		
		response.setHeader("accessToken", accessToken);
		
		
	}
	
	// 로그인이 실패했을 때 실행 메서드
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.out.println("로그인 실패");
		response.setCharacterEncoding("UTF-8");
		System.out.println(failed.getMessage());
		response.getWriter().print(failed.getMessage());
	}
	
	
}
