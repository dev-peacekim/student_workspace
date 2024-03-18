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
	
	// 게시판 글쓰기, 파일 업로드
	@Override
	public int writePost(YkmBoardComm ykmBoardComm, String studyFilePath, List<MultipartFile> fileList) {
		
		System.out.println("YkmServiceImpl writePost start---*");
		int result = ykmBoardDao.writePost(ykmBoardComm);
		
		if(!fileList.isEmpty()) {
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
			
					//System.out.println("saveFileList : "+ ykmBoardDao.saveFileList(ykmBoardCommFile));
				} catch (Exception e) {
					e.printStackTrace();
	                System.out.println("YkmServiceImpl saveFileList error : "+e.getMessage());
				}
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

	@Override
	public List<YkmBoardCommFile> getFileList(int cboard_no) {
		System.out.println("YkmServiceImpl getFileList start---*");
		List<YkmBoardCommFile> getFileList = ykmBoardDao.getFileList(cboard_no);
		return getFileList;
	}

	// 페이징 전체 게시글 조회
	@Override
	public int getTotalCount(YkmBoardComm ykmBoardComm) {
		System.out.println("YkmServiceImpl getTotalCount start ---*");
		int result = ykmBoardDao.getTotalCount(ykmBoardComm);
		System.out.println("YkmServiceImpl getTotalCount result : "+result);
		return result;
	}
	
	// 검색
	@Override
	public List<YkmBoardComm> getSearchList(YkmBoardComm ykmBoardComm) {
		System.out.println("YkmServiceImpl getSearchList start ---*");
		List<YkmBoardComm> getSearchList = ykmBoardDao.getSearchList(ykmBoardComm);
		System.out.println("YkmServiceImpl getSearchList result ===> "+ getSearchList.size());
		return getSearchList;
	}
		
	/* 댓글 RESTful API */
	
	// 댓글 리스트
	@Override
	public List<YkmBoardCommReply> getCommentList(int cboard_no) {
		List<YkmBoardCommReply> getCommentList = ykmBoardDao.getCommentList(cboard_no);
		System.err.println("YkmServiceImpl getCommentList result --> " + getCommentList.size());
		return getCommentList;
	}

	// 댓글 등록
	@Override
	public int writeComment(YkmBoardCommReply ykmBoardCommReply) {
		return ykmBoardDao.writeComment(ykmBoardCommReply);
	}
	
	// 댓글 삭제
	@Override
	public int deleteComment(int creply_no) {
		return ykmBoardDao.deleteComment(creply_no);
	}
	
	// 댓글 수정
	@Override
	public int updateComment(YkmBoardCommReply ykmBoardCommReply) {
		return ykmBoardDao.updateComment(ykmBoardCommReply);
	}
	
	// 댓글 개수 카운트
	@Override
	public int countComment(int cboard_no) {
		return ykmBoardDao.countComment(cboard_no);
	}

	/* 대댓글 등록 */
	@Override
	public int writeReply(YkmBoardCommReply ykmBoardCommReply) {
		System.out.println("YkmServiceImpl writeReply start ---*");
		int result = ykmBoardDao.writeReply(ykmBoardCommReply);
		System.out.println("YkmServiceImpl writeReply : " + result);
		return result;
	}
	
	/* 공모전 리스트 */
	@Override
	public List<YkmBoardComm> getCntPostList(YkmBoardComm ykmBoardComm) {
		System.out.println("YkmServiceImpl getCntPostList start ---*");
		List<YkmBoardComm> getCntPostList = ykmBoardDao.getCntPostList(ykmBoardComm);
		System.out.println("YkmServiceImpl getCntPostList result");
		return getCntPostList;
	}

	// 전체 게시글 카운트
	@Override
	public int getCntTotalCount(YkmBoardComm ykmBoardComm) {
		System.out.println("YkmServiceImpl getCntTotalCount start ---*");
		int result = ykmBoardDao.getCntTotalCount(ykmBoardComm);
		System.out.println("YkmServiceImpl getCntTotalCount result : "+result);
		return result;
	}

	// 공모전 글 전체 검색
	@Override
	public List<YkmBoardComm> getCntSearchList(YkmBoardComm ykmBoardComm) {
		System.out.println("YkmServiceImpl getCntSearchList start ---*");
		List<YkmBoardComm> getCntSearchList = ykmBoardDao.getCntSearchList(ykmBoardComm);
		return getCntSearchList;
	}
	
	@Override
	public YkmBoardCommReply getReplyValue(YkmBoardCommReply ykmBoardCommReply) {
		YkmBoardCommReply replyValue = ykmBoardDao.getReplyValue(ykmBoardCommReply);
		return replyValue;
	}
	
	@Override
	public int updateReply(YkmBoardCommReply ykmBoardCommReply) {
		int result = ykmBoardDao.updateReply(ykmBoardCommReply);
		return result;
	}

	
}