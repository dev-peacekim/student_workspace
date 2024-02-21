package com.blackberry.s20240130103.lhs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LhsController {
	
	@GetMapping(value = "loginForm")
	public String loginForm() {
		return "lhs/loginForm";
	}
	
	@GetMapping(value = "jointerms")
	public String joinTerms() {
		return "lhs/jointerms";
	}
	
	@GetMapping(value = "joinEmailForm")
	public String joinEmail() {
		return "lhs/joinemail";
	}
	
	@GetMapping(value = "joinForm")
	public String joinForm() {
		return "lhs/joinForm";
	}
	
}
