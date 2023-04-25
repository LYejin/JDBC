import java.sql.Connection;
import java.sql.SQLException;

import kr.or.kosa.utils.SingletonHelper;

public class Ex07_SingletonHelper {

	public static void main(String[] args) throws SQLException {
		/*
		Connection conn = null;
		conn = SingletonHelper.getConnection("oracle");
		System.out.println(conn.toString());
		System.out.println(conn.getMetaData().getDatabaseProductName());
		
		Connection conn2 = null;
		conn2 =  SingletonHelper.getConnection("oracle");
		System.out.println(conn2.toString());
		System.out.println(conn2.getMetaData().getDatabaseProductName());
		*/
		/*
		 oracle.jdbc.driver.T4CConnection@6cc4c815
		Oracle
		oracle.jdbc.driver.T4CConnection@6cc4c815
		Oracle
		 */
		
		/*
		Connection conn = null;
		conn = SingletonHelper.getConnection("oracle");
		System.out.println(conn.toString());
		System.out.println(conn.getMetaData().getDatabaseProductName());
		conn.close(); //끄면 안된다. 
		
		Connection conn2 = null;
		conn2 =  SingletonHelper.getConnection("mariadb");
		System.out.println(conn2.toString());
		System.out.println(conn2.getMetaData().getDatabaseProductName());
		
		oracle.jdbc.driver.T4CConnection@6cc4c815
		Oracle
		oracle.jdbc.driver.T4CConnection@6cc4c815
		Exception in thread "main" java.sql.SQLRecoverableException: 접속 종료
			at oracle.jdbc.driver.PhysicalConnection.getMetaData(PhysicalConnection.java:4442)
			at Ex07_SingletonHelper.main(Ex07_SingletonHelper.java:36)
		 */
		
		Connection conn = null;
		conn = SingletonHelper.getConnection("oracle");
		System.out.println(conn.toString());
		System.out.println(conn.getMetaData().getDatabaseProductName());
		SingletonHelper.dbclose(); //끄면 안된다. 
		
		Connection conn2 = null;
		conn2 =  SingletonHelper.getConnection("mariadb");
		System.out.println(conn2.toString());
		System.out.println(conn2.getMetaData().getDatabaseProductName());
	}

}
