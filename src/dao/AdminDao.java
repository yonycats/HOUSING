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


	public Map<String, Object> daySaleTotal(List<Object> param) {
		String sql = " SELECT TO_CHAR(SUM(P.TIC_PRICE), '999,999,999') PRICE\r\n" + 
				"FROM (SELECT SAL_DATE, S.TIC_TIER, T.TIC_PRICE\r\n" + 
				"    FROM SALELIST S, TICKET T\r\n" + 
				"    WHERE S.TIC_TIER = T.TIC_TIER \r\n" + 
				"        AND SUBSTR(SAL_DATE,1,4) = ?\r\n" + 
				"        AND SUBSTR(SAL_DATE,6,2) = ?\r\n" + 
				"        AND SUBSTR(SAL_DATE,9,2) = ?) P"; 
		
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
	
	
}
