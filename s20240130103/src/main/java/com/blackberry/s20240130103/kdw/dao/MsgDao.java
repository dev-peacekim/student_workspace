package com.blackberry.s20240130103.kdw.dao;

import java.util.List;

import com.blackberry.s20240130103.kdw.model.Message;

public interface MsgDao {
	
	
	// 받은쪽지함 리스트
	List<Message> getReceivedMessages(Long msgReceiver, int start, int end);
	// 받은쪽지 저장처리
	void saveMessage(Message message);
	// 받은쪽지 전체 개수
	int totMsgReceiveCnt();
	
}
