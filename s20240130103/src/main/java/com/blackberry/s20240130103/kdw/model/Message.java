package com.blackberry.s20240130103.kdw.model;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class Message {
	
    private Long msg_no;
    private String msg_title;
    private String msg_content;
    private String msg_createdate;
    private String msg_readdate;
    private Long msg_sender;
    private Long msg_receiver;
    private int msg_delete_chk;
    private int msg_store_chk;
    
    // 파일 저장용
    private MultipartFile[] files;
    
}
