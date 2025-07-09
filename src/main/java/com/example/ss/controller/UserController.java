package com.example.ss.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.ss.dto.UserDto;
import com.example.ss.service.UserService;

@Controller
public class UserController {
	private UserService userService;
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/addUser")
	public String addUser() {
		return "addUser";
	}
	
	@PostMapping("/addUserAction")
	public String addUserAction(UserDto userDto) {
		userService.addUser(userDto);
		return "redirect:/login";
	}
	
	@GetMapping("/modifyUser")
	public String modifyUser(Model model) {
		String loginUsername = SecurityContextHolder.getContext().getAuthentication().getName();
		model.addAttribute("loginUsername", loginUsername);
		return "modifyUser";
	}
	
	@PostMapping("/modifyUserAction")
	public String modifyUserAction(UserDto userDto) {
		userService.modifyUser(userDto);
		return "login";
	}

	@GetMapping("/deleteUser")
	public String deleteUser(Model model) {
		String loginUsername = SecurityContextHolder.getContext().getAuthentication().getName();
		model.addAttribute("loginUsername", loginUsername);
		return "deleteUser";
	}
	
    @PostMapping("/deleteUserAction")
    public String deleteUserAction(UserDto userDto, Model model) {
        boolean result = userService.deleteUser(userDto);
        if (!result) {
            model.addAttribute("deleteError", "비밀번호가 일치하지 않습니다.");
            model.addAttribute("loginUsername", userDto.getUsername());
            return "deleteUser";
        }
        return "redirect:/login";
    }
	
}
