package com.blackberry.s20240130103.kdw.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.blackberry.s20240130103.kdw.model.Message;
import com.blackberry.s20240130103.kdw.model.MessageFile;
import com.blackberry.s20240130103.kdw.service.MsgPaging;
import com.blackberry.s20240130103.kdw.service.MsgService;
import com.blackberry.s20240130103.kph.model.KphUsers;
import com.blackberry.s20240130103.kph.service.KphProjectService;
import com.blackberry.s20240130103.lhs.model.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Controller
@RequiredArgsConstructor
@Slf4j
public class MsgController {
	
	private final MsgService msgService;
	private final KphProjectService kphProjectService;


	/* ========== 쪽지함 ========== */
	// 받은 쪽지함 리스트
	@GetMapping(value = "msgReceivebox")
	public String getReceivedMessages(HttpServletRequest request, Model model,
	        @RequestParam(name = "currentPage", defaultValue = "1") String currentPage,
	        @RequestParam(name = "keyword", required = false) String keyword,
	        @RequestParam(name = "type", defaultValue = "all") String type)  {
	    log.info("MsgController getReceivedMessages start...");
	    
	    System.out.println("Received keyword: {}"+ keyword);
	    System.out.println("Received type: {}"+ type);
	    
	    // HttpSession에서 로그인한 사용자 정보 가져오기 (쪽지 받는 사람)
	    Long msgReceiver = (Long) request.getSession().getAttribute("user_no");

	    // 받은 쪽지 수 가져오기
	    int totReceiveMsgCnt = 0;
	    int totUnreadReceiveMsgCnt = 0;
	    // 검색 결과를 가져오는 로직 추가
	    if (keyword != null && !keyword.isEmpty()) {
	        totReceiveMsgCnt = msgService.searchReceiveMsgCnt(msgReceiver, keyword, type);
	    } else {
	        totReceiveMsgCnt = msgService.totReceiveMsgCnt(msgReceiver);
	    }
	    totUnreadReceiveMsgCnt = msgService.totUnreadReceiveMsgCnt(msgReceiver);
	    
	    // 페이징 처리
	    MsgPaging page = new MsgPaging(totReceiveMsgCnt, currentPage);

	    // 로그인한 사용자 정보를 기반으로 받은 쪽지 목록 가져오기 (검색 결과 또는 전체 결과, 페이징 정보 전달)
	    List<Message> receivedMessages;
	    if (keyword != null && !keyword.isEmpty()) {
	        receivedMessages = msgService.searchReceivedMessages(msgReceiver, keyword, type, page.getStart(), page.getEnd());
	    } else {
	        receivedMessages = msgService.getReceivedMessages(msgReceiver, page.getStart(), page.getEnd());
	    }
	    
	    System.out.println("MsgController getReceivedMessages 전체쪽지" + totReceiveMsgCnt);
	    System.out.println("MsgController getReceivedMessages 읽지않은쪽지" + totUnreadReceiveMsgCnt);
	    // 'Model'에 받은 쪽지 목록과 페이징 정보를 담아서 전달
	    model.addAttribute("msgReceiver", msgReceiver);
	    model.addAttribute("totReceiveMsgCnt", totReceiveMsgCnt);
	    model.addAttribute("totUnreadReceiveMsgCnt", totUnreadReceiveMsgCnt);
	    model.addAttribute("receivedMessages", receivedMessages);
	    model.addAttribute("page", page);
	    model.addAttribute("keyword", keyword);
	    model.addAttribute("type", type);

	    return "kdw/msgReceivebox";
	}
	
