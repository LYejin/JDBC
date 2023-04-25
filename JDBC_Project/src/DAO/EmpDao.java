package DAO;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DTO.Emp;
import UTILS.SingletonHelper;

public class EmpDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public EmpDao() {
		conn =null;
		pstmt=null;
		rs=null;
	}
	
	public void empSetting() {
		conn=null;
		pstmt=null;
		rs=null;
	}
	
	//1. 전체조회
	public List<Emp> getEmpAllList() {
		List<Emp> empList = new ArrayList<>();
		empSetting();
		try {
			conn = SingletonHelper.getConnection("oracle");
			String str = "select * from emp";
			pstmt = conn.prepareStatement(str);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Emp emp = new Emp();
				emp.setEmpno(rs.getInt("empno"));
				emp.setEname(rs.getString("ename"));
				emp.setJob(rs.getString("job"));
				emp.setMgr(rs.getInt("mgr"));
				emp.setHiredate(rs.getDate("hiredate"));
				emp.setSal(rs.getInt("sal"));
				emp.setComm(rs.getInt("comm"));
				emp.setDeptno(rs.getInt("deptno"));
				empList.add(emp);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			SingletonHelper.close(pstmt);
			SingletonHelper.close(rs);
		}
		return empList;
	}
	
	//2. 조건조회
	public Emp getEmp(int empno) {
		Emp emp = null;
		empSetting();
		try {
			conn = SingletonHelper.getConnection("oracle");
			String str = "select * from emp where empno=?";
			pstmt = conn.prepareStatement(str);
			pstmt.setInt(1, empno);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				emp = new Emp();
				emp.setEmpno(rs.getInt("empno"));
				emp.setEname(rs.getString("ename"));
				emp.setJob(rs.getString("job"));
				emp.setMgr(rs.getInt("mgr"));
				emp.setHiredate(rs.getDate("hiredate"));
				emp.setSal(rs.getInt("sal"));
				emp.setComm(rs.getInt("comm"));
				emp.setDeptno(rs.getInt("deptno"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			SingletonHelper.close(pstmt);
			SingletonHelper.close(rs);
		}
		return emp;
	}
	
	//3. 삽입
	public int insertEmp(Emp emp) {
		int row =0;
		empSetting();
		try {
			conn = SingletonHelper.getConnection("oracle");
			String str = "insert into emp(empno, ename, job, mgr, hiredate, sal, comm, deptno) values(?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(str);
			pstmt.setInt(1, emp.getEmpno());
			pstmt.setString(2, emp.getEname());
			pstmt.setString(3, emp.getJob());
			pstmt.setInt(4, emp.getMgr());
			pstmt.setDate(5, (java.sql.Date) emp.getHiredate());
			pstmt.setInt(6, emp.getSal());
			pstmt.setInt(7, emp.getComm());
			pstmt.setInt(8, emp.getDeptno());
			row = pstmt.executeUpdate();
			
			if (row >0) {
				System.out.println("insert row : "  + row);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			SingletonHelper.close(pstmt);
			SingletonHelper.close(rs);
		}
		return row;
	}
	
	//4. 삭제
	public int deleteEmp(int empno) {
		int row = 0;
		empSetting();
		try {
			conn = SingletonHelper.getConnection("oracle");
			String str = "delete from emp where empno=?";
			pstmt = conn.prepareStatement(str);
			pstmt.setInt(1, empno);
			row = pstmt.executeUpdate();
			
			if (row >0) {
				System.out.println("delete row : "  + row);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			SingletonHelper.close(pstmt);
			SingletonHelper.close(rs);
		}
		return row;
	}
	
	//5. 수정
	public int updateEmp(Emp emp) {
		int row = 0;
		empSetting();
		try {
			conn = SingletonHelper.getConnection("oracle");
			String str = "update emp set ename=?, job=?, sal=?, hiredate=? where empno=?";
			pstmt = conn.prepareStatement(str);
			pstmt.setString(1, emp.getEname());
			pstmt.setString(2, emp.getJob());
			pstmt.setInt(3, emp.getSal());
			pstmt.setDate(4, (java.sql.Date) emp.getHiredate());
			pstmt.setInt(5, emp.getEmpno());
			row = pstmt.executeUpdate();
			
			if (row >0) {
				System.out.println("update row : "  + row);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			SingletonHelper.close(pstmt);
			SingletonHelper.close(rs);
		}
		return row;
	}
	
	
	//6. 이름검색 (ex. a가 들어간 이름 찾기
	public List<Emp> nameSearch(String st) {
		List<Emp> empList = new ArrayList<>();
		empSetting();
		try {
			conn = SingletonHelper.getConnection("oracle");
			String str = "select * from emp where ename like ?";
			pstmt = conn.prepareStatement(str);
			pstmt.setString(1, "%"+st+"%");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Emp emp = new Emp();
				emp.setEmpno(rs.getInt("empno"));
				emp.setEname(rs.getString("ename"));
				emp.setJob(rs.getString("job"));
				emp.setMgr(rs.getInt("mgr"));
				emp.setHiredate(rs.getDate("hiredate"));
				emp.setSal(rs.getInt("sal"));
				emp.setComm(rs.getInt("comm"));
				emp.setDeptno(rs.getInt("deptno"));
				empList.add(emp);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			SingletonHelper.close(pstmt);
			SingletonHelper.close(rs);
		}
		return empList;
	}
	
	
}
