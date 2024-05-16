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
				"VALUES(?,?,?,?,?,0,0,'N','NORMAL',\r\n" + 
				"(SELECT COM_NO FROM COMPANY WHERE COM_NAME=?))";
		jdbc.update(sql, param);
	}

	public List<Map<String, Object>> retInfo() {
		String sql="SELECT * FROM REALTOR";
		return jdbc.selectList(sql);
	}

	public List<Map<String, Object>> list(List<Object> param) {
		String sql="SELECT REV_NO 리뷰번호,EST_NO 매물번호,REV_CONTENT 내용,REV_SCORE 평점,REV_DATE 작성일 FROM REVIEW\r\n" + 
				"WHERE MEM_ID=?";
		return jdbc.selectList(sql,param);
	}

	public List<Map<String, Object>> myEstList(List<Object> param) {
		String sql="SELECT EST_NO 매물번호,EST_NAME 매물이름,EST_ADDRESS 주소,EST_TYPE 주거형태,\r\n" + 
				"EST_SUPAREA 공급면적,EST_EXCAREA 전용면적,EST_PRICE 가격,EST_TRANTYPE 거래유형,\r\n" + 
				"EST_STATE 판매상태,EST_FEE 관리비,EST_DEPOSIT 보증금,EST_RPTCNT 신고횟수,EST_FLOOR 건물층\r\n" + 
				"FROM ESTATE\r\n" + 
				"WHERE MEM_ID=?\r\n" + 
				"AND RET_DELYN='N'\r\n" + 
				"ORDER BY EST_NO";
		
		return jdbc.selectList(sql,param);
	}

	public List<Map<String, Object>> estDetailList(List<Object> param) {
		String sql="SELECT EST_NO 매물번호,EST_NAME 매물이름,EST_ADDRESS 주소,EST_TYPE 주거형태,\r\n" + 
				"EST_SUPAREA 공급면적,EST_EXCAREA 전용면적,EST_PRICE 가격,EST_TRANTYPE 거래유형,\r\n" + 
				"EST_STATE 판매상태,EST_FEE 관리비,EST_DEPOSIT 보증금,EST_RPTCNT 신고횟수,EST_FLOOR 건물층\r\n" + 
				"FROM ESTATE\r\n" + 
				"WHERE EST_STATE=? AND RET_ID=?\r\n" + 
				"ORDER BY EST_NO";
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
}