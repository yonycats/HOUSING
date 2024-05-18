package view;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import service.AdminService;
import service.EstateService;
import service.MemberService;
import service.RealtorService;
import util.ScanUtil;
import util.View;

public class Print {
	
	static public Map<String, Object> sessionStorage = new HashMap<>();
	
	AdminService adminService = AdminService.getInstance();
	MemberService memberService = MemberService.getInstance();
	RealtorService realtorService = RealtorService.getInstance();
	
	public void printVar() {
		System.out.println("=============================");
	}

	public void printLn(int num) {
		for (int i = 0; i < num; i++)
			System.out.println();
	}

	public void printHome() {
		printVar();
		System.out.println();
		System.out.println("1. 집 매물 정보 보기");
		System.out.println("2. 집 매물 등록하기");
		System.out.println("3. 로그인");
		System.out.println("4. 회원가입");
		System.out.println("5. 공지사항");
		System.out.println();
		printVar();
		System.out.println();
	}
	public void printReviewHome() {
		printVar();
		System.out.println();
		System.out.println("1. 리뷰 수정");
		System.out.println("2. 리뷰 삭제");
		System.out.println("3. 회원 메뉴");
		System.out.println("4. 홈");
		System.out.println();
		printVar();
		System.out.println();
	}
	public void printSignList() {
		printVar();
		System.out.println();
		System.out.println("1. 일반 회원 가입");
		System.out.println("2. 공인중개사 회원 가입");
		System.out.println();
		printVar();
		System.out.println();
	}
	public void printLoginList() {
		printVar();
		System.out.println();
		System.out.println("1. 일반회원 로그인");
		System.out.println("2. 공인중개사 로그인");
		System.out.println();
//		System.out.println("0. 관리자 로그인");
		printVar();
		System.out.println();
	}
	public void printAdmin() {
		System.out.println();
		System.out.println("=========관리자 페이지=========");
		System.out.println("1. 공지사항 관리");
		System.out.println("2. 신고 관리");
		System.out.println("3. 이용권 관리");
		System.out.println("4. 로그아웃");
		printVar();
		System.out.println();
	}

	public void bookListPrint(List<Map<String, Object>> bookList) {
		printVar();

		for (Map<String, Object> map : bookList) {
			BigDecimal bookNo = (BigDecimal) map.get("BOOK_NO");
			String title = (String) map.get("TITLE");
			String content = (String) map.get("CONTENT");
			String pubdate = (String) map.get("PUBDATE");
			System.out.println(bookNo + "\t" + title + "\t" + content + "\t" + pubdate);
		}
		printVar();
		System.out.println();
	}
	
	public void estList(Map<String, Object> map) {
		BigDecimal estNo = (BigDecimal) map.get("EST_NO");
		String estName = (String) map.get("EST_NAME");
		String estAdd = (String) map.get("EST_ADDRESS");
		BigDecimal estFloor = (BigDecimal) map.get("EST_FLOOR");
		BigDecimal estSupArea = (BigDecimal) map.get("EST_SUPAREA");
		BigDecimal estExcArea = (BigDecimal) map.get("EST_EXCAREA");
		BigDecimal estPrice = (BigDecimal) map.get("EST_PRICE");
		BigDecimal estFee = (BigDecimal) map.get("EST_FEE");
		BigDecimal estDeptsit = (BigDecimal) map.get("EST_DEPOSIT");
		String estDate = (String) map.get("EST_DATE");

		String estType = null;
		int estTypeValue = ((BigDecimal) map.get("EST_TRANTYPE")).intValue();
		if (estTypeValue == 1) {
			estType = "단독주택";
		} else if (estTypeValue == 2) {
			estType = "아파트";
		} else if (estTypeValue == 3) {
			estType = "오피스텔";
		} else if (estTypeValue == 4) {
			estType = "빌라";
		} else if (estTypeValue == 5) {
			estType = "원룸";
		}

		String estTranType = "";
		int estTrantypeValue = ((BigDecimal) map.get("EST_TRANTYPE")).intValue();
		if (estTrantypeValue == 1) {
			estTranType = "매매";
		} else if (estTrantypeValue == 2) {
			estTranType = "전세";
		} else if (estTrantypeValue == 3) {
			estTranType = "월세";
		}

		String estState = "";
		int estStateValue = ((BigDecimal) map.get("EST_STATE")).intValue();
		if (estStateValue == 1) {
			estState = "판매중";
		} else if (estStateValue == 2) {
			estState = "예약중";
		}

		System.out.println("No." + estNo + "\t[등록일] " + estDate + "\t[" + estType + "]\t[" + estTranType + "]\t[매물명] "
				+ estName + "\t\t" + estFloor + "층" + "\t[주소] " + estAdd);
		System.out.println("\t[매물상태] " + estState + "\t" + "\t[공급면적] " + estSupArea + "m2\t[전용면적] " + estExcArea
				+ "m2\t[관리비] " + estFee + "원" + "\t[금액] " + estPrice + "원 " + "\t\t [보증금] " + estDeptsit + "원");
		System.out.println();

	}
	
