import java.util.List;

import DAO.DeptDao;
import DTO.Dept;
import DTO.Emp;

/*
App 서버 구성 ( MVC 패턴) >> 웹 
잘 하는 것만 해 

Model (java) >> DTO(데이터를 담을 수 있는 클래스) , DAO(데이터를 처리할 수 있는 클래스(JDBC API)) , SERVICE


View (UI)    >> html , jsp 등등 >> 현재 console 화면제어 (main 정도)


Controller   >> 중앙제어 (통제) >> 웹의 접근 통제 >> 요청과 응답처리 >> JAVA >> Servlet(웹용 자바파일) (main 정도)


*/

public class Program {
	public static void main(String[] args) throws Exception {
		
		/*
		Dept dept = new Dept();
		dept.setDeptno(100);
		dept.setDname("IT");
		dept.setLoc("SEOUL");
		System.out.println(dept.toString());
		
		Emp emp = new Emp(200, "김유신");
		System.out.println(emp.toString());
		*/
		
		//main 함수가  UI 와 요청/응답 처리
		//결국 main 함수가 (view 와 controller 역할 제공)
		
		DeptDao dao = new DeptDao();
		
		System.out.println("[전체조회]");
		List<Dept> deptlist = dao.getDeptAllList(); //요청
		
		//화면구성
		if(deptlist != null) {
			deptPrint(deptlist);
		}
		
		System.out.println("[조건조회]");
		Dept dept = dao.getDeptSearch(10);
		if(dept != null) {
			deptPrint(dept);
		}else {
			System.out.println("조건조회 FAIL");
		}
		
		
		System.out.println("[데이터 삽입]");
		Dept insertdept = new Dept();
		insertdept.setDeptno(100);
		insertdept.setDname("IT");
		insertdept.setLoc("SEOUL");
		
		int insertrow = dao.dataInsert(insertdept);
		if(insertrow > 0 ) {
			System.out.println("INSERT ROW : " + insertrow);
		}else {
			System.out.println("INSERT FAIL");
		}
		
		System.out.println("[방금전 INSERT 한 데이터 조회]");
		deptlist = dao.getDeptAllList(); //변경된 데이터 다시 조회
		if(deptlist != null) {
			deptPrint(deptlist);
		}
		
		System.out.println("[방금전 INSERT 한 데이터 수정]");
		Dept updatedept = new Dept();
		updatedept.setDeptno(100);
		updatedept.setDname("IT_UP");
		updatedept.setLoc("BUSAN");
		
		int updaterow = dao.dataUpdate(updatedept);
		if(updaterow > 0 ) {
			System.out.println("UPDATE ROW : " + updaterow);
		}else {
			System.out.println("UPDATE FAIL");
		}
		
		System.out.println("[방금전 UPDATE 한 데이터 조회]");
		deptlist = dao.getDeptAllList(); //변경된 데이터 다시 조회
		if(deptlist != null) {
			deptPrint(deptlist);
		}
		
		System.out.println("[방금전 UPDATE 데이터 삭제하기]");
		int deleterow = dao.dataDelete(100);
		if(deleterow > 0 ) {
			System.out.println("DELETE ROW : " + deleterow);
		}else {
			System.out.println("DELETE FAIL");
		}
		
		System.out.println("[방금전 DELETE 한 데이터 조회]");
		deptlist = dao.getDeptAllList(); //변경된 데이터 다시 조회
		if(deptlist != null) {
			deptPrint(deptlist);
		}
	}
	
	public static void deptPrint(Dept dept) {
		System.out.println(dept.toString());
	}
	public static void deptPrint(List<Dept> list) {
		for(Dept data : list) {
			System.out.println(data.toString());
		}
	}
}








