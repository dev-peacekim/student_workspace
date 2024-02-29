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
	
	// 받은 쪽지 개수
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
	// 보낸 쪽지 개수
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
	// 보관함 쪽지 개수
    @Override
    public int totStoredMsgCnt(Long storeboxUserNo) {
	    int totStoredMsgCnt = 0;
	    try {
	        Map<String, Long> parameterMap = new HashMap<>();
	        parameterMap.put("storeboxUserNo", storeboxUserNo);
	        totStoredMsgCnt = session.selectOne("com.blackberry.s20240130103.MessageMapper.totStoredMsgCnt", parameterMap);
	        System.out.println("MsgServiceImpl totStoredMsgCnt->" + totStoredMsgCnt);
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("MsgServiceImpl totStoredMsgCnt Exception ->" + e.getMessage());
	    }
	    return totStoredMsgCnt;
    }
    
	@Override
	public int totTrashMsgCnt(Long trashboxUserNo) {
	    int totTrashMsgCnt = 0;
	    try {
	        Map<String, Long> parameterMap = new HashMap<>();
	        parameterMap.put("trashboxUserNo", trashboxUserNo);
	        totTrashMsgCnt = session.selectOne("com.blackberry.s20240130103.MessageMapper.totStoredMsgCnt", parameterMap);
	        System.out.println("MsgServiceImpl totTrashMsgCnt->" + totTrashMsgCnt);
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("MsgServiceImpl totTrashMsgCnt Exception ->" + e.getMessage());
	    }
	    return totTrashMsgCnt;
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
	    List<Message> sentMessages = null;
	    System.out.println("MsgDaoImpl getSentMessages start...");

	    try {
	        Map<String, Object> paramMap = new HashMap<>();
	        paramMap.put("msgSender", msgSender);
	        paramMap.put("start", start);
	        paramMap.put("end", end);

	        sentMessages = session.selectList("kdwSentMessagesAll", paramMap);
	        System.out.println("MsgDaoImpl getSentMessages SentMessages.size()->" + sentMessages.size());
	        System.out.println("MsgDaoImpl getSentMessages start&end" + start + end);
	    } catch (Exception e) {
	        e.printStackTrace(); // 에러 상세 내용 출력
	        System.out.println("MsgDaoImpl getSentMessages e.getMessage()->" + e.getMessage());
	    }

	    return sentMessages;
	}
	
	// 보관함 리스트 가져오기
    @Override
    public List<Message> getStoredMessages(Long storeboxUserNo, int start, int end) {
	    List<Message> storedMessages = null;
	    System.out.println("MsgDaoImpl getStoredMessages start...");

	    try {
	        Map<String, Object> paramMap = new HashMap<>();
	        paramMap.put("storeboxUserNo", storeboxUserNo);
	        paramMap.put("start", start);
	        paramMap.put("end", end);

	        storedMessages = session.selectList("kdwStoredMessagesAll", paramMap);
	        System.out.println("MsgDaoImpl getSentMessages storedMessages.size()->" + storedMessages.size());
	        System.out.println("MsgDaoImpl getSentMessages start&end" + start + end);
	    } catch (Exception e) {
	        e.printStackTrace(); // 에러 상세 내용 출력
	        System.out.println("MsgDaoImpl getSentMessages e.getMessage()->" + e.getMessage());
	    }
	    return storedMessages;
    }
    
	// 휴지통 리스트 가져오기
	@Override
	public List<Message> getTrashMessages(Long trashboxUserNo, int start, int end) {
	    List<Message> trashMessages = null;
	    System.out.println("MsgDaoImpl getTrashMessages start...");

	    try {
	        Map<String, Object> paramMap = new HashMap<>();
	        paramMap.put("trashboxUserNo", trashboxUserNo);
	        paramMap.put("start", start);
	        paramMap.put("end", end);

	        trashMessages = session.selectList("kdwTrashMessagesAll", paramMap);
	        System.out.println("MsgDaoImpl getTrashMessages trashMessages.size()->" + trashMessages.size());
	        System.out.println("MsgDaoImpl getTrashMessages start&end" + start + end);
	    } catch (Exception e) {
	        e.printStackTrace(); // 에러 상세 내용 출력
	        System.out.println("MsgDaoImpl getTrashMessages e.getMessage()->" + e.getMessage());
	    }
	    return trashMessages;
	}
    
	// 쪽지 읽음여부
    @Override
    public int updateReadDate(Long storeboxUserNo, String currentDateStr) {
        log.info("MsgDaoImpl updateReadDate start...");

        try {
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("msgNo", storeboxUserNo);
            paramMap.put("currentDateStr", currentDateStr);

            // MyBatis의 update 쿼리 실행
            int updatedRows = session.update("kdwUpdateReadDate", paramMap);

            log.info("MsgDaoImpl updateReadDate updatedRows -> " + updatedRows);
            return updatedRows;
        } catch (Exception e) {
            log.error("MsgDaoImpl updateReadDate Exception -> " + e.getMessage());
            return 0;
        }
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
