package service;

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
}
