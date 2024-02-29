package com.blackberry.s20240130103.kdw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	/* ========== 쪽지함 ========== */
	
	/* ========== 받은 쪽지함 리스트 불러오기 ========== */
	@GetMapping(value = "msgReceivebox")
	public String getReceivedMessages(HttpServletRequest request, Model model,
	        @RequestParam(name = "currentPage", defaultValue = "1") String currentPage) {
	    log.info("MsgController getReceivedMessages start...");

	    // HttpSession에서 로그인한 사용자 정보 가져오기 (쪽지 받는 사람)
	    Long msgReceiver = (Long) request.getSession().getAttribute("user_no");

	    // 받은 쪽지 수 가져오기
	    int totReceiveMsgCnt = msgService.totReceiveMsgCnt(msgReceiver);

	    // 페이징 처리
	    MsgPaging page = new MsgPaging(totReceiveMsgCnt, currentPage);

	    // 로그인한 사용자 정보를 기반으로 받은 쪽지 목록 가져오기 (페이징 정보 전달)
	    List<Message> receivedMessages = msgService.getReceivedMessages(msgReceiver, page.getStart(), page.getEnd());

	    // 'Model'에 받은 쪽지 목록과 페이징 정보를 담아서 전달
	    model.addAttribute("totalMsgReceive", totReceiveMsgCnt);
	    model.addAttribute("receivedMessages", receivedMessages);
	    model.addAttribute("page", page);

	    return "kdw/msgReceivebox";
	}
	/* ========== 보낸 쪽지함 리스트 불러오기 ========== */
	@GetMapping(value = "msgSendbox")
	public String getSendMessages(HttpServletRequest request, Model model,
	        @RequestParam(name = "currentPage", defaultValue = "1") String currentPage) {
	    log.info("MsgController getSendMessages start...");

	    // HttpSession에서 로그인한 사용자 정보 가져오기 (쪽지 받는 사람)
	    Long msgSender = (Long) request.getSession().getAttribute("user_no");

	    // 보낸 쪽지 수 가져오기 (totMsgCnt 메서드를 이용)
	    int totSentMsgCnt = msgService.totSentMsgCnt(msgSender);

	    // 페이징 처리
	    MsgPaging page = new MsgPaging(totSentMsgCnt, currentPage);

	    // 로그인한 사용자 정보를 기반으로 보낸 쪽지 목록 가져오기 (getSentMessages 메서드 사용)
	    List<Message> sentMessages = msgService.getSentMessages(msgSender, page.getStart(), page.getEnd());

	    // 'Model'에 보낸 쪽지 목록과 페이징 정보를 담아서 전달
	    model.addAttribute("totSentMsgCnt", totSentMsgCnt);
	    model.addAttribute("sentMessages", sentMessages);
	    model.addAttribute("page", page);

	    return "kdw/msgSendbox";
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
