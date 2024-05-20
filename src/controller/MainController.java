package controller;

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
import view.Print;

public class MainController extends Print {

	static public Map<String, Object> sessionStorage = new HashMap<>();

	AdminService adminService = AdminService.getInstance();
	MemberService memberService = MemberService.getInstance();
	EstateService estateService = EstateService.getInstance();
	RealtorService realtorService = RealtorService.getInstance();

	boolean debug = true;

	final int PAGE_SIZE = 5;

	public static void main(String[] args) {
		logo();
		new MainController().start();
	}

	private void start() {
		View view = View.HOME;
		while (true) {
			switch (view) {
			case HOME:
				view = home();
				break;
			case SIGN:
				view = sign();
				break;
			case LOGIN:
				view = login();
				break;
			case ADMINLOGIN:
				view = adminLogin();
				break;
			case ADMIN:
				view = admin();
				break;
			case MEMBER:
				view = member();
				break;
			case REALTOR:
				view = realtor();
				break;
			case MYINFO:
				view = myInfo();
				break;
			case SELLER_INFO:
				view = sellerInfo();
				break;
			case ADMIN_USERLIST:
				view = adminUserList();
				break;
			case ADMIN_MEMBER_LIST:
				view = adminMemberList();
				break;
			case ADMIN_REALTOR_LIST:
				view = adminRealtorList();
				break;
			case ADMIN_COM_LIST:
				view = admincomList();
				break;
			case ADMIN_RETCOM_LIST:
				view = adminRetComList();
				break;
			case NOTICE_LIST:
				view = noticeList();
				break;
			case ADMIN_NOTICE:
				view = adminNotice();
				break;
			case NOTICE_LIST_DETAIL:
				view = noticeListDetail();
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
			case ADMIN_REPORT_RECORD:
				view = adminReportRecord();
				break;
			case ADMIN_REPORT_EST:
				view = adminReportEst();
				break;
			case ADMIN_SALE:
				view = adminSale();
				break;
			case ADMIN_SALEDAY:
				view = adminSaleDay();
				break;
			case ADMIN_SALEMONTH:
				view = adminSaleMonth();
				break;
			case ADMIN_SALEYEAR:
				view = adminSaleYear();
				break;
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
			case MEMBER_REPORT:
				view = memberReport();
				break;
			case MEMBER_REPORTINSERT:
				view = memberReportInsert();
				break;
			case MEMBER_DETAIL:
				view = memberDetail();
				break;
			case MEMBER_UPDATE:
				view = memberUpdate();
				break;
			case REALTOR_UPDATE:
				view = realtorUpdate();
				break;
			case REALTOR_DELETE:
				view = realtorDelete();
				break;
			case MEMBER_DELETE:
				view = memberDelete();
				break;
			case MEMBER_WISHLIST:
				view = memberWishList();
				break;
			case WISHLIST_INSERT:
				view = wishListInsert();
				break;
			case WISHLIST_DELETE:
				view = wishListDelete();
				break;
			case MYESTLIST:
				view = myEstList();
				break;
			case RESERVATION:
				view = reservation();
				break;
			case RESERVATIONLIST:
				view = reservationList();
				break;
			case MYEST_RESERVATIONLIST:
				view = myEstReservationList();
				break;
			case RESERVATION_INSERT:
				view = reservationInsert();
				break;
			case REVIEW:
				view = review();
				break;
			case REVIEW_INSERT:
				view = reviewInsert();
				break;
			case REVIEW_DETAIL:
				view = reviewDetail();
				break;
			case REVIEW_DELETE:
				view = reviewDelete();
				break;
			case REVIEW_UPDATE:
				view = reviewUpdate();
				break;
			case REVIEWLIST:
				view = reviewList();
				break;
			case EST_LIST:
				view = estList();
				break;
			case EST_DETAILLIST:
				view = estDetailList();
				break;
			case EST_DETAIL:
				view = estDetail();
				break;
			case EST_SEARCH:
				view = estSearch();
				break;
			case EST_ADD:
				view = estAdd();
				break;
			case EST_UPDATE:
				view = estUpdate();
				break;
			case EST_DELETE:
				view = estDelete();
				break;
			case FINDID:
				view = findId();
				break;
			case FINDPW:
				view = findPw();
				break;
			default:
				break;
			}
		}
	}

	private View adminRetComList() {

	

		int comNo = (int) sessionStorage.get("comNo");
		List<Object> param = new ArrayList<Object>();
		param.add(comNo);

		Map<String, Object> myCom = adminService.myCom(param);

		comList(myCom);
		List<Object> param2 = new ArrayList<Object>();
		param2.add(comNo);

		List<Map<String, Object>> printAdminRetcomList = adminService.adminRetcomList(param);
		

		for (Map<String, Object> map : printAdminRetcomList) {
			String id = (String) map.get("RET_ID");
			String Name = (String) map.get("RET_NAME");
			String tel = (String) map.get("RET_TEL");
			String email = (String) map.get("RET_MAIL");
			BigDecimal bank = (BigDecimal) map.get("RET_BANK");
			String delyn = (String) map.get("RET_DELYN");
			if (delyn.equals("N")) {
				delyn = "노출유지";
			} else if (delyn.equals("Y")) {
				delyn = "노출중지";
			}
			String ticket = (String) map.get("TIC_TIER");
			BigDecimal rptCnt = (BigDecimal) map.get("RET_RPTCNT");
			System.out.println("[소속 공인중개사 리스트]");
			System.out.println("[ID] " + id + "\t[이름] " + Name + "\t[전화번호] " + tel + "\t[메일] " + email);
			System.out.println("[잔고] " + bank + "\t[노출여부] " + delyn + "\t[신고횟수] " + rptCnt + "\t[보유 이용권] " + ticket);
			System.out.println();
		}
		return View.ADMIN_USERLIST;

	}

	private View wishListDelete() {
		int estNo = (int) sessionStorage.get("estNo");
		List<Object> param = new ArrayList<Object>();
		param.add(estNo);
		String id = (String) sessionStorage.get("member");
		param.add(id);
		memberService.wishListDelete(param);
		return View.MEMBER_WISHLIST;
	}

	private View reservationInsert() {
		if (debug)
			System.out.println("=========예약 추가=========");
		System.out.println();
		int estNo = (int) sessionStorage.get("estNo");
		String year = ScanUtil.nextLine("예약 연도 : ");
		String month = ScanUtil.nextLine("예약 월 : ");
		String day = ScanUtil.nextLine("예약일 : ");

		if (month.equals("1") || month.equals("2") || month.equals("3") || month.equals("4") || month.equals("5")
				|| month.equals("6") || month.equals("7") || month.equals("8") || month.equals("9")) {
			String month1 = "0" + month;
			month = month1;
		}
		if (day.equals("1") || day.equals("2") || day.equals("3") || day.equals("4") || day.equals("5")
				|| day.equals("6") || day.equals("7") || day.equals("8") || day.equals("9")) {
			String day1 = "0" + day;
			day = day1;
		}

		String date = year + month + day;
		String name = ScanUtil.nextLine("예약자 이름 : ");
		String tel = ScanUtil.nextLine("예약자 전화번호 : ");

		List<Object> param = new ArrayList<Object>();
		param.add(date);
		param.add(name);
		param.add(tel);
		param.add(estNo);

		realtorService.realtorReservationInsert(param);

		return View.MYEST_RESERVATIONLIST;
	}

	private View reservation() {
		if (debug)
			System.out.println("=========예약 관리=========");
		System.out.println();

		if (sessionStorage.containsKey("member")) {
			System.out.println("1. 내 방문 예약 리스트");
			System.out.println("2. 내 매물 예약 리스트");
			System.out.println("3. 뒤로가기");
			System.out.println();
			int sel = ScanUtil.nextInt("메뉴 : ");
			if (sel == 1) {
				return View.RESERVATIONLIST;
			} else if (sel == 2) {
				return View.MYEST_RESERVATIONLIST;
			} else if (sel == 3) {
				return View.MEMBER;
			}
		} else if (sessionStorage.containsKey("realtor")) {
			System.out.println("1. 내 매물 예약 리스트");
			System.out.println("2. 뒤로가기");
			System.out.println();
			int sel = ScanUtil.nextInt("메뉴 : ");
			if (sel == 1) {
				return View.MYEST_RESERVATIONLIST;
			} else if (sel == 2) {
				return View.REALTOR;
			}
			return View.REALTOR;
		} else
			return View.HOME;
		return View.HOME;
	}

	private View myEstReservationList() {
		if (debug)
			System.out.println("=========내 매물 예약 리스트=========");
		System.out.println();
		int page = 1;
		if (sessionStorage.containsKey("page"))
			page = (int) sessionStorage.remove("page");
		int startNo = 1 + (page - 1) * PAGE_SIZE;
		int endNo = page * PAGE_SIZE;

		String id = "";

		if (sessionStorage.containsKey("member")) {
			Map<String, Object> realtor = (Map<String, Object>) sessionStorage.get("member");
			id = (String) realtor.get("MEM_ID");
		} else if (sessionStorage.containsKey("realtor")) {
			Map<String, Object> realtor = (Map<String, Object>) sessionStorage.get("realtor");
			id = (String) realtor.get("RET_ID");
		}

		List<Object> param = new ArrayList<Object>();
		param.add(id);
		param.add(id);
		param.add(startNo);
		param.add(endNo);

		List<Map<String, Object>> reservationList = realtorService.reservationList(param);
		if (reservationList == null) {
			if (page != 1) {
				System.out.println("마지막 페이지입니다.");
				page--;
				sessionStorage.put("page", page);
				return View.MYEST_RESERVATIONLIST;
			}
			System.out.println("해당사항 없습니다.");
			return View.MEMBER;
		}
		for (Map<String, Object> map : reservationList) {
			reservationList(map, 1);
		}

		System.out.println("<이전페이지\t\t다음페이지>");
		System.out.println();
		System.out.println("1. 예약상세조회");
		System.out.println("2. 뒤로가기");
		System.out.println();
		String sel = ScanUtil.nextLine("메뉴 : ");

		if (sel.equals("<")) {
			if (page != 1)
				page--;
			sessionStorage.put("page", page);
			return View.MYEST_RESERVATIONLIST;
		} else if (sel.equals(">")) {
			page++;
			sessionStorage.put("page", page);
			return View.MYEST_RESERVATIONLIST;
		} else if (sel.equals("1")) {
			int estNum = ScanUtil.nextInt("매물번호 : ");
			List<Object> param1 = new ArrayList<Object>();
			param1.add(estNum);
			Map<String, Object> estDetail = estateService.estDetail(param1);
			estList(estDetail, 2);// 밑 출력 Print에 추가함
			return View.MYEST_RESERVATIONLIST;
		}
		if (sessionStorage.containsKey("member")) {
			return View.MEMBER;
		} else if (sessionStorage.containsKey("realtor")) {
			return View.REALTOR;
		} else
			return View.HOME;
	}

