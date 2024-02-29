package com.blackberry.s20240130103.kdw.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blackberry.s20240130103.kdw.dao.MsgDao;
import com.blackberry.s20240130103.kdw.model.Message;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MsgServiceImpl implements MsgService {
	@Autowired
	private final MsgDao msgDao;
	// String -> date
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	// 받은 쪽지 개수
	@Override
	public int totReceiveMsgCnt(Long msgReceiver) {
		log.info("MsgServiceImpl totReceiveMsgCnt start...");
		int totReceiveMsgCnt = msgDao.totReceiveMsgCnt(msgReceiver);
		log.info("MsgServiceImpl totReceiveMsgCnt totMsgCnt->"+totReceiveMsgCnt);
		return totReceiveMsgCnt;
	}
	// 보낸 쪽지 개수
	@Override
	public int totSentMsgCnt(Long msgSender) {
		log.info("MsgServiceImpl totSendMsgCnt start...");
		int totSentMsgCnt = msgDao.totSentMsgCnt(msgSender);
		log.info("MsgServiceImpl totSendMsgCnt totMsgCnt->"+totSentMsgCnt);
		return totSentMsgCnt;
	}
	
	// 보관함 쪽지 개수
    @Override
    public int totStoredMsgCnt(Long storeboxUserNo) {
    	log.info("MsgServiceImpl totStoredMsgCnt start...");
		int totStoredMsgCnt = msgDao.totStoredMsgCnt(storeboxUserNo);
		log.info("MsgServiceImpl totStoredMsgCnt totMsgCnt->"+ totStoredMsgCnt);
        return totStoredMsgCnt;
    }
    
	// 휴지통 쪽지 개수
	@Override
	public int totTrashMsgCnt(Long trashboxUserNo) {
    	log.info("MsgServiceImpl totTrashMsgCnt start...");
		int totTrashMsgCnt = msgDao.totTrashMsgCnt(trashboxUserNo);
		log.info("MsgServiceImpl totTrashMsgCnt totMsgCnt->"+ totTrashMsgCnt);
        return totTrashMsgCnt;
	}

    
	// 받은 쪽지 리스트 
	@Override
	public List<Message> getReceivedMessages(Long msgReceiver, int start, int end) {
        log.info("msgDao getReceivedMessages start...");
        return msgDao.getReceivedMessages(msgReceiver, start, end);
	}
	// 보낸 쪽지 리스트 
	@Override
	public List<Message> getSentMessages(Long msgSender, int start, int end) {
        log.info("msgDao getSentMessages start...");
        return msgDao.getSentMessages(msgSender, start, end);
	}
	// 보관함 쪽지 리스트
    @Override
    public List<Message> getStoredMessages(Long storeboxUserNo, int start, int end) {
    	log.info("msgDao getStoredMessages start...");
        return msgDao.getStoredMessages(storeboxUserNo, start, end);
    }
    // 휴지통 쪽지 리스트
	@Override
	public List<Message> getTrashMessages(Long trashboxUserNo, int start, int end) {
		log.info("msgDao getTrashMessages start...");
		return msgDao.getTrashMessages(trashboxUserNo, start, end);
	}
    
    // 쪽지 읽음 여부       
    public int markMessageAsRead(Long msgNo) {
        log.info("MsgServiceImpl markMessageAsRead start...");

        try {
            // 현재 날짜/시간을 문자열로 변환
            String currentDateStr = dateFormat.format(new Date());

            // UPDATE 쿼리 실행
            int updatedRows = msgDao.updateReadDate(msgNo, currentDateStr);

            // 업데이트 성공시 처리
            if (updatedRows > 0) {
                log.info("MsgServiceImpl markMessageAsRead 업데이트 성공!");
            } else {
                log.info("MsgServiceImpl markMessageAsRead 업데이트 실패!");
            }

            return updatedRows;
        } catch (Exception e) {
            // 예외 처리
            log.error("MsgServiceImpl markMessageAsRead 예외 발생: " + e.getMessage());
            return 0;
        }
    }
    
    
	// 쪽지 보내기
	@Override
	public void sendMessage(Message message) {
		log.info("MsgServiceImpl sendMessage start...");
		msgDao.saveMessage(message);
	}











}
