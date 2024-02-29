package com.blackberry.s20240130103.kdw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blackberry.s20240130103.kdw.controller.MsgController;
import com.blackberry.s20240130103.kdw.dao.MsgDao;
import com.blackberry.s20240130103.kdw.model.Message;

import ch.qos.logback.classic.Logger;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MsgServiceImpl implements MsgService {
	@Autowired
	private final MsgDao msgDao;
	
	// 받은 쪽지 개수를 반환
	@Override
	public int totReceiveMsgCnt(Long msgReceiver) {
		log.info("MsgServiceImpl totReceiveMsgCnt start...");
		int totReceiveMsgCnt = msgDao.totReceiveMsgCnt(msgReceiver);
		log.info("MsgServiceImpl totReceiveMsgCnt totMsgCnt->"+totReceiveMsgCnt);
		return totReceiveMsgCnt;
	}
	// 보낸 쪽지 개수를 반환
	@Override
	public int totSentMsgCnt(Long msgSender) {
		log.info("MsgServiceImpl totSendMsgCnt start...");
		int totSentMsgCnt = msgDao.totSentMsgCnt(msgSender);
		log.info("MsgServiceImpl totSendMsgCnt totMsgCnt->"+totSentMsgCnt);
		return totSentMsgCnt;
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
	
	// 쪽지 보내기
	@Override
	public void sendMessage(Message message) {
		log.info("MsgServiceImpl sendMessage start...");
		msgDao.saveMessage(message);
	}










}
