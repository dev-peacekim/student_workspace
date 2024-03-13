package com.blackberry.s20240130103.ykm.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.blackberry.s20240130103.ykm.dao.YkmBoardDao;
import com.blackberry.s20240130103.ykm.model.YkmBoardComm;
import com.blackberry.s20240130103.ykm.model.YkmBoardCommFile;
import com.blackberry.s20240130103.ykm.model.YkmBoardCommReply;
import com.blackberry.s20240130103.ykm.model.YkmPaging;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class YkmServiceImpl implements YkmService {

	private final YkmBoardDao ykmBoardDao;

	/* 스터디 게시판 */
	
	// 게시판 글쓰기
	@Override
	public int writePost(YkmBoardComm ykmBoardComm, String studyFilePath, List<MultipartFile> fileList) {
		
		System.out.println("YkmServiceImpl writePost start---*");
		int result = ykmBoardDao.writePost(ykmBoardComm);
		System.out.println("test22 : " + ykmBoardComm);
		for (MultipartFile file : fileList) {
			// 파일 저장
			String fileName = file.getOriginalFilename();
			String fileExt = fileName.substring(fileName.lastIndexOf("."));
			String fileUuid = UUID.randomUUID().toString()+ fileExt;
			
			File files = new File(studyFilePath, fileUuid);
			try {
				Path uploadPath = Paths.get(studyFilePath + File.separator + fileUuid);
				
				Files.copy(file.getInputStream(), files.toPath(), StandardCopyOption.REPLACE_EXISTING);
				
				YkmBoardCommFile ykmBoardCommFile = new YkmBoardCommFile();
				System.out.println("YkmServiceImpl writePost ykmBoardCommFile : "+ykmBoardCommFile);
				ykmBoardCommFile.setCboard_no(ykmBoardComm.getCboard_no());
				System.out.println("YkmServiceImpl writePost ykmBoardComm.getCboard_no() "+ykmBoardComm.getCboard_no());
				ykmBoardCommFile.setCboard_file_name(fileUuid);
				ykmBoardCommFile.setCboard_file_user_name(fileName);
				
				ykmBoardDao.saveFileList(ykmBoardCommFile);
		
			} catch (Exception e) {
				e.printStackTrace();
                System.out.println("YkmServiceImpl saveFileList error : "+e.getMessage());
			}
		}
		return result;
	}


	// 게시판 리스트
	@Override
	public List<YkmBoardComm> getPostList(YkmBoardComm ykmBoardComm) {
		System.out.println("YkmServiceImpl getPostList start---*");
		List<YkmBoardComm> getPostList = ykmBoardDao.getPostList(ykmBoardComm);
		System.out.println("YkmServiceImpl getPostList result --> " + getPostList.size());
		return getPostList;
	}
	
	// 게시글 보여주기
	@Override
	public YkmBoardComm getPost(int cboard_no) {
		
		return ykmBoardDao.getPost(cboard_no);
	}
	
	/*
	Iterator<YkmBoardComm> boardCommIt = getPostList.iterator();
	
	while(boardCommIt.hasNext()) {
		YkmBoardComm board = boardCommIt.next();
		board.setCountComment(ykmBoardDao.countComment(board.getCboard_no()));
	}
	*/
	
	// 글 수정
	@Override
	public int updatePost(YkmBoardComm ykmBoardComm) {
		System.out.println("YkmServiceImpl updatePost start---*");
		System.out.println("YkmServiceImpl updatePost ykmBoardComm --> "+ ykmBoardComm);
		return ykmBoardDao.updatePost(ykmBoardComm);	
	}

	// 글 삭제
	@Override
	public int deletePost(int cboard_no) {
		System.out.println("YkmServiceImpl deletePost start---*");
		return ykmBoardDao.deletePost(cboard_no);
	}
	
	// 게시판 조회수 카운트
	@Override
	public int increseViewCount(int cboard_no) {
		return ykmBoardDao.increseViewcount(cboard_no);
	}
	
	// 게시판 모집 상태 변경 (모집중 / 모집완료)
	@Override
	public int updateRecruitment(YkmBoardComm ykmBoardComm) {
		System.out.println("YkmServiceImpl updateRecruitment start---*");
		return ykmBoardDao.updateRecruitment(ykmBoardComm);
	}

	
	// 파일 불러오기
//	@Override
//	public Map<String, Object> getFileList(int cboard_no) {
//		System.out.println("YkmServiceImpl getFileList start---*");
//		Map<String, Object> getFileList = ykmBoardDao.getFileList(cboard_no);
//		return getFileList;
//	}
//
	@Override
	public List<YkmBoardCommFile> getFileList(int cboard_no) {
		System.out.println("YkmServiceImpl getFileList start---*");
		List<YkmBoardCommFile> getFileList = ykmBoardDao.getFileList(cboard_no);
		return getFileList;
	}


	
	
	// Reply RESTful API

	@Override
	public List<YkmBoardCommReply> getCommentList(int cboard_no) {
		List<YkmBoardCommReply> getCommentList = ykmBoardDao.getCommentList(cboard_no);
		System.err.println("YkmServiceImpl getCommentList result --> " + getCommentList.size());
		return getCommentList;
	}

	@Override
	public int writeComment(YkmBoardCommReply ykmBoardCommReply) {
		return ykmBoardDao.writeComment(ykmBoardCommReply);
	}

	@Override
	public int deleteComment(int creply_no) {
		return ykmBoardDao.deleteComment(creply_no);
	}

	@Override
	public int updateComment(YkmBoardCommReply ykmBoardCommReply) {
		return ykmBoardDao.updateComment(ykmBoardCommReply);
	}

	@Override
	public int countComment(int cboard_no) {
		return ykmBoardDao.countComment(cboard_no);
	}


	@Override
	public List<YkmBoardComm> getSearchList(YkmBoardComm ykmBoardComm) {
		System.out.println("YkmServiceImpl getSearchList start ---*");
		List<YkmBoardComm> getSearchList = ykmBoardDao.getSearchList(ykmBoardComm);
		System.out.println("YkmServiceImpl getSearchList result ===> "+ getSearchList.size());
		return getSearchList;
	}

	@Override
	public int getTotalCount(YkmBoardComm ykmBoardComm) {
		System.out.println("YkmServiceImpl getTotalCount start ---*");
		int result = ykmBoardDao.getTotalCount(ykmBoardComm);
		System.out.println("YkmServiceImpl getTotalCount result : "+result);
		return result;
	}






	
}