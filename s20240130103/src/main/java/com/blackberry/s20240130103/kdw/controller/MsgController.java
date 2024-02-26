package com.blackberry.s20240130103.kdw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MsgController {
	
	/* ========== Side Bar view 이동 =========== */
	
	// 받은 쪽지함 view 이동
	@GetMapping(value = "msgReceivebox")
	public String receivebox() {
		return "kdw/msgReceivebox";
	}
	// 보낸 쪽지함 view 이동
	@GetMapping(value = "msgSendbox")
	public String msgSendbox() {
		return "kdw/msgSendbox";
	}
	// 쪽지 보관함 view 이동
	@GetMapping(value = "msgStorebox")
	public String msgStorebox() {
		return "kdw/msgStorebox";
	}
	// 휴지통 view 이동
	@GetMapping(value = "msgTrashbox")
	public String msgTrashbox() {
		return "kdw/msgTrashbox";
	}
	
	/* ========== Side Bar End =========== */
	
	
	/* ========== 버튼 view 이동 =========== */
	
    // 쪽지쓰기 버튼 클릭시 쪽지쓰기 view 이동
    @GetMapping(value = "writeMessage")
    public String writeMessagePage() {
        return "kdw/writeMessage"; 
    }
    
    /* ========== 쪽지 쓰기 버튼 이동 End =========== */
    
	
	
}
