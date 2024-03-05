package com.blackberry.s20240130103.kdw.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

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
		log.info("MsgServiceImpl totReceiveMsgCnt totMsgCnt->" + totReceiveMsgCnt);
		return totReceiveMsgCnt;
	}

	// 보낸 쪽지 개수
	@Override
	public int totSentMsgCnt(Long msgSender) {
		log.info("MsgServiceImpl totSendMsgCnt start...");
		int totSentMsgCnt = msgDao.totSentMsgCnt(msgSender);
		log.info("MsgServiceImpl totSendMsgCnt totMsgCnt->" + totSentMsgCnt);
		return totSentMsgCnt;
	}

	// 보관함 쪽지 개수
	@Override
	public int totStoredMsgCnt(Long storeboxUserNo) {
		log.info("MsgServiceImpl totStoredMsgCnt start...");
		int totStoredMsgCnt = msgDao.totStoredMsgCnt(storeboxUserNo);
		log.info("MsgServiceImpl totStoredMsgCnt totMsgCnt->" + totStoredMsgCnt);
		return totStoredMsgCnt;
	}

	// 휴지통 쪽지 개수
	@Override
	public int totTrashMsgCnt(Long trashboxUserNo) {
		log.info("MsgServiceImpl totTrashMsgCnt start...");
		int totTrashMsgCnt = msgDao.totTrashMsgCnt(trashboxUserNo);
		log.info("MsgServiceImpl totTrashMsgCnt totMsgCnt->" + totTrashMsgCnt);
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
	public void sendMsg(Message message, MultipartFile[] files, String path) {
		log.info("MsgServiceImpl sendMsg start...");

		// 쪽지 정보 DB에 저장
		msgDao.sendMsg(message);

		// 파일 업로드 처리
		if (files != null && files.length > 0) {
		    File file = new File(path);

		    if (!file.exists()) {
		        boolean check = file.mkdirs();

		        // 디렉토리 생성에 실패한 경우 콘솔에 출력
		        if (!check) {
		            System.err.println("Failed to create directory: " + path);
		        }
		    }
		    for (MultipartFile f : files) {
		        if (!f.isEmpty()) {
		            System.out.println("file => " + f.getOriginalFilename());

		            try {
		                // HDD에 저장
		                String fileName = saveFile(f, path);

		                // DB에 저장
		                MessageFile messageFile = new MessageFile();
		                messageFile.setMsg_no(message.getMsg_no()); // 쪽지 번호 설정
		                messageFile.setMsg_file_name(fileName); // 첨부파일 가공 식별 저장명 설정
		                messageFile.setMsg_file_user_name(f.getOriginalFilename()); // 첨부파일 오리지날 유저명 설정
		                
		                // DB에 파일 정보 저장
		                msgDao.saveMessageFile(messageFile);
		            } catch (IOException e) {
		                e.printStackTrace();
		                System.err.println("Error saving file: " + e.getMessage());
		            }
		        }
		    }
		}
		log.info("MsgServiceImpl sendMsg end...");
	}

	// 파일 저장
	public String saveFile(MultipartFile multipartFile, String path) throws IOException {
		// 1. 중복되지 않는 파일명 생성(UUID, Date)
		String fileName = UUID.randomUUID().toString();

		// 2. 확장자
		String originalFilename = multipartFile.getOriginalFilename();
		String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
		fileName += fileExtension;

		// 3. 파일 저장
		File file = new File(path, fileName);
		multipartFile.transferTo(file);

		return fileName;
	}
	
	
	// ======== 받은 쪽지함 검색기능 ========
	// 받은 쪽지함 검색기능
	// 검색 결과 개수
	@Override
	public int searchReceiveMsgCnt(Long msgReceiver, String keyword, String type) {
	    log.info("MsgServiceImpl searchReceiveMsgCnt start...");
	    int searchReceivedMsgCount = msgDao.searchReceiveMsgCnt(msgReceiver, keyword, type);
	    System.out.println("MsgServiceImpl searchReceivedMsgCount: " + searchReceivedMsgCount);
	    return searchReceivedMsgCount;
	}
	// 검색 결과 리스트
	@Override
	public List<Message> searchReceivedMessages(Long msgReceiver, String keyword, String type, int start, int end) {
	    log.info("MsgServiceImpl searchReceivedMessages start...");
	    List<Message> searchReceivedMsgList = msgDao.searchReceivedMessages(msgReceiver, keyword, type, start, end);
	    System.out.println("MsgServiceImpl searchReceivedMsgList.size(): " + searchReceivedMsgList.size());
	    return searchReceivedMsgList;
	}
	// ======== 보낸 쪽지함 검색기능 ========
	// 검색 결과 개수
	@Override
	public int searchSentMsgCnt(Long msgSender, String keyword, String type) {
	    log.info("MsgServiceImpl searchSentMsgCnt start...");
	    int searchSentMsgCount = msgDao.searchSentMsgCnt(msgSender, keyword, type);
	    System.out.println("MsgServiceImpl searchSentMsgCount: " + searchSentMsgCount);
	    return searchSentMsgCount;
	}
	// 검색 결과 리스트
	@Override
	public List<Message> searchSentMessages(Long msgSender, String keyword, String type, int start, int end) {
	    log.info("MsgServiceImpl searchSentMessages start...");
	    List<Message> searchSentMsgList = msgDao.searchSentMessages(msgSender, keyword, type, start, end);
	    System.out.println("MsgServiceImpl searchSentMsgList.size(): " + searchSentMsgList.size());
	    return searchSentMsgList;
	}
	// ======== 쪽지 보관함 검색기능 ========
	// 검색 결과 개수
	@Override
	public int searchStoredMsgCnt(Long storeboxUserNo, String keyword, String type) {
	    log.info("MsgServiceImpl searchStoredMsgCnt start...");
	    int searchStoredMsgCount = msgDao.searchStoredMsgCnt(storeboxUserNo, keyword, type);
	    System.out.println("MsgServiceImpl searchStoredMsgCount: " + searchStoredMsgCount);
	    return searchStoredMsgCount;
	}
	// 검색 결과 리스트
	@Override
	public List<Message> searchStoredMessages(Long storeboxUserNo, String keyword, String type, int start, int end) {
	    log.info("MsgServiceImpl searchStoredMessages start...");
	    List<Message> searchStoredMsgList = msgDao.searchStoredMessages(storeboxUserNo, keyword, type, start, end);
	    System.out.println("MsgServiceImpl searchStoredMsgList.size(): " + searchStoredMsgList.size());
	    return searchStoredMsgList;
	}
	// ======== 휴지통 검색기능 ========
	// 검색 결과 개수
	@Override
	public int searchTrashMsgCnt(Long trashboxUserNo, String keyword, String type) {
	    log.info("MsgServiceImpl searchTrashMsgCnt start...");
	    int searchTrashMsgCount = msgDao.searchTrashMsgCnt(trashboxUserNo, keyword, type);
	    System.out.println("MsgServiceImpl searchTrashMsgCount: " + searchTrashMsgCount);
	    return searchTrashMsgCount;
	}
	// 검색 결과 리스트
	@Override
	public List<Message> searchTrashMessages(Long trashboxUserNo, String keyword, String type, int start, int end) {
	    log.info("MsgServiceImpl searchTrashMessages start...");
	    List<Message> searchTrashMsgList = msgDao.searchTrashMessages(trashboxUserNo, keyword, type, start, end);
	    System.out.println("MsgServiceImpl searchTrashMsgList.size(): " + searchTrashMsgList.size());
	    return searchTrashMsgList;
	}
	
	
}
