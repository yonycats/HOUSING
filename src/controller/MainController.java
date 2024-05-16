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
			case ADMIN_NOTICE:
				view = adminNotice();
				break;
			case NOTICE_LIST:
				view = noticeList();
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
			case ADMIN_REPORT_DOING:
				view = adminReportDoing();
				break;
			case ADMIN_REPORT_FINISH:
				view = adminReportFinish();
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
			case MEMBER_DETAIL:
				view = memberDetail();
				break;
			case MEMBER_UPDATE:
				view = memberUpdate();
				break;
			case MEMBER_DELETE:
				view = memberDelete();
				break;
			case MYESTLIST:
				view = myEstList();
				break;
			case MEMBER_WISHLIST:
				view = memberWishList();
				break;
//			case MEMBER_RESERVATIONLIST:
//				view = memberReservationList();
//				break;
			case WISHLIST_INSERT:
				view = wishListInsert();
				break;
			case MEMBER_REPORTINSERT:
				view = memberReportInsert();
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
			case EST_ADD:
				view = estAdd();
				break;
			case EST_UPDATE:
				view = estUpdate();
				break;
			case EST_DELETE:
				view = estDelete();
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
			case RET_REVIEWLIST:
				view = retReviewList();
				break;
			default:
				break;
			}
		}
	}


	private View memberReport() {
		if (debug) System.out.println("=========매물 신고하기=========");
		System.out.println();
		
		List<Object> param = new ArrayList<Object>();
		
		int no = ScanUtil.nextInt("매물 번호 : ");
		
		param.add(no);
		
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

	
	private View retReviewList() {
		if (debug) System.out.println("=========나에 대한 리뷰 목록=========");
		System.out.println();
		
		Map<String, Object> realtor = (Map<String, Object>) sessionStorage.get("realtor");
		List<Object> param = new ArrayList<Object>();
	    String id = (String) realtor.get("RET_ID");
	    param.add(id);
	    
	    List<Map<String, Object>> param1 = realtorService.retReviewList(param);
	    Map<String, Object> param2 = realtorService.retReviewScore(param);
		
	    BigDecimal scoreAvg = (BigDecimal)param2.get("평균평점");
	    System.out.println("[나의 리뷰 평균평점] \t\t"+scoreAvg+"점");
	    System.out.println();
	    
	    for (Map<String, Object> map : param1) {
		    BigDecimal no = (BigDecimal)map.get("REV_NO");
		    String date = (String)map.get("REV_DATE");
		    BigDecimal score = (BigDecimal)map.get("REV_SCORE");
		    String content = (String)map.get("REV_CONTENT");
		    System.out.println("No."+no+" \t[작성일] "+date+"    [별점] "+score+"    [리뷰내용] "+content);
		}
	    System.out.println();
	    
		return View.REALTOR;
	}

	private View wishListInsert() {
		int estNo = (int) sessionStorage.get("estNo");
		List<Object> param = new ArrayList<Object>();
		Map<String, Object> member = (Map<String, Object>) sessionStorage.get("member");
		String id = (String) member.get("MEM_ID");
		param.add(id);
		param.add(estNo);
		boolean wishlistChk=false;
		wishlistChk=memberService.wishlistChk(param);
		if(wishlistChk)memberService.wishListInsert(param);
		else System.out.println("찜 목록에 이미 있습니다.");
		return View.EST_DETAIL;
	}

	private View memberWishList() {
		Map<String,Object> member=(Map<String, Object>) sessionStorage.get("member");
		String id=(String) member.get("MEM_ID");
		List<Object> param=new ArrayList<Object>();
		param.add(id);
		List<Map<String,Object>>wishList=memberService.wishList(param);
		System.out.println("1. 찜 목록 상세보기");
		System.out.println("2. 뒤로가기");
		System.out.println("3. 홈");
		int sel=ScanUtil.menu();
		if(sel==1) {
			String estNo= ScanUtil.nextLine("찜한 매물번호를 입력: ");
			sessionStorage.put("estNo", estNo);
		}
		else if(sel==3)return View.HOME;
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
		System.out.println(review);

		if (sessionStorage.containsKey("member")) {
			System.out.println("1. 리뷰 삭제");
			System.out.println("2. 리뷰 정보 변경");
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
		if (sessionStorage.containsKey("member")) {
			Map<String, Object> member = (Map<String, Object>) MainController.sessionStorage.get("member");
			param.add(member.get("MEM_ID"));
			reviewList = memberService.reviewList(param);
		} else if (sessionStorage.containsKey("realtor")) {
			Map<String, Object> member = (Map<String, Object>) MainController.sessionStorage.get("realtor");
			param.add(member.get("RET_ID"));
			reviewList = memberService.reviewList(param);
		} else
			return View.LOGIN;

		for (Map<String, Object> map : reviewList) {
			System.out.println(map);
		}

		System.out.println("1. 리뷰 상세 보기");
		if (sessionStorage.containsKey("member")) {
			System.out.println("2. 리뷰 작성");
		}
		int sel = ScanUtil.menu();
		if (sel == 1) {
			int boardNo = ScanUtil.nextInt("리뷰 번호입력: ");
			sessionStorage.put("boardNo", boardNo);
			return View.REVIEW_DETAIL;
		} else if (sel == 2 && sessionStorage.containsKey("member"))
			return View.REVIEW_INSERT;
		else
			return View.HOME;
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
		if (debug) System.out.println("=========매물 신고하기=========");
		System.out.println();
		
		
		
		return View.MEMBER;
	}
	
	private View adminReportEst() {
		if (debug) System.out.println("=========매물 신고 상세보기=========");
		System.out.println();
		
		BigDecimal estNo = (BigDecimal)sessionStorage.get("estNo");
		
		
		
		
		return View.ADMIN_REPORT;
	}

	private View adminReportFinish() {
		if (debug) System.out.println("=========처리완료 신고 목록=========");
		System.out.println();
		
		List<Map<String, Object>> param = adminService.adminReportFinish();
		
		if (param==null) {
			System.out.println("해당 신고 내역이 없습니다.");
			System.out.println();
			return View.ADMIN_REPORT;
		}
		
		for (Map<String, Object> map : param) {
			String state = (String)map.get("RPT_STATE");
			if (state.equals("DO")) state = "처리중";
			else if (state.equals("FIN")) state = "처리완료";
			BigDecimal no = (BigDecimal)map.get("RPT_NO");
			String date = (String)map.get("RPT_DATE");
			String memId = (String)map.get("MEM_ID");
			BigDecimal estNo = (BigDecimal)map.get("EST_NO");
			String title = (String)map.get("RPT_TITLE");
			String content = (String)map.get("RPT_CONTENT");
			
			System.out.println("No."+no+"\t\t[신고일] "+date+"\t[신고자] "+memId+"\t[매물번호] "+estNo);
			System.out.println("[상태] "+state+"\t[제목] "+title+"\t[신고 내용 ] "+content);
			System.out.println();
		}
		
		System.out.println("1. 신고 상세보기");
		System.out.println("2. 뒤로가기");
		
		System.out.println();
		
		int sel = ScanUtil.menu();
		
		if (sel==1) return View.ADMIN_REPORT_DETAIL;
		else if (sel==2) return View.ADMIN_REPORT;
		else return View.ADMIN;
	}

	private View adminReportDoing() {
		if (debug) System.out.println("=========미처리 신고 목록=========");
		System.out.println();
		
		List<Map<String, Object>> param = adminService.adminReportDoing();
		
		if (param==null) {
			System.out.println("해당 신고 내역이 없습니다.");
			System.out.println();
			return View.ADMIN_REPORT;
		}
		
		for (Map<String, Object> map : param) {
			String state = (String)map.get("RPT_STATE");
			if (state.equals("DO")) state = "처리중";
			else if (state.equals("FIN")) state = "처리완료";
			BigDecimal no = (BigDecimal)map.get("RPT_NO");
			String date = (String)map.get("RPT_DATE");
			String memId = (String)map.get("MEM_ID");
			BigDecimal estNo = (BigDecimal)map.get("EST_NO");
			String title = (String)map.get("RPT_TITLE");
			String content = (String)map.get("RPT_CONTENT");
			
			System.out.println("No."+no+"\t\t[신고일] "+date+"\t[신고자] "+memId+"\t[매물번호] "+estNo);
			System.out.println("[상태] "+state+"\t[제목] "+title+"\t[신고 내용 ] "+content);
			System.out.println();
		}
		
		System.out.println("1. 신고 상세보기");
		System.out.println("2. 뒤로가기");
		
		System.out.println();
		
		int sel = ScanUtil.menu();
		
		if (sel==1) return View.ADMIN_REPORT_DETAIL;
		else if (sel==2) return View.ADMIN_REPORT;
		else return View.ADMIN;
	}

	private View adminReportDetail() {
		if (debug) System.out.println("=========신고 상세보기=========");
		System.out.println();
		
		List<Object> param = new ArrayList<Object>();
		int sel = ScanUtil.nextInt("번호 선택 : ");
		param.add(sel);
		
		Map<String, Object> ReportDetail = adminService.adminReportDetail(param);
		
		String state = (String)ReportDetail.get("RPT_STATE");
		if (state.equals("DO")) state = "처리중";
		else if (state.equals("FIN")) state = "처리완료";
		BigDecimal no = (BigDecimal)ReportDetail.get("RPT_NO");
		String date = (String)ReportDetail.get("RPT_DATE");
		String memId = (String)ReportDetail.get("MEM_ID");
		BigDecimal estNo = (BigDecimal)ReportDetail.get("EST_NO");
		String title = (String)ReportDetail.get("RPT_TITLE");
		String content = (String)ReportDetail.get("RPT_CONTENT");
		
		System.out.println("No."+no+"\t\t[신고일] "+date+"\t[신고자] "+memId+"\t[매물번호] "+estNo);
		System.out.println("[상태] "+state+"\t[제목] "+title+"\t\t[신고 내용 ] "+content);
		System.out.println();
		
		System.out.println("1. 해당 매물 신고 상세보기");
		System.out.println("2. 뒤로가기");
		System.out.println();
		
		sel = ScanUtil.menu();
		
		if (sel==1) {
			sessionStorage.put("estNo", estNo);
			return View.ADMIN_REPORT_LIST;
		}
		else if (sel==2) return View.ADMIN_REPORT;
		
		return View.ADMIN_REPORT;
	}
	
	private View adminReportList() {
		System.out.println();
		
		List<Map<String, Object>> param = adminService.adminReportList();
		
		for (Map<String, Object> map : param) {
			String state = (String)map.get("RPT_STATE");
			if (state.equals("DO")) state = "처리중";
			else if (state.equals("FIN")) state = "처리완료";
			BigDecimal no = (BigDecimal)map.get("RPT_NO");
			String date = (String)map.get("RPT_DATE");
			String memId = (String)map.get("MEM_ID");
			BigDecimal estNo = (BigDecimal)map.get("EST_NO");
			String title = (String)map.get("RPT_TITLE");
			String content = (String)map.get("RPT_CONTENT");
			
			System.out.println("No."+no+"\t\t[신고일] "+date+"\t[신고자] "+memId+"\t[매물번호] "+estNo);
			System.out.println("[상태] "+state+"\t[제목] "+title+"\t[신고 내용 ] "+content);
			System.out.println();
		}
		
		return View.ADMIN_REPORT;
	}

	private View adminReport() {
		if (debug) System.out.println("=========신고 관리=========");
		System.out.println();
		
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
			System.out.println("  No."+no+" "+tier+"\t [가격]"+price+"원    [설명] "+comment);
		}
		System.out.println("=========================================================================================");

		return View.ADMIN_TICKET;
	}

	private View adminSaleYear() {
		if (debug) System.out.println("=========연 매출 내역=========");
		System.out.println();
		
		System.out.println("매출 조회를 원하는 날짜를 입력하세요.");
		System.out.println();
		String year = ScanUtil.nextLine("연도 : ");
		
		String date1 = year+"0101";
		String date2 = year+"1201";
		
		List<Object> param = new ArrayList<Object>();
		param.add(date1);
		param.add(date2);
		Map<String, Object> date = adminService.adminSaleYear(param);
		
		if (date.get("TIC_PRICE")==null) {
			System.out.println();
			System.out.println("해당 날짜의 판매내역이 없습니다.");
			System.out.println();
			return View.ADMIN_SALE;
		}
		
		String price = (String) date.get("TIC_PRICE");
		System.out.println();
		System.out.println("[ "+year+"년  "+"총매출 ] \t\t\t"+price+"원");
		System.out.println();
		
		List<Map<String, Object>> tier = adminService.yearSaleTier(param);
		for (Map<String, Object> map : tier) {
			String tierName = (String) map.get("TIC_TIER");
			String tierSale = (String) map.get("TIC_PRICE");
			BigDecimal tierCount = (BigDecimal) map.get("판매갯수");
			System.out.println("["+tierName+"]    \t"+"[판매갯수] "+tierCount+"개\t"+tierSale+"원");
		}

		System.out.println();
		return View.ADMIN_SALE;
	}

	private View adminSaleMonth() {
		if (debug) System.out.println("=========월 매출 내역=========");
		System.out.println();
		
		System.out.println("매출 조회를 원하는 날짜를 입력하세요.");
		System.out.println();
		String year = ScanUtil.nextLine("연도 : ");
		String month = ScanUtil.nextLine("월 : ");
		
		if (month.equals("1") || month.equals("2") || month.equals("3") || month.equals("4") || month.equals("5")
				 || month.equals("6") || month.equals("7") || month.equals("8") || month.equals("9")) {
			String month1 = "0"+month;
			month = month1;
		}
		String date1 = year+month+"01";
		String date2 = year+month+"01";
		
		List<Object> param = new ArrayList<Object>();
		param.add(date1);
		param.add(date2);
		Map<String, Object> date = adminService.adminSaleMonth(param);
		
		if (date.get("TIC_PRICE")==null) {
			System.out.println();
			System.out.println("해당 날짜의 판매내역이 없습니다.");
			System.out.println();
			return View.ADMIN_SALE;
		}
		
		String price = (String) date.get("TIC_PRICE");
		System.out.println();
		System.out.println("[ "+year+"년  "+month+"월  "+"총매출 ] \t\t"+price+"원");
		System.out.println();
		
		List<Map<String, Object>> tier = adminService.monthSaleTier(param);
		for (Map<String, Object> map : tier) {
			String tierName = (String) map.get("TIC_TIER");
			String tierSale = (String) map.get("TIC_PRICE");
			BigDecimal tierCount = (BigDecimal) map.get("판매갯수");
			System.out.println("["+tierName+"]    \t"+"[판매갯수] "+tierCount+"개\t"+tierSale+"원");
		}

		System.out.println();
		return View.ADMIN_SALE;
	}

	private View adminSaleDay() {
		if (debug) System.out.println("=========일 매출 내역=========");
		System.out.println();
		
		System.out.println("매출 조회를 원하는 날짜를 입력하세요.");
		System.out.println();
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
		Map<String, Object> date = adminService.adminSaleDay(param);
		
		if (date.get("PRICE")==null) {
			System.out.println();
			System.out.println("해당 날짜의 판매내역이 없습니다.");
			System.out.println();
			return View.ADMIN_SALE;
		}
		
		String price = (String) date.get("PRICE");
		System.out.println();
		System.out.println("[ "+year+"년  "+month+"월  "+day+"일  총매출 ] \t"+price+"원");
		System.out.println();
		
		List<Map<String, Object>> tier = adminService.daySaleTier(param);
		for (Map<String, Object> map : tier) {
			String tierName = (String) map.get("TIC_TIER");
			String tierSale = (String) map.get("TIC_PRICE");
			BigDecimal tierCount = (BigDecimal) map.get("판매갯수");
			System.out.println("["+tierName+"]    \t"+"[판매갯수] "+tierCount+"개\t"+tierSale+"원");
		}

		System.out.println();
		return View.ADMIN_SALE;
	}

	private View adminSale() {
		if (debug) System.out.println("=========매출 관리=========");
		System.out.println();
		
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
		
		System.out.println();
		myInfo();
		
		return View.MEMBER;
	}

	private View adminNoticeDelete() {
		if (debug) System.out.println("=========공지사항 삭제=========");
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
		if (debug) System.out.println("=========공지사항 수정=========");
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
		if (debug) System.out.println("=========공지사항 작성=========");
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
		if (debug) System.out.println("=========공지 상세보기=========");
		System.out.println();
		
		List<Object> param = new ArrayList<Object>();
		int sel = ScanUtil.nextInt("번호 선택 : ");
		param.add(sel);
		
		Map<String, Object> ReportDetail = adminService.noticeListDetail(param);
		
		BigDecimal no = (BigDecimal)ReportDetail.get("RPT_NO");
		String date = (String)ReportDetail.get("RPT_DATE");
		String title = (String)ReportDetail.get("RPT_TITLE");
		String content = (String)ReportDetail.get("RPT_CONTENT");
		
		System.out.println("No."+no+"\t\t[작성일] "+date+"\t[제목] ");
		System.out.println("[공지내용] "+content);
		System.out.println();
		
		if (sessionStorage.containsKey("member")) {
			return View.MEMBER;
		}
		else if (sessionStorage.containsKey("realtor")) {
			return View.REALTOR;
		} 
		else if (sessionStorage.containsKey("admin") || (sessionStorage.containsKey(null))) {
			return View.ADMIN_NOTICE;
		} else return View.HOME;
	}
	
	private View noticeList() {
		System.out.println();
		List<Map<String, Object>> param = adminService.noticeList();
				
		for (Map<String, Object> map : param) {
			BigDecimal no = (BigDecimal)map.get("NTC_NO");
			String date = (String)map.get("NTC_DATE");
			String title = (String)map.get("NTC_TITLE");
			String content = (String)map.get("NTC_CONTENT");
			System.out.println("No."+no+"\t[등록일] "+date+"   "+title);
			System.out.println("[내용] "+content);
			System.out.println();
		}	
		System.out.println();
		
		if (sessionStorage.containsKey("member")) {
			return View.MEMBER;
		}
		else if (sessionStorage.containsKey("realtor")) {
			return View.REALTOR;
		} 
		else if (sessionStorage.containsKey("admin") || (sessionStorage.containsKey(null))) {
			return View.HOME;
		} else return View.HOME;
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
		param.add(estNo);
		return null;
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
		int deposit =ScanUtil.nextInt("보증금: ");
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
		return null;
	}

	private View estDetailList() {
		String id = null;
		List<Object> param = new ArrayList<Object>();
		List<Map<String, Object>> myEstList = null;
		System.out.println();
		System.out.println("1. 판매중 매물 전체 출력");
		System.out.println("2. 예약중 매물 전체 출력");
		System.out.println("3. 판매종료 매물 전체출력");
		System.out.println("4. 판매종료 매물 전체출력");
		System.out.println("5. 판매종료 매물 전체출력");
		System.out.println("6. 뒤로가기");
		int sel = ScanUtil.menu();
		if (sel == 6)
			return View.EST_LIST;
		param.add(sel);
		if (sessionStorage.containsKey("member")) {
			Map<String, Object> member = (Map<String, Object>) sessionStorage.get("member");
			id = (String) member.get("MEM_ID");
			param.add(id);
			myEstList = memberService.estDetailList(param);
		} else if (sessionStorage.containsKey("realtor")) {
			Map<String, Object> realtor = (Map<String, Object>) sessionStorage.get("realtor");
			id = (String) realtor.get("RET_ID");
			param.add(id);
			myEstList = realtorService.estDetailList(param);
		}
		for (Map<String, Object> map : myEstList) {
			System.out.println(map);
		}
		return View.EST_LIST;
	}

	private View myEstList() {
		String id = null;
		List<Object> param = new ArrayList<Object>();
		List<Map<String, Object>> myEstList = null;
		if (sessionStorage.containsKey("member")) {
			Map<String, Object> member = (Map<String, Object>) sessionStorage.get("member");
			id = (String) member.get("MEM_ID");
			param.add(id);
			myEstList = memberService.myEstList(param);
		} else if (sessionStorage.containsKey("realtor")) {
			Map<String, Object> realtor = (Map<String, Object>) sessionStorage.get("realtor");
			id = (String) realtor.get("RET_ID");
			param.add(id);
			myEstList = realtorService.myEstList(param);
		}
		for (Map<String, Object> map : myEstList) {
			System.out.println(map);
		}
		List<Object> param2 = new ArrayList<Object>();
		System.out.println("1. 판매 상태별 상세 출력");
		System.out.println("2. 매물 수정");
		System.out.println("3. 매물 삭제");
		System.out.println("4. 매물 판매상태 변경");
		int sel = ScanUtil.menu();
		if (sel == 1)
			return View.EST_DETAILLIST;
		else if (sel == 2) {
			int estNo = ScanUtil.nextInt("매물 번호입력: ");
			sessionStorage.put("estNo", estNo);
			return View.EST_UPDATE;
		} else if (sel == 3) {
			int estNo = ScanUtil.nextInt("매물 번호입력: ");
			sessionStorage.put("estNo", estNo);
			return View.EST_DELETE;
		}else if(sel==4) {
			int estNo = ScanUtil.nextInt("매물 번호입력: ");
			int state=ScanUtil.nextInt("매물 판매상태 입력: ");
			
			param2.add(state);
			param2.add(estNo);
			if (sessionStorage.containsKey("member")) {
				param2.add(id);
				memberService.estStateUpdate(param2);
			}
			else realtorService.estStateUpdate(param2);
		}
		return View.HOME;
	}

	private View estDetail() {
		int estNo = (int) sessionStorage.get("estNo");
		List<Object> param = new ArrayList<Object>();
		param.add(estNo);
		Map<String, Object> estDetail = estateService.estDetail(param);
		
		System.out.println(estDetail);
		System.out.println("1. 찜하기");
		System.out.println("2. 뒤로가기");
		int sel = ScanUtil.menu();
		if (sel == 1)
			return View.WISHLIST_INSERT;
		else if (sel == 2)
			return View.EST_LIST;
		return null;
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
		return View.HOME;
	}

	private View estList() {
		if (debug) System.out.println("=========집 매물 정보 보기=========");
		System.out.println();

		int page = 1;
		if (sessionStorage.containsKey("page"))
			page = (int) sessionStorage.remove("page");
		int startNo = 1 + (page - 1) * 5;
		int endNo = page * 5;

		List<Object> param = new ArrayList<Object>();
		param.add(startNo);
		param.add(endNo);

		List<Map<String, Object>> list = estateService.estList(param);
		for (Map<String, Object> map : list) {
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
		
		System.out.println("< 이전 페이지 \t 다음 페이지 >");
		System.out.println();
		System.out.println("1. 홈");
		System.out.println("2. 매물 상세보기");
		System.out.println();
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
			return View.HOME;
		} else if (sel.equals("2")) {
			int estNo = ScanUtil.nextInt("매물 번호입력: ");
			sessionStorage.put("estNo", estNo);
			return View.EST_DETAIL;
		}
		
		
		if (sessionStorage.containsKey("member")) {
			return View.MEMBER;
		}
		else if (sessionStorage.containsKey("realtor")) {
			return View.REALTOR;
		}
		else return View.EST_LIST;
	}

	private View memberDetail() {
		if (debug) System.out.println("=========회원정보 페이지=========");
		System.out.println();

		System.out.println("1. 회원 정보 보기\t2. 회원 정보 변경\t3. 회원 탈퇴\t4. 뒤로가기");
		System.out.println();
		
		int sel = ScanUtil.menu();
		
		if (sel == 1) return View.MYINFO;
		else if (sel == 2) return View.MEMBER_UPDATE;
		else if (sel == 3) return View.MEMBER_DELETE;
		else if (sel == 4) return View.MEMBER;
		else return View.MEMBER;
	}

	private View adminLogin() {
		if (debug) System.out.println("=========관리자 로그인 페이지=========");
		System.out.println();

		String id = ScanUtil.nextLine("ID  : ");
		String pw = ScanUtil.nextLine("PASS: ");
		
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
	
	private View myInfo() {
	      if (debug) System.out.println("=========내 정보 보기=========");
	      System.out.println();

	      Map<String, Object> memberInfo = (Map<String, Object>) sessionStorage.get("member");
	      Map<String, Object> realtorInfo = (Map<String, Object>) sessionStorage.get("realtor");
	      
	      if(memberInfo!=null) {
	         String id = (String) memberInfo.get("MEM_ID");
	         
			 List<Object> param = new ArrayList<Object>();
			 param.add(id);
			 Map<String, Object> param1 = memberService.memInfo(param);
			 
	         String pw = (String) param1.get("MEM_PW");
	         String name = (String) param1.get("MEM_NAME");
	         String tel = (String) param1.get("MEM_TEL");
	         String address = (String) param1.get("MEM_ADDRESS");
	         String nicName = (String) param1.get("MEM_NICKNAME");
	         BigDecimal bank = (BigDecimal) param1.get("MEM_BANK");
	         String tier = (String) param1.get("TIC_TIER");
	         BigDecimal rptCnt = (BigDecimal) param1.get("MEM_RPTCNT");
	         System.out.println("ID : " + id + "\t PW : " + pw + "\t 이름 : " + name + "\t 주소 : " + address);
	         System.out.println("전화번호 : " + tel + "\t 닉네임 : " + nicName + "\t 현재 잔액 : " + bank + "\t 보유 이용권 : " + tier
	               + "\t나의 경고 횟수 : " + rptCnt);
	         System.out.println();
	         return View.MEMBER;
	      }
	      else {
	         String id = (String) realtorInfo.get("RET_ID");
	         
			 List<Object> param = new ArrayList<Object>();
			 param.add(id);
			 Map<String, Object> param1 = realtorService.retInfo(param);
	         
	         String pw = (String) param1.get("RET_PW");
	         String name = (String) param1.get("RET_NAME");
	         String tel = (String) param1.get("RET_TEL");
	         String address = (String) param1.get("RET_ADDRESS");
	         String nicName = (String) param1.get("RET_NICKNAME");
	         BigDecimal bank = (BigDecimal) param1.get("RET_BANK");
	         String tier = (String) param1.get("TIC_TIER");
	         BigDecimal rptCnt = (BigDecimal) param1.get("RET_RPTCNT");
	         int comNo=(int) param1.get("COM_NO");
	         System.out.println("ID : " + id + "\t PW : " + pw + "\t 이름 : " + name + "\t 주소 : " + address);
	         System.out.println("전화번호 : " + tel + "\t 닉네임 : " + nicName + "\t 현재 잔액 : " + bank + "\t 보유 이용권 : " + tier
	               + "\t나의 경고 횟수 : " + rptCnt+"\t공인중개사 코드 : "+comNo);
	         System.out.println();
	         return View.REALTOR;
	      }

	   }
	
	private View realtor() {
		if (debug) System.out.println("=========공인중개사 페이지=========");
		System.out.println();

		System.out.println("1. 공지사항\t2.전체 매물 목록보기\t3. 내 매물 등록\t4. 내 매물 정보\t5. 회원 정보");
		System.out.println("6. 리뷰 보기\t7. 예약 목록\t8. 로그아웃");
		System.out.println();
		
		int sel = ScanUtil.menu();

		if (sel == 1) return View.NOTICE_LIST;
		else if (sel == 2) return View.EST_LIST;
		else if (sel == 3) return View.EST_ADD;
		else if (sel == 4) return View.MEMBER_MYESTLIST;
		else if (sel == 5) return View.MYINFO;
		else if (sel == 6) return View.RET_REVIEWLIST;
		else if (sel == 7) return View.MEMBER_RESERVATIONLIST;
		else if (sel == 8) {
			sessionStorage.remove("admin");
			System.out.println("로그아웃이 완료되었습니다.");
			System.out.println();
			return View.HOME;
		}
		else return View.REALTOR;
	}

	private View member() {
		if (debug) System.out.println("=========일반회원 페이지=========");
		System.out.println();

		System.out.println("1. 공지사항\t2.전체 매물 목록보기\t3. 내 매물 등록\t4. 내 매물 정보\t5. 회원 정보");
		System.out.println("6. 리뷰 관리\t7. 예약 목록\t8. 찜 목록\t9. 로그아웃");
		System.out.println();
		
		int sel = ScanUtil.menu();

		if (sel == 1) return View.NOTICE_LIST;
		else if (sel == 2) return View.EST_LIST;
		else if (sel == 3) return View.EST_ADD;
		else if (sel == 4) return View.MEMBER_MYESTLIST;
		else if (sel == 5) return View.MYINFO;
		else if (sel == 6) return View.MEMBER_REVIEW;
		else if (sel == 7) return View.MEMBER_RESERVATIONLIST;
		else if (sel == 8) return View.MEMBER_WISHLIST;
		else if (sel == 9) {
			sessionStorage.remove("admin");
			System.out.println("로그아웃이 완료되었습니다.");
			System.out.println();
			return View.HOME;
		}
		else return View.MEMBER;
	}
	
	private View sign() {
		printSignList();
		int sel = ScanUtil.menu();
		List<Map<String, Object>> memberData = memberService.memList();
		List<Map<String, Object>> realtorData = realtorService.retList();
		String id;
		while (true) {
			int cnt = 0;
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
			if (cnt == 0)
				break;
		}
		String pw = ScanUtil.nextLine("회원 pw입력:");
		String name = ScanUtil.nextLine("회원 이름 입력:");
		String tel = ScanUtil.nextLine("회원 전화번호 입력:");
		String address = ScanUtil.nextLine("회원 주소입력:");
		List<Object> param = new ArrayList<Object>();
		param.add(id);
		param.add(pw);
		param.add(name);
		param.add(tel);
		param.add(address);
		int comNo = 0;
		String nickName = null;
		if (sel == 2) {
			List<Map<String, Object>> comList = realtorService.comList();
			for (Map<String, Object> map : comList) {
				System.out.println(map);
			}
			int sel2 = ScanUtil.nextInt("이중에 당신의 공인중개사가 있나요?(1.네,2.아니오(공인중개사 새로만들기)): ");
			if (sel2 == 1) {
				comNo = ScanUtil.nextInt("공인중개소 번호 입력: ");
			} else if (sel2 == 2) {
				String comName = ScanUtil.nextLine("공인중개소 이름 입력: ");
				String comtel = ScanUtil.nextLine("공인중개소 전화번호 입력: ");
				String comAddress = ScanUtil.nextLine("공인중개소 주소: ");
				String comComent = ScanUtil.nextLine("공인중개소 소개: ");
				List<Object> param2 = new ArrayList<Object>();
				param2.add(comName);
				param2.add(comtel);
				param2.add(comAddress);
				param2.add(comComent);
				realtorService.companyInsert(param2);
				List<Map<String, Object>> comList2 = realtorService.comList();
				for (Map<String, Object> map : comList2) {
					System.out.println(map);
				}
				comNo = ScanUtil.nextInt("공인중개소 번호 입력: ");
			}
			param.add(comNo);
			realtorService.sign(param);

		} else if (sel == 1)
			nickName = ScanUtil.nextLine("회원 닉네임 입력:");
		param.add(nickName);
		memberService.sign(param);
		System.out.println("회원가입 완료");
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
		else if (sel == 0)
			System.out.print("=========관리자");
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
			System.out.println("로그아웃이 완료되었습니다.");
			System.out.println();
			return View.HOME;
		}
		else return View.ADMIN;
	}

	private View home() {
		System.out.println();
		if (debug) System.out.println("==========홈==========");
		System.out.println();

		System.out.println("1. 공지사항");
		System.out.println("2. 집 매물 정보보기");
		System.out.println("3. 집 매물 등록하기");
		System.out.println("4. 로그인");
		System.out.println("5. 회원가입");
		System.out.println();

		int sel = ScanUtil.menu();

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