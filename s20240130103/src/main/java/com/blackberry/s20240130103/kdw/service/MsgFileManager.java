package com.blackberry.s20240130103.kdw.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MsgFileManager extends AbstractView {

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		File file = (File) model.get("file");
		String originalFileName = (String) model.get("originalFileName");
		
		// 한글 처리
		response.setCharacterEncoding("UTF-8");
		// 총 파일의 크기
		response.setContentLengthLong(file.length());
		
		// 다운로드시 파일의 이름을 인코딩
		String encodedFileName = URLEncoder.encode(originalFileName, "UTF-8");
		// header 설정
		response.setHeader("Content-Disposition", "attachment;filename=\"" + encodedFileName + "\"");
		response.setHeader("Content-Transfer-Encoding", "binary");
		// HDD에서 파일을 읽고
		FileInputStream fileInputStream = new FileInputStream(file);
		// 'Client'로 전송 준비
		OutputStream outputStream = response.getOutputStream();
		
		// 전송
		byte[] buffer = new byte[4096];
		int bytesRead;

		while ((bytesRead = fileInputStream.read(buffer)) != -1) {
			outputStream.write(buffer, 0, bytesRead);
		}
		// 자원 해제
		fileInputStream.close();
		outputStream.close();
	}
}
