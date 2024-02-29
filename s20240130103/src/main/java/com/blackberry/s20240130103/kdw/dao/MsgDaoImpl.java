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
	
	// 받은 쪽지 전체 개수
	@Override
	public int totReceiveMsgCnt(Long msgReceiver) {
	    int totReceiveMsgCnt = 0;
	    try {
	        Map<String, Long> parameterMap = new HashMap<>();
	        parameterMap.put("msgReceiver", msgReceiver);
	        totReceiveMsgCnt = session.selectOne("com.blackberry.s20240130103.MessageMapper.totReceiveMsgCnt", parameterMap);
	        System.out.println("MsgServiceImpl totMsgCnt totReceiveMsgCnt->" + totReceiveMsgCnt);
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("MsgServiceImpl totMsgCnt Exception ->" + e.getMessage());
	    }
	    return totReceiveMsgCnt;
	}
	// 보낸 쪽지 전체 개수
	@Override
	public int totSentMsgCnt(Long msgSender) {
	    int totSentMsgCnt = 0;
	    try {
	        Map<String, Long> parameterMap = new HashMap<>();
	        parameterMap.put("msgSender", msgSender);
	        totSentMsgCnt = session.selectOne("com.blackberry.s20240130103.MessageMapper.totSentMsgCnt", parameterMap);
	        System.out.println("MsgServiceImpl totSentMsgCnt->" + totSentMsgCnt);
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("MsgServiceImpl totSentMsgCnt Exception ->" + e.getMessage());
	    }
	    return totSentMsgCnt;
	}

	// 받은 쪽지 리스트 가져오기
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
	        System.out.println("MsgDaoImpl getReceivedMessages start&end" + start + end);
	    } catch (Exception e) {
	        e.printStackTrace(); // 에러 상세 내용 출력
	        System.out.println("MsgDaoImpl getReceivedMessages e.getMessage()->" + e.getMessage());
	    }

	    return receivedMessages;
	}
	// 보낸 쪽지 리스트 가져오기
	@Override
	public List<Message> getSentMessages(Long msgSender, int start, int end) {
	    List<Message> SentMessages = null;
	    System.out.println("MsgDaoImpl getSentMessages start...");

	    try {
	        Map<String, Object> paramMap = new HashMap<>();
	        paramMap.put("msgSender", msgSender);
	        paramMap.put("start", start);
	        paramMap.put("end", end);

	        SentMessages = session.selectList("kdwSentMessagesAll", paramMap);
	        System.out.println("MsgDaoImpl getSentMessages SentMessages.size()->" + SentMessages.size());
	        System.out.println("MsgDaoImpl getSentMessages start&end" + start + end);
	    } catch (Exception e) {
	        e.printStackTrace(); // 에러 상세 내용 출력
	        System.out.println("MsgDaoImpl getSentMessages e.getMessage()->" + e.getMessage());
	    }

	    return SentMessages;
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