	private View reservationList() {
		if (debug)
			System.out.println("=========내 방문 예약 리스트=========");
		System.out.println();
		int page = 1;
		if (sessionStorage.containsKey("page"))
			page = (int) sessionStorage.remove("page");
		int startNo = 1 + (page - 1) * PAGE_SIZE;
		int endNo = page * PAGE_SIZE;

		Map<String, Object> member = (Map<String, Object>) sessionStorage.get("member");

		String id = (String) member.get("MEM_ID");

		List<Object> param = new ArrayList<Object>();
		param.add(id);
		param.add(startNo);
		param.add(endNo);

		List<Map<String, Object>> reservationList = memberService.reservationList(param);
		if (reservationList == null) {
			if (page != 1) {
				System.out.println("마지막 페이지입니다.");
				page--;
				sessionStorage.put("page", page);
				return View.RESERVATIONLIST;
			}
			System.out.println("해당사항 없습니다.");
			return View.MEMBER;
		}

		for (Map<String, Object> map : reservationList) {
			reservationList(map, 2);
		}

		System.out.println("<이전페이지\t\t다음페이지>");
		System.out.println();
		System.out.println("1. 예약상세조회");
		System.out.println("2. 뒤로가기");
		System.out.println();
		String sel = ScanUtil.nextLine("메뉴 : ");

		if (sel.equals("<")) {
			if (page != 1)
				page--;
			sessionStorage.put("page", page);
			return View.RESERVATIONLIST;
		} else if (sel.equals(">")) {
			page++;
			sessionStorage.put("page", page);
			return View.RESERVATIONLIST;
		} else if (sel.equals("1")) {
			int estNum = ScanUtil.nextInt("매물번호 : ");

			List<Object> param1 = new ArrayList<Object>();
			param1.add(estNum);

			Map<String, Object> estDetail = estateService.estDetail(param1);
			estList(estDetail, 2);// 밑 출력 Print에 추가함
			return View.RESERVATIONLIST;
		} else if (sel.equals("2")) {
			return View.RESERVATION;
		}
		if (sessionStorage.containsKey("member")) {
			return View.MEMBER;
		} else if (sessionStorage.containsKey("realtor")) {
			return View.REALTOR;
		} else
			return View.HOME;
	}

	private View admincomList() {
		if (debug)
			System.out.println("=========공인중개소 리스트(가나다순 출력)=========");

		int page = 1;
		if (sessionStorage.containsKey("page"))
			page = (int) sessionStorage.remove("page");
		int startNo = 1 + (page - 1) * PAGE_SIZE;
		int endNo = page * PAGE_SIZE;

		List<Object> param = new ArrayList<Object>();
		param.add(startNo);
		param.add(endNo);

		List<Map<String, Object>> AdminComList = adminService.admincomList(param);
		if (AdminComList == null) {
			if (page != 1) {
				System.out.println("마지막 페이지입니다.");
				page--;
				sessionStorage.put("page", page);
				return View.ADMIN_COM_LIST;
			}
			System.out.println("해당사항 없습니다.");
			return View.MEMBER;
		}
		for (Map<String, Object> map : AdminComList) {
			comList(map);
		}

		System.out.println("<이전페이지\t\t다음페이지>");
		System.out.println();
		System.out.println("1. 상세조회");
		System.out.println("2. 뒤로가기");
		System.out.println();
		String sel = ScanUtil.nextLine("메뉴 : ");

		if (sel.equals("<")) {
			if (page != 1)
				page--;
			sessionStorage.put("page", page);
			return admincomList();
		} else if (sel.equals(">")) {
			page++;
			sessionStorage.put("page", page);
			return admincomList();
		} else if (sel.equals("1")) {
			if (debug)
				System.out.println("=========공인중개소 리스트=========");
			int comNum = ScanUtil.nextInt("회사 번호 입력 : ");
			System.out.println();
			sessionStorage.put("comNo", comNum);
			return View.ADMIN_RETCOM_LIST;
		}
		else if(sel.equals("2"))return View.ADMIN_USERLIST;
		
		return View.ADMIN;
	}

	private View adminRealtorList() {
		if (debug)
			System.out.println("=========공인중개사 리스트 (이용권별 출력)=========");
		int sel = printAdminRealtorList();

		if (sel == 1) {
			if (debug)
				System.out.println("=========공인중개소 리스트 (가나다 출력)=========");

			int comNum = ScanUtil.nextInt("회사 번호 입력 : ");
			System.out.println();
			sessionStorage.put("comNo", comNum);
			return View.ADMIN_RETCOM_LIST;
		}
		else return View.ADMIN_USERLIST;
	}

	private View adminMemberList() {
		if (debug)
			System.out.println("=========일반회원 리스트 (이용권별 출력)=========");
		printAdminMemberList();
		return View.ADMIN_USERLIST;
	}

	private View adminUserList() {
		if (debug)
			System.out.println("=========이용자 리스트=========");
		printAdminUserList();

		int sel = ScanUtil.menu();

		if (sel == 1) {
			return View.ADMIN_MEMBER_LIST;
		} else if (sel == 2) {
			return View.ADMIN_REALTOR_LIST;
		} else if (sel == 3) {
			return View.ADMIN_COM_LIST;
		} else if (sel == 4) {
			return View.ADMIN;
		}

		return View.ADMIN_USERLIST;
	}

	private View findPw() {
		List<Object> param = new ArrayList<Object>();
		int sel = ScanUtil.nextInt("찾으실 ID(1.일반회원,2.공인중개사)");
		String id = ScanUtil.nextLine("본인의 ID를 작성하시오: ");
		String name = ScanUtil.nextLine("본인의 이름을 작성하시오: ");
		String tel = ScanUtil.nextLine("본인의 전화번호를 작성하시오: ");
		param.add(id);
		param.add(name);
		param.add(tel);
		Map<String, Object> person;
		String pw = null;
		if (sel == 1) {
			person = memberService.findPw(param);
			pw = (String) person.get("MEM_PW");
		} else if (sel == 2) {
			person = realtorService.findPw(param);
			pw = (String) person.get("RET_PW");
		}
		System.out.println("당신의 PW:" + pw);
		return View.LOGIN;
	}

	private View findId() {
		List<Object> param = new ArrayList<Object>();
		int sel = ScanUtil.nextInt("찾으실 ID(1.일반회원,2.공인중개사)");
		String name = ScanUtil.nextLine("본인의 이름을 작성하시오: ");
		String tel = ScanUtil.nextLine("본인의 전화번호를 작성하시오: ");
		param.add(name);
		param.add(tel);
		Map<String, Object> person;
		String id = null;
		if (sel == 1) {
			person = memberService.findId(param);
			id = (String) person.get("MEM_ID");
		} else if (sel == 2) {
			person = realtorService.findId(param);
			id = (String) person.get("RET_ID");
		}
		System.out.println("당신의 ID:" + id);
		return View.LOGIN;
	}

	private View memberReport() {
		if (!sessionStorage.containsKey("member")) {
			System.out.println("로그인한 일반회원만 사용가능한 메뉴입니다.");
			System.out.println();
			return View.LOGIN;
		}

		if (debug)
			System.out.println("=========매물 신고하기=========");
		System.out.println();

		Map<String, Object> member = (Map<String, Object>) sessionStorage.get("member");
		int estNo = (int) sessionStorage.get("estNo");
		String title = ScanUtil.nextLine("신고 제목 : ");
		String content = ScanUtil.nextLine("신고 내용 : ");
		String id = (String) member.get("MEM_ID");

		List<Object> param = new ArrayList<Object>();
		param.add(title);
		param.add(content);
		param.add(id);
		param.add(estNo);

		System.out.println();
		System.out.println("정말 신고하시겠습니까?");
		System.out.println();
		int sel = ScanUtil.nextInt("1. 신고\t2. 취소");

		if (sel == 1) {
			int result = memberService.memberReport(param);
			if (result == 1) {
				System.out.println();
				System.out.println("신고를 성공했습니다.");
				System.out.println();
				return View.MEMBER;
			}
		} else if (sel == 2) {
			System.out.println();
			System.out.println("신고를 취소했습니다.");
			System.out.println();
			return View.MEMBER;
		}

		return View.MEMBER;
	}

	private View reviewList() {
		if (debug)
			System.out.println("=========나에 대한 리뷰 목록=========");
		System.out.println();

		List<Map<String, Object>> reviewList = null;
		Map<String, Object> reviewScore = null;
		List<Object> param = new ArrayList<Object>();
		if (sessionStorage.containsKey("member")) {
			Map<String, Object> member = (Map<String, Object>) sessionStorage.get("member");
			String id = (String) member.get("MEM_ID");
			param.add(id);

			reviewList = memberService.memberReviewList(param);
			reviewScore = memberService.memberReviewScore(param);
		} else if (sessionStorage.containsKey("realtor")) {
			Map<String, Object> realtor = (Map<String, Object>) sessionStorage.get("realtor");
			String id = (String) realtor.get("RET_ID");
			param.add(id);

			reviewList = realtorService.retReviewList(param);
			reviewScore = realtorService.retReviewScore(param);
		}
		BigDecimal scoreAvg = (BigDecimal) reviewScore.get("평균평점");
		System.out.println("[나의 리뷰 평균평점] \t\t" + scoreAvg + "점");
		System.out.println();

		for (Map<String, Object> map : reviewList) {
			reviewList(map);
		}
		System.out.println();

		if (sessionStorage.containsKey("member")) {
			return View.MEMBER;
		} else if (sessionStorage.containsKey("realtor")) {

			return View.REALTOR;
		} else
			return View.HOME;
	}

	private View wishListInsert() {
		if (!sessionStorage.containsKey("member")) {
			System.out.println("로그인한 일반회원만 사용가능한 메뉴입니다.");
			System.out.println();
			return View.LOGIN;
		}

		int estNo = (int) sessionStorage.get("estNo");
		List<Object> param = new ArrayList<Object>();
		Map<String, Object> member = (Map<String, Object>) sessionStorage.get("member");
		String id = (String) member.get("MEM_ID");
		param.add(id);
		param.add(estNo);
		boolean wishlistChk = false;
		wishlistChk = memberService.wishlistChk(param);
		if (wishlistChk)
			memberService.wishListInsert(param);
		else
			System.out.println("찜 목록에 이미 있습니다.");
		return View.EST_DETAIL;
	}

