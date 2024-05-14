package service;

import java.util.List;
import java.util.Map;

import dao.NoticeDao;

public class NoticeService {
	private static NoticeService instance;

	private NoticeService() {

	}

	public static NoticeService getInstance() {
		if (instance == null) {
			instance = new NoticeService();
		}
		return instance;
	}
	
	
	NoticeDao noticeDao = NoticeDao.getInstance();

	
	public List<Map<String, Object>> noticeList() {
		return noticeDao.noticeList();
	}

	
	public void adminNoticeInsert(List<Object> param) {
		noticeDao.adminNoticeInsert(param);
	}

	
	public void adminNoticeUpdate(List<Object> param) {
		noticeDao.adminNoticeUpdate(param);
	}

	
	public int adminNoticeDelete(List<Object> param) {
		return noticeDao.adminNoticeDelete(param);
	}
	
	
}
