package com.blackberry.s20240130103.lsl.Service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.blackberry.s20240130103.lsl.model.LslBoardComm;
import com.blackberry.s20240130103.lsl.model.LslCommReply;

public interface LslService {


	/* 게시판 */
	
	// 자유 게시판 리스트 
	int totalBoardFree();
	List<LslBoardComm> boardFreeList(LslBoardComm lslBoardComm);

	// 질문 게시판 리스트 
	int totalBoardAsk();
	List<LslBoardComm> boardAskList(LslBoardComm lslBoardComm);
	

	// 게시판 검색 
	List<LslBoardComm> boardFreeSearch(LslBoardComm lslBoardComm);
	List<LslBoardComm> boardAskSearch(LslBoardComm lslBoardComm);



	// 게시판 상세 페이지 
	LslBoardComm boardFreeContents(int cboard_no);
	LslBoardComm boardAskContents(int cboard_no);
	
	
	// 게시판 상세 페이지 댓글 카운트
	int boardReplyCnt(int cboard_no);
	
	
    // 게시판 글쓰기
	int boardFreeWriteInsert(LslBoardComm lslBoardComm);
	int boardAskWriteInsert(LslBoardComm lslBoardComm);
	
	// 게시판 글 삭제
	int deleteFreeBoard(LslBoardComm lslBoardComm);
	int deleteAskBoard(LslBoardComm lslBoardComm);

	// 게시판 글 조회수 
	int boardFreeViewCnt(LslBoardComm lslBoardComm);
	int boardAskViewCnt(LslBoardComm lslBoardComm);

	// 게시판 수정 페이지
	LslBoardComm boardFreeModify(int cboard_no);
	LslBoardComm boardAskModify(int cboard_no);
	
	// 게시판 글 수정 
	int boardFreeUpdate(LslBoardComm lslBoardComm);
	int boardAskUpdate(LslBoardComm lslBoardComm);
	
	
	// 게시판 파일 업로드 
	
	
	
	
	
	/* 댓글 */
	
	// 댓글 리스트 
	List<LslCommReply> replyBoardFreeAskList(int cboard_no);

	// 댓글 등록
	int insertBoardReply(LslCommReply lslCommReply);
	// 댓글 삭제 
	int deleteBoardReply(int creply_no);

	// 댓글 수정
	int modifyBoardReply(LslCommReply lslCommReply);
	
	
	


	
	
	
	

	

	







}
