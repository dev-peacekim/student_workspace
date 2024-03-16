package com.blackberry.s20240130103.lsl.controller;

import java.util.List;

import org.springframework.ui.Model;
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
	@GetMapping("/reply")
	public List<LslCommReply> replyBoardFreeAskList(@RequestParam("cboard_no") int cboard_no,HttpServletRequest request, Model model) {
	    System.out.println("LslRestController replyBoardFreeList Start..");
	    List<LslCommReply> replyBoardFreeAskList = ls.replyBoardFreeAskList(cboard_no);
	    System.out.println("LslRestController replyBoardFreeList.size() ->" + replyBoardFreeAskList.size());
	    
	   
	    // 댓글 목록에 댓글 번호를 추가
	    for (LslCommReply reply : replyBoardFreeAskList) {
	        // 각 댓글의 사용자 프로필을 가져옴
	        String userProfile = reply.getUser_profile();
	        System.out.println("userProfile : " + userProfile);
	        
	        // 필요한 작업 수행
	        
	        // 댓글 번호를 가져와서 설정
	        reply.setCreply_no(reply.getCreply_no());
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
	  System.out.println("test : " + lslCommReply);
//	    
	  	// insert = 레벨이랑 인덴트 +1씩
	  	
	  
	    int cPreply_group = lslCommReply.getCreply_group();
	    int cboard_no = lslCommReply.getCboard_no();
	    int creply_indent = lslCommReply.getCreply_indent();
	    int creply_level = lslCommReply.getCreply_level();
	    int creply_no = lslCommReply.getCreply_no();
	    String user_profile = lslCommReply.getUser_profile();
	    //String parent_user_id = lslCommReply.getParent_user_id();
	    
	
	   
		   
		   
    System.out.println("reinsertBoardReply creply_group" + cPreply_group);
	    System.out.println("reinsertBoardReply user_no" + user_no);
    
	    lslCommReply.setCreply_no(creply_no);
	    lslCommReply.setUser_no(user_no);
	    lslCommReply.setCboard_no(cboard_no);
	    lslCommReply.setCreply_group(cPreply_group);
	    lslCommReply.setCreply_indent(creply_indent +1);
	    lslCommReply.setUser_profile(user_profile);
	    //lslCommReply.setCreply_level(creply_level + 1);
	    //lslCommReply.setParent_user_id(parent_user_id);
	    
	    int boardReReplyResult = ls.insertBoardReReply(lslCommReply);
	    
	    System.out.println("reinsertBoardReply lslCommReply" + lslCommReply);
	    
	    //  update = 내가 쓴 부모댓글에 해당하는 글들에서 대댓글 레벨 보다 큰 애들 +1
	    
	    // 대댓글 리스트 
	    
//	    List<LslCommReply> repliesAfterParent = ls.getRepliesAfterParent(cPreply_group, creply_no);
//	    
//	    for (LslCommReply reply : repliesAfterParent) {
//	        // 대댓글의 레벨보다 큰 애들의 레벨을 +1로 업데이트
//	        if (reply.getCreply_level() > creply_level) {
//	            reply.setCreply_level(reply.getCreply_level() + 1);
//	            ls.updateReply(reply); // 업데이트 메서드 호출
//	        }
//	    }

	  return boardReReplyResult;
	}
	

	
	
	// 대대댓글 업데이트 
	// 사용자 권한 막기
	
	

}