	private View memberWishList() {

		Map<String, Object> member = (Map<String, Object>) sessionStorage.get("member");
		String id = (String) member.get("MEM_ID");
		List<Object> param = new ArrayList<Object>();
		param.add(id);
		List<Map<String, Object>> wishList = memberService.wishList(param);
		for (Map<String, Object> map : wishList) {
			wishListEst(map);
		}
		System.out.println("1. 찜 목록 상세보기");
		System.out.println("2. 찜 삭제");
		System.out.println("3. 뒤로가기");
		int sel = ScanUtil.menu();
		if (sel == 1) {
			int estNo = ScanUtil.nextInt("찜한 매물번호를 입력: ");
			sessionStorage.put("estNo", estNo);
			return View.EST_DETAIL;
		} else if (sel == 2) {
			int estNo = ScanUtil.nextInt("석제할 찜매물번호를 입력: ");
			sessionStorage.put("estNo", estNo);
			return View.WISHLIST_DELETE;
		}
		return View.MEMBER;
	}

	private View reviewInsert() {
		Map<String, Object> member = (Map<String, Object>) sessionStorage.get("member");
		String id = (String) member.get("MEM_ID");
		List<Object> param = new ArrayList<Object>();

		int estNo = ScanUtil.nextInt("매물번호 입력:");
		int score = ScanUtil.nextInt("평점 입력:");
		String content = ScanUtil.nextLine("리뷰내용 입력:");
		param.add(content);
		param.add(score);
		param.add(id);
		param.add(estNo);
		memberService.reviewInsert(param);
		return View.REVIEW;
	}

	private View reviewDetail() {
		int boardNo = (int) sessionStorage.get("boardNo");
		List<Object> param = new ArrayList<Object>();
		param.add(boardNo);
		Map<String, Object> review = memberService.reviewDetail(param);
		reviewList(review);
		if (sessionStorage.containsKey("member")) {
			System.out.println("1. 리뷰 삭제");
			System.out.println("2. 리뷰 정보 변경");
			System.out.println("3. 뒤로가기");
			int sel = ScanUtil.menu();
			if (sel == 1)
				return View.REVIEW_DELETE;
			else if (sel == 2)
				return View.REVIEW_UPDATE;
		}
		return View.REVIEW;
	}

	private View review() {
		List<Map<String, Object>> reviewList;
		List<Object> param = new ArrayList<Object>();

		Map<String, Object> member = (Map<String, Object>) MainController.sessionStorage.get("member");
		param.add(member.get("MEM_ID"));
		reviewList = memberService.reviewList(param);

		for (Map<String, Object> map : reviewList) {
			reviewList(map);
		}

		System.out.println("1. 리뷰 상세 보기");
		if (sessionStorage.containsKey("member")) {
			System.out.println("2. 리뷰 작성");
		}
		System.out.println("3. 홈");
		int sel = ScanUtil.menu();
		if (sel == 1) {
			int boardNo = ScanUtil.nextInt("리뷰 번호입력: ");
			sessionStorage.put("boardNo", boardNo);
			return View.REVIEW_DETAIL;
		} else if (sel == 2)
			return View.REVIEW_INSERT;
		else
			return View.MEMBER;
	}

	private View reviewUpdate() {
		int boardNo = (int) sessionStorage.get("boardNo");
		Map<String, Object> member = (Map<String, Object>) sessionStorage.get("member");
		String id = (String) member.get("MEM_ID");
		List<Object> param = new ArrayList<Object>();
		String content = ScanUtil.nextLine("바꿀 리뷰내용: ");
		int score = ScanUtil.nextInt("바꿀 평점 ");

		param.add(content);
		param.add(score);
		param.add(boardNo);
		param.add(id);
		memberService.reviewUpdate(param);
		return View.REVIEW;
	}

	private View reviewDelete() {
		int boardNo = (int) sessionStorage.get("boardNo");
		List<Object> param = new ArrayList<Object>();
		param.add(boardNo);

		System.out.println();
		System.out.println("정말 삭제하시겠습니까?");
		System.out.println();
		int sel = ScanUtil.nextInt("1. 삭제\t2. 취소");

		if (sel == 1) {
			int result = memberService.reviewDelete(param);
			if (result == 1) {
				System.out.println();
				System.out.println("리뷰 삭제를 성공했습니다.");
				System.out.println();
				return View.REVIEW;
			}
		} else if (sel == 2) {
			System.out.println();
			System.out.println("리뷰 삭제를 취소했습니다.");
			System.out.println();
			return View.REVIEW;
		}

		return View.REVIEW;
	}

	private View memberReportInsert() {
		if (!sessionStorage.containsKey("member")) {
			System.out.println("로그인한 일반회원만 사용가능한 메뉴입니다.");
			System.out.println();
			return View.LOGIN;
		}
		if (debug)
			System.out.println("=========매물 신고하기=========");
		System.out.println();

		return View.MEMBER;
	}

	private View adminReportEst() {
		if (debug)
			System.out.println("=========매물 신고 상세보기=========");
		System.out.println();

		BigDecimal estNo = (BigDecimal) sessionStorage.get("estNo");

		return View.ADMIN_REPORT;
	}

	private View adminReportRecord() {
		if (debug)
			System.out.println("=========신고 목록 보기=========");
		System.out.println();
		int page = 1;
		if (sessionStorage.containsKey("page"))
			page = (int) sessionStorage.remove("page");
		int startNo = 1 + (page - 1) * 5;
		int endNo = page * 5;
		List<Object> param = new ArrayList<Object>();
		param.add(sessionStorage.get("report"));
		param.add(startNo);
		param.add(endNo);
		List<Map<String, Object>> reportRecord = adminService.adminReportRecord(param);

		if (reportRecord == null) {
			if (page != 1) {
				System.out.println("마지막 페이지");
				page--;
				sessionStorage.put("page", page);
				return View.ADMIN_REPORT_RECORD;
			}
			System.out.println("해당 신고 내역이 없습니다.");
			System.out.println();
			return View.ADMIN_REPORT;
		}

		for (Map<String, Object> map : reportRecord) {
			reportList(map);
		}
		System.out.println("< 이전 페이지 \t 다음 페이지 >");
		System.out.println("1. 신고 상세보기");
		System.out.println("2. 뒤로가기");

		System.out.println();
		String sel = ScanUtil.nextLine("메뉴 : ");
		if (sel.equals("<")) {
			if (page != 1)
				page--;
			sessionStorage.put("page", page);
			return View.ADMIN_REPORT_RECORD;
		} else if (sel.equals(">")) {
			page++;
			sessionStorage.put("page", page);
			return View.ADMIN_REPORT_RECORD;
		} else if (sel.equals("1"))
			return View.ADMIN_REPORT_DETAIL;
		else if (sel.equals("2"))
			return View.ADMIN_REPORT;
		else
			return View.ADMIN;
	}

