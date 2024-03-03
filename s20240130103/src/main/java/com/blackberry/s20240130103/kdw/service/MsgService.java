package com.blackberry.s20240130103.kdw.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.blackberry.s20240130103.kdw.model.Message;
import com.blackberry.s20240130103.lhs.model.User;

public interface MsgService {
	
	// 전체 유저 리스트 가져와서 뿌려주기(나중에 주소록으로 바꿔야함)
	List<User> getAllUsers();
	
	// 받은쪽지함 쪽지 리스트
	List<Message> getReceivedMessages(Long msgReceiver, int start, int end);
	// 보낸쪽지함 쪽지 리스트
	List<Message> getSentMessages(Long msgSender, int start, int end);
	// 쪽지보관함 쪽지 리스트
	List<Message> getStoredMessages(Long storeboxUserNo, int start, int end);
	// 휴지통 쪽지 리스트
	List<Message> getTrashMessages(Long trashboxUserNo, int start, int end);
	
	// 받은쪽지 개수
	int totReceiveMsgCnt(Long msgReceiver);
	// 보낸쪽지 개수
	int totSentMsgCnt(Long msgSender);
	// 쪽지보관함 쪽지 개수
	int totStoredMsgCnt(Long storeboxUserNo);
	// 휴지통 쪽지 개수
	int totTrashMsgCnt(Long trashboxUserNo);
	
	// 받은쪽지함 쪽지 정보
	Message getReceivedMessageByInfo(Long msgNo);
	// 받은쪽지 읽은시간 업데이트
	void updateReadDate(Long msgNo);
	// 보낸쪽지함 쪽지 정보 읽음 업데이트(할 필요없음)
	Message getSentMessageByInfo(Long msgNo);
	
	// 보관버튼 클릭시 보관함으로 이동
	void updateMsgStoreStatus(List<Long> msgNos);
	// 삭제버튼 클릭시 휴지통으로 이동
	void updateMsgDeleteStatus(List<Long> msgNos);
	// 영구삭제버튼 클릭시 해당 쪽지 영구삭제
	void permanentDeleteMessages(List<Long> msgNos);
	// 쪽지 보내기
	void sendMsg(Message message);



	
	

	
	
	
	
	

	
	
	
	
    

    

	

}
