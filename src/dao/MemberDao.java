package dao;

import java.util.List;
import java.util.Map;

import util.JDBCUtil;

public class MemberDao {
	private static MemberDao instance;

	private MemberDao() {

	}

	public static MemberDao getInstance() {
		if (instance == null) {
			instance = new MemberDao();
		}
		return instance;
	}
	
	JDBCUtil jdbc = JDBCUtil.getInstance();
	
	public Map<String, Object> login (List<Object> param) {
		String sql = " SELECT *\r\n" + 
					 "FROM MEMBER\r\n" + 
					 "WHERE MEM_ID = ? \r\n" + 
					 "AND MEM_PW = ? \r\n" + 
					 "AND MEM_DELYN = 'N'";
		
		return jdbc.selectOne(sql, param);
	}
	
	public void sign(List<Object> param) {
	      String sql = " INSERT INTO MEMBER\r\n" + 
	                   "VALUES (?,?,?,?,?,?,0,'N','NORMAL',0)";
	      
	      jdbc.update(sql, param);
	   }

	public void memberUpdate(List<Object> param) {
		String sql = " UPDATE MEMBER\r\n" + 
					 "SET MEM_PW = ?, MEM_NAME = ?, MEM_TEL = ?, \r\n" + 
					 "    MEM_ADDRESS = ?, MEM_NICKNAME = ?\r\n" + 
					 "WHERE MEM_ID = ?";
		
		jdbc.update(sql, param);
	}

	public Map<String, Object> memInfo() {
		String sql = " SELECT MEM_PW, MEM_NAME, MEM_TEL, MEM_ADDRESS, MEM_NICKNAME, MEM_BANK, TIC_TIER, MEM_RPTCNT\r\n" + 
				"FROM MEMBER \r\n" + 
				"WHERE MEM_ID = ?";

		return jdbc.selectOne(sql);
	}
	
	public List<Map<String, Object>> memList() {
		String sql="SELECT * FROM MEMBER";
		return jdbc.selectList(sql);
	}

	public int memberDelete(List<Object> param) {
		String sql = " UPDATE MEMBER\r\n" + 
					 "SET MEM_DELYN = 'Y'\r\n" + 
					 "WHERE MEM_ID = ?";
		
		return jdbc.update(sql, param);
	}
	
	public List<Map<String, Object>> reviewList(List<Object> param) {
		String sql="SELECT REV_NO,EST_NO,REV_CONTENT ,REV_SCORE,TO_CHAR(REV_DATE, 'YYYY.MM.DD')REV_DATE FROM REVIEW\r\n" + 
				"WHERE MEM_ID=?";
		return jdbc.selectList(sql,param);
	}

	public Map<String, Object> reviewDetail(List<Object> param) {
		String sql="SELECT REV_NO,EST_NO,REV_CONTENT ,REV_SCORE,TO_CHAR(REV_DATE, 'YYYY.MM.DD')REV_DATE FROM REVIEW\r\n" + 
				"WHERE REV_NO=?";
		return jdbc.selectOne(sql,param);
	}
	
	
	public List<Map<String, Object>> memberReviewList(List<Object> param) {
		String sql = " SELECT R.REV_NO REV_NO, TO_CHAR(R.REV_DATE, 'YYYY.MM.DD') REV_DATE, R.REV_SCORE REV_SCORE, R.REV_CONTENT REV_CONTENT\n" + 
				 "FROM REVIEW R JOIN ESTATE E ON(R.EST_NO = E.EST_NO)\n" + 
				 "WHERE E.MEM_ID = ?\n" + 
				 "ORDER BY R.REV_DATE DESC";
	
	return jdbc.selectList(sql, param);
	}

	public Map<String, Object> memberReviewScore(List<Object> param) {
		String sql = " SELECT ROUND(AVG(REV_SCORE),1) 평균평점\n" + 
				 "FROM REVIEW R JOIN ESTATE E ON(R.EST_NO = E.EST_NO)\n" + 
				 "WHERE E.MEM_ID = ?";
	
	return jdbc.selectOne(sql, param);
	}
	
	public int reviewDelete(List<Object> param) {
		String sql="DELETE FROM REVIEW\r\n" + 
				"WHERE REV_NO=?";
		return jdbc.update(sql, param);
	}

	public void reviewUpdate(List<Object> param) {
		String sql="UPDATE REVIEW\r\n" + 
				"SET REV_CONTENT=?,REV_SCORE=?,REV_DATE=SYSDATE\r\n" + 
				"WHERE REV_NO=? AND MEM_ID=?";
		jdbc.update(sql, param);
	}

	public void reviewInsert(List<Object> param) {
		String sql="INSERT INTO REVIEW\r\n" + 
				"VALUES((SELECT NVL(MAX(REV_NO),0)+1 FROM REVIEW),?,?,SYSDATE,?,?)";
		jdbc.update(sql, param);
	}
	
	public Map<String, Object> wishListDetail(List<Object> param) {
		String sql="";
		return jdbc.selectOne(sql,param);
	}

	public void wishListInsert(List<Object> param) {
		String sql="INSERT INTO WISHLIST\r\n" + 
				"VALUES((SELECT NVL(MAX(WSL_NO),0)+1 FROM WISHLIST),?,?)";
		jdbc.update(sql, param);
	}