	private View adminReportDetail() {
		if (debug)
			System.out.println("=========신고 상세보기=========");
		System.out.println();

		List<Object> param = new ArrayList<Object>();
		int sel = ScanUtil.nextInt("번호 선택 : ");
		System.out.println();
		param.add(sel);

		Map<String, Object> ReportDetail = adminService.adminReportDetail(param);

		String state = (String) ReportDetail.get("RPT_STATE");
		if (state.equals("DO"))
			state = "처리중";
		else if (state.equals("FIN"))
			state = "처리완료";
		BigDecimal rptNo = (BigDecimal) ReportDetail.get("RPT_NO");
		String date = (String) ReportDetail.get("RPT_DATE");
		String memId = (String) ReportDetail.get("신고자");
		BigDecimal estNo = (BigDecimal) ReportDetail.get("EST_NO");
		String title = (String) ReportDetail.get("RPT_TITLE");
		String content = (String) ReportDetail.get("RPT_CONTENT");
		String estName = (String) ReportDetail.get("EST_NAME");
		String estDelyn = (String) ReportDetail.get("EST_DELYN");
		if (estDelyn.equals("N")) {
			estDelyn = "노출중";
		} else if (estDelyn.equals("Y")) {
			estDelyn = "노출중지";
		}
		String estState = (String) ReportDetail.get("EST_STATE");
		if (estState.equals("1")) {
			estState = "판매중";
		} else if (estState.equals("2")) {
			estState = "예약중";
		} else if (estState.equals("3")) {
			estState = "계약완료";
		} else if (estState.equals("4")) {
			estState = "기한만료";
		} else if (estState.equals("5")) {
			estState = "신고차단";
		}
		String sellerId = "";
		String sellerReportCnt = (String) "";
		String estReportCnt = (String) ReportDetail.get("EST_RPTCNT");
		if (ReportDetail.get("신고당한공인중개사") != null) {
			sellerId = (String) ReportDetail.get("신고당한공인중개사");

			List<Object> retRptcnt = new ArrayList<Object>();
			retRptcnt.add(sellerId);
			Map<String, Object> cnt = adminService.adminReportRetRptcnt(retRptcnt);

			sellerReportCnt = (String) cnt.get("RET_RPTCNT");
		} else if (ReportDetail.get("신고당한회원") != null) {
			sellerId = (String) ReportDetail.get("신고당한회원");

			List<Object> retRptcnt = new ArrayList<Object>();
			retRptcnt.add(sellerId);
			Map<String, Object> cnt = adminService.adminReportMemRptcnt(retRptcnt);

			sellerReportCnt = (String) cnt.get("MEM_RPTCNT");
		}

		System.out.println("[신고번호] " + rptNo + "\t\t[매물번호] " + estNo);
		System.out.println("[상태] " + state + "\t\t[신고일] " + date + "\t[신고자] " + memId);
		System.out.println("[제목] " + title);
		System.out.println("[내용] " + content);
		System.out.println();
		System.out.println();
		System.out.println("* " + estNo + "번 매물 관련 신고 목록 *");
		System.out.println();
		System.out.println("[매물이름] " + estName + "\t[매물노출여부] " + estDelyn);
		System.out.println("[매물상태] " + estState + "\t[매물신고횟수] " + estReportCnt + "\t[판매자아이디] " + sellerId
				+ "\t[판매자신고횟수] " + sellerReportCnt);
		System.out.println();

		List<Object> param1 = new ArrayList<Object>();
		int estno = ((BigDecimal) estNo).intValue();
		int rptno = ((BigDecimal) rptNo).intValue();
		param1.add(estno);

		List<Map<String, Object>> reportListEst = adminService.adminReportDetailEst(param1);

		for (Map<String, Object> map : reportListEst) {
			String rptState = (String) map.get("RPT_STATE");
			if (rptState.equals("DO")) {
				rptState = "처리중";
			} else if (rptState.equals("FIN")) {
				rptState = "처리완료";
			}
			BigDecimal rptNo2 = (BigDecimal) map.get("RPT_NO");
			String rptDate = (String) map.get("RPT_DATE");
			String rptTitle = (String) map.get("RPT_TITLE");
			String rptContent = (String) map.get("RPT_CONTENT");
			System.out.println("No." + rptNo2 + "\t[신고상태] " + rptState + "\t\t[신고일] " + rptDate);
			System.out.println("\t[제목] " + rptTitle);
			System.out.println("\t[내용] " + rptContent);
			System.out.println();
		}

		System.out.println("1. 신고처리");
		System.out.println("2. 기각처리");
		System.out.println("3. 뒤로가기");
		System.out.println();

		sel = ScanUtil.menu();

		List<Map<String, Object>> memberData = memberService.memList();
		List<Map<String, Object>> realtorData = realtorService.retList();

		String seller = "";

		while (true) {
			for (Map<String, Object> map : realtorData) {
				if (map.get("RET_ID").equals(sellerId)) {
					seller = "realtol";
				}
			}
			for (Map<String, Object> map : memberData) {
				if (map.get("MEM_ID").equals(sellerId)) {
					seller = "member";
				}
			}
			if (seller.equals("realtol") || seller.equals("member")) {
				break;
			}
		}

		List<Object> selId = new ArrayList<Object>();
		selId.add(sellerId);
		List<Object> estCntNo = new ArrayList<Object>();
		estCntNo.add(estno);
		List<Object> rptCntNo = new ArrayList<Object>();
		rptCntNo.add(rptno);

		if (sel == 1) {
			if (seller.equals("realtol")) {
				System.out.println();
				System.out.println("신고번호 " + rptno + "번을 신고처리하시겠습니까?");
				System.out.println("1. 예\t2. 아니요");
				System.out.println();

				sel = ScanUtil.menu();

				if (sel == 1) {
					int num1 = adminService.estCntAdd(estCntNo);
					int num2 = adminService.rptDelynFin(rptCntNo);
					int num3 = adminService.retCntAdd(selId);

					if (num1 + num2 + num3 == 3) {
						System.out.println("신고처리가 완료되었습니다.");
						System.out.println();

						adminService.estDelynUpdate(estCntNo);
						adminService.retDelynUpdate(selId);

					} else if (num1 + num2 + num3 < 3) {
						System.out.println("신고처리가 실패했습니다.");
						System.out.println();
					}
				}
				return View.ADMIN_REPORT_RECORD;
			} else if (seller.equals("member")) {
				System.out.println();
				System.out.println("신고번호 " + rptno + "번을 신고처리하시겠습니까?");
				System.out.println("1. 예\t2. 아니요");
				System.out.println();

				sel = ScanUtil.menu();

				if (sel == 1) {
					int num1 = adminService.estCntAdd(estCntNo);
					int num2 = adminService.rptDelynFin(rptCntNo);
					int num3 = adminService.memCntAdd(selId);

					if (num1 + num2 + num3 == 3) {
						System.out.println("신고처리가 완료되었습니다.");
						System.out.println();

						adminService.estDelynUpdate(estCntNo);
						adminService.memDelynUpdate(selId);

					} else if (num1 + num2 + num3 < 3) {
						System.out.println("신고처리가 실패했습니다.");
						System.out.println();
					}
				}
				return View.ADMIN_REPORT_RECORD;
			}

		} else if (sel == 2) {
			List<Object> rptNo3 = new ArrayList<Object>();
			rptNo3.add(rptno);
			System.out.println("신고번호 " + rptNo + "번을 기각처리하시겠습니까?");
			System.out.println("1. 예\t2. 아니요");
			System.out.println();

			sel = ScanUtil.menu();

			if (sel == 1) {
				int num = adminService.rptReject(rptNo3);

				if (num == 1) {
					System.out.println("기각처리가 완료되었습니다.");
					System.out.println();
				} else if (num == 0) {
					System.out.println("기각처리가 실패했습니다.");
					System.out.println();
				}
			}
			return View.ADMIN_REPORT_RECORD;
		} else if (sel == 3)
			return View.ADMIN_REPORT;

		return View.ADMIN_REPORT;
	}

	private View adminReportList() {
		System.out.println();
		int page = 1;
		if (sessionStorage.containsKey("page"))
			page = (int) sessionStorage.remove("page");
		int startNo = 1 + (page - 1) * PAGE_SIZE;
		int endNo = page * PAGE_SIZE;
		List<Object> param = new ArrayList();
		param.add(startNo);
		param.add(endNo);
		List<Map<String, Object>> reportList = adminService.adminReportList(param);
		if (reportList == null) {
			if (page != 1) {
				System.out.println("마지막페이지");
				page--;
				sessionStorage.put("page", page);
				return View.ADMIN_REPORT_LIST;
			} else {
				System.out.println("해당정보가 없습니다");
				return View.ADMIN;
			}
		}
		for (Map<String, Object> map : reportList) {
			reportList(map);
		}
		System.out.println();
		System.out.println("< 이전 페이지 \t 다음 페이지 >");
		System.out.println("1. 뒤로가기");
		String sel = ScanUtil.nextLine("메뉴 : ");
		if (sel.equals("<")) {
			if (page != 1)
				page--;
			sessionStorage.put("page", page);
			return View.ADMIN_REPORT_LIST;
		} else if (sel.equals(">")) {
			page++;
			sessionStorage.put("page", page);
			return View.ADMIN_REPORT_LIST;
		} else
			return View.ADMIN_REPORT;
	}

	private View adminReport() {
		if (debug)
			System.out.println("=========신고 관리=========");
		System.out.println();

		System.out.println("1. 전체 신고 목록");
		System.out.println("2. 미처리 신고 목록");
		System.out.println("3. 처리완료 신고 목록");
		System.out.println("4. 뒤로가기");

		System.out.println();

		int sel = ScanUtil.menu();

		if (sel == 1)
			return View.ADMIN_REPORT_LIST;
		else if (sel == 2) {
			String stateDo = "DO";
			sessionStorage.put("report", stateDo);
			return View.ADMIN_REPORT_RECORD;
		} else if (sel == 3) {
			String stateFin = "FIN";
			sessionStorage.put("report", stateFin);
			return View.ADMIN_REPORT_RECORD;
		} else if (sel == 4)
			return View.ADMIN;
		else
			return View.ADMIN;
	}

	private View adminTicketDelete() {
		if (debug)
			System.out.println("=========이용권 삭제=========");
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
		if (debug)
			System.out.println("=========이용권 수정=========");
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
		if (debug)
			System.out.println("=========이용권 추가=========");

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

		if (sel == 1)
			return View.ADMIN_TICKET_INSERT;
		else if (sel == 2)
			return View.ADMIN_TICKET_UPDATE;
		else if (sel == 3)
			return View.ADMIN_TICKET_DELETE;
		else if (sel == 4)
			return View.ADMIN;
		else
			return View.ADMIN;
	}

	private View tichetList() {
		System.out.println();

		List<Map<String, Object>> param = adminService.tichetList();

		System.out.println("===========================이용권 안내 [1개월 이용시 금액 (VAT 포함)]===============================");
		for (Map<String, Object> map : param) {
			BigDecimal no = (BigDecimal) map.get("TIC_NO");
			String tier = (String) map.get("TIC_TIER");
			String price = (String) map.get("TIC_PRICE");
			String comment = (String) map.get("TIC_COMMENT");
			System.out.println("  No." + no + " " + tier + "\t [가격]" + price + "원    [설명] " + comment);
		}
		System.out.println("=========================================================================================");

		return View.ADMIN_TICKET;
	}

	private View adminSaleYear() {
		if (debug)
			System.out.println("=========연 매출 내역=========");
		System.out.println();

		System.out.println("매출 조회를 원하는 날짜를 입력하세요.");
		System.out.println();
		String year = ScanUtil.nextLine("연도 : ");

		String date1 = year + "0101";
		String date2 = year + "1201";

		List<Object> param = new ArrayList<Object>();
		param.add(date1);
		param.add(date2);
		Map<String, Object> date = adminService.adminSaleYear(param);

		if (date.get("TIC_PRICE") == null) {
			System.out.println();
			System.out.println("해당 날짜의 판매내역이 없습니다.");
			System.out.println();
			return View.ADMIN_SALE;
		}

		String price = (String) date.get("TIC_PRICE");
		System.out.println();
		System.out.println("[ " + year + "년  " + "총매출 ] \t\t\t" + price + "원");
		System.out.println();

		List<Map<String, Object>> tier = adminService.yearSaleTier(param);
		for (Map<String, Object> map : tier) {
			String tierName = (String) map.get("TIC_TIER");
			String tierSale = (String) map.get("TIC_PRICE");
			BigDecimal tierCount = (BigDecimal) map.get("판매갯수");
			System.out.println("[" + tierName + "]    \t" + "[판매갯수] " + tierCount + "개\t" + tierSale + "원");
		}

		System.out.println();
		return View.ADMIN_SALE;
	}

	private View adminSaleMonth() {
		if (debug)
			System.out.println("=========월 매출 내역=========");
		System.out.println();

		System.out.println("매출 조회를 원하는 날짜를 입력하세요.");
		System.out.println();
		String year = ScanUtil.nextLine("연도 : ");
		String month = ScanUtil.nextLine("월 : ");

		if (month.equals("1") || month.equals("2") || month.equals("3") || month.equals("4") || month.equals("5")
				|| month.equals("6") || month.equals("7") || month.equals("8") || month.equals("9")) {
			String month1 = "0" + month;
			month = month1;
		}

		String date1 = year + month + "01";
		String date2 = year + month + "01";

		List<Object> param = new ArrayList<Object>();
		param.add(date1);
		param.add(date2);
		Map<String, Object> date = adminService.adminSaleMonth(param);

		if (date.get("TIC_PRICE") == null) {
			System.out.println();
			System.out.println("해당 날짜의 판매내역이 없습니다.");
			System.out.println();
			return View.ADMIN_SALE;
		}

		String price = (String) date.get("TIC_PRICE");
		System.out.println();
		System.out.println("[ " + year + "년  " + month + "월  " + "총매출 ] \t\t" + price + "원");
		System.out.println();

		List<Map<String, Object>> tier = adminService.monthSaleTier(param);
		for (Map<String, Object> map : tier) {
			String tierName = (String) map.get("TIC_TIER");
			String tierSale = (String) map.get("TIC_PRICE");
			BigDecimal tierCount = (BigDecimal) map.get("판매갯수");
			System.out.println("[" + tierName + "]    \t" + "[판매갯수] " + tierCount + "개\t" + tierSale + "원");
		}

		System.out.println();
		return View.ADMIN_SALE;
	}