	/* ========== 보낸 쪽지함 리스트 불러오기 ========== */
	@GetMapping(value = "msgSendbox")
	public String getSendMessages(HttpServletRequest request, Model model,
	        @RequestParam(name = "currentPage", defaultValue = "1") String currentPage,
	        @RequestParam(name = "keyword", required = false) String keyword,
	        @RequestParam(name = "type", defaultValue = "all") String type) {
	    log.info("MsgController getSendMessages start...");
	    
	    System.out.println("Received keyword: {}"+ keyword);
	    System.out.println("Received type: {}"+ type);
	    
	    // HttpSession에서 로그인한 사용자 정보 가져오기 (쪽지 받는 사람)
	    Long msgSender = (Long) request.getSession().getAttribute("user_no");

	    // 보낸 쪽지 수 가져오기 (totMsgCnt 메서드를 이용)
	    int totSentMsgCnt = 0;
	    int totUnreadSentMsgCnt = 0;
	    
	    // 검색 결과를 가져오는 로직 추가
	    if (keyword != null && !keyword.isEmpty()) {
	    	totSentMsgCnt = msgService.searchSentMsgCnt(msgSender, keyword, type);
	    } else {
	    	totSentMsgCnt = msgService.totSentMsgCnt(msgSender);
	    }
	    totUnreadSentMsgCnt = msgService.totUnreadSentMsgCnt(msgSender);
	    
	    // 페이징 처리
	    MsgPaging page = new MsgPaging(totSentMsgCnt, currentPage);

	    // 로그인한 사용자 정보를 기반으로 보낸 쪽지 목록 가져오기 (getSentMessages 메서드 사용)
	    List<Message> sentMessages;
	    
	    if (keyword != null && !keyword.isEmpty()) {
	    	sentMessages = msgService.searchSentMessages(msgSender, keyword, type, page.getStart(), page.getEnd());
	    } else {
	    	sentMessages = msgService.getSentMessages(msgSender, page.getStart(), page.getEnd());
	    }
	    
	    System.out.println("MsgController getSendMessages 전체쪽지" + totSentMsgCnt);
	    System.out.println("MsgController getSendMessages 읽지않은쪽지" + totUnreadSentMsgCnt);
	    
	    // 'Model'에 보낸 쪽지 목록과 페이징 정보를 담아서 전달
	    model.addAttribute("msgSender", msgSender);
	    model.addAttribute("totSentMsgCnt", totSentMsgCnt);
	    model.addAttribute("totUnreadSentMsgCnt", totUnreadSentMsgCnt);
	    model.addAttribute("sentMessages", sentMessages);
	    model.addAttribute("page", page);
	    model.addAttribute("keyword", keyword);
	    model.addAttribute("type", type);

	    return "kdw/msgSendbox";
	}
	
	// 쪽지 보관함 리스트 가져오기
	@GetMapping(value = "msgStorebox")
	public String getStoreboxMessages(HttpServletRequest request, Model model,
	        @RequestParam(name = "currentPage", defaultValue = "1") String currentPage,
	        @RequestParam(name = "keyword", required = false) String keyword,
	        @RequestParam(name = "type", defaultValue = "all") String type) {
	    log.info("MsgController getStoreboxMessages start...");
	    
	    System.out.println("Received keyword: {}"+ keyword);
	    System.out.println("Received type: {}"+ type);
	    
	    // HttpSession에서 로그인한 사용자 정보 가져오기 (전체 쪽지)
	    Long storeboxUserNo = (Long) request.getSession().getAttribute("user_no");
	    
	    // msg_store_chk 값이 1인 쪽지들만 가져오기
	    int totStoredMsgCnt = 0;
	    int totUnreadStoredMsgCnt = 0;
	    
	    // 검색 결과를 가져오는 로직 추가
	    if (keyword != null && !keyword.isEmpty()) {
	    	totStoredMsgCnt = msgService.searchStoredMsgCnt(storeboxUserNo, keyword, type);
	    } else {
	    	totStoredMsgCnt = msgService.totStoredMsgCnt(storeboxUserNo);
	    }
	    totUnreadStoredMsgCnt = msgService.totUnreadStoredMsgCnt(storeboxUserNo);
	    
	    // 페이징 처리
	    MsgPaging page = new MsgPaging(totStoredMsgCnt, currentPage);
	    // 로그인한 사용자 정보를 기반으로 보낸 쪽지 목록 가져오기
	    List<Message> storedMessages;
	    
	    if (keyword != null && !keyword.isEmpty()) {
	    	storedMessages = msgService.searchStoredMessages(storeboxUserNo, keyword, type, page.getStart(), page.getEnd());
	    } else {
	    	storedMessages = msgService.getStoredMessages(storeboxUserNo, page.getStart(), page.getEnd());
	    }
	    
	    // 'Model'에 보관된 쪽지 목록과 페이징 정보를 담아서 전달
	    model.addAttribute("totStoredMsgCnt", totStoredMsgCnt);
	    model.addAttribute("totUnreadStoredMsgCnt", totUnreadStoredMsgCnt);
	    model.addAttribute("storedMessages", storedMessages);
	    model.addAttribute("page", page);
	    model.addAttribute("storeboxUserNo", storeboxUserNo);
	    model.addAttribute("keyword", keyword);
	    model.addAttribute("type", type);

	    return "kdw/msgStorebox";
	}
	
