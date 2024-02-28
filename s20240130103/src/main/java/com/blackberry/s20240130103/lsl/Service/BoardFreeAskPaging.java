package com.blackberry.s20240130103.lsl.Service;

import lombok.Data;

@Data
public class BoardFreeAskPaging {
	private int currentPage = 1;	private int rowPage   = 10;
	private int pageBlock = 10;		
	private int start;				private int end;
	private int startPage;			private int endPage;
	private int total;				private int totalPage;
	
	
	public BoardFreeAskPaging(int tatal, String currentPage1) {
		this.total =tatal;
		if(currentPage1 != null ) {
			this.currentPage =Integer.parseInt(currentPage1);
		}
		//  
		start = (currentPage - 1) * rowPage + 1;
		end = start +  rowPage - 1;
		
		totalPage = (int)Math.ceil((double)total/rowPage);
		
		startPage = currentPage-(currentPage - 1)%pageBlock;
		endPage = startPage+pageBlock - 1;
		
		if(endPage > totalPage) {
			endPage = totalPage;
		}
		
	}
	
}