	private View adminSaleDay() {
		if (debug)
			System.out.println("=========일 매출 내역=========");
		System.out.println();

		System.out.println("매출 조회를 원하는 날짜를 입력하세요.");
		System.out.println();
		String year = ScanUtil.nextLine("연도 : ");
		String month = ScanUtil.nextLine("월 : ");
		String day = ScanUtil.nextLine("일 : ");

		if (month.equals("1") || month.equals("2") || month.equals("3") || month.equals("4") || month.equals("5")
				|| month.equals("6") || month.equals("7") || month.equals("8") || month.equals("9")) {
			String month1 = "0" + month;
			month = month1;
		}
		if (day.equals("1") || day.equals("2") || day.equals("3") || day.equals("4") || day.equals("5")
				|| day.equals("6") || day.equals("7") || day.equals("8") || day.equals("9")) {
			String day1 = "0" + day;
			day = day1;
		}
		String dates = year + month + day;

		List<Object> param = new ArrayList<Object>();
		param.add(dates);
		Map<String, Object> date = adminService.adminSaleDay(param);

		if (date.get("PRICE") == null) {
			System.out.println();
			System.out.println("해당 날짜의 판매내역이 없습니다.");
			System.out.println();
			return View.ADMIN_SALE;
		}

		String price = (String) date.get("PRICE");
		System.out.println();
		System.out.println("[ " + year + "년  " + month + "월  " + day + "일  총매출 ] \t" + price + "원");
		System.out.println();

		List<Map<String, Object>> tier = adminService.daySaleTier(param);
		for (Map<String, Object> map : tier) {
			String tierName = (String) map.get("TIC_TIER");
			String tierSale = (String) map.get("TIC_PRICE");
			BigDecimal tierCount = (BigDecimal) map.get("판매갯수");
			System.out.println("[" + tierName + "]    \t" + "[판매갯수] " + tierCount + "개\t" + tierSale + "원");
		}

		System.out.println();
		return View.ADMIN_SALE;
	}

	private View adminSale() {
		if (debug)
			System.out.println("=========매출 관리=========");
		System.out.println();

		System.out.println("1. 일 매출 조회");
		System.out.println("2. 월 매출 조회");
		System.out.println("3. 연 매출 조회");
		System.out.println("4. 뒤로가기");

		int sel = ScanUtil.nextInt("메뉴 : ");

		if (sel == 1)
			return View.ADMIN_SALEDAY;
		else if (sel == 2)
			return View.ADMIN_SALEMONTH;
		else if (sel == 3)
			return View.ADMIN_SALEYEAR;
		else if (sel == 4)
			return View.ADMIN;
		else
			return View.ADMIN;
	}

	private View memberDelete() {
		if (debug)
			System.out.println("=========회원 탈퇴=========");

		Map<String, Object> member = (Map<String, Object>) sessionStorage.get("member");
		String id = (String) member.get("MEM_ID");

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
		if (debug)
			System.out.println("=========회원정보 변경=========");
		Map<String, Object> member = (Map<String, Object>) sessionStorage.get("member");
		String id = (String) member.get("MEM_ID");

		List<Object> param1 = new ArrayList<Object>();
		param1.add(id);
		Map<String, Object> member1 = memberService.memInfo(param1);
		memberInfo(member1, id);
		System.out.println();
		System.out.println("수정할 정보를 입력하세요.");

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

		System.out.println();
		myInfo();

		return View.MEMBER;
	}

	private View realtorUpdate() {
		if (debug)
			System.out.println("=========회원정보 변경=========");
		Map<String, Object> member = (Map<String, Object>) sessionStorage.get("realtor");
		String id = (String) member.get("RET_ID");

		List<Object> param1 = new ArrayList<Object>();
		param1.add(id);
		Map<String, Object> realtor1 = memberService.memInfo(param1);
		memberInfo(realtor1, id);
		realtorInfo(realtor1, id);
		System.out.println();
		System.out.println("수정할 정보를 입력하세요.");

		String pw = ScanUtil.nextLine("PW : ");
		String name = ScanUtil.nextLine("이름 : ");
		String tel = ScanUtil.nextLine("전화번호 : ");
		String email = ScanUtil.nextLine("이메일 : ");

		List<Object> param = new ArrayList<Object>();

		param.add(pw);
		param.add(name);
		param.add(tel);
		param.add(email);
		param.add(id);

		realtorService.realtorUpdate(param);

		System.out.println();
		myInfo();

		return View.REALTOR;
	}

	private View realtorDelete() {
		if (debug)
			System.out.println("=========회원 탈퇴=========");

		Map<String, Object> member = (Map<String, Object>) sessionStorage.get("realtor");
		String id = (String) member.get("RET_ID");

		List<Object> param = new ArrayList<Object>();
		param.add(id);

		System.out.println();
		System.out.println("정말 탈퇴하시겠습니까?");
		System.out.println();
		int sel = ScanUtil.nextInt("1. 삭제\t2. 취소");

		if (sel == 1) {
			int result = realtorService.realtorDelete(param);
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
			return View.REALTOR;
		}
		return View.HOME;
	}

	private View adminNoticeDelete() {
		if (debug)
			System.out.println("=========공지사항 삭제=========");
		System.out.println();

		List<Object> param = new ArrayList<Object>();

		int no = ScanUtil.nextInt("공지번호 : ");

		param.add(no);

		System.out.println();
		System.out.println("정말 삭제하시겠습니까?");
		System.out.println();
		int sel = ScanUtil.nextInt("1. 삭제\t2. 취소");

		if (sel == 1) {
			int result = adminService.adminNoticeDelete(param);
			if (result == 1) {
				System.out.println();
				System.out.println("공지 삭제를 성공했습니다.");
				System.out.println();
				return View.ADMIN_NOTICE;
			}
		} else if (sel == 2) {
			System.out.println();
			System.out.println("공지 삭제를 취소했습니다.");
			System.out.println();
			return View.ADMIN_NOTICE;
		}

		return View.ADMIN_NOTICE;
	}

	private View adminNoticeUpdate() {
		if (debug)
			System.out.println("=========공지사항 수정=========");
		System.out.println();

		List<Object> param = new ArrayList<Object>();

		int no = ScanUtil.nextInt("공지번호 : ");
		String title = ScanUtil.nextLine("제목 : ");
		String content = ScanUtil.nextLine("내용 : ");

		param.add(title);
		param.add(content);
		param.add(no);

		adminService.adminNoticeUpdate(param);

		return View.ADMIN_NOTICE;
	}

	private View adminNoticeInsert() {
		if (debug)
			System.out.println("=========공지사항 작성=========");
		System.out.println();

		List<Object> param = new ArrayList<Object>();

		String title = ScanUtil.nextLine("제목 : ");
		String content = ScanUtil.nextLine("내용 : ");

		param.add(title);
		param.add(content);

		adminService.adminNoticeInsert(param);

		return View.ADMIN_NOTICE;
	}

	private View noticeListDetail() {
		if (debug)
			System.out.println("=========공지 상세보기=========");
		System.out.println();

		List<Object> param = new ArrayList<Object>();
		int no = (int) sessionStorage.get("noticeNo");
		param.add(no);
		System.out.println();

		Map<String, Object> noticeDetail = adminService.noticeListDetail(param);

		BigDecimal num = (BigDecimal) noticeDetail.get("NTC_NO");
		String date = (String) noticeDetail.get("NTC_DATE");
		String title = (String) noticeDetail.get("NTC_TITLE");
		String content = (String) noticeDetail.get("NTC_CONTENT");

		System.out.println("No." + num + "\t\t[작성일] " + date);
		System.out.println("[제목] " + title);
		System.out.println();
		System.out.println(content);
		System.out.println();

		if (sessionStorage.containsKey("member")) {
			return View.MEMBER;
		} else if (sessionStorage.containsKey("realtor")) {
			return View.REALTOR;
		} else if (sessionStorage.containsKey("admin")) {
			return View.ADMIN_NOTICE;
		} else if ((sessionStorage.containsKey(null))) {
			return View.NOTICE_LIST;
		} else
			return View.HOME;
	}

	private View noticeList() {
		int page = 1;
		List<Map<String, Object>> notices = adminService.noticeList();
		int totalPages = (notices.size() + PAGE_SIZE - 1) / PAGE_SIZE;

		while (true) {
			int start = (page - 1) * PAGE_SIZE;
			int end;
			if (start + PAGE_SIZE > notices.size()) {
				end = notices.size();
			} else {
				end = start + PAGE_SIZE;
			}
			System.out.println();
			for (int i = start; i < end; i++) {
				Map<String, Object> map = notices.get(i);
				BigDecimal no = (BigDecimal) map.get("NTC_NO");
				String date = (String) map.get("NTC_DATE");
				String title = (String) map.get("NTC_TITLE");
				String content = (String) map.get("NTC_CONTENT");
				System.out.println("No." + no + "\t[등록일] " + date + "   " + title);
				System.out.println("[내용] " + content);
				System.out.println();
			}

			System.out.println("< 이전 페이지");
			System.out.println("> 다음 페이지");
			System.out.println("1. 공지사항 상세보기");
			System.out.println("2. 뒤로가기");
			if (sessionStorage.containsKey("admin"))
				System.out.println("3. 공지사항 관리");
			System.out.println();

			String sel = ScanUtil.nextLine("메뉴>");

			if (sel.equals(">")) {
				if (page < totalPages)
					page++;
				else {
					System.out.println("마지막 페이지입니다.");
				}
			} else if (sel.equals("<")) {
				if (page > 1)
					page--;
				else {
					System.out.println("첫번째 페이지입니다.");
				}
			} else if (sel.equals("1")) {
				int noticeNo = ScanUtil.nextInt("공지사항 번호 입력: ");
				sessionStorage.put("noticeNo", noticeNo);
				return View.NOTICE_LIST_DETAIL;
			} else if (sel.equals("2")) {
				if (sessionStorage.containsKey("member")) {
					return View.MEMBER;
				} else if (sessionStorage.containsKey("realtor")) {
					return View.REALTOR;
				} else if (sessionStorage.containsKey("admin") || (sessionStorage.containsKey(null))) {
					return View.ADMIN;
				} else
					return View.HOME;
			} else if (sel.equals("3")) {
				return View.ADMIN_NOTICE;
			} else {
				return View.HOME;
			}
		}
	}

