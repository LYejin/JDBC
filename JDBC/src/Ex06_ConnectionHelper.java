import java.sql.Connection;
import java.sql.SQLException;

import kr.or.kosa.utils.ConnectionHelper;
import kr.or.kosa.utils.SingletonHelper;

public class Ex06_ConnectionHelper {
	public static void main(String[] args) throws SQLException {
		Connection conn = null;
		conn = ConnectionHelper.getConnection("oracle"); // 계속 새로운 객체 생성 

		System.out.println(conn.toString());
		System.out.println(conn.getMetaData().getDatabaseProductName());
		System.out.println(conn.getMetaData().getDatabaseProductVersion());
		System.out.println(conn.isClosed());
		ConnectionHelper.close(conn);
		System.out.println(conn.isClosed());
		
		conn = ConnectionHelper.getConnection("oracle","HR", "1004");
		System.out.println(conn);
		//oracle.jdbc.driver.T4CConnection@4cf4d528
		//connection 요청시 마다 새로운 객체 생성
		//현업 (ConnectionPool) > 미리 연결 객체 생성해 놓고 > 가져다 쓰고 반환하는 방식
		
		//복습 (single 복습)
		//하나의 연결 객체를 공유해서 사용할 수 없을까?
		//singleton 으로 ...
		conn = SingletonHelper.getConnection("oracle","HR", "1004");
		System.out.println(conn);
	}

}
