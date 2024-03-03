package com.blackberry.s20240130103.ykm.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.blackberry.s20240130103.ykm.model.YkmBoardComm;
import com.blackberry.s20240130103.ykm.service.YkmService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class YkmBoardController {

	private final YkmService ykmService;

	/* mapping */

	@GetMapping(value = "boardStudy")
	public String boardStudy(YkmBoardComm ykmBoardComm, Model model) {
		List<YkmBoardComm> renderBoardList = ykmService.renderBoardList();
		model.addAttribute("boardStudyList", renderBoardList);
		return "ykm/boardStudy";
	}

	@GetMapping(value = "boardContest")
	public String boardContest() {
		return "ykm/boardContest";
	}

	@GetMapping(value = "boardWriteForm")
	public String boardWriteForm() {
		return "ykm/boardWriteForm";
	}

	@GetMapping(value = "boardUpdateForm")
	public String boardUpdateForm(HttpServletRequest request, Model model) {
		int cboard_no = Integer.parseInt(request.getParameter("cboard_no"));
		model.addAttribute("showUpdatePost", ykmService.getPost(cboard_no));
		return "ykm/boardUpdateForm";
	}

	@GetMapping(value = "boardPost")
	public String boardDetail(HttpServletRequest request, Model model) {
		int cboard_no = Integer.parseInt(request.getParameter("cboard_no"));
		YkmBoardComm renderPostContent = ykmService.renderPostContent(cboard_no);

		model.addAttribute("renderPostContent", renderPostContent);
		return "ykm/boardPost";
	}

	/* form */
	@PostMapping(value = "submitPostToBoard")
	public String submitPostToBoard(HttpServletRequest request, YkmBoardComm ykmBoardComm) {
		System.out.println("YkmController submitPostToBoard start---*");

		Long user_no = (Long) request.getSession().getAttribute("user_no");
		ykmBoardComm.setUser_no(user_no);
		// System.out.println("ykmBoardComm board : " + ykmBoardComm);
		int result = ykmService.insertBoardStudyPost(ykmBoardComm);
		return "redirect:/boardStudy";
	}

	@PostMapping(value = "modifyPost") // 게시글 수정 버튼을 누르면 수정이 되어야함
	public String updatePost(HttpServletRequest request, YkmBoardComm ykmBoardComm) {
		int cboard_no = Integer.parseInt(request.getParameter("cboard_no"));
		ykmBoardComm.setCboard_no(cboard_no);
		return "ykm/boardStudy";
	}

	@GetMapping(value = "boardUpdatesubmit")
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

	@GetMapping(value = "boardModify")
	public String boardModify() {
		return "ykm/boardModify";
	}
}