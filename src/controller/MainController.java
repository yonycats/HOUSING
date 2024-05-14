package controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import service.AdminService;
import service.EstateService;
import service.MemberService;
import service.NoticeService;
import service.RealtorService;
import util.ScanUtil;
import util.View;
import view.Print;

public class MainController extends Print {

	static public Map<String, Object> sessionStorage = new HashMap<>();

	AdminService adminService = AdminService.getInstance();
	MemberService memberService = MemberService.getInstance();
	NoticeService noticeService = NoticeService.getInstance();
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
//			case ADMINLOGIN:
//				view = adminLogin();
//				break;
//			case MEMBERLOGIN:
//				view = memberLogin();
//				break;
			case SIGN:
				view = sign();
				break;
			case LOGIN:
				view = login();
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
			case NOTICE_LIST:
				view = noticeList();
				break;
			case MYINFO:
				view = myInfo();
				break;
			case ADMIN_NOTICE:
				view = adminNotice();
				break;
//			case ADMIN_REPORT:
//				view = adminReport();
//				break;
//			case ADMIN_TICKET:
//				view = adminTicket();
//				break;
//			case MEMBER_UPDATE:
//				view = memberUpdate();
//				break;
//			case MEMBER_DELETE:
//				view = memberDelete();
//				break;
//			case MEMBER_MYESTLIST:
//				view = memberMyEstList();
//				break;
//			case MEMBER_REVIEW:
//				view = memberReview();
//				break;
//			case MEMBER_MYSALELIST:
//				view = memberMySaleList();
//				break;
//			case MEMBER_WISHLIST:
//				view = memberWishList();
//				break;
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
	private View realtor() {
		// TODO Auto-generated method stub
		return null;
	}

	private View sign() {
		printSignList();
		int sel = ScanUtil.menu();
//		while (true) {
//			String id=ScanUtil.nextLine("회원 id입력:");
//			if (data.containsKey(id))
//				System.out.println("이미존재하는 아이디");
//			else
//				break;
//		}
		String id = ScanUtil.nextLine("회원 id입력:");
		String pw = ScanUtil.nextLine("회원 pw입력:");
		String name = ScanUtil.nextLine("회원 이름 입력:");
		String tel = ScanUtil.nextLine("회원 전화번호 입력:");
		String address = ScanUtil.nextLine("회원 주소입력:");
		String nickName = ScanUtil.nextLine("회원 닉네임 입력:");
		List<Object> param = new ArrayList<Object>();
		param.add(id);
		param.add(pw);
		param.add(name);
		param.add(tel);
		param.add(address);
		param.add(nickName);
		if (sel == 2) {
			String comName = ScanUtil.nextLine("공인중개소 이름 입력: ");
			String comtel = ScanUtil.nextLine("공인중개소 전화번호 입력: ");
			String comAddress = ScanUtil.nextLine("공인중개소 주소: ");
			String comComent = ScanUtil.nextLine("공인중개소 소개: ");
			param.add(comName);
			param.add(comtel);
			param.add(comAddress);
			param.add(comComent);
			realtorService.sign(param);

		} else if (sel == 1)
			memberService.sign(param);
		System.out.println("회원가입 완료");
		return View.HOME;

	}

	private View noticeList() {
		System.out.println();
		List<Map<String, Object>> param = noticeService.noticeList();

		for (Map<String, Object> map : param) {
			String date = (String) map.get("NTC_DATE");
			String title = (String) map.get("NTC_TITLE");
			String content = (String) map.get("NTC_CONTENT");
			System.out.println("[등록일] " + date + "   " + title + "    \t [내용] " + content);
		}
		System.out.println();
		return View.HOME;
	}

	private View adminNotice() {
		if (debug)
			System.out.println("=========공지사항 관리=========");
		System.out.println();

		noticeList();

		System.out.println("1. 공지사항 작성");
		System.out.println("2. 공지사항 수정");
		System.out.println("3. 공지사항 삭제");

		System.out.println();
		return null;
	}

	private View estAdd() {
		if (!sessionStorage.containsKey("member")) {
			System.out.println("로그인한 회원만 사용가능한 메뉴입니다.");
			return View.MEMBERLOGIN;
		}

		return View.HOME;
	}

