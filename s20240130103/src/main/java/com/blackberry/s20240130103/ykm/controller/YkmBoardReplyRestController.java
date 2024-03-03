package com.blackberry.s20240130103.ykm.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blackberry.s20240130103.ykm.model.YkmBoardCommReply;
import com.blackberry.s20240130103.ykm.service.YkmService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class YkmBoardReplyRestController {
	
	/*
	 게시물 : post 
	 PostService, PostServiceImpl, PostDao, PostDaoImpl, PostController
	 getPostList : 게시물 목록 가져오기 
	 getPost : 게시물 한건 가져오기
	 writePost : 게시물 등록
	 deletePost : 게시물 삭제
	 updatePost : 게시물 수정
	 
	 댓글 : comment
	 CommentService, CommentServiceImpl, CommentDao, CommentDaoImpl, CommentController
	 getCommentList
	 getComment
	 writeComment
	 deleteComment
	 updateComment
	 
	 */

	private final YkmService ykmService;
	
	
	@GetMapping("/board/post")
	public List<YkmBoardCommReply> renderReplyList(@RequestParam("cboard_no") int cboard_no) {
		System.out.println("YkmBoardReplyRestController renderReplyList start ---*");
		List<YkmBoardCommReply> renderReplyList = ykmService.renderReplyList(cboard_no);
		System.out.println("YkmBoardReplyRestController renderReplyList --> " + renderReplyList.size());
		return renderReplyList;
	}

	@PostMapping("/comment")
	public int insertComment(@RequestBody YkmBoardCommReply ykmBoardCommReply) {
		int result = ykmService.insertComment(ykmBoardCommReply);
		return result;
	}

	@DeleteMapping("/comment")
	public int deleteComment(@RequestParam("creply_no") int creply_no) {
		int result = ykmService.deleteComment(creply_no);
		return result;
	}

	@PutMapping("/comment")
	public int updateComment(@RequestBody YkmBoardCommReply ykmBoardCommReply) {
		int result = ykmService.updateComment(ykmBoardCommReply);
		return result;
	}
}
