package com.blackberry.s20240130103.kdw.model;

import lombok.Data;

@Data
public class BoardProject {
	
	private   Long pboard_no;          // 글번호(SEQ)
	private   Long project_no;         // 프로젝트번호
	private String pboard_title;       // 제목
	private String pboard_content; 	   // 내용
	private String pboard_date;        // 작성일
	private    int pboard_cnt;         // 조회수
	private   Long user_no;            // 작성자번호
	private    int pboard_delete_chk;  // 삭제상태 0 = 삭제X, 1 = 삭제O
	private String pboard_update_date; // 최종수정일자
	
}
