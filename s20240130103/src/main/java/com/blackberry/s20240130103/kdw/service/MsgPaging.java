package com.blackberry.s20240130103.kdw.service;

import lombok.Data;

@Data
public class MsgPaging {
    // 현재 페이지
    private int currentPage = 1;

    // 한 페이지에 보여질 쪽지 수
    private int rowPage = 15;

    // 페이지네이션 블록에 보여질 페이지 수
    private int pageBlock = 5;

    // 현재 페이지에 보여질 쪽지 시작과 끝 인덱스
    private int start;
    private int end;

    // 현재 페이지네이션 블록의 시작과 끝 페이지
    private int startPage;
    private int endPage;

    // 전체 쪽지 수
    private int total;

    // 전체 페이지 수
    private int totalPage;

    // 생성자
    public MsgPaging(int total, String currentPage1) {
        this.total = total;
        if (currentPage1 != null) {
            this.currentPage = Integer.parseInt(currentPage1);
        }

        // 현재 페이지에 보여질 쪽지 시작과 끝 인덱스 계산
        start = (currentPage - 1) * rowPage + 1;
        end = start + rowPage - 1;

        // 전체 페이지 수 계산
        totalPage = (int) Math.ceil((double) total / rowPage);

        // 현재 페이지네이션 블록의 시작과 끝 페이지 계산
        startPage = currentPage - (currentPage - 1) % pageBlock;
        endPage = startPage + pageBlock - 1;

        // 현재 페이지네이션 블록의 끝 페이지가 전체 페이지 수보다 크면 조정
        if (endPage > totalPage) {
            endPage = totalPage;
        }
    }
}