package com.blackberry.s20240130103.ykm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.blackberry.s20240130103.ykm.model.YkmBoardComm;
import com.blackberry.s20240130103.ykm.service.YkmService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class YkmboardController {
	
	private final YkmService ykmService;

	/* mapping */
	
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
	
	@GetMapping(value="boardDetail")
	public String boardDetail() {
		return "ykm/boardDetail";
	}
	
	@GetMapping(value="boardModify")
	public String boardModify() {
		return "ykm/boardModify";
	}
	
	
	/* form */
	
	@PostMapping(value = "boardPost")
	public int boardPost(HttpServletRequest request, YkmBoardComm ykmBoardComm) {
		System.out.println("YkmController boardPost Start...");
		// request 활용해서 세션에 저장한 유저 no 받아오기
		String user_no = (String)request.getSession().getAttribute("user_no");
		
		if (user_no != null) {
			int result = ykmService.insertStuPost(ykmBoardComm, "user_no");
			return result;
		} else {
			return -1;
		}
		
	
	}
	
	
	
	
	

	@GetMapping(value="boardUpdatesubmit")
	public String boardUpdatesubmit() {
		return "ykm/boardStudy";
	}
	
	@GetMapping(value = "boardUpdatereset")
	public String boardUpdatereset() {
		return "ykm/boardStudy";
	}
	
	@GetMapping(value = "boardComment")
	public String boardComment() {
		return "ykm/boardComment";
	}

	
}