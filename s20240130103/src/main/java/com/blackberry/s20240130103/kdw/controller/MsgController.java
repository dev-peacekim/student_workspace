package com.blackberry.s20240130103.kdw.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.blackberry.s20240130103.kdw.model.Message;
import com.blackberry.s20240130103.kdw.service.MsgPaging;
import com.blackberry.s20240130103.kdw.service.MsgService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Controller
@RequiredArgsConstructor
@Slf4j
public class MsgController {
	
	private final MsgService msgService;
	
	/* ========== Side Bar view 이동 =========== */
	
	/*
	 * // 받은 쪽지함 view 이동
	 * 
	 * @GetMapping(value = "msgReceivebox") public String kdwReceivebox() { return
	 * "kdw/msgReceivebox"; }
	 */
	
	// 보낸 쪽지함 view 이동
	@GetMapping(value = "msgSendbox")
	public String kdwMsgSendbox() {
		return "kdw/msgSendbox";
	}
	// 쪽지 보관함 view 이동
	@GetMapping(value = "msgStorebox")
	public String kdwMsgStorebox() {
		return "kdw/msgStorebox";      
	}
	// 휴지통 view 이동
	@GetMapping(value = "msgTrashbox")
	public String kdwMsgTrashbox() {
		return "kdw/msgTrashbox";
	}
	
	/* ========== Side Bar End =========== */
	
	/* ========== 받은 메세지 리스트 불러오기 ========== */
	@GetMapping(value = "msgReceivebox")
	public String getReceivedMessages(HttpServletRequest request, Model model,
	        @RequestParam(name = "currentPage", defaultValue = "1") String currentPage) {
	    log.info("MsgController getReceivedMessages start...");

	    // 전체 쪽지 수 가져오기
	    int totalMsgReceive = msgService.totalMsgReceive();

	    // 페이징 처리
	    MsgPaging page = new MsgPaging(totalMsgReceive, currentPage);

	    // HttpSession에서 로그인한 사용자 정보 가져오기 (쪽지 보내는 사람)
	    Long msgReceiver = (Long) request.getSession().getAttribute("user_no");

	    // 로그인한 사용자 정보를 기반으로 받은 쪽지 목록 가져오기 (페이징 정보 전달)
	    List<Message> receivedMessages = msgService.getReceivedMessages(msgReceiver, page.getStart(), page.getEnd());

	    // 'Model'에 받은 쪽지 목록과 페이징 정보를 담아서 전달
	    model.addAttribute("totalMsgReceive", totalMsgReceive);
	    model.addAttribute("receivedMessages", receivedMessages);
	    model.addAttribute("page", page);

	    return "kdw/msgReceivebox";
	}
	

    // 쪽지쓰기 버튼 클릭시 쪽지쓰기 view 이동
    @GetMapping(value = "msgWrite")
    public String kdwWriteMessagePage() {
        return "kdw/msgWrite"; 
    }
    
    
    /* ========== 받은편지함 버튼 기능 구현 =========== */
    
    // 쪽지 보내기 메서드
    @PostMapping(value = "msgSent")
    public String kdwSendMessage(Message message) {
    	log.info("MsgController sendMessage start...");
        msgService.sendMessage(message);
        return "kdw/msgSent";
    }
    /* ========== 받은편지함 버튼 기능 구현 END =========== */
    
	
}
