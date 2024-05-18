package dao;

import java.util.List;
import java.util.Map;

import util.JDBCUtil;

public class RealtorDao {
	private static RealtorDao instance;

	private RealtorDao() {
		
	}

	public static RealtorDao getInstance() {
		if (instance == null) {
			instance = new RealtorDao();
		}
		return instance;
	}
	
	JDBCUtil jdbc=JDBCUtil.getInstance();
	
	public Map<String,Object> login(List<Object> param) {
		String sql="SELECT * FROM REALTOR\r\n" + 
				"WHERE RET_ID=?\r\n" + 
				"AND RET_PW=?\r\n" + 
				"AND RET_DELYN='N'";
		return jdbc.selectOne(sql, param);
	}

	public void sign(List<Object> param) {
	      String sql="INSERT INTO REALTOR\r\n" + 
	    		  	 "            VALUES(?,?,?,?,?,0,0,'N','NORMAL',?)";
	      jdbc.update(sql, param);
	   }
	
	public Map<String, Object> retInfo(List<Object> param) {
		String sql = " SELECT RET_PW, RET_NAME, RET_TEL, RET_MAIL, RET_RPTCNT, RET_BANK, RET_DELYN, TIC_TIER, COM_NO\n" + 
					 "				FROM REALTOR\n" + 
					 "				WHERE RET_ID = ?";

		return jdbc.selectOne(sql);
	}

	public List<Map<String, Object>> retList() {
		String sql="SELECT * FROM REALTOR";
		return jdbc.selectList(sql);
	}

	public List<Map<String, Object>> list(List<Object> param) {
		String sql="SELECT REV_NO 리뷰번호,EST_NO 매물번호,REV_CONTENT 내용,REV_SCORE 평점,REV_DATE 작성일 FROM REVIEW\r\n" + 
				"WHERE MEM_ID=?";
		return jdbc.selectList(sql,param);
	}

	public List<Map<String, Object>> comList(List<Object> param1) {
		String sql=" SELECT *\n" + 
				"FROM\n" + 
				"(SELECT ROWNUM RN, COM_NO, COM_NAME, COM_TEL \n" + 
				"FROM \n" + 
				"    (SELECT COM_NO, COM_NAME, COM_TEL \n" + 
				"    FROM COMPANY\n" + 
				"    WHERE COM_DELYN='N'\n" + 
				"    ORDER BY COM_NAME))\n" + 
				"WHERE (RN>=? AND RN<=?)";
		
		return jdbc.selectList(sql, param1);
	}

	public void companyInsert(List<Object> param) {
		String sql="INSERT INTO COMPANY\r\n" + 
				"VALUES((SELECT NVL(MAX(COM_NO),0)+1 FROM COMPANY),?,?,?,?,'N')";
		jdbc.update(sql, param);
	}
	
	public List<Map<String, Object>> myEstList(List<Object> param) {
		String sql="SELECT * FROM\r\n" + 
				"(SELECT ROWNUM RN,EST_NO, EST_NAME, EST_ADDRESS, EST_FLOOR, EST_TYPE, EST_SUPAREA, EST_EXCAREA,\n" + 
				"				EST_PRICE, EST_TRANTYPE, EST_STATE, EST_FEE, EST_DEPOSIT, TO_CHAR(EST_DATE, 'YYYY.MM.DD') EST_DATE\n" + 
				"				FROM ESTATE\n" + 
				"				WHERE RET_ID=?\n" + 
				"				AND EST_DELYN='N'\n" + 
				"                ORDER BY EST_NO)\r\n" + 
				"                WHERE RN>=? AND RN<=?";
		
		return jdbc.selectList(sql,param);
	}
	
	public List<Map<String, Object>> estDetailList(List<Object> param) {
		String sql="SELECT * FROM     \r\n" + 
				"    (SELECT ROWNUM RN,EST_NO, EST_NAME, EST_ADDRESS, EST_FLOOR, EST_TYPE, EST_SUPAREA, EST_EXCAREA,\r\n" + 
				"				EST_PRICE, EST_TRANTYPE, EST_STATE, EST_FEE, EST_DEPOSIT,EST_RPTCNT, TO_CHAR(EST_DATE, 'YYYY.MM.DD') EST_DATE \r\n" + 
				"				FROM ESTATE\r\n" + 
				"				WHERE EST_STATE=? AND RET_ID=?\r\n" + 
				"				ORDER BY EST_NO)\r\n" + 
				"                WHERE RN>=? AND RN<=?";
		return jdbc.selectList(sql,param);
	}

