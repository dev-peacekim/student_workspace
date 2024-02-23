package com.blackberry.s20240130103.ykm.controller;

import org.springframework.stereotype.Controller;
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

	@GetMapping(value = "boardWriteForm")
	public String boardWriteForm() {
		return "ykm/boardWriteForm";
	}
	
	@GetMapping(value = "boardUpdateForm")
	public String boardUpdateForm() {
		return "ykm/boardUpdateForm";
	}
	
	@GetMapping(value="boardDetailForm")
	public String boardDetailForm() {
		return "ykm/boardDetailForm";
	}
	
	
	/* button */
	
	@GetMapping(value = "boardStuConWrite")
	public String boardStuConWrite() {
		return "ykm/boardWriteForm";
	}


	@GetMapping(value="boardWritesubmit")
	public String boardWritesubmit() {
		return "ykm/boardStudy";
	}
	
	@GetMapping(value = "boardWritereset")
	public String boardWritereset() {
		return "ykm/boardStudy";
	}
	
	@GetMapping(value="boardUpdatesubmit")
	public String boardUpdatesubmit() {
		return "ykm/boardStudy";
	}
	
	@GetMapping(value = "boardUpdatereset")
	public String boardUpdatereset() {
		return "ykm/boardStudy";
	}
	

	
}