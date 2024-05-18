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

    public List<Map<String, Object>> adminReportList(List<Object> param) {
		String sql = " SELECT * FROM             \r\n" + 
		      		"    (SELECT ROWNUM RN,RPT_STATE, RPT_NO, TO_CHAR(RPT_DATE,'YYYY.MM.DD') RPT_DATE, MEM_ID, EST_NO,\r\n" + 
		      		"                RPT_TITLE, RPAD(RPT_CONTENT, 40, ' ') RPT_CONTENT\r\n" + 
		      		"                FROM REPORT\r\n" + 
		      		"                ORDER BY RPT_DATE DESC)\r\n" + 
		      		"                WHERE RN>=? AND RN<=?";
		      
		return jdbc.selectList(sql,param);
	}
		   
	public List<Map<String, Object>> adminReportRecord(List<Object> param) {
	    String sql = "SELECT * FROM\r\n" + 
		      		"    (SELECT ROWNUM RN, RPT_STATE, RPT_NO, TO_CHAR(RPT_DATE,'YYYY.MM.DD') RPT_DATE,\r\n" + 
		      		"    MEM_ID, EST_NO, RPT_TITLE, RPAD(RPT_CONTENT, 40, ' ') RPT_CONTENT\r\n" + 
		      		"    FROM REPORT\r\n" + 
		      		"    WHERE RPT_STATE = ?\r\n" + 
		      		"    ORDER BY RPT_DATE DESC)\r\n" + 
		      		"    WHERE RN>=? AND RN<=?";
		   
		return jdbc.selectList(sql, param);
	}

	public Map<String, Object> adminReportDetail(List<Object> param) {
		String sql = " SELECT R.RPT_STATE, R.RPT_NO, TO_CHAR(R.RPT_DATE,'YYYY.MM.DD') RPT_DATE, R.MEM_ID 신고자, \r\n" + 
				"       R.RPT_TITLE, R.RPT_CONTENT,\r\n" + 
				"       E.EST_NO, E.RET_ID 신고당한공인중개사, E.MEM_ID 신고당한회원, E.EST_NAME, TO_CHAR(E.EST_STATE) EST_STATE, TO_CHAR(E.EST_RPTCNT) EST_RPTCNT, E.EST_DELYN\r\n" + 
				"FROM REPORT R, ESTATE E\r\n" + 
				"WHERE RPT_NO = ?\r\n" + 
				"    AND R.EST_NO = E.EST_NO\r\n" + 
				"ORDER BY RPT_NO DESC";
		
		return jdbc.selectOne(sql, param);
	}
	
	public List<Map<String, Object>> noticeList() {
		String sql = " SELECT NTC_NO, TO_CHAR(NTC_DATE, 'YYYY.MM.DD') NTC_DATE, \r\n" + 
					 "       SUBSTR(NTC_TITLE,1,30) NTC_TITLE, SUBSTR(NTC_CONTENT,1,50) NTC_CONTENT\r\n" + 
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

	public Map<String, Object> noticeListDetail(List<Object> param) {
		String sql = " SELECT NTC_NO, TO_CHAR(NTC_DATE, 'YYYY.MM.DD') NTC_DATE, NTC_TITLE, NTC_CONTENT\r\n" + 
				"FROM NOTICE\r\n" + 
				"WHERE NTC_NO = ?";

	return jdbc.selectOne(sql, param);
	}

	public List<Map<String, Object>> adminReportDetailEst(List<Object> param1) {
		String sql = " SELECT TO_CHAR(R.RPT_STATE) RPT_STATE, R.RPT_NO, TO_CHAR(R.RPT_DATE,'YYYY.MM.DD') RPT_DATE, R.MEM_ID 신고자, \r\n" + 
				"       R.RPT_TITLE, R.RPT_CONTENT,\r\n" + 
				"       E.EST_NO, E.RET_ID 신고당한공인중개사, E.MEM_ID 신고당한회원, E.EST_NAME, TO_CHAR(E.EST_STATE) EST_STATE, E.EST_RPTCNT, E.EST_DELYN\r\n" + 
				"FROM REPORT R, ESTATE E\r\n" + 
				"WHERE E.EST_NO = ?\r\n" + 
				"    AND R.EST_NO = E.EST_NO\r\n" + 
				"ORDER BY RPT_NO DESC";

	return jdbc.selectList(sql, param1);
	}

	public Map<String, Object> adminReportRetRptcnt(List<Object> retRptcnt) {
		String sql = " SELECT RET_ID, TO_CHAR(RET_RPTCNT) RET_RPTCNT, RET_DELYN\r\n" + 
				"FROM REALTOR\r\n" + 
				"WHERE RET_ID = ?";

	return jdbc.selectOne(sql, retRptcnt);
	}
	
	public Map<String, Object> adminReportMemRptcnt(List<Object> retRptcnt) {
		String sql = " SELECT MEM_ID, TO_CHAR(MEM_RPTCNT) MEM_RPTCNT, MEM_DELYN\r\n" + 
				"FROM MEMBER\r\n" + 
				"WHERE MEM_ID = ?";

	return jdbc.selectOne(sql, retRptcnt);
	}

	public int retCntAdd(List<Object> selId) {
		String sql = " UPDATE REALTOR\r\n" + 
				"SET RET_RPTCNT = (RET_RPTCNT+1)\r\n" + 
				"WHERE RET_ID = ?";
		
		return jdbc.update(sql, selId);
	}

	public int memCntAdd(List<Object> selId) {
		String sql = " UPDATE MEMBER\r\n" + 
				"SET MEM_RPTCNT = (MEM_RPTCNT+1)\r\n" + 
				"WHERE MEM_ID = ?";
		
		return jdbc.update(sql, selId);
	}

	public int rptReject(List<Object> rptNo3) {
		String sql = " UPDATE REPORT\r\n" + 
				"SET RPT_STATE = 'FIN'\r\n" + 
				"WHERE RPT_NO = ?";
		
		return jdbc.update(sql, rptNo3);
	}

	public int rptDelynFin(List<Object> rptCntNo) {
		String sql = " UPDATE REPORT\r\n" + 
				"SET RPT_STATE = 'FIN'\r\n" + 
				"WHERE RPT_NO = ?";
		
		return jdbc.update(sql, rptCntNo);
	}

	public int estCntAdd(List<Object> estCntNo) {
		String sql = " UPDATE ESTATE\r\n" + 
				"SET EST_RPTCNT = (EST_RPTCNT+1)\r\n" + 
				"WHERE EST_NO = ?";
		
		return jdbc.update(sql, estCntNo);
	}

	public void estDelynUpdate(List<Object> estCntNo) {
		String sql = " UPDATE ESTATE\r\n" + 
				"SET EST_DELYN = 'Y'\r\n" + 
				"WHERE EST_NO = ? AND EST_RPTCNT > 5";
		
		jdbc.update(sql, estCntNo);
	}

	public void retDelynUpdate(List<Object> selId) {
		String sql = " UPDATE REALTOR\r\n" + 
				"SET RET_DELYN = 'Y'\r\n" + 
				"WHERE RET_ID = ? AND RET_RPTCNT > 5";
		
		jdbc.update(sql, selId);
	}

	public void memDelynUpdate(List<Object> selId) {
		String sql = " UPDATE MEMBER\r\n" + 
				"SET MEM_DELYN = 'Y'\r\n" + 
				"WHERE MEM_ID = ? AND MEM_RPTCNT > 5";
		
		jdbc.update(sql, selId);
	}
	
	public List<Map<String, Object>> adminMemberList(List<Object> param) {
		String sql=" SELECT *\r\n" + 
				"FROM\r\n" + 
				"    (SELECT ROWNUM RN, M.*\r\n" + 
				"    FROM\r\n" + 
				"        (SELECT M.MEM_ID MEM_ID,\r\n" + 
				"                M.MEM_PW MEM_PW,\r\n" + 
				"                M.MEM_NAME MEM_NAME,\r\n" + 
				"                M.MEM_TEL MEM_TEL,\r\n" + 
				"                M.MEM_ADDRESS MEM_ADDRESS,\r\n" + 
				"                M.MEM_NICKNAME MEM_NICKNAME,\r\n" + 
				"                M.MEM_BANK MEM_BANK,\r\n" + 
				"                M.MEM_DELYN MEM_DELYN,\r\n" + 
				"                M.TIC_TIER TIC_TIER,\r\n" + 
				"                M.MEM_RPTCNT MEM_RPTCNT\r\n" + 
				"        FROM MEMBER M JOIN TICKET T ON (M.TIC_TIER = T.TIC_TIER)\r\n" + 
				"                ORDER BY TIC_NO DESC) M\r\n" + 
				"                ORDER BY RN)\r\n" + 
				"WHERE (RN>=? AND RN<=?)";

		return jdbc.selectList(sql, param);
	}

	public List<Map<String, Object>> adminRealtorList(List<Object> param) {
		String sql=" SELECT *\r\n" + 
				"FROM\r\n" + 
				"    (SELECT ROWNUM RN, R.*\r\n" + 
				"    FROM\r\n" + 
				"        (SELECT R.RET_ID RET_ID,\r\n" + 
				"                R.RET_PW RET_PW,\r\n" + 
				"                R.RET_NAME RET_NAME,\r\n" + 
				"                R.RET_TEL RET_TEL,\r\n" + 
				"                R.RET_MAIL RET_MAIL,\r\n" + 
				"                R.RET_RPTCNT RET_RPTCNT,\r\n" + 
				"                R.RET_BANK RET_BANK,\r\n" + 
				"                R.RET_DELYN RET_DELYN,\r\n" + 
				"                R.TIC_TIER TIC_TIER,\r\n" + 
				"                R.COM_NO COM_NO\r\n" + 
				"        FROM REALTOR R JOIN TICKET T ON (R.TIC_TIER = T.TIC_TIER)\r\n" + 
				"                ORDER BY TIC_NO DESC) R\r\n" + 
				"                ORDER BY RN)\r\n" + 
				"WHERE (RN>=? AND RN<=?)";

		return jdbc.selectList(sql, param);
	}

	public List<Map<String, Object>> adminRetcomList(List<Object> param) {
		String sql=" SELECT C.COM_NO COM_NO,\r\n" + 
				"C.COM_NAME COM_NAME,\r\n" + 
				"C.COM_ADDRESS COM_ADDRESS,\r\n" + 
				"C.COM_TEL COM_TEL,\r\n" + 
				"C.COM_INTRO COM_INTRO,\r\n" + 
				"C.COM_DELYN COM_DELYN,\r\n" + 
				"R.RET_ID RET_ID,\r\n" + 
				"R.RET_PW RET_PW,\r\n" + 
				"R.RET_NAME RET_NAME,\r\n" + 
				"R.RET_TEL RET_TEL,\r\n" + 
				"R.RET_MAIL RET_MAIL,\r\n" + 
				"R.RET_RPTCNT RET_RPTCNT,\r\n" + 
				"R.RET_BANK RET_BANK,\r\n" + 
				"R.RET_DELYN RET_DELYN,\r\n" + 
				"R.TIC_TIER TIC_TIER\r\n" + 
				"FROM COMPANY C JOIN REALTOR R ON (C.COM_NO = R.COM_NO)";

		return jdbc.selectList(sql, param);
	}

	public Map<String, Object> myCom(List<Object> myCom) {
		String sql = " SELECT * FROM COMPANY WHERE COM_NO = ?";

	return jdbc.selectOne(sql, myCom);
	}

	public List<Map<String, Object>> admincomList(List<Object> param) {
		String sql=" SELECT *\r\n" + 
				"FROM\r\n" + 
				"(SELECT ROWNUM RN, COM_NO, COM_NAME, COM_TEL, COM_ADDRESS, COM_INTRO\r\n" + 
				"FROM \r\n" + 
				"    (SELECT COM_NO, COM_NAME, COM_TEL, COM_ADDRESS, COM_INTRO\r\n" + 
				"    FROM COMPANY\r\n" + 
				"    WHERE COM_DELYN='N'\r\n" + 
				"    ORDER BY COM_NAME))\r\n" + 
				"WHERE (RN>=? AND RN<=?)";

		return jdbc.selectList(sql, param);
	}
	
}
