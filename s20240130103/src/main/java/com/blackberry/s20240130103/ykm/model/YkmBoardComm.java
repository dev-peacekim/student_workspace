package com.blackberry.s20240130103.ykm.model;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class YkmBoardComm {

	// BoardComm
	private int cboard_no;
	private String cboard_title;
	private String cboard_content;
	private int cboard_viewcnt;
	private Date cboard_date;
	private Long user_no;
	private int cboard_delete_chk;
	private int comm_big;
	private int comm_mid;
	private int comm_big2;
	private int comm_mid2;
	private Date comm_update_date;
	
	// BoardCommFile
	private int cboard_file_cnt;
	private String cboard_name;
	
	// Users 조회용
	private String user_id;
	private String user_nic;
	private String user_profile;
	
	// 댓글 카운트
	private int countComment;
	private int reply_count;		

	// 검색 조회용
	private String type;
	private String keyword;
	
	// 페이징 조회용
	private String pageNum;
	private int start;
	private int end;
	private String currentPage;
	
	
}
