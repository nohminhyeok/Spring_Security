package com.example.ss.security;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
    // Spring Security 필터들을 Bean으로 등록 → 체인 형태로 등록
    // HttpRequest를 가로채서 래핑하여 Spring Security에서 사용 가능한 HttpSecurity 타입으로 변환해 인자로 받음 
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		
		// 인자값(httpSecurity)을 이용하여 인증, 인가를 설정
		
		// 1) CSRF 설정이 기본값이 true - form에서 값을 넘길 때 약속된 암호화된 토큰 값을 넘겨야 한다 → 사용하지 않도록 변경
		// Spring Security 설정값 중 CSRF 설정 정보를 가지는 CsrfConfigurer 정보를 변경
		httpSecurity.csrf((csrfConfigurer) -> csrfConfigurer.disable());
		
		// 2) 인가 설정
		// Spring Security 설정값 AuthorizationManagerRequestMatcherRegistry(인가 리스트) 설정 정보를 수정
		httpSecurity.authorizeHttpRequests((requestMatcherRegistry) -> 
					 requestMatcherRegistry.requestMatchers("/","/login","/loginAction","/addUser","/addUserAction", "/WEB-INF/view/**").permitAll()
										   .requestMatchers("/admin/**").hasRole("ADMIN") // 테이블 role 컬럼값 "ROLE_ADMIN"
										   .requestMatchers("/user/**").hasAnyRole("ADMIN", "USER") // 테이블 role 컬럼값 "ROLE_USER"
										   .anyRequest().authenticated()); // 그 외의 요청은 로그인 한 사람만 요청 가능
        
		// 3) 로그인 설정
		// FormLoginConfigurer 설정값을 변경
		httpSecurity.formLogin((formLoginConfigurer) -> 
					 formLoginConfigurer.loginPage("/login")
										.loginProcessingUrl("/loginAction") // 인증을 위해 필터를 가로채는 loginAction 주소 -> UserDetailService의 구현체를 호출
										.successHandler(new AuthenticationSuccessHandler() {
											@Override
											public void onAuthenticationSuccess(HttpServletRequest request
																			   ,HttpServletResponse response
																			   ,Authentication authentication) throws IOException, ServletException {
												System.out.println("로그인 성공");
												// request 값을 가공, response
												response.sendRedirect("/");
											}
										})
										.failureHandler(new AuthenticationFailureHandler() {
											@Override
											public void onAuthenticationFailure(HttpServletRequest request
																			   ,HttpServletResponse response
																			   ,AuthenticationException exception) throws IOException, ServletException {
												System.out.println("로그인 실패");
												response.sendRedirect("/login");
											}
										}));
        
        // 4) 인증(로그아웃) 설정
        httpSecurity.logout((logoutConfigurer) -> 
        			 logoutConfigurer.logoutUrl("/logout")
        			 				 .invalidateHttpSession(true)
        			 				 .logoutSuccessUrl("/login"));

        return httpSecurity.build(); // httpSecurity 설정이 끝나면 build 후 SecurityFilterChain 타입으로 변환
    }

}
