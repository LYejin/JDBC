import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*
JDBC 작업

1. select
1.1 결과 집합 생성 

2. insert, update, delete
2.1 결과 집합 생성 없어요(x) >> resultSet (x)
2.2 반영결과 return (반영된 행의 수) >> update 5건 수정 >> return 5;

ex)
update emp set sal=0; >> 실행 >> update 14건 >> return 14
update emp set sal=100 where empno=9999 >> update 0건 (이런 데이터가 없어서) >> return 0

결과를 받아서 사번코드 로직처리

Today key Point
1. Oracle DML (developer, Cmd (sqlplus), Tool) 작업 commit or rollback 강제
2. JDBC API 사용해서 Oracle DML 작업을 수행하면 >> default >> auto commit >> 실반영
3. JDBC API 사용해서 JAVA 코드에서 delete from emp 실행 >> JDBC 자동 commit >> 실반영
4. 그래서 JDBC API 옵션을 통해서 commit, rollback 강제하는 방법 제공

begin
	A계좌 인출 (update ...)
	B계좌 입금 (update ...)
end
논리적인 단위로 transaction(트랜직션)
전체 성공 아니면 실패
commit or rollback 
을 해줘야 돌아간다...!!! 기억하기!! 공유한다면 이부분 중요하게 생각하기 

>>업무 처리 >> JDBC >> autocommit 옵션 >> false 전환
>>반드시 java code에서 commit, rollback 강제 >> commit을 안하고 실수 많이 하니 커밋 자동으로 만들었다..

실습쿼리 ^^
show user;

create table dmlemp
as
    select * from emp;
    
select * from dmlemp;

select * from user_constraints where table_name='DMLEMP';

alter table dmlemp
add constraint pk_dmlemp_empno primary key(empno);

select * from dmlemp;

 */


public class Ex03_Oracle_DML_Insert {

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		
		try {
			//드라이버로딩 생략
			
			//연결객체 생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:49161:xe","KOSA","1004");
			stmt = conn.createStatement();
			
			//INSERT
			int empno=0;
			String ename=null;
			int deptno=0;
			
			Scanner sc = new Scanner(System.in);
			System.out.println("사번입력 : ");
			empno = Integer.parseInt(sc.nextLine());
			
			System.out.println("이름입력 : ");
			ename = sc.nextLine();
			
			System.out.println("부서입력 : ");
			deptno = Integer.parseInt(sc.nextLine());
			
			//insert into dmlemp(empno, ename, deptno) values(100,'홍길동',10)
			//전통적이고 고전적인 방법
			String sql = "insert into dmlemp(empno, ename, deptno)";
					sql += " values(" + empno + ",'" + ename + "'," + deptno + ")";
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
