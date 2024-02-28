package com.blackberry.s20240130103.lsl.model;

import lombok.Data;

@Data
public class LslBoardComm {
	// board_comm
	private int cboard_no;
	private String cboard_title;
	private String cboard_content;
	private int cboard_viewcnt;
	private String cboard_date;
	private Long user_no;
	private int cboard_delete_chk;
	private int comm_big;
	private int comm_mid;
	private int comm_big2;
	private int comm_mid2;
	private String comm_update_date;

	// Part part

	// board_file_comm
	private int cboard_file_cnt;
	private String cboard_file_name;
	private String cboard_file_user_name;

	// 조회용
	private String search;
	private String keyword;
	private String pageNum;
	private int start;
	private int end;
	// Page 정보
	private String currentPage;
	private int pageBlock;
	private int rowPage;
	private int startPage;			private int endPage;
	private int total;				private int totalPage;
	
	
	// 댓글 조회용
	private Long creply_no;
	private int creply_cnt;
	// 회원 이름 조회용

	private String user_name;
}
