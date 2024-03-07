package com.blackberry.s20240130103.lhs.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.blackberry.s20240130103.lhs.service.AdminService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LhsAdminController {
	
	private final AdminService adminService;
	
	@GetMapping("/adminMain")
	public String adminMain() {
		Map<String, Long> tableCntMap = adminService.selectTablesCnt();
		return "admin/admin_main";
	}
	
}
