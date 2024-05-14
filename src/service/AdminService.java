package service;

import java.util.List;
import java.util.Map;

import controller.MainController;
import dao.AdminDao;

public class AdminService {
	private static AdminService instance;

	private AdminService() {

	}

	public static AdminService getInstance() {
		if (instance == null) {
			instance = new AdminService();
		}
		return instance;
	}
	
	
	AdminDao adminDao = AdminDao.getInstance();
	
	
	public boolean login(List<Object> param) {
		Map<String, Object> admin = adminDao.login(param);
		
		if(admin == null) {
			return false;
		} else if(admin != null) {
		MainController.sessionStorage.put("admin", admin);
		}
		return true;
	}
	
	
}
