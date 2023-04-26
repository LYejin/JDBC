package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DTO.Dept;
import UTILS.SingletonHelper;

/*
1. DB연결
2. CRUD 함수
3. 기본 5가지 + 알파 (조건 검색, 문자열 검색)
3.1 전체조회, 조건조회, insert, update, delete 함수
 */
public class DeptDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public DeptDao() {
		conn= null;
		pstmt= null;
		rs= null;
	}
	
	//1. 전체조회 >>  select 결과 >> return multi row => (Dept 객체 여러개)
	//select deptno, dname, loc from dept
	public List<Dept> getDeptAllList() {
		//여러건의 데이터 (Dept 객체 여러개)
		List<Dept> deptlist = new ArrayList<>();
		//deptlist.add(new Dept());
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = SingletonHelper.getConnection("mariadb");
			String sql = "select deptno, dname, loc from dept";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Dept dept = new Dept(); //하나의 row를 담을 수 있는 DTO 객체 생성
				dept.setDeptno(rs.getInt("deptno"));
				dept.setDname(rs.getString("dname"));
				dept.setLoc(rs.getString("loc"));
				deptlist.add(dept); //ArrrayList 객체 담기 ... 5건 >> Dept 객체 5개 add
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			SingletonHelper.close(rs);
			SingletonHelper.close(pstmt);
		}
		return deptlist;
	}
	
	//2. 조건조회 >> select 결과(where deptno=?) >> return single row (Dept 객체 하나)
	//select deptno, dname, loc from dept where deptno=?
	public Dept getDeptSearch(int num) {
		Dept dept = null;
		conn = null;
		pstmt = null;
		rs=null;
		try {
			conn = SingletonHelper.getConnection("mariadb");
			String sql = "select deptno, dname, loc from dept where deptno=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				dept = new Dept();
				dept.setDeptno(rs.getInt("deptno"));
				dept.setDname(rs.getString("dname"));
				dept.setLoc(rs.getString("loc"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			SingletonHelper.close(rs);
			SingletonHelper.close(pstmt);
		}
		return dept;
	}
	
	
	//3. 데이터 삽입 >> parameter (Dept 객체) >> return 정수
	//insert into dept(deptno,dname,loc) values(?,?,?)
	public int dataInsert(Dept dept) throws Exception {
		conn = null;
		pstmt = null;
		int row = 0;
		try {
			conn = SingletonHelper.getConnection("mariadb");
			String sql = "insert into dept(deptno,dname,loc) values(?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dept.getDeptno());
			pstmt.setString(2, dept.getDname());
			pstmt.setString(3, dept.getLoc());
			row = pstmt.executeUpdate();
			if (row > 0) {
				System.out.println("insert row count : " + row);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			SingletonHelper.close(pstmt);
		}
		return row;
	}
	
	
	//4. 데이터 수정 >> parameter (Dept 객체) >> return 정수
	//update dept set dname=?, loc=? where deptno=?
	public int dataUpdate(Dept dept) {
		conn = null;
		pstmt = null;
		int row = 0;
		try {
			conn = SingletonHelper.getConnection("mariadb");
			String sql = "update dept set dname=?, loc=? where deptno=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dept.getDname());
			pstmt.setString(2, dept.getLoc());
			pstmt.setInt(3, dept.getDeptno());
			row = pstmt.executeUpdate();
			if (row > 0) {
				System.out.println("update row count : " + row);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			SingletonHelper.close(pstmt);
		}
		return row;
	}
	
	
	//5. 데이터 삭제>> parameter (Dept 객체) >> return 정수
	//delete from dept where deptno=?
	public int dataDelete(int deptno) {
		conn = null;
		pstmt = null;
		int row = 0;
		try {
			conn = SingletonHelper.getConnection("mariadb");
			String sql = "delete from dept where deptno=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, deptno);
			row = pstmt.executeUpdate();
			if (row > 0) {
				System.out.println("delete row count : " + row);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			SingletonHelper.close(pstmt);
		}
		return row;
	}
}
