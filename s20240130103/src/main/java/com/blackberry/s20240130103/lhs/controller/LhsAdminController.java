package com.blackberry.s20240130103.lhs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LhsAdminController {
	
	@GetMapping("/adminMain")
	public String adminMain() {
		return "admin/admin_main";
	}
	
}
