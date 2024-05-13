package service;

import java.util.List;
import java.util.Map;

import dao.EstateDao;
import dao.MemberDao;

public class EstateService {
	private static EstateService instance;

	private EstateService() {

	}

	public static EstateService getInstance() {
		if (instance == null) {
			instance = new EstateService();
		}
		return instance;
	}
	
	
	EstateDao estateDao = EstateDao.getInstance();

	
	public List<Map<String, Object>> estList() {
		return estateDao.estList();
	}
	
	
	
	
	
	
	
	
	
	
}
