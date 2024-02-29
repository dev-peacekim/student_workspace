package com.blackberry.s20240130103.ykm.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.blackberry.s20240130103.ykm.model.YkmBoardComm;
import com.blackberry.s20240130103.ykm.model.YkmBoardCommReply;
import com.blackberry.s20240130103.ykm.service.YkmService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class YkmboardController {
	
	private final YkmService ykmService;

	/* mapping */
	
	@GetMapping(value="boardStudy")
	public String boardStudy(YkmBoardComm ykmBoardComm, Model model) {
		List<YkmBoardComm> boardStudyList = ykmService.spreadBoardList();
		model.addAttribute("boardStudyList", boardStudyList);
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
	public String boardDetail(HttpServletRequest request, Model model) {
		int cboard_no = Integer.parseInt(request.getParameter("cboard_no"));
		YkmBoardComm renderPostContent = ykmService.renderPostContent(cboard_no);
		
		// System.out.println(renderPostContent);
		// 나중에 대댓글 할 때 관련 정보 담아줘야댐
		model.addAttribute("renderPostContent", renderPostContent);
		return "ykm/boardDetail";
	}
	
	@GetMapping(value="boardModify")
	public String boardModify() {
		return "ykm/boardModify";
	}
	
	
	/* form */
	@PostMapping(value = "boardPost")
	public String boardPost(HttpServletRequest request, YkmBoardComm ykmBoardComm) {
		System.out.println("YkmController boardPost start---*");
		
		Long user_no = (Long)request.getSession().getAttribute("user_no");
		ykmBoardComm.setUser_no(user_no);
		// System.out.println("ykmBoardComm board : " + ykmBoardComm);
		int result = ykmService.insertBoardStudyPost(ykmBoardComm);
		System.out.println("ykmBoardComm result : " + result);

		return "redirect:/boardStudy";
	}
	
	@PostMapping(value = "StudyPostComment")
	public String StudyPostComment(YkmBoardCommReply ykmBoardCommReply, HttpServletRequest request) {
		System.out.println("YkmController StudyPostComment start---*");
		Long user_no = (Long)request.getSession().getAttribute("user_no");
		ykmBoardCommReply.setUser_no(user_no);
		ykmService.insertStudyPostComment(ykmBoardCommReply);
		return "redirect:/boardStudy";
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