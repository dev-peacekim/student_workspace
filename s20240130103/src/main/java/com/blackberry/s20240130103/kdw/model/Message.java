package com.blackberry.s20240130103.kdw.model;

import lombok.Data;

@Data
public class Message {
	
    private Long msg_no;
    private String msg_title;
    private String msg_content;
    private String msg_createdate;
    private String msg_readdate;
    private Long msg_sender;
    private int msg_receiver;
    private int msg_delete_chk;
    private int msg_store_chk;
    
}
