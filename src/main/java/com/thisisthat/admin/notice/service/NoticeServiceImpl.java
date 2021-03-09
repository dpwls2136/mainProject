package com.thisisthat.admin.notice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thisisthat.admin.notice.dao.NoticeDAO;
import com.thisisthat.admin.notice.vo.NoticeVO;

@Service("NoticeService")
public class NoticeServiceImpl implements NoticeService {
	@Autowired
	private NoticeDAO noticeDAO;

	@Override
	public void insertNotice(NoticeVO noticeVO) {
		noticeDAO.insertNotice(noticeVO);
	}

	@Override
	public void updateNotice(NoticeVO noticeVO) {
		 noticeDAO.updateNotice(noticeVO);		
	}

	@Override
	public void deleteNotice(NoticeVO noticeVO) {
		 noticeDAO.deleteNotice(noticeVO);
		
	}

	@Override
	public NoticeVO getNotice(NoticeVO noticeVO) {
		return noticeDAO.getNotice(noticeVO);
	}

	@Override
	public List<NoticeVO> getNoticeList(NoticeVO noticeVO) {
		 return noticeDAO.getNoticeList(noticeVO);
	}
	
}
