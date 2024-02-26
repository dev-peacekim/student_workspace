package com.blackberry.s20240130103.lsl.model;

import java.util.Date;

import lombok.Data;

@Data
public class LslBoardComm {
	// 글번호 Seq
	private int cboard_no;
	private String cboard_tilte;
	private String cboard_content;
	private int cboard_viewcnt;
	private Date cboard_date;
	// 작성자 번호 FK
	private int user_no;
	private  int cboard_delete_chk;
	
	// 공통 분류 테이블
	private int comm_big;
	private int comm_mid;
	private int comm_big2;
	private int comm_mid2;
	
	

}
