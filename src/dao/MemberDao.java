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
		String sql = " INSERT INTO JAVA_MEMBER (MEM_NO, ID, PASS, NAME, DELYN, ROLE)\r\n" + 
					 "VALUES ((SELECT NVL(MAX(MEM_NO), 0)+1 FROM JAVA_MEMBER), ?, ?, ?, 'N', 1)";
		
		jdbc.update(sql, param);
	}

	public void memberUpdate(List<Object> param) {
		String sql = " UPDATE MEMBER\r\n" + 
					 "SET MEM_PW = ?, MEM_NAME = ?, MEM_TEL = ?, \r\n" + 
					 "    MEM_ADDRESS = ?, MEM_NICKNAME = ?\r\n" + 
					 "WHERE MEM_ID = ?";
		
		jdbc.update(sql, param);
	}

	
	public Map<String, Object> myInfo(List<Object> param) {
		String sql = " SELECT MEM_PW, MEM_NAME, MEM_TEL, MEM_ADDRESS, MEM_NICKNAME, MEM_BANK, TIC_TIER, MEM_RPTCNT\r\n" + 
				"FROM MEMBER \r\n" + 
				"WHERE MEM_ID = ?";

		return jdbc.selectOne(sql, param);
	}

	public int memberDelete(List<Object> param) {
		String sql = " UPDATE MEMBER\r\n" + 
					 "SET MEM_DELYN = 'Y'\r\n" + 
					 "WHERE MEM_ID = ?";
		
		return jdbc.update(sql, param);
	}
	
	
	
}
