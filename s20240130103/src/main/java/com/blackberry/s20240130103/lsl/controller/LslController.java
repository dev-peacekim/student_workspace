package com.blackberry.s20240130103.lsl.controller;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.blackberry.s20240130103.lsl.Service.LslService;
import com.blackberry.s20240130103.lsl.model.LslBoardComm;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LslController {
	private final LslService ls;
	
	// 자유게시판 페이지
	@GetMapping(value ="boardFree" )
	public String boardFree() {
		System.out.println("LslController boardFree Start...");
		return "lsl/boardFree";
	}

	@GetMapping(value = "boardFreeList")
	public String boardFreeList() {
		System.out.println("LslController boardFreeList Start...");
		return "lsl/boardFree";
	} 

	// 글작성 페이지
	@GetMapping(value = "boardFreeWrite" )
	public String boardFreeWrite() {
		return "lsl/boardFreeWrite";
	}
	// 자유게시판 글 작성
	@PostMapping(value="boardWrite")
	public String freeBoardWrite(LslBoardComm lslBoardComm) {
		System.out.println("LslController freeBoardWrite Start...");
		System.out.println("LslController freeBoardWrite lslBoardComm->"+lslBoardComm);
		
		int insertResult = ls.freeBoardWrite(lslBoardComm);
		if(insertResult > 0) return "redirect:boardFreeList";
		else {
			
		}
		
	 return "redirect:boardFree";
	}
	
}
