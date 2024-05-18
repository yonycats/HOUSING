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

	
	public List<Map<String, Object>> estList(List<Object> param) {
		return estateDao.estList(param);
	}

	public void estAdd(List<Object> param) {
		estateDao.estAdd(param);
	}

	public Map<String, Object> estDetail(List<Object> param) {
		return estateDao.estDetail(param);
	}
	
	public List<Map<String, Object>> estSearchList(List<Object> param) {
		return estateDao.estSearchList(param);
	}
}
