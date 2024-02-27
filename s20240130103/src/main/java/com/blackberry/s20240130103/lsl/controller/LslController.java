package com.blackberry.s20240130103.lsl.controller;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.blackberry.s20240130103.lsl.Service.LslService;
import com.blackberry.s20240130103.lsl.model.LslBoardComm;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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

	  // 글 작성 페이지
    @GetMapping(value = "boardFreeWrite")
    public String boardFreeWrite() {
        return "lsl/boardFreeWrite";
    }

    // 자유게시판 글 작성
    @PostMapping(value = "boardWrite")
    public String freeBoardWrite(HttpServletRequest request, LslBoardComm lslBoardComm) {
        HttpSession session = request.getSession();
        Long user_no = (Long) session.getAttribute("user_no");
        int insertResult = ls.freeBoardWrite(lslBoardComm, user_no);
        if (insertResult > 0) return "redirect:boardFreeList";
        else return "redirect:boardFree";
    }
	
}
