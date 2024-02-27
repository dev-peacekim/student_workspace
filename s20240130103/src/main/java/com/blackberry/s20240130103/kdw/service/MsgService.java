package com.blackberry.s20240130103.kdw.service;

import java.util.List;

import com.blackberry.s20240130103.kdw.model.Message;

public interface MsgService {

	// 받은쪽지함 리스트
	List<Message> getReceivedMessages(Long msgReceiver, int start, int end);
	
	int totalMsgReceive();
	
	// 받은쪽지 저장처리
	void sendMessage(Message message);

}
