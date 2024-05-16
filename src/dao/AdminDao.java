package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import util.JDBCUtil;
import util.View;

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

	

	
}
