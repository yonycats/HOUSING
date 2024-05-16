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
		Map<String, Object> member = adminDao.login(param);
		
		if(member == null) {
			return false;
		} else if(member != null) {
		MainController.sessionStorage.put("member", member);
		}
		return true;
	}
	
	public List<Map<String, Object>> tichetList() {
		return adminDao.tichetList();
	}

	public void adminTicketInsert(List<Object> param) {
		adminDao.adminTicketInsert(param);
	}
	
	public void adminTicketUpdate(List<Object> param) {
		adminDao.adminTicketUpdate(param);
	}
	
	public int adminTicketDelete(List<Object> param) {
		return adminDao.adminTicketDelete(param);
	}

	public List<Map<String, Object>> adminReportList() {
		return adminDao.adminReportList();
	}

	public List<Map<String, Object>> adminReportDoing() {
		return adminDao.adminReportDoing();
	}

	public List<Map<String, Object>> adminReportFinish() {
		return adminDao.adminReportFinish();
	}

	public Map<String, Object> adminReportDetail(List<Object> param) {
		return adminDao.adminReportDetail(param);
	}

	public Map<String, Object> adminSaleDay(List<Object> param) {
		return adminDao.adminSaleDay(param);
	}
	
	public List<Map<String, Object>> daySaleTier(List<Object> param) {
		return adminDao.daySaleTier(param);
	}

	public Map<String, Object> adminSaleMonth(List<Object> param) {
		return adminDao.adminSaleMonth(param);
	}

	public List<Map<String, Object>> monthSaleTier(List<Object> param) {
		return adminDao.monthSaleTier(param);
	}

	public Map<String, Object> adminSaleYear(List<Object> param) {
		return adminDao.adminSaleYear(param);
	}

	public List<Map<String, Object>> yearSaleTier(List<Object> param) {
		return adminDao.yearSaleTier(param);
	}
	
	
}
