package com.blackberry.s20240130103.lsl.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.blackberry.s20240130103.lsl.Service.LslService;
import com.blackberry.s20240130103.lsl.model.LslBoardComm;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LslController {
	private final LslService ls;
	
	
	
	
	@GetMapping(value = "boardFree")
	public String boardFreeList(LslBoardComm boardComm, Model model) {
		System.out.println("LslController boardfreeList Start...");
		List<LslBoardComm> boardFreeList = ls.getBoardFreeList(boardComm);
		model.addAttribute("boardFreeList", boardFreeList);
		return "lsl/boardFree";
	}
	
	@GetMapping(value = "boardAsk")
	public String boardAsk() {
		return "lsl/boardAsk";
	}
	
	@GetMapping(value = "boardFreeWrite" )
	public String boardFreeWrite(Model model) {
		System.out.println("LslController boardFreeWrite Start...");
		model.addAttribute("LslCommReply");
		return "lsl/boardFreeWrite";
	}
	
//	@PostMapping(value = "boardFreeWrite") 
//	public String submitWriteForm (@RequestParam("Cboard_Title") String title,
//	                               @RequestParam("Cboard_File_Name") MultipartFile file,
//	                               @RequestParam("Cboard_Content") String content,
//	                               HttpServletRequest request) {
//	    HttpSession session = request.getSession();
//	    LslUser currentUser = (LslUser) session.getAttribute("user_no");
//
//	    if(currentUser == null) {
//	        return "redirect:/loginForm";
//	    }
//
//	    Long userId = currentUser.getUser_no();
//	    
//	    LslBoardComm boardComm = new LslBoardComm();
//	    boardComm.setCboard_title(title);
//	    boardComm.setCboard_content(content);
//	    boardComm.setCboard_date(new Date()); // 현재 시간 설정
//	    
//	    try {
//	        // 파일 업로드 처리
//	        if (!file.isEmpty()) {
//	            byte[] bytes = file.getBytes();
//	            Path path = Paths.get("/path/to/upload/dir/" + file.getOriginalFilename());
//	            Files.write(path, bytes);
//	            
//	            // 파일 정보 설정
//	            LslBoardCommFile boardCommFile = new LslBoardCommFile();
//	            boardCommFile.setCboard_no(boardComm.getCboard_no()); // 게시글 번호 설정
//	            boardCommFile.setCboard_file_name(file.getOriginalFilename());
//	            boardCommFile.setCboard_file_cnt(0);
//	            // 파일 정보를 데이터베이스에 저장하는 서비스 메서드 호출
//	            ls.saveBoardFile(boardCommFile);
//	        }
//	        
//	        // 게시글 정보를 데이터베이스에 저장
//	        ls.saveBoard(boardComm);
//	        
//	        // 게시판으로 리다이렉트
//	        return "redirect:/boardFree";
//	    } catch (IOException e) {
//	        e.printStackTrace();
//	        // IOException 처리
//	        return "redirect:/boardFree"; 
//	    }
//	}



	 
	
	@GetMapping(value = "boardFreeContents" )
	public String boardFreeContents() {
		return "lsl/boardFreeContents";
	}
	
	@GetMapping(value = "boardFreeModify")
	public String boardFreeModify() {
		return "lsl/boardFreeModify";
	}
}