	private View myInfo() {
		if (debug)
			System.out.println("=========내 정보 보기=========");
		System.out.println();

		Map<String, Object> myInfo = (Map<String, Object>) sessionStorage.get("member");

		String id = (String) myInfo.get("MEM_ID");
		String pw = (String) myInfo.get("MEM_PW");
		String name = (String) myInfo.get("MEM_NAME");
		String tel = (String) myInfo.get("MEM_TEL");
		String address = (String) myInfo.get("MEM_ADDRESS");
		String nicName = (String) myInfo.get("MEM_NICKNAME");
		BigDecimal bank = (BigDecimal) myInfo.get("MEM_BANK");
		String tier = (String) myInfo.get("TIC_TIER");
		BigDecimal rptCnt = (BigDecimal) myInfo.get("MEM_RPTCNT");

		System.out.println("ID : " + id + "\t PW : " + pw + "\t 이름 : " + name + "\t 주소 : " + address);
		System.out.println("전화번호 : " + tel + "\t 닉네임 : " + nicName + "\t 현재 잔액 : " + bank + "\t 보유 이용권 : " + tier
				+ "\t나의 경고 횟수 : " + rptCnt);
		System.out.println();

		return View.MEMBER;
	}

	private View estList() {
		if (debug)
			System.out.println("=========집 매물 정보 보기=========");
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
		if (debug)
			System.out.println("=========일반회원 페이지=========");
		System.out.println();

		System.out.println("1. 회원 정보 보기\t2. 회원 정보 변경\t3. 회원 탈퇴\t4. 내 매물 정보\t5. 리뷰 관리");
		System.out.println("6. 거래 목록\t7. 찜 목록\t8. 신고\t9. 홈\t10. 로그아웃");
		System.out.println();

		int sel = ScanUtil.menu();

		if (sel == 1)
			return View.MYINFO;
		else if (sel == 2)
			return View.MEMBER_UPDATE;
		else if (sel == 3)
			return View.MEMBER_DELETE;
		else if (sel == 4)
			return View.MEMBER_MYESTLIST;
		else if (sel == 5)
			return View.MEMBER_REVIEW;
		else if (sel == 6)
			return View.MEMBER_MYSALELIST;
		else if (sel == 7)
			return View.MEMBER_WISHLIST;
		else if (sel == 8)
			return View.MEMBER_REPORT;
		else if (sel == 9)
			return View.HOME;
		else if (sel == 10) {
			sessionStorage.remove("admin");
			return View.HOME;
		} else
			return View.ADMIN;

	}
	private View login() {
		boolean loginChk=false;
		printLoginList();
		int sel = ScanUtil.menu();
		if(sel==1) System.out.print("=========일반회원");
		else if(sel==2) System.out.print("=========공인중개사");
		else if(sel==0) System.out.print("=========관리자");
		System.out.println(" 로그인 페이지=========");
		
		String id = ScanUtil.nextLine("ID  : ");
		String pw = ScanUtil.nextLine("PASS: ");
		
		List<Object> param = new ArrayList<Object>();
		param.add(id);
		param.add(pw);
		
		if(sel==1) loginChk = memberService.login(param);
		else if(sel==2) loginChk = realtorService.login(param);
		else if(sel==0) loginChk = adminService.login(param);
		
		
		if (!loginChk) {
			System.out.println("로그인을 실패했습니다.");
			return View.LOGIN;
		} else {
			System.out.println("로그인에 성공했습니다.");
			if(sel==1) {
				return View.MEMBER;
			}
			else if(sel==2) {
				return View.REALTOR;
			}
			else if(sel==0) {
				return View.ADMIN;
			}
		}
		return null;
	}
	private View admin() {

		printAdmin();

		int sel = ScanUtil.menu();

		if (sel == 1)
			return View.ADMIN_NOTICE;
		else if (sel == 2)
			return View.ADMIN_REPORT;
		else if (sel == 3)
			return View.ADMIN_TICKET;
		else if (sel == 4) {
			sessionStorage.remove("admin");
			return View.HOME;
		} else
			return View.ADMIN;
	}

	private View home() {
		if (debug)
			System.out.println("==========홈==========");
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
			return View.LOGIN;
		else if (sel == 4)
			return View.SIGN;
		else if (sel == 5)
			return View.NOTICE_LIST;
//		else if (sel == 0)
//			return View.ADMINLOGIN;
		return View.HOME;
	}

}