	public void estUpdate(List<Object> param) {
		String sql="UPDATE ESTATE\r\n" + 
				"SET EST_NAME=?,EST_ADDRESS=?,EST_TYPE=?,EST_SUPAREA=?,EST_EXCAREA=?,\r\n" + 
				"EST_PRICE=?,EST_FEE=?,EST_DEPOSIT=?,EST_FLOOR=?,EST_DATE=SYSDATE\r\n" + 
				"WHERE EST_NO=?ANDEST_DELYN='N'AND RET_ID=?";
		jdbc.update(sql, param);
	}

	public void estDelete(List<Object> param) {
		String sql="UPDATE ESTATE\r\n" + 
				"SET EST_DELYN='Y'\r\n" + 
				"WHERE EST_NO=? AND RET_ID=?";
		jdbc.update(sql, param);
		
	}

	public void estStateUpdate(List<Object> param) {
		String sql="UPDATE ESTATE\r\n" + 
				"SET EST_STATE=?\r\n" + 
				"WHERE EST_NO=? AND RET_ID=?";
		jdbc.update(sql, param);
		
	}

	public List<Map<String, Object>> retReviewList(List<Object> param) {
		String sql = " SELECT R.REV_NO REV_NO, TO_CHAR(R.REV_DATE, 'YYYY.MM.DD') REV_DATE, R.REV_SCORE REV_SCORE, R.REV_CONTENT REV_CONTENT\n" + 
					 "FROM REVIEW R JOIN ESTATE E ON(R.EST_NO = E.EST_NO)\n" + 
					 "WHERE E.RET_ID = ?\n" + 
					 "ORDER BY R.REV_DATE DESC";
		
		return jdbc.selectList(sql, param);
	}

	public Map<String, Object> retReviewScore(List<Object> param) {
		String sql = " SELECT ROUND(AVG(REV_SCORE),1) 평균평점\n" + 
					 "FROM REVIEW R JOIN ESTATE E ON(R.EST_NO = E.EST_NO)\n" + 
					 "WHERE E.RET_ID = ?";
		
		return jdbc.selectOne(sql, param);
	}

	public Map<String, Object> sellerInfo(List<Object> param) {
		String sql= "SELECT \r\n" + 
					"    R.RET_NAME AS 판매자이름,\r\n" + 
					"    R.RET_TEL AS 판매자전화번호,\r\n" + 
					"    C.COM_NAME AS 판매자회사이름,\r\n" + 
					"    C.COM_ADDRESS AS 판매자회사주소,\r\n" + 
					"    C.COM_TEL AS 판매자회사전화번호,\r\n" + 
					"    ROUND(AVG(RE.REV_SCORE), 1) AS 평균별점\r\n" + 
					"FROM REALTOR R\r\n" + 
					"JOIN ESTATE E ON R.RET_ID = E.RET_ID\r\n" + 
					"JOIN REVIEW RE ON E.EST_NO = RE.EST_NO\r\n" + 
					"JOIN COMPANY C ON R.COM_NO = C.COM_NO\r\n" + 
					"WHERE E.RET_ID = (SELECT RET_ID FROM ESTATE WHERE EST_NO = ?)\r\n" + 
					"GROUP BY R.RET_ID, R.RET_NAME, R.RET_TEL, C.COM_NAME, C.COM_ADDRESS, C.COM_TEL";
		
		return jdbc.selectOne(sql, param);
	}

	public Map<String, Object> myAddComNo() {
		String sql= " SELECT *\n" + 
					"FROM COMPANY\n" + 
					"WHERE ROWNUM = 1\n" + 
					"ORDER BY COM_NO DESC";
		
		return jdbc.selectOne(sql);
	}
	
	public Map<String, Object> findId(List<Object> param) {
		String sql="SELECT * FROM REALTOR\r\n" + 
				"WHERE RET_NAME=?\r\n" + 
				"AND RET_TEL=?";
		return jdbc.selectOne(sql, param);
	}

	public Map<String, Object> findPw(List<Object> param) {
		String sql="SELECT * FROM REALTOR\r\n" + 
				"WHERE RET_ID=?\r\n" + 
				"AND RET_NAME=?\r\n" + 
				"AND RET_TEL=?";
		return jdbc.selectOne(sql, param);
	}
	
}