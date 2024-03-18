package com.blackberry.s20240130103.ykm.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.blackberry.s20240130103.ykm.model.YkmBoardComm;
import com.blackberry.s20240130103.ykm.model.YkmBoardCommFile;
import com.blackberry.s20240130103.ykm.model.YkmPaging;
import com.blackberry.s20240130103.ykm.service.YkmService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class YkmBoardController {

	private final YkmService ykmService;

	
	// 글 수정할 때 글쓴 사용자만 수정할 수 있게 권한 체크
	
	/* 스터디 게시판 */
	
	// 스터디 게시판 리스트 조회, 페이징 카운트
	@GetMapping(value = "boardStudy")
	public String boardStudy(YkmBoardComm ykmBoardComm, Model model) {

		System.out.println("YkmController boardStudy start ---*");
		
		int totalCount = ykmService.getTotalCount(ykmBoardComm);
		YkmPaging stuPage = new YkmPaging(totalCount, ykmBoardComm.getCurrentPage());
		ykmBoardComm.setStart(stuPage.getStart());
		ykmBoardComm.setEnd(stuPage.getEnd());
		
		List<YkmBoardComm> getPostList = ykmService.getPostList(ykmBoardComm);
		model.addAttribute("stuPage", stuPage);
		model.addAttribute("comm_mid2", ykmBoardComm.getComm_mid2());
		model.addAttribute("getPostList", getPostList);
		model.addAttribute("totalCount", totalCount);
		return "ykm/boardStudy";
	}

	// 게시글 보여주기
	@GetMapping(value = "/post")
	public String getPost(HttpServletRequest request, Model model) {
		// System.out.println("YkmController getPost start ---*");
		int cboard_no = Integer.parseInt(request.getParameter("cboard_no"));
		YkmBoardComm getPost = ykmService.getPost(cboard_no);
		
		ykmService.increseViewCount(cboard_no); // 조회수 카운트 메서드
		int countComment = ykmService.countComment(cboard_no); // 댓글 개수 카운트 메서드

		List<YkmBoardCommFile> getFileList = ykmService.getFileList(cboard_no);
		for(YkmBoardCommFile file : getFileList) {
			System.out.println("test : " + file);
		}
		model.addAttribute("countComment",countComment);
		model.addAttribute("getPost", getPost);
		model.addAttribute("getFileList", getFileList); // 업로드한 파일 목록 보여주기
		System.out.println("getFileList 파일리스트 : "+getFileList);

		System.out.println("YkmController getPost finish ---*");
		
		return "ykm/boardPost";
	}

	
	// 글 작성 시 게시판 분류
	@GetMapping(value = "/writeForm")
	public String boardWriteForm(YkmBoardComm ykmBoardComm, Model model) {
		model.addAttribute("comm_mid", ykmBoardComm.getComm_mid());
		model.addAttribute("comm_big", ykmBoardComm.getComm_big());
		return "ykm/boardWriteForm";
	}
	
	// 글 작성
	@RequestMapping(value = "writePost")
	public String writePost(HttpServletRequest request, YkmBoardComm ykmBoardComm, 
							@RequestParam("cboard_file_name") List<MultipartFile> fileList,RedirectAttributes redirect) {
		System.out.println("YkmController writePost start---*");
		Long user_no = (Long) request.getSession().getAttribute("user_no");
		ykmBoardComm.setUser_no(user_no);	

		// 게시판 분류
		int comm_big = ykmBoardComm.getComm_big();
		int comm_mid = ykmBoardComm.getComm_mid();
		
		// 파일 업로드
		String studyFilePath = request.getSession().getServletContext().getRealPath("/upload/studyBoardFile/");

		redirect.addAttribute("comm_mid", comm_mid);
		redirect.addAttribute("comm_mid", comm_big);
		
		int result = ykmService.writePost(ykmBoardComm, studyFilePath, fileList);
		
		if (comm_big == 200 && comm_mid == 10) {
			System.out.println("YkmController writePost result "+result);
			return "redirect:/boardContest";
			
		} else if (comm_big == 200 && comm_mid == 20) {
			return "redirect:/boardStudy";
		}
		return "ykm/boardWriteForm";
	}
	
	// 글 수정 폼
	@GetMapping(value = "/updateForm")
	public String UpdatePostForm(HttpServletRequest request, Model model) {
		System.out.println("updatePostForm updatePostForm start---*");
		int cboard_no = Integer.parseInt(request.getParameter("cboard_no"));
		YkmBoardComm getPost = ykmService.getPost(cboard_no);
		List<YkmBoardCommFile> getFileList = ykmService.getFileList(cboard_no);
		model.addAttribute("getPost", getPost);
		model.addAttribute("getFileList", getFileList);
		
		// 로그인 유저
		Long currentUserNo = (Long) request.getSession().getAttribute("user_no");
		// 글 작성자
		Long postWriter = getPost.getUser_no();

		// 권한 체크 (글 작성자와 로그인 유저가 같을 경우엔 페이지 이용 가능, 아니라면 로그인 폼으로 이동)
		if (getPost != null && currentUserNo !=null && currentUserNo.equals(postWriter)) {
			return "ykm/boardUpdateForm";
		} else {
			return "lhs/loginForm";
		}
	}
	
	// 글 수정
	@RequestMapping(value = "updatePost")  
	public String updatePost(HttpServletRequest request, Model model, YkmBoardComm ykmBoardComm) {
		System.out.println("YkmController updatePost start---*");
		int cboard_no = Integer.parseInt(request.getParameter("cboard_no"));
		ykmBoardComm.setCboard_no(cboard_no);
		int updatePost = ykmService.updatePost(ykmBoardComm);
		System.out.println("YkmController updatePost result--> " + updatePost);
		model.addAttribute("updatePost", updatePost);
		
		return "redirect:/boardStudy";
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
		List<YkmBoardComm> searchResult = ykmService.getSearchList(ykmBoardComm);

		System.out.println("YkmController getSearchList searchResult : "+searchResult);
		model.addAttribute("getPostList", searchResult);
		return "ykm/boardStudy";
	}
	
	// 파일 다운로드
	@GetMapping(value="/fileDownload")
	public void getDownloadFile(@RequestParam("cboard_file_name") String cboard_file_name, 
								@RequestParam("cboard_file_user_name") String cboard_file_user_name,
								HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("YkmController getDownloadFile start---*");

		try {
		YkmBoardCommFile ykmBoardCommFile = new YkmBoardCommFile();
		ykmBoardCommFile.setCboard_file_name(cboard_file_name);
		ykmBoardCommFile.setCboard_file_user_name(cboard_file_user_name);
					
		System.out.println("YkmController getDownloadFile ykmBoardCommFile : "+ykmBoardCommFile);
	    String getFilePath = request.getSession().getServletContext().getRealPath("/upload/studyBoardFile/")+ykmBoardCommFile.getCboard_file_name();
		System.out.println("YkmController getDownloadFile getFilePath : "+getFilePath);
	   
		File file = new File(getFilePath);
		
	    if (file.exists()) {
	    	String mimeType = URLConnection.guessContentTypeFromName(file.getName());
	    	if (mimeType == null) {
	    	    mimeType = "application/octet-stream";
	    	}
	    
    	response.setContentType(mimeType);
    	response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(ykmBoardCommFile.getCboard_file_user_name(), "UTF-8") + "\"");	
    	response.setContentLength((int) file.length());

    	InputStream is = new BufferedInputStream(new FileInputStream(file));
    	FileCopyUtils.copy(is, response.getOutputStream());
	 } 
	    
	} catch (IOException e) {
		e.printStackTrace();
		System.out.println("YkmController getDownloadFile error+ " +e.getMessage());
	}
}

	/* 공모전 게시판 */
	
	// 공모전 게시글 리스트 조회
	@GetMapping(value = "boardContest")
	public String boardContest(YkmBoardComm ykmBoardComm, Model model, HttpServletRequest request) {
		System.out.println("YkmController boardContest start ---*");

		int CnttotalCount = ykmService.getCntTotalCount(ykmBoardComm);
		YkmPaging stuPage = new YkmPaging(CnttotalCount, ykmBoardComm.getCurrentPage());
		ykmBoardComm.setStart(stuPage.getStart());
		ykmBoardComm.setEnd(stuPage.getEnd());
	
		List<YkmBoardComm> getCntPostList = ykmService.getCntPostList(ykmBoardComm);
		model.addAttribute("stuPage", stuPage);
		model.addAttribute("getCntPostList", getCntPostList);
		
		return "ykm/boardContest";
	}
	
	// 검색
	@PostMapping(value="boardCntSearch")
	public String getCntSearchList(YkmBoardComm ykmBoardComm, Model model) {
		System.out.println("YkmController getCntSearchList start---*");
		List<YkmBoardComm> searchResult = ykmService.getCntSearchList(ykmBoardComm);
		model.addAttribute("getCntPostList", searchResult);
		
		return "ykm/boardContest";
	}

}