	public void reportList(Map<String, Object> map) {
		String state = (String) map.get("RPT_STATE");
		if (state.equals("DO"))
			state = "처리중";
		else if (state.equals("FIN"))
			state = "처리완료";
		BigDecimal no = (BigDecimal) map.get("RPT_NO");
		String date = (String) map.get("RPT_DATE");
		String memId = (String) map.get("MEM_ID");
		BigDecimal estNo = (BigDecimal) map.get("EST_NO");
		String title = (String) map.get("RPT_TITLE");

		System.out.println(
				"No." + no + "\t[상태] " + state + "\t\t[신고일] " + date + "\t[신고자] " + memId + "\t[매물번호] " + estNo);
		System.out.println("[제목] " + title);
		System.out.println();
	}
	
	
	public Object comList() {
		int page = 1;
		if (sessionStorage.containsKey("page")) {
			page = (int) sessionStorage.remove("page");
		}
		int startNo = 1 + (page - 1) * 5;
		int endNo = page * 5;

		List<Object> param1 = new ArrayList<Object>();
		param1.add(startNo);
		param1.add(endNo);

		List<Map<String, Object>> list = realtorService.comList(param1);
		for (Map<String, Object> map : list) {
			String comName = (String) map.get("COM_NAME");
			BigDecimal comNum = (BigDecimal) map.get("COM_NO");
			String comTel = (String) map.get("COM_TEL");
			System.out.println("No. " + comNum + "\t[회사명] " + comName + "\t[회사번호] " + comTel);
			System.out.println();
		}

		System.out.println("<이전페이지\t\t다음페이지>");
		System.out.println();
		System.out.println("이중에 당신의 공인중개사가 있나요? (가나다순 출력)");
		System.out.println("1.네\t2.아니오 [공인중개사 새로만들기]");
		System.out.println();
		String sel3 = ScanUtil.nextLine("메뉴 : ");

		if (sel3.equals("<")) {
			if (page != 1)
				page--;
			sessionStorage.put("page", page);
			return comList();
		} else if (sel3.equals(">")) {
			page++;
			sessionStorage.put("page", page);
			return comList();
		} else if (sel3.equals("1") || sel3.equals("2")) {
			int sel4 = Integer.parseInt(sel3);
			return sel4;
		}
		else return comList();
	}
	
	public void reviewList(Map<String, Object> map) {
		BigDecimal no = (BigDecimal) map.get("REV_NO");
		String date = (String) map.get("REV_DATE");
		BigDecimal score = (BigDecimal) map.get("REV_SCORE");
		String content = (String) map.get("REV_CONTENT");
		System.out.println("No." + no + " \t[작성일] " + date + "    [별점] " + score + "    [리뷰내용] " + content);
	}
	
	public View printAdminUserList() {
		System.out.println();
		System.out.println("1. 일반회원 목록 조회");
		System.out.println("2. 공인중개사 목록 조회");
		System.out.println("3. 공인중개소 목록 조회");
		System.out.println("4. 뒤로가기");
		return View.ADMIN_USERLIST;
	}
	