    // 휴지통 리스트 가져오기
    @GetMapping(value = "msgTrashbox")
    public String getTrashboxMessages(HttpServletRequest request, Model model,
	        @RequestParam(name = "currentPage", defaultValue = "1") String currentPage,
	        @RequestParam(name = "keyword", required = false) String keyword,
	        @RequestParam(name = "type", defaultValue = "all") String type) {
        log.info("MsgController getTrashboxMessages start...");
        
	    System.out.println("Received keyword: {}"+ keyword);
	    System.out.println("Received type: {}"+ type);
        
        // HttpSession에서 로그인한 사용자 정보 가져오기 (휴지통 쪽지)
        Long trashboxUserNo = (Long) request.getSession().getAttribute("user_no");

        // msg_trash_chk 값이 1인 쪽지들만 가져오기
        int totTrashMsgCnt = 0;
        int totUnreadTrashMsgCnt = 0;
        
	    // 검색 결과를 가져오는 로직 추가
	    if (keyword != null && !keyword.isEmpty()) {
	    	totTrashMsgCnt = msgService.searchTrashMsgCnt(trashboxUserNo, keyword, type);
	    } else {
	    	totTrashMsgCnt = msgService.totTrashMsgCnt(trashboxUserNo);
	    }
	    totUnreadTrashMsgCnt = msgService.totUnreadTrashMsgCnt(trashboxUserNo);
	    
        // 페이징 처리
        MsgPaging page = new MsgPaging(totTrashMsgCnt, currentPage);
        
        // 로그인한 사용자 정보를 기반으로 휴지통 쪽지 목록 가져오기
        List<Message> trashMessages;
        
	    if (keyword != null && !keyword.isEmpty()) {
	    	trashMessages = msgService.searchTrashMessages(trashboxUserNo, keyword, type, page.getStart(), page.getEnd());
	    } else {
	    	trashMessages = msgService.getTrashMessages(trashboxUserNo, page.getStart(), page.getEnd());
	    }
        
        // 'Model'에 보관된 휴지통 쪽지 목록과 페이징 정보를 담아서 전달
        model.addAttribute("totTrashMsgCnt", totTrashMsgCnt);
        model.addAttribute("totUnreadTrashMsgCnt", totUnreadTrashMsgCnt);
        model.addAttribute("trashMessages", trashMessages);
        model.addAttribute("page", page);
        model.addAttribute("trashboxUserNo", trashboxUserNo);
	    model.addAttribute("keyword", keyword);
	    model.addAttribute("type", type);

        return "kdw/msgTrashbox";
    }
    
    // ========== 받은 쪽지 읽기 & 보낸 쪽지 읽기  ========== 
    
    // 받은 쪽지 읽기 및 첨부 파일 정보 불러오기
    @GetMapping(value = "msgReadReceived")
    public String readReceivedMessageInfo(@RequestParam("msg_no") Long msgNo, Model model) {
        log.info("MsgController readReceivedMessage start...");

        // msgNo를 사용하여 해당 쪽지 정보 및 첨부 파일 정보 가져오기
        Message receivedMessageInfo = msgService.getReceivedMessageByInfo(msgNo);
        // 파일첨부가된 쪽지 리스트 = (다운로드 기능)
        List<MessageFile> fileMsgs = msgService.getMessageFiles(msgNo);
        // 보낸사람,받는사람 번호로 닉네임, 아이디 가져오기
        Long senderNo = receivedMessageInfo.getMsg_sender();
        Long receiverNo =  receivedMessageInfo.getMsg_receiver();
        User senderUser = msgService.findUserDetailsById(senderNo);
        User receiverUser = msgService.findUserDetailsById(receiverNo);
        
        // 보낸사람 닉네임,아이디 정보
        model.addAttribute("senderUser", senderUser);
        // 받은사람 닉네임,아이디 정보;
        model.addAttribute("receiverUser", receiverUser);
        // 쪽지에 대한 정보
        model.addAttribute("receivedMessageInfo", receivedMessageInfo);
        model.addAttribute("fileMsgs", fileMsgs); // 첨부 파일 정보 모델에 추가

        log.info("MsgController readReceivedMessage receivedMessageInfo => {}", receivedMessageInfo);
        log.info("MsgController readReceivedMessage fileMsgs.size() => {}", fileMsgs.size());
        return "kdw/msgReadReceived";
    }
    
