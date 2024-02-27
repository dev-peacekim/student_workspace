package com.blackberry.s20240130103.lsl.model;


import lombok.Data;

@Data
public class LslBoardComm {
	// board_comm
    private Long cboard_no;
    private String cboard_title; 
    private String cboard_content;
    private int cboard_viewcnt;
    private String cboard_date;
    private int user_no;
    private int cboard_delete_chk;
    private int comm_big;
    private int comm_mid;
    private int comm_big2;
    private int comm_mid2;
    
    
    // board_file_comm
	// 파일 순서 번호 Max
	private int cboard_file_cnt;
	private String cboard_file_name;
	
    
}


