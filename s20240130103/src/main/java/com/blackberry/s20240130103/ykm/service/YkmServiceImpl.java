package com.blackberry.s20240130103.ykm.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import org.springframework.stereotype.Service;

import com.blackberry.s20240130103.ykm.dao.YkmBoardDao;
import com.blackberry.s20240130103.ykm.model.YkmBoardComm;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class YkmServiceImpl implements YkmService {

	private final YkmBoardDao ykmBoardDao;

	@Override
	public int insertBoardStudyPost(YkmBoardComm ykmBoardComm) {
		System.out.println("YkmServiceImpl insertBoardStudyPost start---*");
		int result = ykmBoardDao.insertBoardStudyPost(ykmBoardComm);
		return result;
	}

	@Override
	public List<YkmBoardComm> spreadBoardList() {
		List<YkmBoardComm> spreadBoardList = ykmBoardDao.spreadBoardList();
		Iterator<YkmBoardComm> boardIter = spreadBoardList.iterator();
		while (boardIter.hasNext()) {
			YkmBoardComm ykmBoardComm = boardIter.next();
			ykmBoardComm.setCboard_date(ykmBoardComm.getCboard_date().substring(0, 10));
		}
		return spreadBoardList;
	}

	@Override
	public List<YkmBoardComm> renderPostContent(int cboard_no) {
		List<YkmBoardComm> renderPostContent = ykmBoardDao.renderPostContent(cboard_no);
		 for (YkmBoardComm ykmBoardComm : renderPostContent) {
		        String cboard_date = ykmBoardComm.getCboard_date();
		        StringTokenizer tokenizer = new StringTokenizer(cboard_date, " ");
		        String dateToken = tokenizer.nextToken();
		        String timeToken = tokenizer.nextToken();
		      
		        int hour = Integer.parseInt(new StringTokenizer(timeToken, ":").nextToken());
		        String amPm = (hour >= 12) ? "오후" : "오전";
		        hour = (hour > 12) ? hour - 12 : hour;
		        
		        String formattedTime = amPm + " " + hour + ":" + tokenizer.nextToken() + ":" + tokenizer.nextToken();
		        ykmBoardComm.setCboard_date(dateToken + " " + formattedTime);
		}
		return renderPostContent;
	}

	
}
