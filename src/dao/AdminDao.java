package dao;

import java.util.List;
import java.util.Map;

import util.JDBCUtil;

public class AdminDao {
	private static AdminDao instance;

	private AdminDao() {

	}

	public static AdminDao getInstance() {
		if (instance == null) {
			instance = new AdminDao();
		}
		return instance;
	}
	
	
	JDBCUtil jdbc = JDBCUtil.getInstance();
	
	
	public Map<String, Object> login (List<Object> param) {
		String sql = " SELECT * \r\n" + 
					 "FROM ADMIN\r\n" + 
					 "WHERE ADM_ID = ? \r\n" + 
					 "AND ADM_PW = ?";
		
		return jdbc.selectOne(sql, param);
	}

	public List<Map<String, Object>> tichetList() {
		String sql = " SELECT TIC_NO, TIC_TIER, TO_CHAR(TIC_PRICE, '999,999,999') TIC_PRICE, TIC_COMMENT\r\n" + 
				"FROM TICKET\r\n" + 
				"ORDER BY TIC_NO";
		
		return jdbc.selectList(sql);
	}

	public void adminTicketInsert(List<Object> param) {
		String sql = " INSERT INTO TICKET (TIC_NO, TIC_TIER, TIC_PRICE, TIC_COMMENT)\r\n" + 
					 "VALUES ((SELECT NVL(MAX(TIC_NO),0)+1 FROM TICKET), ?, ?, ?)";
				
		jdbc.update(sql, param);
	}
	
	public void adminTicketUpdate(List<Object> param) {
		String sql = " UPDATE TICKET\r\n" + 
					 "SET TIC_TIER = ?, TIC_PRICE = ?, TIC_COMMENT = ?\r\n" + 
					  "WHERE TIC_NO = ?";
	
		jdbc.update(sql, param);
	}

	public int adminTicketDelete(List<Object> param) {
			String sql = " DELETE FROM TICKET\r\n" + 
					"WHERE TIC_NO = ?";
		
		return jdbc.update(sql, param);
	}

	public List<Map<String, Object>> adminReportList() {
		String sql = " SELECT RPT_STATE, RPT_NO, TO_CHAR(RPT_DATE,'YYYY.MM.DD') RPT_DATE, MEM_ID, EST_NO, \r\n" + 
					 "       RPAD(RPT_TITLE, 20, ' ') RPT_TITLE, RPAD(RPT_CONTENT, 40, ' ') RPT_CONTENT\r\n" + 
					 "FROM REPORT\r\n" + 
					 "ORDER BY RPT_DATE DESC";
		
		return jdbc.selectList(sql);
	}

	public List<Map<String, Object>> adminReportDoing() {
		String sql = " SELECT RPT_STATE, RPT_NO, TO_CHAR(RPT_DATE,'YYYY.MM.DD') RPT_DATE, MEM_ID, EST_NO, \r\n" + 
					 "       RPAD(RPT_TITLE, 20, ' ') RPT_TITLE, RPAD(RPT_CONTENT, 40, ' ') RPT_CONTENT\r\n" + 
					 "FROM REPORT\r\n" + 
					 "WHERE RPT_STATE = 'DO'\r\n" + 
					 "ORDER BY RPT_DATE DESC";
	
		return jdbc.selectList(sql);
	}

	public List<Map<String, Object>> adminReportFinish() {
		String sql = " SELECT RPT_STATE, RPT_NO, TO_CHAR(RPT_DATE,'YYYY.MM.DD') RPT_DATE, MEM_ID, EST_NO, \r\n" + 
					 "       RPAD(RPT_TITLE, 20, ' ') RPT_TITLE, RPAD(RPT_CONTENT, 40, ' ') RPT_CONTENT\r\n" + 
					 "FROM REPORT\r\n" + 
					 "WHERE RPT_STATE = 'FIN'\r\n" + 
					 "ORDER BY RPT_DATE DESC";

		return jdbc.selectList(sql);
	}

	public Map<String, Object> adminReportDetail(List<Object> param) {
		String sql = " SELECT RPT_STATE, RPT_NO, TO_CHAR(RPT_DATE,'YYYY.MM.DD') RPT_DATE, MEM_ID, EST_NO, \r\n" + 
					 "       RPT_TITLE, RPT_CONTENT\r\n" + 
					 "FROM REPORT\r\n" + 
					 "WHERE RPT_NO = ?\r\n" + 
					 "ORDER BY RPT_DATE DESC";
		
		return jdbc.selectOne(sql, param);
	}
	
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
	
	public Map<String, Object> adminSaleDay(List<Object> param) {
		String sql = " SELECT TO_CHAR(SUM(P.TIC_PRICE), '999,999,999') PRICE\r\n" + 
				"FROM (SELECT SAL_DATE, S.TIC_TIER, T.TIC_PRICE\r\n" + 
				"    FROM SALELIST S, TICKET T\r\n" + 
				"    WHERE S.TIC_TIER = T.TIC_TIER \r\n" + 
				"        AND SAL_DATE = ?) P"; 
		
		return jdbc.selectOne(sql, param);
	}
	
