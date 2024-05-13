package dao;

import java.util.List;
import java.util.Map;

import util.JDBCUtil;

public class NoticeDao {
	private static NoticeDao instance;

	private NoticeDao() {

	}

	public static NoticeDao getInstance() {
		if (instance == null) {
			instance = new NoticeDao();
		}
		return instance;
	}
	
	JDBCUtil jdbc = JDBCUtil.getInstance();

	
	public List<Map<String, Object>> noticeList() {
		String sql = " SELECT TO_CHAR(NTC_DATE, 'YYYY.MM.DD') NTC_DATE, \r\n" + 
					 "       SUBSTR(NTC_TITLE,1,20) NTC_TITLE, SUBSTR(NTC_CONTENT,1,25) NTC_CONTENT\r\n" + 
					 "FROM NOTICE\r\n" + 
					 "ORDER BY NTC_DATE DESC";

		return jdbc.selectList(sql);
	}
	
	
	
	
	
	
}
