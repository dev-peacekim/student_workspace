package com.blackberry.s20240130103.kdw.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.blackberry.s20240130103.kdw.model.Message;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Repository
@RequiredArgsConstructor
@Slf4j
public class MsgDaoImpl implements MsgDao {
	
	private final SqlSession session;
	
	// 받은쪽지 전체 개수
	@Override
	public int totMsgReceiveCnt() {
		int totMsgReceiveCount = 0;
		System.out.println("EmpDaoImpl start total...");
		try {
			totMsgReceiveCount = session.selectOne("com.blackberry.s20240130103.MessageMapper.totMsgReceiveCount");
			System.out.println("MsgDaoImpl totMsgReceiveCnt totMsgReceiveCount->" + totMsgReceiveCount);
		} catch (Exception e) {
			System.out.println("MsgDaoImpl totMsgReceiveCnt Exception ->" + e.getMessage());
		}
		return totMsgReceiveCount;
	}
	// 받은 쪽지 가져오기
	@Override
	public List<Message> getReceivedMessages(Long msgReceiver, int start, int end) {
	    List<Message> receivedMessages = null;
	    System.out.println("MsgDaoImpl getReceivedMessages start...");

	    try {
	        Map<String, Object> paramMap = new HashMap<>();
	        paramMap.put("msgReceiver", msgReceiver);
	        paramMap.put("start", start);
	        paramMap.put("end", end);

	        receivedMessages = session.selectList("kdwReceivedMessagesAll", paramMap);
	        System.out.println("MsgDaoImpl getReceivedMessages receivedMessages.size()->" + receivedMessages.size());
	    } catch (Exception e) {
	        e.printStackTrace(); // 에러 상세 내용 출력
	        System.out.println("MsgDaoImpl getReceivedMessages e.getMessage()->" + e.getMessage());
	    }

	    return receivedMessages;
	}
	
	@Override
	public void saveMessage(Message message) {
		try {
	        session.insert("saveMessage", message); // 쪽지 정보 삽입
	        log.info("MsgDaoImpl saveMessage start successful");
	    } catch (Exception e) {
	        System.out.println("MsgDaoImpl saveMessage Exception: " + e.getMessage());
	    }
	}







	
	







}
