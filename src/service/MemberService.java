package service;

import java.util.List;
import java.util.Map;

import controller.MainController;
import dao.MemberDao;

public class MemberService {
	private static MemberService instance;

	private MemberService() {

	}

	public static MemberService getInstance() {
		if (instance == null) {
			instance = new MemberService();
		}
		return instance;
	}
	
	
	MemberDao memberDao = MemberDao.getInstance();
	
	
	public boolean login(List<Object> param) {
		Map<String, Object> member = memberDao.login(param);
		
		if(member == null) {
			return false;
		} else if(member != null) {
		MainController.sessionStorage.put("member", member);
		}
		return true;
	}
	
	
	public void sign(List<Object> param) {
		memberDao.sign(param);
	}

	
	public void memberUpdate(List<Object> param) {
		memberDao.memberUpdate(param);
	}

	
	public Map<String, Object> myInfo(List<Object> param) {
		return memberDao.myInfo(param);
	}

	
	public int memberDelete(List<Object> param) {
		return memberDao.memberDelete(param);
	}
	
	
}
