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

	public List<Map<String, Object>> adminReportList(List<Object> param) {
	      return adminDao.adminReportList(param);
	   }

	public List<Map<String, Object>> adminReportRecord(List<Object> param1) {
		return adminDao.adminReportRecord(param1);
	}

	public Map<String, Object> adminReportDetail(List<Object> param) {
		return adminDao.adminReportDetail(param);
	}
	
	public List<Map<String, Object>> noticeList() {
		return adminDao.noticeList();
	}

	
	public void adminNoticeInsert(List<Object> param) {
		adminDao.adminNoticeInsert(param);
	}

	
	public void adminNoticeUpdate(List<Object> param) {
		adminDao.adminNoticeUpdate(param);
	}

	
	public int adminNoticeDelete(List<Object> param) {
		return adminDao.adminNoticeDelete(param);
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

	public Map<String, Object> noticeListDetail(List<Object> param) {
		return adminDao.noticeListDetail(param);
	}

	public List<Map<String, Object>> adminReportDetailEst(List<Object> param1) {
		return adminDao.adminReportDetailEst(param1);
	}

	public Map<String, Object> adminReportRetRptcnt(List<Object> retRptcnt) {
		return adminDao.adminReportRetRptcnt(retRptcnt);
	}
	
	public Map<String, Object> adminReportMemRptcnt(List<Object> retRptcnt) {
		return adminDao.adminReportMemRptcnt(retRptcnt);
	}

	public int retCntAdd(List<Object> selId) {
		return adminDao.retCntAdd(selId);
	}

	public int memCntAdd(List<Object> selId) {
		return adminDao.memCntAdd(selId);
	}

	public int rptReject(List<Object> rptNo3) {
		return adminDao.rptReject(rptNo3);
	}

	public int rptDelynFin(List<Object> rptCntNo) {
		return adminDao.rptDelynFin(rptCntNo);
	}

	public int estCntAdd(List<Object> estCntNo) {
		return adminDao.estCntAdd(estCntNo);
	}

	public void estDelynUpdate(List<Object> estCntNo) {
		adminDao.estDelynUpdate(estCntNo);
	}

	public void retDelynUpdate(List<Object> selId) {
		adminDao.retDelynUpdate(selId);
	}

	public void memDelynUpdate(List<Object> selId) {
		adminDao.memDelynUpdate(selId);
	}
	
	public List<Map<String, Object>> adminMemberList(List<Object> param) {
		return adminDao.adminMemberList(param);
	}

	public List<Map<String, Object>> adminRealtorList(List<Object> param) {
		return adminDao.adminRealtorList(param);
	}

	public List<Map<String, Object>> adminRetcomList(List<Object> param) {
		return adminDao.adminRetcomList(param);
	}

	public Map<String, Object> myCom(List<Object> myCom) {
		return adminDao.myCom(myCom);
	}

	public List<Map<String, Object>> admincomList(List<Object> param) {
		return adminDao.adminRetcomList(param);
	}
	
}