	private View adminNotice() {
		if (debug)
			System.out.println("=========공지사항 관리=========");
		System.out.println();

		noticeList();

		System.out.println("1. 공지사항 작성");
		System.out.println("2. 공지사항 수정");
		System.out.println("3. 공지사항 삭제");
		System.out.println("4. 뒤로가기");

		System.out.println();

		int sel = ScanUtil.menu();

		if (sel == 1)
			return View.ADMIN_NOTICE_INSERT;
		else if (sel == 2)
			return View.ADMIN_NOTICE_UPDATE;
		else if (sel == 3)
			return View.ADMIN_NOTICE_DELETE;
		else if (sel == 4)
			return View.ADMIN;
		else
			return View.ADMIN;
	}

	private View estDelete() {
		int estNo = (int) sessionStorage.get("estNo");
		List<Object> param = new ArrayList<Object>();
		param.add(estNo);
		String id = null;
		if (sessionStorage.containsKey("member")) {
			Map<String, Object> member = (Map<String, Object>) sessionStorage.get("member");
			id = (String) member.get("MEM_ID");
			param.add(id);
			memberService.estDelete(param);
		} else if (sessionStorage.containsKey("realtor")) {
			Map<String, Object> realtor = (Map<String, Object>) sessionStorage.get("realtor");
			id = (String) realtor.get("RET_ID");
			param.add(id);
			realtorService.estDelete(param);
		}
		if (sessionStorage.containsKey("member"))
			return View.MEMBER;
		else if (sessionStorage.containsKey("realor"))
			return View.REALTOR;
		else
			return View.HOME;
	}

	private View estUpdate() {
		int estNo = (int) sessionStorage.get("estNo");
		List<Object> param = new ArrayList<Object>();
		String id = null;
		String name = ScanUtil.nextLine("매물이름: ");
		String add = ScanUtil.nextLine("매물주소: ");
		int type = ScanUtil.nextInt("주거 형태(1.단독주택,2.아파트, 3.오피스텔, 4.빌라, 5.원룸): ");
		int supArea = ScanUtil.nextInt("공급면적: ");
		int excArea = ScanUtil.nextInt("전용면적: ");
		int price = ScanUtil.nextInt("가격: ");
		int fee = ScanUtil.nextInt("관리비: ");
		int deposit = ScanUtil.nextInt("보증금: ");
		int floor = ScanUtil.nextInt("건물 층: ");

		param.add(name);
		param.add(add);
		param.add(type);
		param.add(supArea);
		param.add(excArea);
		param.add(price);
		param.add(fee);
		param.add(deposit);
		param.add(floor);
		param.add(estNo);
		if (sessionStorage.containsKey("member")) {
			Map<String, Object> member = (Map<String, Object>) sessionStorage.get("member");
			id = (String) member.get("MEM_ID");
			param.add(id);
			memberService.estUpdate(param);
		} else if (sessionStorage.containsKey("realtor")) {
			Map<String, Object> realtor = (Map<String, Object>) sessionStorage.get("realtor");
			id = (String) realtor.get("RET_ID");
			param.add(id);
			realtorService.estUpdate(param);
		}

		if (sessionStorage.containsKey("member"))
			return View.MEMBER;
		else if (sessionStorage.containsKey("realor"))
			return View.REALTOR;
		else
			return View.HOME;
	}

	private View estDetailList() {
		String id = null;
		List<Object> param = new ArrayList<Object>();
		List<Map<String, Object>> myEstList = null;

		int page = 1;
		if (sessionStorage.containsKey("page"))
			page = (int) sessionStorage.remove("page");
		int startNo = 1 + (page - 1) * PAGE_SIZE;
		int endNo = page * PAGE_SIZE;

		int sel = (int) sessionStorage.get("estDetailList");
		if (sel == 6)
			return View.EST_LIST;
		param.add(sel);
		if (sessionStorage.containsKey("member")) {
			Map<String, Object> member = (Map<String, Object>) sessionStorage.get("member");
			id = (String) member.get("MEM_ID");
			param.add(id);
			param.add(startNo);
			param.add(endNo);
			myEstList = memberService.estDetailList(param);
		} else if (sessionStorage.containsKey("realtor")) {
			Map<String, Object> realtor = (Map<String, Object>) sessionStorage.get("realtor");
			id = (String) realtor.get("RET_ID");
			param.add(id);
			param.add(startNo);
			param.add(endNo);
			myEstList = realtorService.estDetailList(param);
		}
		if (myEstList == null) {
			if (page != 1) {
				System.out.println("마지막페이지");
				page--;
				sessionStorage.put("page", page);
				return View.EST_DETAILLIST;
			}
			System.out.println("해당 매물 정보가 없습니다.");
			return View.MYESTLIST;

		}
		for (Map<String, Object> map : myEstList) {
			estList(map, 1);
		}

		System.out.println();
		System.out.println("< 이전 페이지 \t 다음 페이지 >");
		System.out.println("1. 매물 상세보기");
		System.out.println("2. 내 매물 목록");
		System.out.println("3. 홈");
		String sel2 = ScanUtil.nextLine("메뉴 : ");

		if (sel2.equals("<")) {
			if (page != 1)
				page--;
			sessionStorage.put("page", page);
			return View.EST_DETAILLIST;
		} else if (sel2.equals(">")) {
			page++;
			sessionStorage.put("page", page);
			return View.EST_DETAILLIST;
		} else if (sel2.equals("2")) {
			return View.MYESTLIST;
		} else if (sel2.equals("1")) {
			int estNo = ScanUtil.nextInt("매물 번호입력: ");
			sessionStorage.put("estNo", estNo);
			return View.EST_DETAIL;
		} else if (sel2.equals("3")) {
			if (sessionStorage.containsKey("member"))
				return View.MEMBER;
			else if (sessionStorage.containsKey("realor"))
				return View.REALTOR;
			else
				return View.HOME;
		} else
			return View.EST_DETAILLIST;
	}

	private View myEstList() {
	      String id = null;
	      int page = 1;
	      if (sessionStorage.containsKey("page"))
	         page = (int) sessionStorage.remove("page");
	      int startNo = 1 + (page - 1) * PAGE_SIZE;
	      int endNo = page * PAGE_SIZE;

	      List<Object> param = new ArrayList<Object>();
	      List<Map<String, Object>> myEstList = null;
	      if (sessionStorage.containsKey("member")) {
	         Map<String, Object> member = (Map<String, Object>) sessionStorage.get("member");
	         id = (String) member.get("MEM_ID");
	         param.add(id);
	         param.add(startNo);
	         param.add(endNo);
	         myEstList = memberService.myEstList(param);
	      } else if (sessionStorage.containsKey("realtor")) {
	         Map<String, Object> realtor = (Map<String, Object>) sessionStorage.get("realtor");
	         id = (String) realtor.get("RET_ID");
	         param.add(id);
	         param.add(startNo);
	         param.add(endNo);
	         myEstList = realtorService.myEstList(param);
	      }
	      if (myEstList == null) {
	         if (page != 1) {
	            System.out.println("마지막페이지");
	            page--;
	            sessionStorage.put("page", page);
	            return View.MYESTLIST;
	         } else {
	            System.out.println("해당정보가 없습니다");
	            return View.HOME;
	         }
	      }
	      for (Map<String, Object> map : myEstList) {
	         estList(map, 1);
	      }
	      List<Object> param2 = new ArrayList<Object>();
	      System.out.println();
	      System.out.println("< 이전 페이지 \t 다음 페이지 >");
	      System.out.println("1. 판매 상태별 상세 출력");
	      System.out.println("2. 매물 수정");
	      System.out.println("3. 매물 삭제");
	      System.out.println("4. 매물 판매상태 변경");
	      String sel = ScanUtil.nextLine("메뉴 : ");
	      if (sel.equals("<")) {
	         if (page != 1)
	            page--;
	         sessionStorage.put("page", page);
	         return View.MYESTLIST;
	      } else if (sel.equals(">")) {
	         page++;
	         sessionStorage.put("page", page);
	         return View.MYESTLIST;
	      } else if (sel.equals("1")) {
	         System.out.println();
	         System.out.println("1. 판매중 매물 전체 출력");
	         System.out.println("2. 예약중 매물 전체 출력");
	         System.out.println("3. 계약완료 매물 전체 출력");
	         System.out.println("4. 기한만료 매물 전체 출력");
	         System.out.println("5. 신고차단 매물 전체 출력");
	         System.out.println("6. 뒤로가기");
	         int sel2 = ScanUtil.menu();
	         if(sel2==6)return View.MYESTLIST;
	         sessionStorage.put("estDetailList", sel2);
	         return View.EST_DETAILLIST;
	      } else if (sel.equals("2")) {
	         int estNo = ScanUtil.nextInt("매물 번호입력: ");
	         sessionStorage.put("estNo", estNo);
	         return View.EST_UPDATE;
	      } else if (sel.equals("3")) {
	         int estNo = ScanUtil.nextInt("매물 번호입력: ");
	         sessionStorage.put("estNo", estNo);
	         return View.EST_DELETE;
	      } else if (sel.equals("4")) {
	         int estNo = ScanUtil.nextInt("매물 번호입력: ");
	         int state = ScanUtil.nextInt("매물 판매상태 입력(1.판매중,2.예악중,3.계약완료): ");
	         if (state == 2) {
	            sessionStorage.put("estNo", estNo);
	            return View.RESERVATION_INSERT;
	         } else if (state == 1 || state == 3) {
	            param2.add(state);
	            param2.add(estNo);
	            realtorService.estStateUpdate(param2);
	         }
	         if (sessionStorage.containsKey("member")) {
	            param2.add(id);
	            memberService.estStateUpdate(param2);
	         } else {
	            param2.add(id);
	            realtorService.estStateUpdate(param2);
	         }
	      }
	      

	      if (sessionStorage.containsKey("member"))
	         return View.MEMBER;
	      else if (sessionStorage.containsKey("realor"))
	         return View.REALTOR;
	      else
	         return View.HOME;
	   }

	private View estDetail() {
		int estNo = (int) sessionStorage.get("estNo");
		List<Object> param = new ArrayList();
		param.add(estNo);
		Map<String, Object> estDetail = estateService.estDetail(param);
		estList(estDetail, 2);
		System.out.println("1. 판매자 정보 확인");
		System.out.println("2. 찜하기");
		System.out.println("3. 신고하기");
		System.out.println("4. 뒤로가기");
		System.out.println("5. 홈");
		int sel = ScanUtil.menu();
	      if (sel == 1)
	         return View.SELLER_INFO;
	      if (sel == 2)
	         return View.WISHLIST_INSERT;
	      if (sel == 3)
	         return View.MEMBER_REPORT;
	      else if (sel == 4)
	         return View.EST_LIST;
	      else if (sel == 5) {
	         if (sessionStorage.containsKey("member"))
	            return View.MEMBER;
	         else if (sessionStorage.containsKey("realor"))
	            return View.REALTOR;
	         else
	            return View.HOME;
	      }
	         return View.EST_LIST;
	   }

