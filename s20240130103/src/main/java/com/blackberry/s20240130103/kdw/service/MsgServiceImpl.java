package com.blackberry.s20240130103.kdw.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.blackberry.s20240130103.kdw.dao.MsgDao;
import com.blackberry.s20240130103.kdw.model.Message;
import com.blackberry.s20240130103.kdw.model.MessageFile;
import com.blackberry.s20240130103.lhs.model.User;

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
	private static final String UPLOAD_DIR = "webapp/upload/msgFile";
	
	
	// 유저 전체 리스트 가져오기
    @Override
    public List<User> getAllUsers() {
        log.info("MsgServiceImpl getAllUsers start...");
        return msgDao.getAllUsers();
    }
	
	// ========== 각 쪽지함 쪽지 개수 가져오기 ============
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

	// ========== 각 쪽지함 리스트 가져오기 ============
	// 받은 쪽지 리스트 
	@Override
	public List<Message> getReceivedMessages(Long msgReceiver, int start, int end) {
        log.info("MsgServiceImpl getReceivedMessages start...");
        return msgDao.getReceivedMessages(msgReceiver, start, end);
	}
	// 보낸 쪽지 리스트 
	@Override
	public List<Message> getSentMessages(Long msgSender, int start, int end) {
        log.info("MsgServiceImpl getSentMessages start...");
        return msgDao.getSentMessages(msgSender, start, end);
	}
	// 보관함 쪽지 리스트
    @Override
    public List<Message> getStoredMessages(Long storeboxUserNo, int start, int end) {
    	log.info("MsgServiceImpl getStoredMessages start...");
        return msgDao.getStoredMessages(storeboxUserNo, start, end);
    }
    // 휴지통 쪽지 리스트
	@Override
	public List<Message> getTrashMessages(Long trashboxUserNo, int start, int end) {
		log.info("MsgServiceImpl getTrashMessages start...");
		return msgDao.getTrashMessages(trashboxUserNo, start, end);
	}
	
	// ========== 받은쪽지 읽기 & 보낸쪽지 읽기 읽은시간 업데이트============
	// 받은 쪽지 정보
	@Override
	public Message getReceivedMessageByInfo(Long msgNo) {
	    log.info("MsgServiceImpl getReceivedMessageById start...");
	    Message receivedMessageInfo = msgDao.getMessageByInfo(msgNo);

	    // 읽은 시간이 'null'이면 업데이트
	    if (receivedMessageInfo.getMsg_readdate() == null) {
	        updateReadDate(msgNo);
	        log.info("msg_readdate update... msgNo => ", msgNo);

	        // 업데이트 후에는 새로 받아옴
	        receivedMessageInfo = msgDao.getMessageByInfo(msgNo);
	    }
	    return receivedMessageInfo;
	}
	// 받은 쪽지 읽은 시간 업데이트
	@Override
	public void updateReadDate(Long msgNo) {
	    log.info("MsgServiceImpl updateReadDate start...");
	    msgDao.updateReadDate(msgNo);
	}
	// 보낸 쪽지 정보
	@Override
	public Message getSentMessageByInfo(Long msgNo) {
		log.info("MsgServiceImpl getSentMessageById start...");
		Message sentMessageInfo = msgDao.getMessageByInfo(msgNo);
		log.info("MsgServiceImpl getSentMessageById msgNo => " + msgNo);
		log.info("MsgServiceImpl getSentMessageById sentMessageInfo => " + sentMessageInfo);
		return sentMessageInfo;
	}
	
	// 파일 저장
	private void saveMessageFile(MultipartFile file, Long msgNo, int fileCount) {
		try { // 파일 저장 폴더 경로 (프로젝트 내의 upload 폴더)
			String uploadPath = "/upload/userImg/";

			// 업로드 폴더가 없으면 생성
			File uploadFolder = new File(uploadPath);
			if (!uploadFolder.exists()) {
				uploadFolder.mkdirs();
			}

			// 저장될 파일명 (파일 순서번호 + 파일명)
			String savedFileName = fileCount + "_" + file.getOriginalFilename();

			// 파일 저장
			Path filePath = Paths.get(uploadPath + savedFileName);
			Files.write(filePath, file.getBytes());

			// 데이터베이스에 저장할 파일 정보 객체 생성
			MessageFile messageFile = new MessageFile();
			messageFile.setMsg_no(msgNo);
			messageFile.setMsg_file_cnt(fileCount);
			messageFile.setMsg_file_name(savedFileName);
			messageFile.setMsg_file_user_name(file.getOriginalFilename()); // 사용자에게 보여지는 파일명으로 설정

			// 파일 정보 저장
			msgDao.saveMessageFile(messageFile);
		} catch (Exception e) {
			log.error("Error occurred while saving message file: {}", e.getMessage());
			throw new RuntimeException("Failed to save message file", e);
		}
	}

	@Override
	public void updateMsgStoreStatus(List<Long> msgNos) {
		log.info("MsgServiceImpl updateMsgStoreStatus start...");
		msgDao.updateMsgStoreStatus(msgNos);
	}

	@Override
	public void updateMsgDeleteStatus(List<Long> msgNos) {
	    log.info("MsgServiceImpl updateMsgDeleteStatus start...");
	    msgDao.updateMsgDeleteStatus(msgNos);
	}

	// 쪽지 영구 삭제 서비스 구현
	@Override
	public void permanentDeleteMessages(List<Long> msgNos) {
	    log.info("MsgServiceImpl permanentDeleteMessages start...");
	    msgDao.permanentDeleteMessages(msgNos);
	}
	// 쪽지 보내기
    @Override
    public void sendMsg(Message message) {
    	log.info("MsgServiceImpl sendMsg start...");
        // 현재 날짜를 메시지에 추가
        LocalDateTime sentDate = LocalDateTime.now();
        message.setMsg_createdate(sentDate.toString());

        msgDao.sendMsg(message);
        log.info("MsgServiceImpl sendMsg end...");
    }


    
   











}
