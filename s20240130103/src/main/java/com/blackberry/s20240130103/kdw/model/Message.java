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
    
    // 멀티 파일 저장용 
    private MultipartFile[] files;
    
    // 검색용
	private String keyword; // 검색 키워드
	private String type; // 검색 타입
}
