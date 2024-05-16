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
		String sql = " INSERT INTO JAVA_MEMBER (MEM_NO, MEM_ID, MEM_PW, MEM_NAME, MEM_DELYN)\r\n" + 
					 "VALUES ((SELECT NVL(MAX(MEM_NO), 0)+1 FROM MEMBER), ?, ?, ?, 'N')";
		
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
		String sql="SELECT REV_NO 리뷰번호,EST_NO 매물번호,REV_CONTENT 내용,REV_SCORE 평점,REV_DATE 작성일 FROM REVIEW\r\n" + 
				"WHERE MEM_ID=?";
		return jdbc.selectList(sql,param);
	}

	public int reviewDelete(List<Object> param) {
		String sql="DELETE FROM REVIEW\r\n" + 
				"WHERE REV_NO=?";
		return jdbc.update(sql, param);
	}

	public Map<String, Object> reviewDetail(List<Object> param) {
		String sql="SELECT REV_NO 리뷰번호,EST_NO 매물번호,REV_CONTENT 내용,REV_SCORE 평점,REV_DATE 작성일 FROM REVIEW\r\n" + 
				"WHERE REV_NO=?";
		return jdbc.selectOne(sql,param);
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
		String sql="SELECT EST_NO 매물번호,EST_NAME 매물이름,EST_ADDRESS 주소,EST_TYPE 주거형태,\r\n" + 
				"EST_SUPAREA 공급면적,EST_EXCAREA 전용면적,EST_PRICE 가격,EST_TRANTYPE 거래유형,\r\n" + 
				"EST_STATE 판매상태,EST_FEE 관리비,EST_DEPOSIT 보증금,EST_RPTCNT 신고횟수,EST_FLOOR 건물층\r\n" + 
				"FROM ESTATE\r\n" + 
				"WHERE MEM_ID=?\r\n" + 
				"AND EST_DELYN='N'\r\n" + 
				"ORDER BY EST_NO";
		
		return jdbc.selectList(sql,param);
	}

	public List<Map<String, Object>> estDetailList(List<Object> param) {
		String sql="SELECT EST_NO 매물번호,EST_NAME 매물이름,EST_ADDRESS 주소,EST_TYPE 주거형태,\r\n" + 
				"EST_SUPAREA 공급면적,EST_EXCAREA 전용면적,EST_PRICE 가격,EST_TRANTYPE 거래유형,\r\n" + 
				"EST_STATE 판매상태,EST_FEE 관리비,EST_DEPOSIT 보증금,EST_RPTCNT 신고횟수,EST_FLOOR 건물층\r\n" + 
				"FROM ESTATE\r\n" + 
				"WHERE EST_STATE=? AND MEM_ID=?\r\n" + 
				"ORDER BY EST_NO";
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
	
}
