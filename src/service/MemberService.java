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

	
	public Map<String, Object> memInfo(List<Object> param) {
		return memberDao.memInfo(param);
	}
	
	public List<Map<String, Object>> memList() {
		return memberDao.memList();
	}
	
	public int memberDelete(List<Object> param) {
		return memberDao.memberDelete(param);
	}
	

	public List<Map<String, Object>> reviewList(List<Object> param) {
		return memberDao.reviewList(param);
	}

	public List<Map<String, Object>> memberReviewList(List<Object> param) {
		return memberDao.memberReviewList(param);
	}

	public Map<String, Object> memberReviewScore(List<Object> param) {
		return memberDao.memberReviewScore(param);
	}
	
	public int reviewDelete(List<Object> param) {
		return memberDao.reviewDelete(param);
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

	public int memberReport(List<Object> param) {
		return memberDao.memberReport(param);
	}
	
	public Map<String, Object> sellerInfo(List<Object> param) {
		return memberDao.sellerInfo(param);
	}

	public Map<String, Object> findPw(List<Object> param) {
		return memberDao.findPw(param);
	}

	public Map<String, Object> findId(List<Object> param) {
		return memberDao.findId(param);
	}

	public List<Map<String, Object>> reservationList(List<Object> param) {
		return memberDao.reservationList(param);
	}

	public void wishListDelete(List<Object> param) {
		memberDao.wishListDelete(param);
		
	}

	
}
