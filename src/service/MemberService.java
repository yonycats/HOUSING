package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import controller.MainController;
import dao.MemberDao;
import oracle.net.ns.SessionAtts;

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

	public List<Map<String, Object>> memInfo() {
		
		return memberDao.memInfo();
	}

	public List<Map<String, Object>> reviewList(List<Object> param) {
		return memberDao.reviewList(param);
	}

	public void reviewDelete(List<Object> param) {
		memberDao.reviewDelete(param);
		
	}

	public Map<String, Object> reviewDetail(List<Object> param) {
		return memberDao.reviewDetail(param);
	}

	public void reviewUpdate(List<Object> param) {
		memberDao.reviewUpdate(param);
	}

	public void reviewInsert(List<Object> param) {
		memberDao.reviewInsert(param);
		
	}

	public Map<String, Object> wishListDetail(List<Object> param) {
		return memberDao.wishListDetail(param);
	}

	public void wishListInsert(List<Object> param) {
		memberDao.wishListInsert(param);
	}

	public List<Map<String, Object>> myEstList(List<Object> param) {
		return memberDao.myEstList(param);
	}

	public List<Map<String, Object>> estDetailList(List<Object> param) {
		return memberDao.estDetailList(param);
	}

	public void estUpdate(List<Object> param) {
		memberDao.estUpdate(param);
		
	}

	public void estDelete(List<Object> param) {
		memberDao.estDelete(param);
		
	}

	public void estStateUpdate(List<Object> param) {
		memberDao.estStateUpdate(param);
	}

	public List<Map<String, Object>> wishList(List<Object> param) {
		
		return memberDao.wishList(param);
	}

	public boolean wishlistChk(List<Object> param) {
		if(memberDao.wishlistChk(param)==null)return true;
		else return false;
	}
	
	
}
