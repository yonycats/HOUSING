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
		String sql = " SELECT NTC_NO, TO_CHAR(NTC_DATE, 'YYYY.MM.DD') NTC_DATE, \r\n" + 
					 "       SUBSTR(NTC_TITLE,1,20) NTC_TITLE, SUBSTR(NTC_CONTENT,1,25) NTC_CONTENT\r\n" + 
					 "FROM NOTICE\r\n" + 
					 "ORDER BY NTC_NO DESC";

		return jdbc.selectList(sql);
	}

	
	public void adminNoticeInsert(List<Object> param) {
		String sql = " INSERT INTO NOTICE (NTC_NO, NTC_TITLE, NTC_CONTENT, NTC_DATE)\r\n" + 
				"VALUES ((SELECT NVL(MAX(NTC_NO),0)+1 FROM NOTICE), ?, ?, SYSDATE)";
		
		jdbc.update(sql, param);
	}

	
	public void adminNoticeUpdate(List<Object> param) {
		String sql = " UPDATE NOTICE\r\n" + 
					 "SET NTC_TITLE = ?, NTC_CONTENT = ?\r\n" + 
					 "WHERE NTC_NO = ?";
		
		jdbc.update(sql, param);
	}

	public int adminNoticeDelete(List<Object> param) {
		String sql = " DELETE FROM NOTICE\r\n" + 
				"WHERE NTC_NO = ?";
	
	return jdbc.update(sql, param);
	}

	
}