	public int printAdminMemberList() {
		System.out.println();
		int page = 1;
		if (sessionStorage.containsKey("page")) {
			page = (int) sessionStorage.remove("page");
		}
		int startNo = 1 + (page - 1) * 5;
		int endNo = page * 5;

		List<Object> param = new ArrayList<Object>();
		param.add(startNo);
		param.add(endNo);
		
		List<Map<String, Object>> adminMemberList = adminService.adminMemberList(param);
		for (Map<String, Object> map : adminMemberList) {
			BigDecimal rowNum = (BigDecimal) map.get("RN");
			String id = (String) map.get("MEM_ID");
			String Name = (String) map.get("MEM_NAME");
			String tel = (String) map.get("MEM_TEL");
			String address = (String) map.get("MEM_ADDRESS");
			String nicName = (String) map.get("MEM_NICKNAME");
			BigDecimal bank = (BigDecimal) map.get("MEM_BANK");
			String delyn = (String) map.get("MEM_DELYN");
			if (delyn.equals("N")) {
				delyn = "노출유지";
			} else if (delyn.equals("Y")) {
				delyn = "노출중지";
			}
			String ticket = (String) map.get("TIC_TIER");
			BigDecimal rptCnt = (BigDecimal) map.get("MEM_RPTCNT");
			System.out.println("No. " + rowNum + "\t[ID] " + id + "\t[이름] " + Name + "\t[전화번호] " + tel + "\t[주소] " + address);
			System.out.println("[닉네임] " + nicName + "\t[잔고] " + bank + "\t[노출여부] " + delyn + "\t[신고횟수] " + rptCnt + "\t[보유 이용권] " + ticket);
			System.out.println();
		}

		System.out.println("<이전페이지\t\t다음페이지>");
		System.out.println();
		System.out.println("1. 뒤로가기");
		System.out.println();
		String sel = ScanUtil.nextLine("메뉴 : ");

		if (sel.equals("<")) {
			if (page != 1)
				page--;
			sessionStorage.put("page", page);
			return printAdminMemberList();
		} else if (sel.equals(">")) {
			page++;
			sessionStorage.put("page", page);
			return printAdminMemberList();
		} else if (sel.equals("1")) {
			int num = Integer.parseInt(sel);
			return num;
		}
		else return printAdminMemberList();
	}
	
	public int printAdminRealtorList() {
		System.out.println();
		int page = 1;
		if (sessionStorage.containsKey("page")) {
			page = (int) sessionStorage.remove("page");
		}
		int startNo = 1 + (page - 1) * 5;
		int endNo = page * 5;

		List<Object> param = new ArrayList<Object>();
		param.add(startNo);
		param.add(endNo);
		
		List<Map<String, Object>> adminRealtorList = adminService.adminRealtorList(param);
		for (Map<String, Object> map : adminRealtorList) {
			BigDecimal rowNum = (BigDecimal) map.get("RN");
			String id = (String) map.get("RET_ID");
			String Name = (String) map.get("RET_NAME");
			String tel = (String) map.get("RET_TEL");
			String email = (String) map.get("RET_MAIL");
			BigDecimal comNum = (BigDecimal) map.get("COM_NO");
			BigDecimal bank = (BigDecimal) map.get("RET_BANK");
			String delyn = (String) map.get("RET_DELYN");
			if (delyn.equals("N")) {
				delyn = "노출유지";
			} else if (delyn.equals("Y")) {
				delyn = "노출중지";
			}
			String ticket = (String) map.get("TIC_TIER");
			BigDecimal rptCnt = (BigDecimal) map.get("RET_RPTCNT");
			System.out.println("No. " + rowNum + "\t[ID] " + id + "\t[이름] " + Name + "\t[전화번호] " + tel + "\t[메일] " + email);
			System.out.println("[회사번호] " + comNum + "\t\t[잔고] " + bank + "\t[노출여부] " + delyn + "\t[신고횟수] " + rptCnt + "\t[보유 이용권] " + ticket);
			System.out.println();
		}

		System.out.println("<이전페이지\t\t다음페이지>");
		System.out.println();
		System.out.println("1. 회사 상세조회");
		System.out.println("2. 뒤로가기");
		System.out.println();
		String sel = ScanUtil.nextLine("메뉴 : ");

		if (sel.equals("<")) {
			if (page != 1)
				page--;
			sessionStorage.put("page", page);
			return printAdminRealtorList();
		} else if (sel.equals(">")) {
			page++;
			sessionStorage.put("page", page);
			return printAdminRealtorList();
		} else if (sel.equals("1") || sel.equals("2")) {
			int num = Integer.parseInt(sel);
			return num;
		}
		else return printAdminRealtorList();
	}
	
	
}


