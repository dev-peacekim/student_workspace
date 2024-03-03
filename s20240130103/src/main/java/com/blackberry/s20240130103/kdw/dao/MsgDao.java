package com.blackberry.s20240130103.kdw.dao;

import java.util.List;

import com.blackberry.s20240130103.kdw.model.Message;
import com.blackberry.s20240130103.kdw.model.MessageFile;
import com.blackberry.s20240130103.lhs.model.User;

public interface MsgDao {
	
	// 전체유저 리스트
	List<User> getAllUsers();
	
	// 받은쪽지함 리스트
	List<Message> getReceivedMessages(Long msgReceiver, int start, int end);
	// 보낸쪽지함 리스트
	List<Message> getSentMessages(Long msgSender, int start, int end);
	// 쪽지보관함 리스트
	List<Message> getStoredMessages(Long storeboxUserNo, int start, int end);
	// 휴지통 리스트
	List<Message> getTrashMessages(Long trashboxUserNo, int start, int end);
	
	// 받은쪽지 쪽지 개수
	int totReceiveMsgCnt(Long msgReceiver);
	// 보낸쪽지 쪽지 개수
	int totSentMsgCnt(Long msgSender);
	// 쪽지보관함 쪽지 개수
	int totStoredMsgCnt(Long storeboxUserNo);
	// 휴지통 쪽지 개수
	int totTrashMsgCnt(Long trashboxUserNo);
	
	// 받은쪽지 & 보낸쪽지 쪽지정보 가져오기
	Message getMessageByInfo(Long msgNo);
	// 받은쪽지 읽은시간 업데이트
	void updateReadDate(Long msgNo);

	// 보관 버튼 클릭시 msg_store_chk = 1
	void updateMsgStoreStatus(List<Long> msgNos);
	// 삭제 버튼 클릭시 msg_delete_chk = 1
	void updateMsgDeleteStatus(List<Long> msgNos);
	// 영구삭제버튼 클릭시 해당 쪽지 영구삭제
	void permanentDeleteMessages(List<Long> msgNos);
	
	// 쪽지 보내기
	void sendMsg(Message message);
	
	// 파일 저장 처리
	void saveMessageFile(MessageFile messageFile);
	
	


	



	
	
	

	
	

	
	

	
	

	
	
}
