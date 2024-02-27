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
	
	private final MsgDao msgDao;
	
	// 쪽지 리스트 
	@Override
	public List<Message> getReceivedMessages(Long msgReceiver, int start, int end) {
        log.info("msgDao getReceivedMessages start...");
        return msgDao.getReceivedMessages(msgReceiver, start, end);
	}

	// 쪽지 보내기
	@Override
	public void sendMessage(Message message) {
		log.info("MsgServiceImpl sendMessage start...");
		msgDao.saveMessage(message);
	}
	
	// 받은 쪽지함에 대한 전체 쪽지 개수를 반환
	@Override
	public int totalMsgReceive() {
		log.info("MsgServiceImpl totalMsgReceive start...");
		int totMsgReceiveCnt = msgDao.totMsgReceiveCnt();
		log.info("MsgServiceImpl totalMsgReceive totMsgReceiveCnt->"+totMsgReceiveCnt);
		return totMsgReceiveCnt;
	}





}