	public List<Map<String, Object>> daySaleTier(List<Object> param) {
		String sql = " SELECT *\r\n" + 
				"FROM\r\n" + 
				"(SELECT T.TIC_NO, S.SAL_DATE, S.TIC_TIER, COUNT(S.TIC_TIER) 판매갯수, TO_CHAR(SUM(TIC_PRICE),'999,999,999') TIC_PRICE\r\n" + 
				"FROM SALELIST S, TICKET T\r\n" + 
				"WHERE S.TIC_TIER = T.TIC_TIER \r\n" + 
				"      AND SAL_DATE = ?\r\n" + 
				"GROUP BY S.TIC_TIER, S.SAL_DATE, T.TIC_NO) P\r\n" + 
				"ORDER BY P.TIC_NO";

		return jdbc.selectList(sql, param);
	}

	public Map<String, Object> adminSaleMonth(List<Object> param) {
		String sql = " SELECT TO_CHAR(SUM(T.TIC_PRICE),'999,999,999') TIC_PRICE\r\n" + 
					 "FROM SALELIST S, TICKET T\r\n" + 
					 "WHERE S.TIC_TIER = T.TIC_TIER \r\n" + 
					 "    AND (S.SAL_DATE BETWEEN TO_DATE(?, 'YYYY-MM-DD') \r\n" + 
					 "        AND LAST_DAY(TO_DATE(?, 'YYYY-MM-DD')))";
		
		return jdbc.selectOne(sql, param);
	}

	public List<Map<String, Object>> monthSaleTier(List<Object> param) {
		String sql = " SELECT P.TIC_NO, P.SAL_DATE, P.TIC_TIER, COUNT(P.TIC_TIER) 판매갯수, TO_CHAR(SUM(P.TIC_PRICE),'999,999,999') TIC_PRICE\r\n" + 
					 "FROM\r\n" + 
					 "    (SELECT T.TIC_NO, TO_CHAR(S.SAL_DATE, 'YYYY.MM') SAL_DATE, S.TIC_TIER, SUM(TIC_PRICE) TIC_PRICE\r\n" + 
					 "    FROM SALELIST S, TICKET T\r\n" + 
					 "    WHERE S.TIC_TIER = T.TIC_TIER \r\n" + 
					 "        AND (S.SAL_DATE BETWEEN TO_DATE(?, 'YYYY-MM-DD') \r\n" + 
					 "        AND LAST_DAY(TO_DATE(?, 'YYYY-MM-DD')))\r\n" + 
					 "    GROUP BY S.TIC_TIER, S.SAL_DATE, T.TIC_NO\r\n" + 
					 "    ORDER BY S.SAL_DATE DESC) P\r\n" + 
					 "GROUP BY P.SAL_DATE, P.TIC_TIER, P.TIC_NO\r\n" + 
					 "ORDER BY P.TIC_NO";
		
		return jdbc.selectList(sql, param);
	}
	

	public Map<String, Object> adminSaleYear(List<Object> param) {
		String sql = " SELECT TO_CHAR(SUM(P.TIC_PRICE),'999,999,999') TIC_PRICE\r\n" + 
					 "FROM (SELECT SAL_DATE, S.TIC_TIER, T.TIC_PRICE\r\n" + 
					 "    FROM SALELIST S, TICKET T\r\n" + 
					 "    WHERE S.TIC_TIER = T.TIC_TIER \r\n" + 
					 "        AND (S.SAL_DATE BETWEEN TO_DATE(?, 'YYYY-MM-DD') \r\n" + 
					 "        AND LAST_DAY(TO_DATE(?, 'YYYY-MM-DD')))) P";
	
		return jdbc.selectOne(sql, param);
	}

	public List<Map<String, Object>> yearSaleTier(List<Object> param) {
		String sql = " SELECT P.TIC_NO, P.SAL_DATE, P.TIC_TIER, COUNT(P.TIC_TIER) 판매갯수, TO_CHAR(SUM(P.TIC_PRICE),'999,999,999') TIC_PRICE\r\n" + 
					 "FROM\r\n" + 
					 "(SELECT T.TIC_NO, TO_CHAR(S.SAL_DATE, 'YYYY') SAL_DATE, S.TIC_TIER, SUM(TIC_PRICE) TIC_PRICE\r\n" + 
					 "FROM SALELIST S, TICKET T\r\n" + 
					 "WHERE S.TIC_TIER = T.TIC_TIER \r\n" + 
					 "    AND (S.SAL_DATE BETWEEN TO_DATE(?, 'YYYY-MM-DD') \r\n" + 
					 "        AND LAST_DAY(TO_DATE(?, 'YYYY-MM-DD')))\r\n" + 
					 "GROUP BY S.TIC_TIER, S.SAL_DATE, T.TIC_NO\r\n" + 
					 "ORDER BY S.SAL_DATE DESC) P\r\n" + 
					 "GROUP BY P.SAL_DATE, P.TIC_TIER, P.TIC_NO\r\n" + 
					 "ORDER BY P.TIC_NO";
	
		return jdbc.selectList(sql, param);
	}
	
	
}
