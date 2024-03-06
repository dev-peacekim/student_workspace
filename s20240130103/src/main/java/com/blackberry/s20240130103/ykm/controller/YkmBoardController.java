package com.blackberry.s20240130103.ykm.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.blackberry.s20240130103.ykm.model.YkmBoardComm;
import com.blackberry.s20240130103.ykm.service.YkmService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class YkmBoardController {

	private final YkmService ykmService;

	// 스터디 게시판
	@RequestMapping(value = "boardStudy")
	public String getPostList(YkmBoardComm ykmBoardComm, Model model) {
		// System.out.println("YkmController getPostList start ---*");
		List<YkmBoardComm> getPostList = ykmService.getPostList();
		model.addAttribute("getPostList", getPostList);
		// System.out.println("YkmController getPostList result --> "+ getPostList.size());
		return "ykm/boardStudy";
	}
	
	@GetMapping(value = "post")
	public String getPost(HttpServletRequest request, Model model) {
		// System.out.println("YkmController getPost start ---*");
		int cboard_no = Integer.parseInt(request.getParameter("cboard_no"));
		YkmBoardComm getPost = ykmService.getPost(cboard_no);
		
		// 여기서 increseViewCount 호출 안했넹~
		ykmService.increseViewCount(cboard_no); // 다시 해보삼
		
		// int getViewCount = ykmService.getViewCount(cboard_no); // ??? 이해 안가는 코드임 그.. getpost를 할때 조회수를 증가시키는거 아닌가요..
		// 아니지 여기서는 게시물을 가져오는거야 즉 게시물 보기 화면이지 그러니까 여기서 조회수를 증가시켜야지
		model.addAttribute("getPost", getPost);
		// model.addAttribute("getViewCount", getViewCount); // 이건 필요없는 코드고
		
		// 서비스부터 하나씩 볼께~
		
		// model.addAttribute("getViewCount", getViewCount);
		// System.out.println("YkmController getPost finish ---*");
		return "ykm/boardPost";
	}

	// mapping
	@GetMapping(value = "/writeForm")
	public String boardWriteForm() {
		return "ykm/boardWriteForm";
	}
	
	
	@RequestMapping(value = "writePost")
	public String writePost(HttpServletRequest request, YkmBoardComm ykmBoardComm) {
		System.out.println("YkmController writePost start---*");
		Long user_no = (Long) request.getSession().getAttribute("user_no");
		System.out.println("유저" + user_no);
		ykmBoardComm.setUser_no(user_no);
		int result = ykmService.writePost(ykmBoardComm);
		System.out.println("YkmController writePost result"+result);
		return "forward:boardStudy";
	}
	
	
	@GetMapping(value = "/updateForm")
	public String updatePostForm(HttpServletRequest request, Model model) {
		int cboard_no = Integer.parseInt(request.getParameter("cboard_no"));
		System.out.println("updatePostForm updatePostForm start---*");
		YkmBoardComm getPost = ykmService.getPost(cboard_no);
		model.addAttribute("getPost", getPost);
		return "ykm/boardUpdateForm";
	}
	
	
	@RequestMapping(value = "updatePost") // 게시글 수정 버튼을 누르면 수정이 되어야함
	public String updatePost(HttpServletRequest request, Model model, YkmBoardComm ykmBoardComm) {
		System.out.println("YkmController updatePost start---*");
		int cboard_no = Integer.parseInt(request.getParameter("cboard_no"));
		ykmBoardComm.setCboard_no(cboard_no);
		int updatePost = ykmService.updatePost(ykmBoardComm);
		System.out.println("YkmController updatePost result--> " + updatePost);
		model.addAttribute("updatePost", updatePost);
		return "forward:boardStudy";
	}
	
	@RequestMapping(value="/deletePost")
	public String deletePost(HttpServletRequest request, Model model, YkmBoardComm ykmBoardComm) {
		System.out.println("controller deletePost cboard_no : " + request.getParameter("cboard_no"));
		int cboard_no = Integer.parseInt(request.getParameter("cboard_no"));
		ykmBoardComm.setCboard_no(cboard_no);
		int deletePost = ykmService.deletePost(cboard_no);
		System.out.println("YkmController deletePost result --> " + deletePost);
		model.addAttribute("deletePost", deletePost);
		return "forward:boardStudy";
	}
	
	
	
	
	/*
	 게시물 : post 
	 PostService, PostServiceImpl, PostDao, PostDaoImpl, PostController
	 getPostList : 게시물 목록 가져오기 
	 getPost : 게시물 한건 가져오기
	 writePost : 게시물 등록
	 deletePost : 게시물 삭제
	 updatePost : 게시물 수정
	 */

	
	
	
	// 공모전 게시판
	@GetMapping(value = "boardContest")
	public String boardContest() {
		return "ykm/boardContest";
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