package com.blackberry.s20240130103.kdw.service;

import java.util.List;

import com.blackberry.s20240130103.kdw.model.Message;

public interface MsgService {

	// 받은쪽지함 리스트
	List<Message> getReceivedMessages(Long msgReceiver, int start, int end);
	// 보낸쪽지함 리스트
	List<Message> getSentMessages(Long msgSender, int start, int end);
	// 쪽지보관함 리스트
	List<Message> getStoredMessages(Long storeboxUserNo, int start, int end);
	// 휴지통 보관함 리스트
	List<Message> getTrashMessages(Long trashboxUserNo, int start, int end);
	
	// 받은쪽지 개수
	int totReceiveMsgCnt(Long msgReceiver);
	// 보낸쪽지 개수
	int totSentMsgCnt(Long msgSender);
	// 쪽지보관함 쪽지 개수
	int totStoredMsgCnt(Long storeboxUserNo);
	// 휴지통 쪽지 개수
	int totTrashMsgCnt(Long trashboxUserNo);
	
	
	// 쪽지 읽음 여부
	int markMessageAsRead(Long msgNo);
	
	// 받은쪽지 저장처리
	void sendMessage(Message message);
	
	
	
    

    

	

}