    // 보낸쪽지 읽기('msg_readdate'가 'null'이면 'msg_readdate'업데이트)
    @GetMapping(value = "msgReadSent")
    public String readSentMessageInfo(@RequestParam("msg_no") Long msgNo, Model model) {
        log.info("MsgController readSentMessage start...");

        // msgNo를 사용하여 해당 쪽지 정보 가져오기 및 읽은 시간 업데이트
        Message sentMessageInfo = msgService.getSentMessageByInfo(msgNo);
        // 파일첨부가된 쪽지 리스트 = (다운로드 기능)
        List<MessageFile> fileMsgs = msgService.getMessageFiles(msgNo); 
        
        // 보낸사람,받는사람 번호로 닉네임, 아이디 가져오기
        Long senderNo = sentMessageInfo.getMsg_sender();
        Long receiverNo =  sentMessageInfo.getMsg_receiver();
        User senderUser = msgService.findUserDetailsById(senderNo);
        User receiverUser = msgService.findUserDetailsById(receiverNo);
        
        // 보낸사람 닉네임,아이디 정보
        model.addAttribute("senderUser", senderUser);
        // 받은사람 닉네임,아이디 정보;
        model.addAttribute("receiverUser", receiverUser);
        
        model.addAttribute("sentMessageInfo", sentMessageInfo);
        model.addAttribute("fileMsgs", fileMsgs); // 첨부 파일 정보 모델에 추가
        log.info("MsgController readSentMessage sentMessageInfo => " + sentMessageInfo);
        log.info("MsgController readReceivedMessage fileMsgs.size() => {}", fileMsgs.size());
        // 보낸쪽지 읽기 페이지로 이동
        return "kdw/msgReadSent";
    }
    
    
    /* ========== 다운로드 기능 =========== */
	// 파일 상세정보 조회와 다운로드
	@GetMapping("/downloadFile")
	public void downloadFile(@RequestParam("msgNo") Long msgNo, @RequestParam("fileCnt") int fileCnt, HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("MsgController downloadFile msgNo: " + msgNo);
		System.out.println("MsgController downloadFile fileCnt: " + fileCnt);
		System.out.println("MsgController downloadFile request: " + request);
		System.out.println("MsgController downloadFile response: " + response);
	    try {
	        MessageFile messageFile = msgService.getFileDetail(msgNo, fileCnt);
	        if (messageFile != null) {
	            // 파일의 실제 경로 설정
	            String filePath = request.getSession().getServletContext().getRealPath("/upload/msgFile/") + messageFile.getMsg_file_name();
	            File file = new File(filePath);
	            if (file.exists()) {
	                String mimeType = URLConnection.guessContentTypeFromName(file.getName());
	                if (mimeType == null) {
	                    mimeType = "application/octet-stream";
	                }
	                
	                response.setContentType(mimeType);
	                response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(messageFile.getMsg_file_user_name(), "UTF-8") + "\"");
	                response.setContentLength((int) file.length());

	                InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
	                FileCopyUtils.copy(inputStream, response.getOutputStream());
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	
    /* ========== 버튼 기능 구현 =========== */
	
    // 답장쓰기 버튼 -> 답장쓰기 View 이동
    @GetMapping(value = "msgReply")
    public String msgReplyPage(HttpServletRequest request, Model model, 
    			@RequestParam(value = "sender", required = false) Long senderUserNo) {
    log.info("MsgController msgReplyPage start...");
    // 세션에서 보내는 사람의 아이디 가져오기
    Long senderId = (Long) request.getSession().getAttribute("user_no");
    // 유저 테이블에서 모든 사용자 목록 가져오기
    List<User> userList = msgService.getAllUsers();
    
    if (senderUserNo != null) {
        User senderUser = msgService.findUserDetailsById(senderUserNo);
        System.out.println("MsgController msgReplyPage userNo: " + senderUserNo);
        System.out.println("MsgController msgReplyPage senderUser: " + senderUser);
        // 읽기 페이지에서 받은 보낸 사람 정보 -> 답장쓰기 받는사람
        model.addAttribute("receiverUser", senderUser);
        model.addAttribute("senderUserNo", senderUserNo);
    }
    
    model.addAttribute("senderId", senderId);
    model.addAttribute("userList", userList);
    
    return "kdw/msgReply";
    }
	
    // 쪽지쓰기 페이지로 이동(쪽지쓰기 버튼)
	@GetMapping(value = "msgWrite")
	public String msgWritePage(HttpServletRequest request, Model model) {
	    System.out.println("MsgController msgWritePage Start...");
	    // 세션에서 보내는 사람의 아이디 가져오기
	    Long senderId = (Long) request.getSession().getAttribute("user_no");
	    
	    // 유저 테이블에서 모든 사용자 목록 가져오기
	    List<User> userList = msgService.getAllUsers();
	    
	    // 워크스페이스 페이지에서 요청하는 받는사람(닉네임+아이디로 인풋 입력)
	    String userNoParam = request.getParameter("user_no");
	    Long userNo = null;
	    User userNicId = null;

	    System.out.println("MsgController msgWritePage userNoParam: " + userNoParam);
	    
	    if (userNoParam != null && !userNoParam.isEmpty()) {
	        try {
	            userNo = Long.parseLong(userNoParam);
	            userNicId = msgService.findUserDetailsById(userNo);
	            model.addAttribute("receiverNick", userNicId.getUser_nic());
	            model.addAttribute("receiverUserId", userNicId.getUser_id());
	        } catch (NumberFormatException e) {
	        	e.printStackTrace();
	        }
	    }

	    // 모델에 데이터 추가 (세션 유저no, 유저리스트, receiverId)
	    model.addAttribute("senderId", senderId);
	    model.addAttribute("userList", userList);
	    // 워크스페이스 페이지에서 요청하는 받는사람
	    model.addAttribute("userNo", userNo);

	    return "kdw/msgWrite";
	}
    
    // 쪽지 보내기 - 멀티 업로드(보내기 버튼)
    @PostMapping(value = "msgSent")
    public String sendMsg (Message message, @RequestParam("files") MultipartFile[] files, HttpServletRequest request) {
    	
    	String msgReceivers = request.getParameter("msg_receivers");

    	// 쉼표로 문자열을 분리하고, 공백을 제거한 후 Long 타입으로 변환합니다.
    	List<Long> receiversList = Arrays.stream(msgReceivers.split(","))
    	                                      .map(String::trim) // 배열들 한번에 공백 삭제
    	                                      .map(Long::parseLong) // 타입'Long'으로 파싱
    	                                      //변환된 Long 값들을 List<Long>으로 수집
    	                                      .collect(Collectors.toList());  
    	
    	System.out.println("MsgController sendMsg message : " + message);
        try {
            // HttpSession에서 로그인한 사용자 정보 가져오기 (쪽지 보내는 사람)
            Long msgSender = (Long) request.getSession().getAttribute("user_no");
            // 업로드 경로 설정
            String path = request.getSession().getServletContext().getRealPath("/upload/msgFile/");
            
            message.setMsg_sender(msgSender);
            
            // 각 수신자에 대해 메시지 전송 로직을 반복 실행합니다.
            for(Long receiver : receiversList) {
                message.setMsg_receiver(receiver); // 수신자 설정
                msgService.sendMsg(message, files, path); // 메시지 전송 서비스 호출
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("MsgController sendMsg Exception ->" + e.getMessage());
        }
        return "kdw/msgSent";
    }
    
    // 주소록 버튼 클릭시 -> 주소록 모달 페이지 이동
    @GetMapping("/getAddressBookList")
    @ResponseBody
    public List<KphUsers> getAddressBookList(HttpServletRequest request) {
    	System.out.println("MsgController getAddressBookList start...");
        Long userNo = Long.parseLong(request.getSession().getAttribute("user_no").toString());
        System.out.println("MsgController getAddressBookList userNo: " + userNo);
        List<KphUsers> addressUserList = kphProjectService.addressUserList(userNo);
        System.out.println("MsgController getAddressBookList addressUserList.size(): " + addressUserList.size());
        return addressUserList;
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
    /* ========== 버튼 기능 구현 END =========== */
    
    
}
