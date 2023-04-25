import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Ex05_Oracle_DML_Delete {
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		
		try {
			//드라이버로딩 생략
			
			//연결객체 생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:49161:xe","KOSA","1004");
			stmt = conn.createStatement();
			
			//UPDATE
			int deptno=0;
			
			Scanner sc = new Scanner(System.in);
			
			System.out.println("부서입력 : ");
			deptno = Integer.parseInt(sc.nextLine());
			
			//delete from dmlemp where deptno=10
			//전통적이고 고전적인 방법
			String sql = "delete from dmlemp where deptno=" + deptno; // 자동 commit 
			//이 방법을 사용했어요 실개발 (오타작렬)
			System.out.println(sql);
			
			//현재 values(?,?,?) ? 한개 parameter
			//executeUpdate() 함수 (insert, update, delete) 수
			int resultrow = stmt.executeUpdate(sql);
			
			if(resultrow > 0) {
				System.out.println("반영된 행의 수 : " +resultrow);
			} else {
				System.out.println("예외가 발생된 것이 아니고 : 반영된 행이 없다");
			}
			
		} catch (Exception e) {
			//문제 발생 ... 고번 처리
			System.out.println("SQL 예외발생 : " + e.getMessage()); 
			// executeUpdate에서 예외발생해서 if문 통과
			// pk에 걸린 이유는 100이 동일해서
		} finally {
			//강제 실행 블럭 사용
			//자원해제
			try {
				stmt.close();
			} catch (SQLException e2) {
				// TODO: handle exception
			}
			try {
				conn.close();
			} catch (SQLException e2) {
				// TODO: handle exception
			}
		}
		
	}

}
