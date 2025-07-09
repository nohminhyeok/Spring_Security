package com.example.ss.dto;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.ss.domain.UserDomain;

// Spring Security 인증에 사용하는 DTO
public class CustomUserDetails implements UserDetails {
	private UserDomain userDomain;
	
	public CustomUserDetails(UserDomain userDomain) {
		this.userDomain = userDomain;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> roleList = new ArrayList<>();
		GrantedAuthority grantedAuthority = new GrantedAuthority() {
			@Override
			public String getAuthority() {
				
				return userDomain.getRole();
			}
			
		};
		
		roleList.add(grantedAuthority);
		
		return roleList;
	}

	@Override
	public String getPassword() { // Spring Security는 사용자가 입력한 토크안에 password와 DB에서 받아온 getPassword() 결과물과 비교하여 인증처리
		// TODO Auto-generated method stub
		return userDomain.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userDomain.getUsername();
	}
	
	/*
		1) spring security - username, password
	 
		2) select password from user where username = username 

		1) password == 2) password --> 로그인
	 */

}
