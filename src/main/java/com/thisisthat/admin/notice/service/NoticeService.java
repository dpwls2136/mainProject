package com.thisisthat.admin.notice.service;

import java.util.List;

import com.thisisthat.admin.notice.vo.NoticeVO;

public interface NoticeService {
	
	void insertNotice(NoticeVO noticeVO);
	
	void updateNotice(NoticeVO noticeVO);
	
	void deleteNotice(NoticeVO noticeVO);
	
	NoticeVO Article(NoticeVO noticeVO);
	
	List<NoticeVO> noticeList(NoticeVO noticeVO);
}
