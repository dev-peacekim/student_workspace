package com.blackberry.s20240130103.lhs.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blackberry.s20240130103.lhs.service.AdminService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LhsAdminController {
	
	private final AdminService adminService;
	
	@GetMapping("/adminMain")
	public String adminMain(Model model) {
		Map<String, Long> tableCntMap = adminService.selectTablesCnt();
		model.addAttribute("cntMap",tableCntMap);
		return "admin/admin_main";
	}
	
	@GetMapping("userCntListAjax")
	@ResponseBody
	public String listMapUserCnt(){
		System.out.println("여기옴");
		List<Map<String, Long>> userJoinCntList = adminService.selectUserJoinCnt();
		Gson gson = new GsonBuilder().setDateFormat("MM-dd").create();
		String jsonstr = gson.toJson(userJoinCntList);
		System.out.println(jsonstr);
		return jsonstr;
	}
	
}
