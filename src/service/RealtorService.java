package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import controller.MainController;
import dao.RealtorDao;

public class RealtorService {
	private static RealtorService instance;

	private RealtorService() {

	}

	public static RealtorService getInstance() {
		if (instance == null) {
			instance = new RealtorService();
		}
		return instance;
	}

	RealtorDao realtorDao = RealtorDao.getInstance();

	public boolean login(List<Object> param) {

		Map<String, Object> realtor = realtorDao.login(param);
		if (realtor == null) {
			return false;
		}
		MainController.sessionStorage.put("realtor", realtor);
		return true;
	}

	public void sign(List<Object> param) {
		realtorDao.sign(param);
	}
	
	public Map<String, Object> retInfo(List<Object> param) {
		return realtorDao.retInfo(param);
	}

	public List<Map<String, Object>> retList() {
		return realtorDao.retList();
	}

	public List<Map<String, Object>> list() {
		List<Object> param=new ArrayList<Object>();
		Map<String, Object> member=(Map<String, Object>) MainController.sessionStorage.get("realtor");
		param.add(member.get("RET_ID"));
		return realtorDao.list(param);
	}

	public List<Map<String, Object>> comList(List<Object> param1) {
		return realtorDao.comList(param1);
	}
	
	public void companyInsert(List<Object> param) {
		realtorDao.companyInsert(param);
	}
	
	public List<Map<String, Object>> myEstList(List<Object> param) {
		return realtorDao.myEstList(param);
	}

	public List<Map<String, Object>> estDetailList(List<Object> param) {
		return realtorDao.estDetailList(param);
	}

	public void estUpdate(List<Object> param) {
		realtorDao.estUpdate(param);
	}

	public void estDelete(List<Object> param) {
		realtorDao.estDelete(param);		
	}

	public void estStateUpdate(List<Object> param) {
		realtorDao.estStateUpdate(param);
	}

	public List<Map<String, Object>> retReviewList(List<Object> param) {
		return realtorDao.retReviewList(param);
	}

	public Map<String, Object> retReviewScore(List<Object> param) {
		return realtorDao.retReviewScore(param);
	}
	
	public Map<String, Object> sellerInfo(List<Object> param) {
		return realtorDao.sellerInfo(param);
	}

	public Map<String, Object> myAddComNo() {
		return realtorDao.myAddComNo();
	}

	public Map<String, Object> findPw(List<Object> param) {
		return realtorDao.findPw(param);
	}

	public Map<String, Object> findId(List<Object> param) {
		return realtorDao.findId(param);
	}

	public List<Map<String, Object>> reservationList(List<Object> param) {
		return realtorDao.reservationList(param);
	}

	public void realtorReservationInsert(List<Object> param) {
		realtorDao.realtorReservationInsert(param);
	}
	
	public void realtorUpdate(List<Object> param) {
		realtorDao.realtorUpdate(param);
	}
	public int realtorDelete(List<Object> param) {
		return realtorDao.realtorDelete(param);
}

}
