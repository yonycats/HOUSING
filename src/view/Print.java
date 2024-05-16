package view;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class Print {
	public void printVar() {
		System.out.println("=============================");
	}

	public void printLn(int num) {
		for (int i = 0; i < num; i++)
			System.out.println();
	}

	public void printHome() {
		printVar();
		System.out.println("1. 집 매물 정보 보기");
		System.out.println("2. 집 매물 등록하기");
		System.out.println("3. 로그인");
		System.out.println("4. 회원가입");
		System.out.println("5. 공지사항");
		printVar();
		System.out.println();
	}
	public void printReviewHome() {
		printVar();
		System.out.println("1. 리뷰 수정");
		System.out.println("2. 리뷰 삭제");
		System.out.println("3. 회원 메뉴");
		System.out.println("4. 홈");
		printVar();
		System.out.println();
	}
	public void printSignList() {
		printVar();
		System.out.println("1. 일반 회원 가입");
		System.out.println("2. 공인중개사 회원 가입");
		printVar();
		System.out.println();
	}
	public void printLoginList() {
		printVar();
		System.out.println("1. 일반 회원 로그인");
		System.out.println("2. 공인중개사 로그인");
//		System.out.println("0. 관리자 로그인");
		printVar();
		System.out.println();
	}
	public void printAdmin() {
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
}


