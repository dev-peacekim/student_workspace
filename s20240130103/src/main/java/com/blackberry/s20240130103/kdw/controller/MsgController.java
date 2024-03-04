package com.blackberry.s20240130103.kdw.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.blackberry.s20240130103.kdw.model.Message;
import com.blackberry.s20240130103.kdw.model.MessageFile;
import com.blackberry.s20240130103.kdw.service.MsgPaging;
import com.blackberry.s20240130103.kdw.service.MsgService;
import com.blackberry.s20240130103.lhs.model.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Controller
@RequiredArgsConstructor
@Slf4j
public class MsgController {
	
	@Autowired
	private final MsgService msgService;


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
	    model.addAttribute("msgReceiver", msgReceiver);
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
	
	// 쪽지 보관함 리스트 가져오기
	@GetMapping(value = "msgStorebox")
	public String getStoreboxMessages(HttpServletRequest request, Model model,
	        @RequestParam(name = "currentPage", defaultValue = "1") String currentPage) {
	    log.info("MsgController getStoreboxMessages start...");
	    // HttpSession에서 로그인한 사용자 정보 가져오기 (전체 쪽지)
	    Long storeboxUserNo = (Long) request.getSession().getAttribute("user_no");

	    // msg_store_chk 값이 1인 쪽지들만 가져오기
	    int totStoredMsgCnt = msgService.totStoredMsgCnt(storeboxUserNo);
	    // 페이징 처리
	    MsgPaging page = new MsgPaging(totStoredMsgCnt, currentPage);
	    // 로그인한 사용자 정보를 기반으로 보낸 쪽지 목록 가져오기
	    List<Message> storedMessages = msgService.getStoredMessages(storeboxUserNo, page.getStart(), page.getEnd());

	    // 'Model'에 보관된 쪽지 목록과 페이징 정보를 담아서 전달
	    model.addAttribute("totStoredMsgCnt", totStoredMsgCnt);
	    model.addAttribute("storedMessages", storedMessages);
	    model.addAttribute("page", page);
	    model.addAttribute("storeboxUserNo", storeboxUserNo);

	    return "kdw/msgStorebox";
	}
	
    // 휴지통 리스트 가져오기
    @GetMapping(value = "msgTrashbox")
    public String getTrashboxMessages(HttpServletRequest request, Model model,
            @RequestParam(name = "currentPage", defaultValue = "1") String currentPage) {
        log.info("MsgController getTrashboxMessages start...");
        // HttpSession에서 로그인한 사용자 정보 가져오기 (휴지통 쪽지)
        Long trashboxUserNo = (Long) request.getSession().getAttribute("user_no");

        // msg_trash_chk 값이 1인 쪽지들만 가져오기
        int totTrashMsgCnt = msgService.totTrashMsgCnt(trashboxUserNo);
        // 페이징 처리
        MsgPaging page = new MsgPaging(totTrashMsgCnt, currentPage);
        // 로그인한 사용자 정보를 기반으로 휴지통 쪽지 목록 가져오기
        List<Message> trashMessages = msgService.getTrashMessages(trashboxUserNo, page.getStart(), page.getEnd());

        // 'Model'에 보관된 휴지통 쪽지 목록과 페이징 정보를 담아서 전달
        model.addAttribute("totTrashMsgCnt", totTrashMsgCnt);
        model.addAttribute("trashMessages", trashMessages);
        model.addAttribute("page", page);
        model.addAttribute("trashboxUserNo", trashboxUserNo);

        return "kdw/msgTrashbox";
    }
    
    // ========== 받은 쪽지 읽기 & 보낸 쪽지 읽기  ========== 
    
    // 받은쪽지 읽기('msg_readdate'가 'null'이면 'msg_readdate'업데이트)
    @GetMapping(value = "msgReadReceived")
    public String readReceivedMessageInfo(@RequestParam("msg_no") Long msgNo, Model model) {
        log.info("MsgController readReceivedMessage start...");

        // msgNo를 사용하여 해당 쪽지 정보 가져오기 및 읽은 시간 업데이트
        Message receivedMessageInfo = msgService.getReceivedMessageByInfo(msgNo);

        model.addAttribute("receivedMessageInfo", receivedMessageInfo);
        log.info("MsgController readSentMessage receivedMessageInfo => " + receivedMessageInfo);
        // 쪽지 읽기 페이지로 이동
        return "kdw/msgReadReceived";
    }
    
