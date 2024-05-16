package dao;

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

	
	public List<Map<String, Object>> estList(List<Object> param) {
		String sql = " SELECT * FROM\r\n"
				+ "(SELECT ROWNUM RN, EST_NO, EST_NAME, EST_ADDRESS, EST_FLOOR, EST_TYPE, EST_SUPAREA, EST_EXCAREA,\r\n"
				+ "				 EST_PRICE, EST_TRANTYPE, EST_STATE, EST_FEE, EST_DEPOSIT, TO_CHAR(EST_DATE, 'YYYY.MM.DD') EST_DATE\r\n"
				+ "FROM ESTATE\r\n" + "WHERE EST_DELYN='N'\r\n" + "AND EST_STATE IN (1,2)\r\n" + "ORDER BY EST_NO)\r\n"
				+ "WHERE RN>=? AND RN<=?";

		return jdbc.selectList(sql, param);
	}
	
	public void estAdd(List<Object> param) {
		String sql = "INSERT INTO ESTATE\r\n"
				+ "VALUES((SELECT NVL(MAX(EST_NO),0)+1 FROM ESTATE),?,?,?,?,?,?,?,1,?,?,0,'N',?,?,?,SYSDATE)";
		jdbc.update(sql, param);
	}

	public Map<String, Object> estDetail(List<Object> param) {
		String sql="SELECT * FROM ESTATE\r\n" + 
				"WHERE EST_NO=?";
		return jdbc.selectOne(sql, param);
	}
	
	
	
}
