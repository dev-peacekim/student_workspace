package com.blackberry.s20240130103.lsl.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blackberry.s20240130103.lsl.Service.LslService;
import com.blackberry.s20240130103.lsl.model.LslCommReply;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LslRestController {
	
	private final LslService ls;
	
	
	// 게시글 
	
	
	
	
	
	
	// 댓글
	
	// 자유게시판 댓글 리스트
   @GetMapping("/reply/listAll")
	public List<LslCommReply> replyBoardFreeList( @RequestParam("cboard_no") int cboard_no) {
    	System.out.println("LslRestController replyBoardFreeList Start..");
		List<LslCommReply> replyBoardFreeList = ls.replyBoardFreeList(cboard_no);
		System.out.println("LslRestController replyBoardFreeList.size() ->" + replyBoardFreeList.size());
		
	
	return replyBoardFreeList;
   }
    
	@PostMapping("/replys")
	public int insertBoardReply(@RequestBody LslCommReply lslCommReply) {
		System.out.println("LslRestController insertBoardReply Start....");
		int boardFreeAskResult = ls.insertBoardReply(lslCommReply);
		System.out.println("LslRestController boardFreeAskResult ->" + boardFreeAskResult);
		return boardFreeAskResult;
	}
	
	
	
	
	
	

}
