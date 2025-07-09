package com.example.ss.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.ss.dto.UserDto;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String login() {
		return "login";
	}
}
