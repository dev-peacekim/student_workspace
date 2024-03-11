package com.blackberry.s20240130103.ykm.model;


import lombok.Data;

@Data
public class YkmPaging {
	//  페이징, 검색
	private int currentPage = 1; 	// 현재 페이지 번호
	private int itemsPerPage = 5;	// 한 페이지에 보여질 항목의 수
	private int pageBlock = 5;		// 페이지 블록의 크기
	private int start;				// 시작 숫자
	private int startPage;			// 현재 페이지 블록의 시작 
	private int end;				// 끝 숫자
	private int endPage;			// 현재 페이지 블록의 끝
	private int total;			// 전체 항목의 수
	private int totalPages;			// 전체 페이지의 수
	
	public YkmPaging(int total, String currentPage1) {
		
		this.total = total;
		
		if(currentPage1 != null && !currentPage1.isEmpty()) {
			this.currentPage = Integer.parseInt(currentPage1);
		}
		// 2 5
		start = (currentPage -1 ) * itemsPerPage +1; // 6
		end = start + itemsPerPage -1; // 10
		totalPages = (int)Math.ceil((double)total / itemsPerPage); // 7
		startPage = currentPage - (currentPage-1) % pageBlock; // 1
		endPage = startPage + pageBlock; // 11
	
		if (endPage > totalPages) {
			endPage = totalPages;
		}
	}


}
