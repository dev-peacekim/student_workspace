package com.blackberry.s20240130103.lsl.model;

import lombok.Data;

@Data
public class LslBoardCommFile {
	
	private int cboard_no;
	// 파일 순서 번호 Max
	private int cboard_file_cnt;
	private String cboard_file_name;
	
}
