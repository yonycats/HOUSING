package controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import service.AdminService;
import service.EstateService;
import service.MemberService;
import service.NoticeService;
import util.ScanUtil;
import util.View;
import view.Print;

public class MainController extends Print {

	static public Map<String, Object> sessionStorage = new HashMap<>();

	AdminService adminService = AdminService.getInstance();
	MemberService memberService = MemberService.getInstance();
	NoticeService noticeService = NoticeService.getInstance();
	EstateService estateService = EstateService.getInstance();

	boolean debug = true;

	public static void main(String[] args) {
		new MainController().start();
	}

	private void start() {
		View view = View.HOME;
		while (true) {
			switch (view) {
			case HOME:
				view = home();
				break;
			case ADMINLOGIN:
				view = adminLogin();
				break;
			case MEMBERLOGIN:
				view = memberLogin();
				break;
			case ADMIN:
				view = admin();
				break;
			case MEMBER:
				view = member();
				break;
			case MYINFO:
				view = myInfo();
				break;
			case ADMIN_NOTICE:
				view = adminNotice();
				break;
			case NOTICE_LIST:
				view = noticeList();
				break;
			case ADMIN_NOTICE_INSERT:
				view = adminNoticeInsert();
				break;
			case ADMIN_NOTICE_UPDATE:
				view = adminNoticeUpdate();
				break;
			case ADMIN_NOTICE_DELETE:
				view = adminNoticeDelete();
				break;
			case ADMIN_REPORT:
				view = adminReport();
				break;
			case ADMIN_REPORT_LIST:
				view = adminReportList();
				break;
			case ADMIN_REPORT_DETAIL:
				view = adminReportDetail();
				break;
			case ADMIN_REPORT_DOING:
				view = adminReportDoing();
				break;
			case ADMIN_REPORT_FINISH:
				view = adminReportFinish();
				break;
			case ADMIN_SALE:
				view = adminSale();
				break;
			case ADMIN_SALEDAY:
				view = adminSaleDay();
				break;
//			case ADMIN_SALEMONTH:
//				view = adminSaleMonth();
//				break;
//			case ADMIN_SALEYEAR:
//				view = adminSaleYear();
//				break;
			case ADMIN_TICKET:
				view = adminTicket();
				break;
			case TICKET_LIST:
				view = tichetList();
				break;
			case ADMIN_TICKET_INSERT:
				view = adminTicketInsert();
				break;
			case ADMIN_TICKET_UPDATE:
				view = adminTicketUpdate();
				break;
			case ADMIN_TICKET_DELETE:
				view = adminTicketDelete();
				break;
			case MEMBER_UPDATE:
				view = memberUpdate();
				break;
			case MEMBER_DELETE:
				view = memberDelete();
				break;
//			case MEMBER_REPORT:
//				view = memberReport();
//				break;
			case EST_LIST:
				view = estList();
				break;
			case EST_ADD:
				view = estAdd();
				break;
			default:
				break;
			}
		}
	}

	
	private View adminReportFinish() {
		if (debug) System.out.println("=========처리완료 신고 목록=========");
		System.out.println();
		
		return View.ADMIN_REPORT;
	}

	
	private View adminReportDoing() {
		if (debug) System.out.println("=========미처리 신고 목록=========");
		System.out.println();
		
		return View.ADMIN_REPORT;
	}

	
	private View adminReportDetail() {
		if (debug) System.out.println("=========신고 상세보기=========");
		System.out.println();
		
		return View.ADMIN_REPORT;
	}
	
	
	private View adminReportList() {
		System.out.println();
		
		List<Map<String, Object>> param = adminService.adminReportList();
		
		int no = param.get("RPT_NO");
		
		return View.ADMIN_REPORT;
	}

	
	private View adminReport() {
		if (debug) System.out.println("=========신고 관리=========");
		System.out.println();

		adminReportList();
		
		System.out.println("1. 전체 신고 목록");
		System.out.println("2. 미처리 신고 목록");
		System.out.println("3. 처리완료 신고 목록");
		System.out.println("4. 뒤로가기");
		
		System.out.println();
		
		int sel = ScanUtil.menu();
		
		if (sel==1) return View.ADMIN_REPORT_LIST;
		else if (sel==2) return View.ADMIN_REPORT_DOING;
		else if (sel==3) return View.ADMIN_REPORT_FINISH;
		else if (sel==4) return View.ADMIN;
		else return View.ADMIN;
	}

	
	private View adminTicketDelete() {
		if (debug) System.out.println("=========이용권 삭제=========");
		System.out.println();
		
		List<Object> param = new ArrayList<Object>();
		
		int no = ScanUtil.nextInt("이용권 번호 : ");
		
		param.add(no);
		
		System.out.println();
		System.out.println("정말 삭제하시겠습니까?");
		System.out.println();
		int sel = ScanUtil.nextInt("1. 삭제\t2. 취소");
		
		if (sel == 1) {
			int result = adminService.adminTicketDelete(param);
			if (result == 1) {
				System.out.println();
				System.out.println("이용권 삭제를 성공했습니다.");
				System.out.println();
				return View.ADMIN_TICKET;
			}
		} else if (sel == 2) {
			System.out.println();
			System.out.println("이용권 삭제를 취소했습니다.");
			System.out.println();
			return View.ADMIN_TICKET;
		}
		
		return View.ADMIN_TICKET;
	}

	
	private View adminTicketUpdate() {
		if (debug) System.out.println("=========이용권 수정=========");
		System.out.println();
		
		int no = ScanUtil.nextInt("이용권 번호 : ");
		String name = ScanUtil.nextLine("이용권명 : ");
		int price = ScanUtil.nextInt("이용권 가격 : ");
		String comment = ScanUtil.nextLine("이용권 설명 : ");
		
		List<Object> param = new ArrayList<Object>();
		param.add(name);
		param.add(price);
		param.add(comment);
		param.add(no);
		
		adminService.adminTicketUpdate(param);
		
		return View.ADMIN_TICKET;
	}

	
	private View adminTicketInsert() {
		if (debug) System.out.println("=========이용권 추가=========");
		
		String name = ScanUtil.nextLine("이용권명 : ");
		int price = ScanUtil.nextInt("이용권 가격 : ");
		String comment = ScanUtil.nextLine("이용권 설명 : ");
		
		List<Object> param = new ArrayList<Object>();
		param.add(name);
		param.add(price);
		param.add(comment);
		
		adminService.adminTicketInsert(param);
		
		return View.ADMIN_TICKET;
	}

	
	private View adminTicket() {
		tichetList();
		System.out.println();
		
		System.out.println("1. 이용권 추가");
		System.out.println("2. 이용권 수정");
		System.out.println("3. 이용권 삭제");
		System.out.println("4. 뒤로가기");
		System.out.println();
		
		int sel = ScanUtil.nextInt("메뉴 : ");
		
		if (sel==1) return View.ADMIN_TICKET_INSERT;
		else if (sel==2) return View.ADMIN_TICKET_UPDATE;
		else if (sel==3) return View.ADMIN_TICKET_DELETE;
		else if (sel==4) return View.ADMIN;
		else return View.ADMIN;
	}
	
	
	private View tichetList() {
		System.out.println();
		
		List<Map<String, Object>> param = adminService.tichetList();
		
		System.out.println("===========================이용권 안내 [1개월 이용시 금액 (VAT 포함)]===============================");
		for (Map<String, Object> map : param) {
			BigDecimal no = (BigDecimal)map.get("TIC_NO");
			String tier = (String)map.get("TIC_TIER");
			String price = (String)map.get("TIC_PRICE");
			String comment = (String)map.get("TIC_COMMENT");
			System.out.println(" No."+no+" "+tier+"\t [가격]"+price+"원    [설명] "+comment);
		}
		System.out.println("========================================================================================");

		return View.ADMIN_TICKET;
	}

	
//	private View adminSaleDay() {
//		if (debug) System.out.println("=========일 매출 내역=========");
//		System.out.println();
//		
//		System.out.println("매출 조회를 원하는 날짜를 입력하세요.");
//		String year = ScanUtil.nextLine("연도 : ");
//		String month = ScanUtil.nextLine("월 : ");
//		String day = ScanUtil.nextLine("일 : ");
//		String dates = year+month+day;
//		
//		List<Object> param = new ArrayList<Object>();
//		param.add(dates);
//		List<Map<String, Object>> date = adminService.daySaleTotal(param);
//		
//		prints(date);
//		
//		System.out.println();
//		return View.ADMIN_SALE;
//	}
	
	
	private View adminSaleDay() {
		if (debug) System.out.println("=========일 매출 내역=========");
		System.out.println();
		
		System.out.println("매출 조회를 원하는 날짜를 입력하세요.");
		String year = ScanUtil.nextLine("연도 : ");
		String month = ScanUtil.nextLine("월 : ");
		String day = ScanUtil.nextLine("일 : ");
		
		if (month.equals("1") || month.equals("2") || month.equals("3") || month.equals("4") || month.equals("5")
				 || month.equals("6") || month.equals("7") || month.equals("8") || month.equals("9")) {
			String month1 = "0"+month;
			month = month1;
		}
		if (day.equals("1") || day.equals("2") || day.equals("3") || day.equals("4") || day.equals("5")
				 || day.equals("6") || day.equals("7") || day.equals("8") || day.equals("9")) {
			String day1 = "0"+day;
			day = day1;
		}
		String dates = year+month+day;
		
		List<Object> param = new ArrayList<Object>();
		param.add(dates);
		Map<String, Object> date = adminService.daySaleTotal(param);
		
		String price = (String) date.get("PRICE");
		System.out.println("[ "+year+"년  "+month+"월  "+day+"일  매출 ] "+price+"원");
		
		System.out.println();
		return View.ADMIN_SALE;
	}

	
	private View adminSale() {
		if (debug) System.out.println("=========매출 관리=========");
		
		System.out.println("1. 일 매출 조회");
		System.out.println("2. 월 매출 조회");
		System.out.println("3. 연 매출 조회");
		System.out.println("4. 뒤로가기");

		int sel = ScanUtil.nextInt("메뉴 : ");
		
		if (sel == 1) return View.ADMIN_SALEDAY;
		else if (sel == 2) return View.ADMIN_SALEMONTH;
		else if (sel == 3) return View.ADMIN_SALEYEAR;
		else if (sel == 4) return View.ADMIN;
		else return View.ADMIN;
	}

	
	private View memberDelete() {
		if (debug) System.out.println("=========회원 탈퇴=========");
		
		Map<String, Object> member = (Map<String, Object>) sessionStorage.get("member");
		String id = (String)member.get("MEM_ID");
		
		List<Object> param = new ArrayList<Object>();
		param.add(id);
		
		System.out.println();
		System.out.println("정말 탈퇴하시겠습니까?");
		System.out.println();
		int sel = ScanUtil.nextInt("1. 삭제\t2. 취소");
		
		if (sel == 1) {
			int result = memberService.memberDelete(param);
			if (result == 1) {
				System.out.println();
				System.out.println("회원 탈퇴에 성공했습니다.");
				System.out.println();
				return View.HOME;
			}
		} else if (sel == 2) {
			System.out.println();
			System.out.println("회원 탈퇴를 취소하였습니다.");
			System.out.println();
			return View.MEMBER;
		}
		return View.HOME;
	}

	
	private View memberUpdate() {
		if(debug) System.out.println("=========회원정보 변경=========");
		
		myInfo();
		System.out.println();
		System.out.println("수정할 정보를 입력하세요.");
		
		Map<String, Object> member = (Map<String, Object>) sessionStorage.get("member");
		String id = (String)member.get("MEM_ID");
		
		String pw = ScanUtil.nextLine("PW : ");
		String name = ScanUtil.nextLine("이름 : ");
		String tel = ScanUtil.nextLine("전화번호 : ");
		String add = ScanUtil.nextLine("주소 : ");
		String nickName = ScanUtil.nextLine("닉네임 : ");
		
		List<Object> param = new ArrayList<Object>();
		
		param.add(pw);
		param.add(name);
		param.add(tel);
		param.add(add);
		param.add(nickName);
		param.add(id);
		
		memberService.memberUpdate(param);
		
		myInfo();
		
		return View.MEMBER;
	}

	
	private View adminNoticeDelete() {
		if (debug) System.out.println("=========공지사항 삭제=========");
		noticeList();
		System.out.println();
		
		List<Object> param = new ArrayList<Object>();
		
		int no = ScanUtil.nextInt("공지번호 : ");
		
		param.add(no);
		
		noticeService.adminNoticeDelete(param);
		
		return View.ADMIN_NOTICE;
	}

	
	private View adminNoticeUpdate() {
		if (debug) System.out.println("=========공지사항 수정=========");
		noticeList();
		System.out.println();
		
		List<Object> param = new ArrayList<Object>();
		
		int no = ScanUtil.nextInt("공지번호 : ");
		String title = ScanUtil.nextLine("제목 : ");
		String content = ScanUtil.nextLine("내용 : ");
		
		param.add(title);
		param.add(content);
		param.add(no);
		
		noticeService.adminNoticeUpdate(param);
		
		return View.ADMIN_NOTICE;
	}

	
	private View adminNoticeInsert() {
		if (debug) System.out.println("=========공지사항 작성=========");
		noticeList();
		System.out.println();
		
		List<Object> param = new ArrayList<Object>();
		
		String title = ScanUtil.nextLine("제목 : ");
		String content = ScanUtil.nextLine("내용 : ");
		
		param.add(title);
		param.add(content);
		
		noticeService.adminNoticeInsert(param);
		
		return View.ADMIN_NOTICE;
	}

	
	private View noticeList() {
		System.out.println();
		List<Map<String, Object>> param = noticeService.noticeList();
				
		for (Map<String, Object> map : param) {
			BigDecimal no = (BigDecimal)map.get("NTC_NO");
			String date = (String)map.get("NTC_DATE");
			String title = (String)map.get("NTC_TITLE");
			String content = (String)map.get("NTC_CONTENT");
			System.out.println("No."+no+"\t[등록일] "+date+"   "+title+"    \t [내용] "+content);
		}	
		System.out.println();
		return View.HOME;
	}
	
	
	private View adminNotice() {
		if (debug) System.out.println("=========공지사항 관리=========");
		System.out.println();

		noticeList();
		
		System.out.println("1. 공지사항 작성");
		System.out.println("2. 공지사항 수정");
		System.out.println("3. 공지사항 삭제");
		System.out.println("4. 뒤로가기");
		
		System.out.println();
		
		int sel = ScanUtil.menu();
		
		if (sel==1) return View.ADMIN_NOTICE_INSERT;
		else if (sel==2) return View.ADMIN_NOTICE_UPDATE;
		else if (sel==3) return View.ADMIN_NOTICE_DELETE;
		else if (sel==4) return View.ADMIN;
		else return View.ADMIN;
	}

	
	private View estAdd() {
		if(!sessionStorage.containsKey("member")) {
			System.out.println("로그인한 회원만 사용가능한 메뉴입니다.");
			return View.MEMBERLOGIN;
		}
		
		
		
		return View.HOME;
	}

	
	private View myInfo() {
		System.out.println();
		
		Map<String, Object> member = (Map<String, Object>) sessionStorage.get("member");
		String id = (String)member.get("MEM_ID");
		
		List<Object> param = new ArrayList<Object>();
		param.add(id);
		Map<String, Object> param1 = memberService.myInfo(param);
		
		String pw = (String)param1.get("MEM_PW");
		String name = (String)param1.get("MEM_NAME");
		String tel = (String)param1.get("MEM_TEL");
		String address = (String)param1.get("MEM_ADDRESS");
		String nicName = (String)param1.get("MEM_NICKNAME");
		BigDecimal bank = (BigDecimal)param1.get("MEM_BANK");
		String tier = (String)param1.get("TIC_TIER");
		BigDecimal rptCnt = (BigDecimal)param1.get("MEM_RPTCNT");
		
		System.out.println("ID : "+id+"\t PW : "+pw+"\t 이름 : "+name+"\t 주소 : "+address);
		System.out.println("전화번호 : "+tel+"\t 닉네임 : "+nicName+"\t 현재 잔액 : "+bank+"\t 보유 이용권 : "+tier+"\t나의 경고 횟수 : "+rptCnt);
		System.out.println();
		
		return View.MEMBER;
	}
	
	
	private View estList() {
		if (debug) System.out.println("=========집 매물 정보 보기=========");
		System.out.println();

		List<Map<String, Object>> param = estateService.estList();

		int cnt = 1;
		for (Map<String, Object> map : param) {
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

			String estType = "";
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

			System.out.println("No." + estNo + "\t[등록일] " + estDate + "\t[" + estType + "]\t[" + estTranType
					+ "]\t[매물명] " + estName + "\t\t" + estFloor + "층" + "\t[주소] " + estAdd);
			System.out.println("\t[매물상태] " + estState + "\t" + "\t[공급면적] " + estSupArea + "m2\t[전용면적] " + estExcArea
					+ "m2\t[관리비] " + estFee + "원" + "\t[금액] " + estPrice + "원 " + "\t\t [보증금] " + estDeptsit + "원");
			System.out.println();
		}
		System.out.println();

		return View.HOME;
	}

	
	private View member() {
		if (debug) System.out.println("=========일반회원 페이지=========");
		System.out.println();

		System.out.println("1. 회원 정보 보기\t2. 회원 정보 변경\t3. 회원 탈퇴\t4. 내 매물 정보\t5. 리뷰 관리");
		System.out.println("6. 거래 목록\t7. 찜 목록\t\t8. 신고\t\t9. 홈\t\t10. 로그아웃");
		System.out.println();
		
		int sel = ScanUtil.menu();

		if (sel == 1) return View.MYINFO;
		else if (sel == 2) return View.MEMBER_UPDATE;
		else if (sel == 3) return View.MEMBER_DELETE;
		else if (sel == 4) return View.MEMBER_MYESTLIST;
		else if (sel == 5) return View.MEMBER_REVIEW;
		else if (sel == 6) return View.MEMBER_MYSALELIST;
		else if (sel == 7) return View.MEMBER_WISHLIST;
		else if (sel == 8) return View.MEMBER_REPORT;
		else if (sel == 9) return View.HOME;
		else if (sel == 10) {
			sessionStorage.remove("admin");
			return View.HOME;
		}
		else return View.ADMIN;
		
	}
	
	
	private View memberLogin() {
		if (debug) System.out.println("=========일반회원 로그인 페이지=========");
		System.out.println();

//		String id = ScanUtil.nextLine("ID  : ");
//		String pw = ScanUtil.nextLine("PASS: ");
		String id = "abc123kdm";
		String pw = "pass123!";
		
		List<Object> param = new ArrayList<Object>();
		param.add(id);
		param.add(pw);
		
		boolean loginChk = memberService.login(param);
		
		if(!loginChk) {
			System.out.println("로그인을 실패했습니다.");
			return View.MEMBERLOGIN;
		} else {
			System.out.println("로그인에 성공했습니다.");
		}
		System.out.println();
		
		if(loginChk) return View.MEMBER;
		else return View.HOME;
	}
	
	
	private View adminLogin() {
		if (debug) System.out.println("=========관리자 로그인 페이지=========");
		System.out.println();

//		String id = ScanUtil.nextLine("ID  : ");
//		String pw = ScanUtil.nextLine("PASS: ");
		String id = "admin";
		String pw = "admin";
		
		List<Object> param = new ArrayList<Object>();
		param.add(id);
		param.add(pw);
		
		boolean loginChk = adminService.login(param);
		
		if(!loginChk) {
			System.out.println("로그인을 실패했습니다.");
			return View.ADMINLOGIN;
		} else {
			System.out.println("로그인에 성공했습니다.");
		}
		System.out.println();
		
		if(loginChk) return View.ADMIN;
		else return View.HOME;
	}
	
	
	private View admin() {
		
		if (debug) System.out.println("=========관리자 페이지=========");
		System.out.println();
		
		System.out.println("1. 공지사항 관리");
		System.out.println("2. 신고 관리");
		System.out.println("3. 이용권 관리");
		System.out.println("4. 매출 관리");		
		System.out.println("5. 로그아웃");
		System.out.println();
		
		int sel = ScanUtil.menu();
		
		if (sel==1) return View.ADMIN_NOTICE;
		else if (sel==2) return View.ADMIN_REPORT;
		else if (sel==3) return View.ADMIN_TICKET;
		else if (sel==4) return View.ADMIN_SALE;
		else if (sel==5) {
			sessionStorage.remove("admin");
			return View.HOME;
		}
		else return View.ADMIN;
	}


	private View home() {
		if (debug) System.out.println("==========홈==========");
		System.out.println();

		System.out.println("1. 집 매물 정보 보기");
		System.out.println("2. 집 매물 등록하기");
		System.out.println("3. 로그인");
		System.out.println("4. 회원가입");
		System.out.println("5. 공지사항");
		System.out.println();

		int sel = ScanUtil.menu();

		if (sel == 1)
			return View.EST_LIST;
		else if (sel == 2)
			return View.EST_ADD;
		else if (sel == 3)
			return View.MEMBERLOGIN;
		else if (sel == 4)
			return View.SIGN;
		else if (sel == 5)
			return View.NOTICE_LIST;
		else if (sel == 0)
			return View.ADMINLOGIN;
		return View.HOME;
	}

}