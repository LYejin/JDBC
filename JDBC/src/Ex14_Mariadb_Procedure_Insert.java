import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;

import kr.or.kosa.utils.SingletonHelper;

public class Ex14_Mariadb_Procedure_Insert {

	public static void main(String[] args) {
		Connection conn = null;
		CallableStatement cstmt = null;
		
		try {
			conn = SingletonHelper.getConnection("mariadb");
			String sql = "{call insert_dept5(?,?,?,?)}";
			cstmt = conn.prepareCall(sql);
			
			cstmt.setInt(1, 9999);
			cstmt.setString(2, "IT");
			cstmt.setString(3, "KOREA");
			cstmt.registerOutParameter(4, Types.VARCHAR);
			
			cstmt.execute();
			
			String mariadb_msg = (String) cstmt.getObject(4);
			System.out.println("mariadb_msg : " + mariadb_msg);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			SingletonHelper.close(cstmt);
		}
	}

}