	private View estAdd() {
		if (!sessionStorage.containsKey("member") && !sessionStorage.containsKey("realtor")) {
			System.out.println("로그인한 회원만 사용가능한 메뉴입니다.");
			System.out.println();
			return View.LOGIN;
		}
		System.out.println();
		List<Object> tranTypeList = new ArrayList<Object>();
		String name = ScanUtil.nextLine("매물이름: ");
		String add = ScanUtil.nextLine("매물주소: ");
		int type = ScanUtil.nextInt("주거 형태(1.단독주택,2.아파트, 3.오피스텔, 4.빌라, 5.원룸): ");
		int supArea = ScanUtil.nextInt("공급면적: ");
		int excArea = ScanUtil.nextInt("전용면적: ");
		int price = ScanUtil.nextInt("가격: ");
		while (true) {
			int tranType = ScanUtil.nextInt("거래유형(1.매매, 2.전세, 3.월세): ");
			tranTypeList.add(tranType);
			System.out.println("거래유형을 더 추가하시겠습니까?");
			int sel = ScanUtil.nextInt("1. 네 2.아니오");
			if (sel == 2)
				break;
		}
		int fee = ScanUtil.nextInt("관리비: ");
		System.out.println("보증금이 있나요?");
		int sel = ScanUtil.nextInt("1. 네, 2. 아니오");
		int deposit = (sel == 1) ? ScanUtil.nextInt("보증금: ") : 0;
		int floor = ScanUtil.nextInt("건물 층: ");
		Map<String, Object> member = (Map<String, Object>) sessionStorage.get("member");
		Map<String, Object> realtor = (Map<String, Object>) sessionStorage.get("realtor");
		for (int i = 0; i < tranTypeList.size(); i++) {
			List<Object> param = new ArrayList<Object>();
			param.add(name);
			param.add(add);
			param.add(type);
			param.add(supArea);
			param.add(excArea);
			param.add(price);
			param.add(tranTypeList.get(i));
			param.add(fee);
			param.add(deposit);
			if (realtor == null)
				param.add(null);
			else
				param.add(realtor.get("RET_ID"));

			if (member == null)
				param.add(null);
			else
				param.add(member.get("MEM_ID"));
			param.add(floor);
			estateService.estAdd(param);
		}
		if (sessionStorage.containsKey("member"))
			return View.MEMBER;
		else if (sessionStorage.containsKey("realtor"))
			return View.REALTOR;
		return View.HOME;
	}

	private View estSearch() {

		List<Object> param = new ArrayList();

		String add = ScanUtil.nextLine("지역: ");
		int type = ScanUtil.nextInt("주거 형태(1.단독주택,2.아파트, 3.오피스텔, 4.빌라, 5.원룸): ");
		int tranType = ScanUtil.nextInt("거래유형(1.매매, 2.전세, 3.월세): ");
		int minSupArea = ScanUtil.nextInt("최소 공급면적: ");
		int maxSupArea = ScanUtil.nextInt("최대 공급면적: ");
		int minExcArea = ScanUtil.nextInt("최소 전용면적: ");
		int maxExcArea = ScanUtil.nextInt("최대 전용면적: ");
		int minprice = ScanUtil.nextInt("최소 가격: ");
		int maxPrice = ScanUtil.nextInt("최대 가격: ");
		add = "%" + add + "%";
		param.add(add);
		param.add(type);
		param.add(tranType);
		param.add(minSupArea);
		param.add(maxSupArea);
		param.add(minExcArea);
		param.add(maxExcArea);
		param.add(minprice);
		param.add(maxPrice);

		List<Map<String, Object>> estSearchList = estateService.estSearchList(param);

		if (estSearchList == null) {
			System.out.println("검색결과 없음");
			System.out.println("다시 검색하시겠습니까?(1.네, 2.아니오");
			int sel = ScanUtil.menu();
			if (sel == 1)
				return View.EST_SEARCH;
			else
				return View.EST_LIST;
		} else {
			int page = 1;
			while (true) {
				int start = (page - 1) * PAGE_SIZE;
				int end;
				if (start + PAGE_SIZE > estSearchList.size()) {
					end = estSearchList.size();
				} else {
					end = start + PAGE_SIZE;
				}
				List<Map<String, Object>> currentPageList = estSearchList.subList(start, end);
				for (Map<String, Object> map : currentPageList) {
					estList(map, 1);
				}

				System.out.println("\n'<' 이전 페이지, '>' 다음 페이지");
				System.out.println("1. 다시 매물 조건 검색");
				System.out.println("2. 매물 상세보기");
				System.out.println("3. 홈");
				String sel = ScanUtil.nextLine("메뉴 : ");

				if (sel.equals("<")) {
					if (page > 1) {
						page--;
					} else {
						System.out.println("첫 번째 페이지입니다.");
					}
				} else if (sel.equals(">")) {
					if (end < estSearchList.size()) {
						page++;
					} else {
						System.out.println("마지막 페이지입니다.");
					}
				} else if (sel.equals("1")) {
					return View.EST_SEARCH;
				} else if (sel.equals("2")) {
					int estNo = ScanUtil.nextInt("매물 번호입력: ");
					sessionStorage.put("estNo", estNo);
					return View.EST_DETAIL;
				} else if (sel.equals("3")) {
					return View.HOME;
				}
			}
		}
	}

	private View estList() {
		if (debug)
			System.out.println("=========집 매물 정보 보기=========");
		System.out.println();

		int page = 1;
		if (sessionStorage.containsKey("page"))
			page = (int) sessionStorage.remove("page");
		int startNo = 1 + (page - 1) * PAGE_SIZE;
		int endNo = page * PAGE_SIZE;

		List<Object> param = new ArrayList();
		param.add(startNo);
		param.add(endNo);

		List<Map<String, Object>> list = estateService.estList(param);
		if (list == null && page != 1) {
			System.out.println("마지막페이지");
			page--;
			sessionStorage.put("page", page);
			return View.EST_LIST;
		}
		for (Map<String, Object> map : list) {
			estList(map, 1);// 밑 출력 Print에 추가함
		}
		System.out.println();
		System.out.println("< 이전 페이지 \t 다음 페이지 >");
		System.out.println("1. 매물 조건 검색");
		System.out.println("2. 매물 상세보기");
		System.out.println("3. 홈");
		String sel = ScanUtil.nextLine("메뉴 : ");

		if (sel.equals("<")) {
			if (page != 1)
				page--;
			sessionStorage.put("page", page);
			return View.EST_LIST;
		} else if (sel.equals(">")) {
			page++;
			sessionStorage.put("page", page);
			return View.EST_LIST;
		} else if (sel.equals("1")) {
			return View.EST_SEARCH;
		} else if (sel.equals("2")) {
			int estNo = ScanUtil.nextInt("매물 번호입력: ");
			sessionStorage.put("estNo", estNo);
			return View.EST_DETAIL;
		} else if (sel.equals("3")) {
			return View.HOME;
		}

		if (sessionStorage.containsKey("member")) {
			return View.MEMBER;
		} else if (sessionStorage.containsKey("realtor")) {
			return View.REALTOR;
		} else
			return View.EST_LIST;
	}

	private View sellerInfo() {
		int estNo = (int) sessionStorage.get("estNo");
		String id = null;
		List<Object> param = new ArrayList();
		param.add(estNo);
		Map<String, Object> memberSeller = memberService.sellerInfo(param);
		Map<String, Object> realtorSeller = realtorService.sellerInfo(param);
		if (memberSeller != null) {
			String SellerName = (String) memberSeller.get("판매자이름");
			String SellerNic = (String) memberSeller.get("판매자닉네임");
			BigDecimal SellerScore = (BigDecimal) memberSeller.get("평균별점");
			String SellerTel = (String) memberSeller.get("판매자전화번호");

			System.out.println("[판매자이름] " + SellerName + "\t[판매자닉네임] " + SellerNic + "\t[평균리뷰별점] " + SellerScore
					+ "\t[판매자연락처] " + SellerTel);
			System.out.println();
			System.out.println("[판매자에게 연락하여 방문일을 예약하세요.]");
			System.out.println("[하우징에서 보고 연락한다고 말씀하시면    ]");
			System.out.println("[더욱 빠른 예약이 가능합니다.        ]");
		} else if (realtorSeller != null) {
			String SellerName = (String) realtorSeller.get("판매자이름");
			BigDecimal SellerScore = (BigDecimal) realtorSeller.get("평균별점");
			String SellerTel = (String) realtorSeller.get("판매자전화번호");
			String SellerComName = (String) realtorSeller.get("판매자회사이름");
			String SellerComAddress = (String) realtorSeller.get("판매자회사주소");
			String SellerComTel = (String) realtorSeller.get("판매자회사전화번호");

			System.out.println("[판매자이름] " + SellerName + "\t[평균리뷰별점] " + SellerScore
					+ "\t[판매자연락처] " + SellerTel);
			System.out.println(
					"[공인중개소명] " + SellerComName + "\t[회사번호] " + SellerComTel + "\t[공인중개소 주소] " + SellerComAddress);
		}
		System.out.println();
		return View.EST_DETAIL;
	}

	private View memberDetail() {
		if (debug)
			System.out.println("=========회원정보 페이지=========");
		System.out.println();

		System.out.println("1. 회원 정보 보기\t2. 회원 정보 변경\t3. 회원 탈퇴\t4. 뒤로가기");
		System.out.println();

		int sel = ScanUtil.menu();

		if (sel == 1)
			return View.MYINFO;
		else if (sel == 2)
			return View.MEMBER_UPDATE;
		else if (sel == 3)
			return View.MEMBER_DELETE;
		else if (sel == 4)
			return View.MEMBER;
		else
			return View.MEMBER;
	}

	private View adminLogin() {
		if (debug)
			System.out.println("=========관리자 로그인 페이지=========");
		System.out.println();

		String id = ScanUtil.nextLine("ID  : ");
		String pw = ScanUtil.nextLine("PASS: ");

		List<Object> param = new ArrayList<Object>();
		param.add(id);
		param.add(pw);

		boolean loginChk = adminService.login(param);

		if (!loginChk) {
			System.out.println("로그인을 실패했습니다.");
			return View.ADMINLOGIN;
		} else {
			System.out.println("로그인에 성공했습니다.");
		}
		System.out.println();

		if (loginChk)
			return View.ADMIN;
		else
			return View.HOME;
	}

