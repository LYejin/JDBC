package kr.or.kosa.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/*
JDBC 작업 (DB결정)
ex) 회원테이블
1. 전체조회, 조건조회, 삽입, 삭제, 수정
각각의 작업을 할 때마다
1. 드라이버 로딩
2. 연결객체 생성 ... 지원해제
3. 반복적인 코드 ... 제거

공통적인 작업 별도 분리 .... 기능이 많이 사용되면 ...

기능(함수) >> 자주 (static) >> overloading (편하게) >> 다형성(유연하게)

 */

public class ConnectionHelper {
	public static Connection getConnection(String dsn) {
		Connection conn = null;
		try {
			if (dsn.equals("oracle")) {
				conn= DriverManager.getConnection("jdbc:oracle:thin:@localhost:49161:xe","KOSA","1004");
			} else if (dsn.equals("mariadb")) {
				conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/kosadb","kosa","1004");
			} 
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}
	public static Connection getConnection(String dsn, String id, String pwd) {
		Connection conn = null;
		try {
			if (dsn.equals("oracle")) {
				conn= DriverManager.getConnection("jdbc:oracle:thin:@localhost:49161:xe","KOSA","1004"); // id, pwd)
			} else if (dsn.equals("mariadb")) {
				conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/kosadb","kosa","1004"); // id, pwd)
			} 
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}
	
	
	// 오버로딩으로 close 함수 생성
	public static void close(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	public static void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
		
	public static void close(Statement stsmt) {
		if (stsmt != null) {
			try {
				stsmt.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	public static void close(PreparedStatement pstsmt) {
		if (pstsmt != null) {
			try {
				pstsmt.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
}