    // 보낸쪽지 읽기('msg_readdate'가 'null'이면 'msg_readdate'업데이트)
    @GetMapping(value = "msgReadSent")
    public String readSentMessageInfo(@RequestParam("msg_no") Long msgNo, Model model) {
        log.info("MsgController readSentMessage start...");

        // msgNo를 사용하여 해당 쪽지 정보 가져오기 및 읽은 시간 업데이트
        Message sentMessageInfo = msgService.getSentMessageByInfo(msgNo);

        model.addAttribute("sentMessageInfo", sentMessageInfo);
        log.info("MsgController readSentMessage sentMessageInfo => " + sentMessageInfo);
        // 쪽지 읽기 페이지로 이동
        return "kdw/msgReadSent";
    }
    
    
    /* ========== 버튼 기능 구현 =========== */
    
    
    // 쪽지쓰기 페이지로 이동
    @GetMapping(value = "msgWrite")
    public String msgWritePage(HttpServletRequest request, Model model) {
        // 세션에서 보내는 사람의 아이디 가져오기
        Long senderId = (Long) request.getSession().getAttribute("user_no");

        // 유저 테이블에서 모든 사용자 목록 가져오기
        List<User> userList = msgService.getAllUsers();
        
        // 모델에 데이터 추가 (세션ID, 유저리스트)
        model.addAttribute("senderId", senderId);
        model.addAttribute("userList", userList);

        // JSP 페이지 이름 반환
        return "kdw/msgWrite";
    }
    
    // 쪽지 보내기
    @PostMapping("/msgSent")
    public String sendMsg (Message message, @RequestParam("files") MultipartFile[] files, HttpServletRequest request) {
    	System.out.println("MsgController sendMsg message : " + message);
        try {
            // HttpSession에서 로그인한 사용자 정보 가져오기 (쪽지 보내는 사람)
            Long msgSender = (Long) request.getSession().getAttribute("user_no");
            // 업로드 경로 설정
            String path = request.getSession().getServletContext().getRealPath("/upload/msgFile/");

            message.setMsg_sender(msgSender);
            msgService.sendMsg(message, files, path);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("MsgController sendMsg Exception ->" + e.getMessage());
        }
        return "kdw/msgSent";
    }
    
    // 답장 쓰기 버튼 -> 답장쓰기 View 이동
    @GetMapping(value = "msgReply")
    public String msgReplyPage(Model model) {
    log.info("MsgController msgReplyPage start...");
    return "kdw/msgReply";
    }
    
    // '보관' 버튼 클릭 시 msg_store_chk를 1로 업데이트
    @PostMapping(value = "updateMsgStoreStatus")
    public void updateMsgStoreStatus(@RequestBody Map<String, List<Long>> requestData, HttpServletResponse response) {
        List<Long> msgNos = requestData.get("msgNos");
        log.info("MsgController updateMsgStoreStatus 시작...");
        try {
            msgService.updateMsgStoreStatus(msgNos);
            System.out.println("쪽지 보관 성공");
        } catch (Exception e) {
            log.error("쪽지 보관에 실패했습니다.", e);
            e.printStackTrace();
            System.out.println("MsgController updateMsgStoreStatus Exception ->" + e.getMessage());
        }
    }
    
    // '삭제' 버튼 클릭 시 msg_delete_chk를 1로 업데이트
    @PostMapping(value = "updateMsgDeleteStatus")
    public void updateMsgDeleteStatus(@RequestBody Map<String, List<Long>> requestData, HttpServletResponse response) {
        List<Long> msgNos = requestData.get("msgNos");
        log.info("MsgController updateMsgDeleteStatus start...");
        
        try {
            msgService.updateMsgDeleteStatus(msgNos);
            System.out.println("쪽지 삭제 성공");
        } catch (Exception e) {
            log.error("쪽지 삭제에 실패했습니다.", e);
            e.printStackTrace();
            System.out.println("MsgController updateMsgDeleteStatus Exception ->" + e.getMessage());
        }
    }
    
    
    // '영구 삭제' 버튼 클릭 시 쪽지 영구 삭제
    @PostMapping(value = "/permanentDeleteMessages")
    public void permanentDeleteMessages(@RequestBody Map<String, List<Long>> requestData, HttpServletResponse response) {
        List<Long> msgNos = requestData.get("msgNos");
        log.info("MsgController permanentDeleteMessages start...");
        
        try {
            msgService.permanentDeleteMessages(msgNos);
            System.out.println("쪽지 영구 삭제 성공");
        } catch (Exception e) {
            log.error("쪽지 영구 삭제에 실패했습니다.", e);
            e.printStackTrace();
            System.out.println("MsgController permanentDeleteMessages Exception ->" + e.getMessage());
        }
    }
    /* ========== 받은편지함 버튼 기능 구현 END =========== */
    
	
}
