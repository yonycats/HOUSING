package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import util.JDBCUtil;

public class EstateDao {
	private static EstateDao instance;

	private EstateDao() {

	}

	public static EstateDao getInstance() {
		if (instance == null) {
			instance = new EstateDao();
		}
		return instance;
	}

	JDBCUtil jdbc = JDBCUtil.getInstance();

//	public List<Map<String, Object>> estList() {
//		String sql = " SELECT EST_NO, EST_NAME, EST_ADDRESS, EST_FLOOR, EST_TYPE, EST_SUPAREA, EST_EXCAREA, \r\n" + 
//					 "EST_PRICE, EST_TRANTYPE, EST_STATE, EST_FEE, EST_DEPOSIT, TO_CHAR(EST_DATE, 'YYYY.MM.DD') EST_DATE\r\n" + 
//					 "FROM ESTATE\r\n" + 
//					 "WHERE EST_DELYN = 'N'\r\n" + 
//					 "AND EST_STATE IN (1,2)\r\n" + 
//					 "ORDER BY EST_DATE DESC";
//		
//		return jdbc.selectList(sql);
//	}

	public List<Map<String, Object>> estList(List<Object> param) {
		String sql = " SELECT * FROM\r\n"
				+ "(SELECT ROWNUM RN, EST_NO, EST_NAME, EST_ADDRESS, EST_FLOOR, EST_TYPE, EST_SUPAREA, EST_EXCAREA,\r\n"
				+ "				 EST_PRICE, EST_TRANTYPE, EST_STATE, EST_FEE, EST_DEPOSIT, TO_CHAR(EST_DATE, 'YYYY.MM.DD') EST_DATE\r\n"
				+ "FROM ESTATE\r\n" + "WHERE EST_DELYN='N'\r\n" + "AND EST_STATE IN (1,2)\r\n" + "ORDER BY EST_NO)\r\n"
				+ "WHERE RN>=? AND RN<=?";

		return jdbc.selectList(sql, param);
	}

//	public void estAdd(List<Object> param, int tranTypeCnt) {
//	    String sql = "INSERT INTO ESTATE\r\n" + 
//	            "	            (EST_NO, EST_NAME, EST_ADDRESS, EST_TYPE, EST_SUPAREA, EST_EXCAREA, EST_PRICE, EST_TRANTYPE, EST_STATE, EST_FEE, EST_DEPOSIT,RET_RPTCNT,EST_DELYN, RET_ID, MEM_ID, EST_FLOOR, EST_DATE)\r\n" + 
//	            "	            VALUES((SELECT NVL(MAX(EST_NO),0)+1 FROM ESTATE),?,?,?,?,?,?,?,?,?,?,0,'N',?,?,?,SYSDATE)";
//
//	    for (int i = 0; i < tranTypeCnt; i++) {
//	        List<Object> param2 = new ArrayList<Object>(param.subList(0, 8));
//	        param2.add(param.get(8 + i));
//	        for(int j=0;j<param.size()-(8+tranTypeCnt);j++) {
//	            param2.add(param.get(8+tranTypeCnt+j));
//	        }
//	        jdbc.update(sql, param2);
//	    }
//	}

//	public void estAdd(List<Object> param, int tranTypeCnt) {
//	    String sql = "INSERT INTO ESTATE\r\n" + 
//	            "	            (EST_NO, EST_NAME, EST_ADDRESS, EST_TYPE, EST_SUPAREA, EST_EXCAREA, EST_PRICE, EST_TRANTYPE, EST_STATE, EST_FEE, EST_DEPOSIT,RET_RPTCNT,EST_DELYN, RET_ID, MEM_ID, EST_FLOOR, EST_DATE)\r\n" + 
//	            "	            VALUES((SELECT NVL(MAX(EST_NO),0)+1 FROM ESTATE),?,?,?,?,?,?,?,?,?,?,0,'N',?,?,?,SYSDATE)";
//
//	    for (int i = 0; i < tranTypeCnt; i++) {
//	        List<Object> param2 = new ArrayList<Object>(param.subList(0, 8));
//	        param2.add(param.get(8 + i));
//	        for(int j=9; j<param.size()-(tranTypeCnt-1); j++) {
//	            param2.add(param.get(j+tranTypeCnt-1));
//	        }
//	        jdbc.update(sql, param2);
//	    }
//	}

//	    String sql = "INSERT INTO ESTATE " + 
//	            "(EST_NO, EST_NAME, EST_ADDRESS, EST_TYPE, EST_SUPAREA, EST_EXCAREA, EST_PRICE, EST_TRANTYPE, EST_STATE, EST_FEE, EST_DEPOSIT, RET_RPTCNT, EST_DELYN, RET_ID, MEM_ID, EST_FLOOR, EST_DATE) " + 
//	            "VALUES((SELECT NVL(MAX(EST_NO),0)+1 FROM ESTATE),?,?,?,?,?,?,?,1,?,?,0,'N',?,?,?,SYSDATE)";

	public void estAdd(List<Object> param) {
		String sql = "INSERT INTO ESTATE\r\n"
				+ "VALUES((SELECT NVL(MAX(EST_NO),0)+1 FROM ESTATE),?,?,?,?,?,?,?,1,?,?,0,'N',?,?,?,SYSDATE)";
		jdbc.update(sql, param);
	}

}
