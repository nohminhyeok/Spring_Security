package com.example.ss.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ss.domain.UserDomain;
import com.example.ss.dto.UserDto;
import com.example.ss.mapper.UserMapper;

@Service
public class UserService {

	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private UserMapper userMapper;
	
	public UserService(BCryptPasswordEncoder bCryptPasswordEncoder, UserMapper userMapper) {
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.userMapper = userMapper;
	}
	
	// 회원 수정
	  public void modifyUser(UserDto userDto) {
	      UserDomain userDomain = new UserDomain();
	      userDomain.setUsername(userDto.getUsername());
	      userDomain.setRole("ROLE_USER");
	      userDomain.setChangePw(bCryptPasswordEncoder.encode(userDto.getChangePw()));
	      userMapper.modifyUser(userDomain);
	  }
	
	// 회원 삭제
    public boolean deleteUser(UserDto userDto) {
        UserDomain user = userMapper.selectByUsername(userDto.getUsername());
        if (user == null) return false;
        if (!bCryptPasswordEncoder.matches(userDto.getPassword(), user.getPassword())) {
            return false;
        }
        UserDomain userDomain = new UserDomain();
        userDomain.setUsername(userDto.getUsername());
        userMapper.delete(userDomain);
        return true;
    }
	
	// 회원 가입
	public void addUser(UserDto userDto) {
		if(userMapper.selectByUsername(userDto.getUsername()) != null) {
			System.out.println(userDto.getUsername()+"사용자 이름이 존재합니다.");
			// throw new RuntimeException("");
			return;
		}
		// dto -> domain
		UserDomain userDomain = new UserDomain();
		userDomain.setUsername(userDto.getUsername());
		userDomain.setRole("ROLE_USER");
		userDomain.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
		userMapper.insert(userDomain);
	}

}