	private View myInfo() {
		if (debug)
			System.out.println("=========내 정보 보기=========");
		System.out.println();

		Map<String, Object> memberInfo = (Map<String, Object>) sessionStorage.get("member");
		Map<String, Object> realtorInfo = (Map<String, Object>) sessionStorage.get("realtor");

		if (sessionStorage.containsKey("member")) {
			String id = (String) memberInfo.get("MEM_ID");
			List<Object> param = new ArrayList<Object>();
			param.add(id);
			Map<String, Object> member = memberService.memInfo(param);
			memberInfo(member, id);
		} else {
			String id = (String) realtorInfo.get("RET_ID");
			List<Object> param = new ArrayList<Object>();
			param.add(id);
			Map<String, Object> param1 = realtorService.retInfo(param);

			realtorInfo(param1, id);
		}
		System.out.println("1. 회원정보 수정");
		System.out.println("2. 회원탈퇴");
		System.out.println("3. 홈");
		int sel = ScanUtil.menu();
		if (sel == 1) {

			if (sessionStorage.containsKey("member"))
				return View.MEMBER_UPDATE;
			else
				return View.REALTOR_UPDATE;
		} else if (sel == 2) {
			return View.MEMBER_DELETE;
		} else {
			if (sessionStorage.containsKey("member"))
				return View.MEMBER;
			else if (sessionStorage.containsKey("realor"))
				return View.REALTOR;
			else
				return View.HOME;
		}
	}

	private View realtor() {
		if (debug)
			System.out.println("=========공인중개사 페이지=========");
		System.out.println();

		System.out.println("1. 공지사항\t2.전체 매물 목록보기\t3. 내 매물 등록\t4. 내 매물 정보\t5. 회원 정보");
		System.out.println("6. 리뷰 보기\t7. 예약 목록\t8. 로그아웃");
		System.out.println();

		int sel = ScanUtil.menu();

		if (sel == 1)
			return View.NOTICE_LIST;
		else if (sel == 2)
			return View.EST_LIST;
		else if (sel == 3)
			return View.EST_ADD;
		else if (sel == 4)
			return View.MYESTLIST;
		else if (sel == 5)
			return View.MYINFO;
		else if (sel == 6)
			return View.REVIEWLIST;
		else if (sel == 7)
			return View.RESERVATION;
		else if (sel == 8) {
			sessionStorage.remove("realtor");
			System.out.println("로그아웃이 완료되었습니다.");
			System.out.println();
			return View.HOME;
		} else
			return View.REALTOR;
	}

	private View member() {
		if (debug)
			System.out.println("=========일반회원 페이지=========");
		System.out.println();

		System.out.println("1. 공지사항\t2.전체 매물 목록보기\t3. 내 매물 등록\t4. 내 매물 정보\t5. 회원 정보");
		System.out.println("6. 리뷰 관리\t7. 예약 목록\t8. 찜 목록\t\t9. 로그아웃");
		System.out.println();

		int sel = ScanUtil.menu();

		if (sel == 1)
			return View.NOTICE_LIST;
		else if (sel == 2)
			return View.EST_LIST;
		else if (sel == 3)
			return View.EST_ADD;
		else if (sel == 4)
			return View.MYESTLIST;
		else if (sel == 5)
			return View.MYINFO;
		else if (sel == 6)
			return View.REVIEW;
		else if (sel == 7)
			return View.RESERVATION;
		else if (sel == 8)
			return View.MEMBER_WISHLIST;
		else if (sel == 9) {
			sessionStorage.remove("member");
			System.out.println("로그아웃이 완료되었습니다.");
			System.out.println();
			return View.HOME;
		} else
			return View.MEMBER;
	}

	private View sign() {
		printSignList();
		int sel = ScanUtil.menu();
		List<Map<String, Object>> memberData = memberService.memList();
		List<Map<String, Object>> realtorData = realtorService.retList();
		String id;
		while (true) {
			int cnt = 0;
			System.out.println();
			System.out.println("id는 8글자 이상, 15글자 이하, 영문 소문자, 숫자로 조합만 가능합니다.");
			id = ScanUtil.nextLine("회원 id입력:");
			for (Map<String, Object> map : realtorData) {
				if (map.get("RET_ID").equals(id)) {
					System.out.println("이미 존재하는 ID");
					cnt++;
				}
				break;
			}
			for (Map<String, Object> map : memberData) {
				if (map.get("MEM_ID").equals(id)) {
					System.out.println("이미 존재하는 ID");
					cnt++;
				}
				break;
			}
			if (id.length() < 8 || id.length() > 15) {
				cnt++;
			}
			if (!id.matches("[a-z0-9]+")) {
				cnt++;
			}
			if (cnt == 0)
				break;
		}
		String pw;
		while (true) {
			int cnt = 0;
			System.out.println();
			System.out.println("pw는 8글자 이상, 15글자 이하, 영문 대문자, 소문자, 숫자로 조합만 가능합니다.");
			pw = ScanUtil.nextLine("회원 pw입력:");
			if (pw.length() < 8 || pw.length() > 15) {
				cnt++;
			}
			if (!pw.matches(
					"^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*(),.?\":{}|<>])[\\w!@#$%^&*(),.?\":{}|<>]+$")) {
				cnt++;
			}
			if (cnt == 0)
				break;
		}
		String name;
		while (true) {
			int cnt = 0;
			System.out.println();
			System.out.println("이름은 2글자 이상, 5글자 이하만 가능합니다.");
			name = ScanUtil.nextLine("회원 이름 입력:");
			if (name.length() < 2 || name.length() > 5) {
				cnt++;
			}
			if (cnt == 0)
				break;
		}
		String tel;
		while (true) {
			int cnt = 0;
			System.out.println();
			System.out.println("휴대폰 번호는 -를 포함해서 13자리를 입력해주세요.");
			tel = ScanUtil.nextLine("회원 전화번호 입력:");
			if (tel.length() != 13) {
				cnt++;
			}
			if (cnt == 0)
				break;
		}
		System.out.println();
		String address = ScanUtil.nextLine("회원 주소입력:");
		System.out.println();
		List<Object> param = new ArrayList<Object>();
		param.add(id);
		param.add(pw);
		param.add(name);
		param.add(tel);
		param.add(address);
		int comNo = 0;
		String nickName = null;
		if (sel == 2) {

			int sel2 = (int) signComList();

			if (sel2 == 1) {
				System.out.println();
				comNo = ScanUtil.nextInt("공인중개소 번호 입력: ");
				System.out.println();
			} else if (sel2 == 2) {
				System.out.println();
				String comName = ScanUtil.nextLine("공인중개소 이름 입력: ");
				String comtel = ScanUtil.nextLine("공인중개소 전화번호 입력: ");
				String comAddress = ScanUtil.nextLine("공인중개소 주소: ");
				String comComent = ScanUtil.nextLine("공인중개소 소개: ");
				System.out.println();
				List<Object> param2 = new ArrayList<Object>();
				param2.add(comName);
				param2.add(comtel);
				param2.add(comAddress);
				param2.add(comComent);
				realtorService.companyInsert(param2);
				Map<String, Object> comList2 = realtorService.myAddComNo();
				comList(comList2);
				comNo = ScanUtil.nextInt("공인중개소 번호 입력: ");
			}
			param.add(comNo);
			realtorService.sign(param);
		} else if (sel == 1) {
			System.out.println("닉네임은 중복이 가능합니다.");
			nickName = ScanUtil.nextLine("회원 닉네임 입력:");
			param.add(nickName);
			memberService.sign(param);
		}
		System.out.println();
		System.out.println("****** 회원가입 완료 ******");
		return View.HOME;
	}

	private View login() {
		boolean loginChk = false;
		printLoginList();
		int sel = ScanUtil.menu();
		if (sel == 1)
			System.out.print("=========일반회원");
		else if (sel == 2)
			System.out.print("=========공인중개사");
		else if (sel == 3)
			return View.FINDID;
		else if (sel == 4)
			return View.FINDPW;
		else if (sel == 5)
			return View.HOME;
		else if (sel == 0)
			System.out.print("=========관리자");
		else
			return View.HOME;
		System.out.println(" 로그인 페이지=========");

		String id = ScanUtil.nextLine("ID  : ");
		String pw = ScanUtil.nextLine("PASS: ");

		List<Object> param = new ArrayList<Object>();
		param.add(id);
		param.add(pw);

		if (sel == 1)
			loginChk = memberService.login(param);
		else if (sel == 2)
			loginChk = realtorService.login(param);
		else if (sel == 0)
			loginChk = adminService.login(param);

		if (!loginChk) {
			System.out.println();
			System.out.println("로그인을 실패했습니다.");
			System.out.println();
			return View.LOGIN;
		} else {
			System.out.println();
			System.out.println("로그인에 성공했습니다.");
			System.out.println();
			if (sel == 1) {
				return View.MEMBER;
			} else if (sel == 2) {
				return View.REALTOR;
			} else if (sel == 0) {
				return View.ADMIN;
			}
		}
		return View.LOGIN;
	}

	private View admin() {

		if (debug)
			System.out.println("=========관리자 페이지=========");
		System.out.println();

		System.out.println("1. 공지사항 관리");
		System.out.println("2. 신고 관리");
		System.out.println("3. 이용권 관리");
		System.out.println("4. 매출 관리");
		System.out.println("5. 이용자 리스트");
		System.out.println("6. 로그아웃");
		System.out.println();

		int sel = ScanUtil.menu();

		if (sel == 1)
			return View.ADMIN_NOTICE;
		else if (sel == 2)
			return View.ADMIN_REPORT;
		else if (sel == 3)
			return View.ADMIN_TICKET;
		else if (sel == 4)
			return View.ADMIN_SALE;
		else if (sel == 5)
			return View.ADMIN_USERLIST;
		else if (sel == 6) {
			sessionStorage.remove("admin");
			System.out.println("로그아웃이 완료되었습니다.");
			System.out.println();
			return View.HOME;
		} else
			return View.ADMIN;
	}

	private View home() {
		System.out.println();
		if (debug)
			System.out.println("==========홈==========");
		System.out.println();

		System.out.println("1. 공지사항");
		System.out.println("2. 집 매물 정보보기");
		System.out.println("3. 집 매물 등록하기");
		System.out.println("4. 로그인");
		System.out.println("5. 회원가입");
		System.out.println();

		int sel = ScanUtil.menu();
		System.out.println();

		if (sel == 1)
			return View.NOTICE_LIST;
		else if (sel == 2)
			return View.EST_LIST;
		else if (sel == 3)
			return View.EST_ADD;
		else if (sel == 4)
			return View.LOGIN;
		else if (sel == 5)
			return View.SIGN;
		else if (sel == 0)
			return View.ADMINLOGIN;
		return View.HOME;
	}
}