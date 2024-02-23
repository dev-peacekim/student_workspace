package com.blackberry.s20240130103.ykm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BoardStudyController {
	
	@GetMapping(value="boardStudy")
	public String boardStudy() {
		return "ykm/boardStudy";
	}
	
	
	@GetMapping(value="boardContest")
	public String boardContest() {
		return "ykm/boardContest";
	}
	

	@GetMapping(value="boardRegisteWrite")
	public String boardRegisterDetail() {
		return "ykm/boardRegisterForm";
	}
	
	
}