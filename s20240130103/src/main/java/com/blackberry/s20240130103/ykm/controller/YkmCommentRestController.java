package com.blackberry.s20240130103.ykm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.blackberry.s20240130103.ykm.model.YkmBoardComm;
import com.blackberry.s20240130103.ykm.model.YkmBoardCommReply;
import com.blackberry.s20240130103.ykm.service.YkmService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class YkmCommentRestController {

	private final YkmService ykmService;
	
	
	/* 댓글 comment */
	// 로그인 한 유저 정보
	@GetMapping("/userNo")
	public Long getUserNo(HttpServletRequest request) {
		System.out.println("YkmCommentRestController getUserNo start ---*");
		Long result = (Long)request.getSession().getAttribute("user_no"); 
		System.out.println("YkmCommentRestController result -->" +result);
		return result;
	}
	
	
	@GetMapping("/comment")
	public List<YkmBoardCommReply> getCommentList(@RequestParam("cboard_no") int cboard_no) {
		System.out.println("YkmBoardReplyRestController getCommentList start ---*");
		List<YkmBoardCommReply> getCommentList = ykmService.getCommentList(cboard_no);
		System.out.println("YkmBoardReplyRestController getCommentList --> " + getCommentList.size());
		return getCommentList;
	}

	@PostMapping("/comment")
	public int writeComment(@RequestBody YkmBoardCommReply ykmBoardCommReply, HttpServletRequest request) {
		Long user_no = (Long)request.getSession().getAttribute("user_no");
	    ykmBoardCommReply.setUser_no(user_no);
		return ykmService.writeComment(ykmBoardCommReply);
	}

	@DeleteMapping("/comment/{creply_no}")
	public int deleteComment(@PathVariable("creply_no") int creply_no) {
		System.out.println("deletemapping creply_no : " + creply_no);
		int result = ykmService.deleteComment(creply_no);
		System.out.println("YkmBoardReplyRestController deleteComment ===> "+result);
		return result;
	}

	@PutMapping("/comment")
	public int updateComment(@RequestBody YkmBoardCommReply ykmBoardCommReply) {
		return ykmService.updateComment(ykmBoardCommReply);
	}

	
	
	/* study post */
	
	@GetMapping(value = "/postWriter/{cboard_no}")
	public int getPostWriter(@PathVariable("cboard_no") int cboard_no) {
		System.out.println("YkmRestController getPostWriter start---*");
		return ykmService.getPostWriter(cboard_no);
	}
	
	@PutMapping(value = "/recruitment")
	public int updateRecruitment(@RequestBody YkmBoardComm ykmBoardComm) {
		System.out.println("YkmRestController updateRecruitment start---*");
		return ykmService.updateRecruitment(ykmBoardComm);
	}
	
	@GetMapping(value="/recruitment/{cboard_no}")
	public Map<String, Integer> getRecruitment(@PathVariable("cboard_no") int cboard_no) {
		System.out.println("YkmRestController getRecruitment start---*");
		int getRecruitment = ykmService.getRecruitment(cboard_no);

        Map<String, Integer> resultMap = new HashMap<>();
        resultMap.put("cboard_no", cboard_no);
        resultMap.put("comm_mid2", getRecruitment);

        return resultMap;
	}
	
	
	/*
	 댓글 : comment
	 CommentService, CommentServiceImpl, CommentDao, CommentDaoImpl, CommentController
	 getCommentList
	 getComment
	 writeComment
	 deleteComment
	 updateComment
	 */

}