	public List<Map<String, Object>> myEstList(List<Object> param) {
		String sql="SELECT * FROM\r\n" + 
				"(SELECT ROWNUM RN,EST_NO, EST_NAME, EST_ADDRESS, EST_FLOOR, EST_TYPE, EST_SUPAREA, EST_EXCAREA,\r\n" + 
				"				EST_PRICE, EST_TRANTYPE, EST_STATE, EST_FEE, EST_DEPOSIT,EST_RPTCNT, TO_CHAR(EST_DATE, 'YYYY.MM.DD') EST_DATE\r\n" + 
				"				FROM ESTATE\r\n" + 
				"				WHERE MEM_ID=?\r\n" + 
				"				AND EST_DELYN='N'\r\n" + 
				"                ORDER BY EST_NO)\r\n" + 
				"                WHERE RN>=? AND RN<=?";
		return jdbc.selectList(sql,param);
	}
	
	public List<Map<String, Object>> estDetailList(List<Object> param) {
		String sql="SELECT * FROM     \r\n" + 
				"    (SELECT ROWNUM RN,EST_NO, EST_NAME, EST_ADDRESS, EST_FLOOR, EST_TYPE, EST_SUPAREA, EST_EXCAREA,\r\n" + 
				"				EST_PRICE, EST_TRANTYPE, EST_STATE, EST_FEE, EST_DEPOSIT,EST_RPTCNT, TO_CHAR(EST_DATE, 'YYYY.MM.DD') EST_DATE \r\n" + 
				"				FROM ESTATE\r\n" + 
				"				WHERE EST_STATE=? AND MEM_ID=?\r\n" + 
				"				ORDER BY EST_NO)\r\n" + 
				"                WHERE RN>=? AND RN<=?";
		return jdbc.selectList(sql,param);
	}

	public void estUpdate(List<Object> param) {
		String sql="UPDATE ESTATE\r\n" + 
				"SET EST_NAME=?,EST_ADDRESS=?,EST_TYPE=?,EST_SUPAREA=?,EST_EXCAREA=?,\r\n" + 
				"EST_PRICE=?,EST_FEE=?,EST_DEPOSIT=?,EST_FLOOR=?,EST_DATE=SYSDATE\r\n" + 
				"WHERE EST_NO=?ANDEST_DELYN='N' AND MEM_ID=?";
		jdbc.update(sql, param);
	}

	public void estDelete(List<Object> param) {
		String sql="UPDATE ESTATE\r\n" + 
				"SET EST_DELYN='Y'\r\n" + 
				"WHERE EST_NO=? AND MEM_ID=?";
		jdbc.update(sql, param);
	}

	public void estStateUpdate(List<Object> param) {
		String sql="UPDATE ESTATE\r\n" + 
				"SET EST_STATE=?\r\n" + 
				"WHERE EST_NO=? AND MEM_ID=?";
		jdbc.update(sql, param);
	}

	public List<Map<String, Object>> wishList(List<Object> param) {
		String sql="SELECT WSL_NO 찜번호,EST_NO 매물번호\r\n" + 
				"FROM WISHLIST\r\n" + 
				"WHERE MEM_ID=?";
		return jdbc.selectList(sql,param);
	}

	public Map<String, Object> wishlistChk(List<Object> param) {
		String sql="SELECT * FROM WISHLIST\r\n" + 
				"WHERE MEM_ID=?\r\n" + 
				"AND EST_NO=?";
		return jdbc.selectOne(sql, param);
	}

	public int memberReport(List<Object> param) {
		String sql = " INSERT INTO REPORT\r\n" + 
					 "VALUES ((SELECT NVL(MAX(NTC_NO),0)+1 FROM NOTICE), ?, ?, ?, ?, SYSDATE, 'DO' )";
	
	return jdbc.update(sql, param);
	}
	
	public Map<String, Object> sellerInfo(List<Object> param) {
		String sql="SELECT \r\n" + 
				"    M.MEM_NAME 판매자이름,\r\n" + 
				"    M.MEM_TEL 판매자전화번호,\r\n" + 
				"    M.MEM_ADDRESS 판매자주소,\r\n" + 
				"    M.MEM_NICKNAME 판매자닉네임,\r\n" + 
				"    ROUND(AVG(R.REV_SCORE),1) AS 평균별점\r\n" + 
				"FROM MEMBER M\r\n" + 
				"JOIN ESTATE E ON M.MEM_ID = E.MEM_ID\r\n" + 
				"JOIN REVIEW R ON E.EST_NO = R.EST_NO\r\n" + 
				"WHERE E.MEM_ID = (SELECT MEM_ID FROM ESTATE WHERE EST_NO =?)\r\n" + 
				"GROUP BY M.MEM_ID, M.MEM_NAME, M.MEM_TEL, M.MEM_ADDRESS, M.MEM_NICKNAME";
		return jdbc.selectOne(sql, param);
	}
	
	public Map<String, Object> findId(List<Object> param) {
		String sql="SELECT * FROM MEMBER\r\n" + 
					"WHERE MEM_NAME=?\r\n" + 
					"AND MEM_TEL=?";
		return jdbc.selectOne(sql, param);
	}
	
	public Map<String, Object> findPw(List<Object> param) {
		String sql=" SELECT * FROM MEMBER\r\n" + 
				"WHERE MEM_ID=?\r\n" + 
				"AND MEM_NAME=?\r\n" + 
				"AND MEM_TEL=?";
		return jdbc.selectOne(sql, param);
	}


	
}
