package com.blackberry.s20240130103.ykm.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.blackberry.s20240130103.ykm.model.YkmBoardComm;
import com.blackberry.s20240130103.ykm.model.YkmBoardCommFile;
import com.blackberry.s20240130103.ykm.model.YkmPaging;
import com.blackberry.s20240130103.ykm.service.YkmService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class YkmBoardController {

	private final YkmService ykmService;

	// 스터디 게시판
	@GetMapping(value = "boardStudy")
	public String boardStudy(YkmBoardComm ykmBoardComm, Model model, HttpServletRequest request) {

		//int comm_mid2 = request.getParameter("comm_mid2")==null ? 0 : Integer.parseInt(request.getParameter("comm_mid2")); 
		System.out.println("YkmController boardStudy start ---*");

		int totalCount = ykmService.getTotalCount(ykmBoardComm);
		YkmPaging stuPage = new YkmPaging(totalCount, ykmBoardComm.getCurrentPage());
		ykmBoardComm.setStart(stuPage.getStart());
		ykmBoardComm.setEnd(stuPage.getEnd());
		
		List<YkmBoardComm> getPostList = ykmService.getPostList(ykmBoardComm);
		model.addAttribute("stuPage", stuPage);
		model.addAttribute("comm_mid2", ykmBoardComm.getComm_mid2());
		model.addAttribute("getPostList", getPostList);
		//System.out.println("YkmController getPostList result --> "+ getPostList.size());
		return "ykm/boardStudy";
	}

	@GetMapping(value = "post")
	public String getPost(HttpServletRequest request, Model model) {
		// System.out.println("YkmController getPost start ---*");
		int cboard_no = Integer.parseInt(request.getParameter("cboard_no"));
		YkmBoardComm getPost = ykmService.getPost(cboard_no);
		
		ykmService.increseViewCount(cboard_no); // 조회수 카운트 메서드
		int countComment = ykmService.countComment(cboard_no); // 댓글 개수 카운트 메서드
		List<YkmBoardCommFile> getFileList = ykmService.getFileList(cboard_no);
		model.addAttribute("countComment",countComment);
		model.addAttribute("getPost", getPost);
		model.addAttribute("getFileList",getFileList);
		// System.out.println("YkmController getPost finish ---*");
		return "ykm/boardPost";
	}

	
	// mapping
	@GetMapping(value = "/writeForm")
	public String boardWriteForm() {
		return "ykm/boardWriteForm";
	}
	
	
	@RequestMapping(value = "writePost")
	public String writePost(HttpServletRequest request, YkmBoardComm ykmBoardComm, 
							@RequestParam("cboard_file_name") List<MultipartFile> fileList) {
		System.out.println("YkmController writePost start---*");
		
		Long user_no = (Long) request.getSession().getAttribute("user_no");
		ykmBoardComm.setUser_no(user_no);	

		// 파일 업로드
		String studyFilePath = request.getSession().getServletContext().getRealPath("/upload/studyBoardFile/");
		
		int result = ykmService.writePost(ykmBoardComm, studyFilePath, fileList);
		System.out.println("YkmController writePost ykmBoardComm fileList : "+fileList);
		System.out.println("YkmController writePost result "+result);
		
		return "redirect:/boardStudy";
	}
	
	
	@GetMapping(value = "/updateForm")
	public String updatePostForm(HttpServletRequest request, Model model) {
		int cboard_no = Integer.parseInt(request.getParameter("cboard_no"));
		System.out.println("updatePostForm updatePostForm start---*");
		YkmBoardComm getPost = ykmService.getPost(cboard_no);
		model.addAttribute("getPost", getPost);
		return "ykm/boardUpdateForm";
	}
	
	
	@RequestMapping(value = "updatePost")  
	public String updatePost(HttpServletRequest request, Model model, YkmBoardComm ykmBoardComm) {
		System.out.println("YkmController updatePost start---*");
		int cboard_no = Integer.parseInt(request.getParameter("cboard_no"));
		ykmBoardComm.setCboard_no(cboard_no);
		int updatePost = ykmService.updatePost(ykmBoardComm);
		System.out.println("YkmController updatePost result--> " + updatePost);
		model.addAttribute("updatePost", updatePost);
		return "re:boardStudy";
	}
	
	@RequestMapping(value="/deletePost")
	public String deletePost(HttpServletRequest request, Model model, YkmBoardComm ykmBoardComm) {
		System.out.println("YkmController deletePost cboard_no : " + request.getParameter("cboard_no"));
		int cboard_no = Integer.parseInt(request.getParameter("cboard_no"));
		ykmBoardComm.setCboard_no(cboard_no);
		int deletePost = ykmService.deletePost(cboard_no);
		System.out.println("YkmController deletePost result --> )" + deletePost);
		model.addAttribute("deletePost", deletePost);
		return "forward:boardStudy";
	}
	
	

	// 검색
	@PostMapping(value="/boardSearch")
	public String getSearchList(YkmBoardComm ykmBoardComm, Model model) {
		System.out.println("YkmController getSearchList start---*");
		System.out.println("YkmController getSearchList ykmBoardComm : "+ykmBoardComm);
		List<YkmBoardComm> searchResult = ykmService.getSearchList(ykmBoardComm);

		System.out.println("YkmController getSearchList searchResult : "+searchResult);
		model.addAttribute("getPostList", searchResult);
		return "ykm/boardStudy";
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