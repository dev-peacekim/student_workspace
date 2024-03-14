package com.blackberry.s20240130103.lsl.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blackberry.s20240130103.lsl.Service.LslService;
import com.blackberry.s20240130103.lsl.model.LslCommReply;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LslRestController {
	
	private final LslService ls;
	
	
	@GetMapping("/userno")
	public Long boardUserNo(HttpServletRequest request) {
	    Long user_no = (Long) request.getSession().getAttribute("user_no");
	    System.out.println("sessionUseNo ->" + user_no);
	    return user_no;
	}
	
	
	
	@GetMapping("/getUserInfo")
	public String getUserNic(HttpServletRequest request) {
		String user_nic = (String)request.getSession().getAttribute("user_nic");
		return user_nic;
		
	}
	
	// 댓글
	// 게시판 댓글 리스트
	   @GetMapping("/replys")
		public List<LslCommReply> replyBoardFreeAskList(@RequestParam("cboard_no") int cboard_no) {
	    	System.out.println("LslRestController replyBoardFreeList Start..");
			List<LslCommReply> replyBoardFreeAskList = ls.replyBoardFreeAskList(cboard_no);
			System.out.println("LslRestController replyBoardFreeList.size() ->" + replyBoardFreeAskList.size());
			 // 댓글 목록에 댓글 번호를 추가
	        for (LslCommReply reply : replyBoardFreeAskList) {
	            reply.setCreply_no(reply.getCreply_no()); // 댓글 번호를 가져와서 설정
	        }
	        
	        return replyBoardFreeAskList;
		   }
   
    
   //  게시판 댓글 등록 
	@PostMapping("/replys/insert")
	public int insertBoardReply(@RequestBody LslCommReply lslCommReply, HttpServletRequest request) {
		Long user_no = (Long) request.getSession().getAttribute("user_no");
		System.out.println("LslRestController insertBoardReply Start....");
		lslCommReply.setUser_no(user_no);
		int boardFreeAskResult = ls.insertBoardReply(lslCommReply);
		System.out.println("LslRestController boardFreeAskResult ->" + boardFreeAskResult);
		return boardFreeAskResult;
	}
	
	// 게시판 댓글 삭제 
	@DeleteMapping("/replys/delete")
	public int deleteBoardReply(@RequestParam("creply_no") int creply_no ) {
		System.out.println("LslRestController deleteBoardReply Start....");
		int boardFreeAskResult = ls.deleteBoardReply(creply_no);
		
		return boardFreeAskResult;
	}
	
	@PutMapping("/replys/modify")
	public int modifyBoardReply(@RequestBody LslCommReply lslCommReply) {
		System.out.println("modifyBoardReply : " + lslCommReply);
		return ls.modifyBoardReply(lslCommReply);
	}
	
	
	// 대댓글 

	
	// 
	// 대댓글 등록
	@PostMapping("/rereplys/reinsert")
	public int reinsertBoardReply(@RequestBody LslCommReply lslCommReply, HttpServletRequest request) {
	    Long user_no = (Long) request.getSession().getAttribute("user_no");
	    System.out.println("reinsertBoardReply user_no" + user_no);
	  
	    
	    int Pcreply_no = lslCommReply.getCreply_no();
	    int cboard_no = lslCommReply.getCboard_no();
	    
	    System.out.println("reinsertBoardReply creply_group" + Pcreply_no);
	    System.out.println("reinsertBoardReply user_no" + user_no);
	    
	    lslCommReply.setUser_no(user_no);
	    lslCommReply.setCboard_no(cboard_no);
	    lslCommReply.setCreply_group(Pcreply_no);
	    
	    System.out.println("reinsertBoardReply lslCommReply" + lslCommReply);
	    
	    int boardReReplyResult = ls.insertBoardReReply(lslCommReply);
	    
	    return boardReReplyResult;
	}
	
	
	
	

}
