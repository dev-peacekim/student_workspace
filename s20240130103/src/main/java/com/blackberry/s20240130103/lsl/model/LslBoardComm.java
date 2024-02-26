package com.blackberry.s20240130103.lsl.model;

import java.util.Date;

import lombok.Data;

@Data
public class LslBoardComm {
    private int cboard_no;
    private String cboard_title; // 오타 수정: cboard_tilte -> cboard_title
    private String cboard_content;
    private int cboard_viewcnt;
    private Date cboard_date;
    private int user_no;
    private int cboard_delete_chk;
    private int comm_big;
    private int comm_mid;
    private int comm_big2;
    private int comm_mid2;
    
}


