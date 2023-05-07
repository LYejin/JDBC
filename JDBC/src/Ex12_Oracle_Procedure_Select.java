import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import kr.or.kosa.utils.SingletonHelper;
import oracle.jdbc.internal.OracleTypes;
import oracle.jdbc.oracore.OracleType;


/*
create or replace procedure usp_EmpList
(
  p_sal IN number,
  p_cursor OUT SYS_REFCURSOR -- APP 사용하기 타입
)
is
    begin
        open p_cursor 
        for select empno, ename ,sal from emp where sal > p_sal;
    end;

    var out_cursor REFCURSOR
    exec usp_EmpList(2000,:out_cursor)
    print out_cursor;

*/
public class Ex12_Oracle_Procedure_Select {

	public static void main(String[] args) {
		  Connection conn=null;
		  CallableStatement cstmt = null; //명령객체 (프로시져)
		  ResultSet rs = null;
		  
		  try {
			     conn =  SingletonHelper.getConnection("oracle");
			     String sql="{call usp_EmpList(?,?)}";
			     cstmt = conn.prepareCall(sql);
			     
			     //usp_EmpList(?,?)  >> ? input ,  ? output
			     cstmt.setInt(1, 2000);
			     cstmt.registerOutParameter(2, OracleTypes.CURSOR); // p_cursor OUT SYS_REFCURSOR 
			  
			     cstmt.execute();
			     
			     rs = (ResultSet)cstmt.getObject(2);
			     while(rs.next()) {
			    	 System.out.println(rs.getInt(1) + "/" + rs.getString(2) + "/" + rs.getInt(3));
			     }
			     
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			SingletonHelper.close(rs);
			SingletonHelper.close(cstmt);
		}
		  
		  

	}

}
