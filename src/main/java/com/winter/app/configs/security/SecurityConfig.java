package com.winter.app.configs.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private JwtTokenManager jwtTokenManager;
	
	@Autowired
	private AuthenticationConfiguration authenticationConfiguration;
	
	@Autowired
	private AddLogoutSuccessHandler addLogoutSuccessHandler;
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		// 1. 권한 설정(인가)
		httpSecurity
			.cors(cors -> cors.configurationSource(this.corsConfiguration()))
			.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests(
				auth -> {
					auth
						.requestMatchers("/api/notice/add").hasRole("ADMIN")	
						.requestMatchers("/api/notice").authenticated()	
						.anyRequest().permitAll();
			})
		
		// 2. Form Login
			.formLogin(formLogin -> formLogin.disable())
			
		// 3. logout 설정
			.logout(logout -> {
				logout
					.logoutUrl("/api/member/logout")
					.invalidateHttpSession(true)
					.deleteCookies("access_token", "refresh_token") //jsessionID도
					.logoutUrl("/")
					.logoutSuccessHandler(addLogoutSuccessHandler)
					;
			})
		
		// 4. Session
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		
		// 5. HttpBasic
			.httpBasic(http -> http.disable())
			
		// 6. Token 에 관련된 필터를 등록
			.addFilter(new JwtAuthenticationFilter(this.authenticationConfiguration.getAuthenticationManager(), jwtTokenManager))
			.addFilter(new JwtLoginFilter(this.authenticationConfiguration.getAuthenticationManager(), jwtTokenManager))
			;
		return httpSecurity.build();
	}
	
	CorsConfigurationSource corsConfiguration() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(List.of("*")); // origins를 쓰려면 setallowedcredentials
		
		//configuration.setAllowCredentials(true); 사용하면
		//setAllowedOrigins 사용이 안됨
		//setAllowedOriginPatterns를 사용
		// *를 사용 못 함
		
//		configuration.setAllowedOriginPatterns(List.of("http://localhost:*"));
//		configuration.setAllowCredentials(true);
		// Method에서 *은 사용 X
		configuration.setAllowedMethods(List.of("GET", "POST", "PATCH", "PUT", "OPTIONS"));
		configuration.setAllowedHeaders(List.of("*")); //이건 *사용 가능, 모든 헤더를 다 하겠다..
		
		configuration.setExposedHeaders(List.of("accessToken"));
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		
		return source;
		
	}
	
	
}
