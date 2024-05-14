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
				"WHERE ID=?\r\n" + 
				"AND PASS=?\r\n" + 
				"AND DELYN='N'";
		return jdbc.selectOne(sql, param);
	}

	public void sign(List<Object> param) {
		String sql="INSERT INTO REALTOR\\r\\n\" + \r\n" + 
				"VALUES((SELECT NVL(MAX(MEM_NO),0)+1 FROM REALTOR),?,?,?,?,?,?,?,?,?,?,'N'";
		jdbc.selectOne(sql, param);
	}
}