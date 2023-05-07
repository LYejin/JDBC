package DAO;

import java.sql.Connection;
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
		conn= null;
		pstmt= null;
		rs= null;
	}
	
	//1. 사원 정보 전체조회
	//select emp_id, emp_name, emp_job, emp_sal, dept_id, emp_mgr_id from emp
	public List<Emp> getDeptAllList() {
		//여러건의 데이터 (Dept 객체 여러개)
		List<Emp> empList = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = SingletonHelper.getConnection("oracle");
			String sql = "select emp_id, emp_name, emp_job, emp_sal, dept_id, emp_mgr_id from emp";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Emp emp = new Emp();
				emp.setEmpId(rs.getInt("emp_id"));
				emp.setEmpName(rs.getString("emp_name"));
				emp.setEmpJob(rs.getString("emp_job"));
				emp.setEmpSal(rs.getInt("emp_sal"));
				emp.setDeptId(rs.getString("dept_id"));
				emp.setEmpMgrId(rs.getInt("emp_mgr_id"));
				empList.add(emp);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			SingletonHelper.close(rs);
			SingletonHelper.close(pstmt);
		}
		return empList;
	}
	
	//2. 사원 id를 통한 사원조회 
	//select emp_name, emp_job, emp_sal, dept_id, emp_mgr_id from emp where emp_id=?
	public Emp getDeptSearch(int num) {
		Emp emp = null;
		conn = null;
		pstmt = null;
		rs=null;
		try {
			conn = SingletonHelper.getConnection("oracle");
			String sql = "select emp_name, emp_job, emp_sal, dept_id, emp_mgr_id from emp where emp_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				emp = new Emp();
				emp.setEmpName(rs.getString("emp_name"));
				emp.setEmpJob(rs.getString("emp_job"));
				emp.setEmpSal(rs.getInt("emp_sal"));
				emp.setDeptId(rs.getString("dept_id"));
				emp.setEmpMgrId(rs.getInt("emp_mgr_id"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			SingletonHelper.close(rs);
			SingletonHelper.close(pstmt);
		}
		return emp;
	}
	
	
	//3. 사원 데이터 삽입
	//insert into dept(emp_id,emp_name,emp_job,emp_sal,dept_id,emp_mgr_id) values(?,?,?,?,?,?)
	public int dataInsert(Emp emp) {
		conn = null;
		pstmt = null;
		int row = 0;
		try {
			conn = SingletonHelper.getConnection("oracle");
			String sql = "insert into emp(emp_id,emp_name,emp_job,emp_sal,dept_id,emp_mgr_id) values(?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, emp.getEmpId());
			pstmt.setString(2, emp.getEmpName());
			pstmt.setString(3, emp.getEmpJob());
			pstmt.setInt(4, emp.getEmpSal());
			pstmt.setString(5, emp.getDeptId());
			pstmt.setInt(6, emp.getEmpMgrId());
			row = pstmt.executeUpdate();
			if (row > 0) {
				System.out.println("사원 insert가 완료되었습니다.");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			SingletonHelper.close(pstmt);
		}
		return row;
	}
	
	public int dataInsert(Emp emp, int num) {
		conn = null;
		pstmt = null;
		int row = 0;
		try {
			conn = SingletonHelper.getConnection("oracle");
			String sql = "insert into emp(emp_id,emp_name,emp_job,emp_sal,dept_id) values(?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, emp.getEmpId());
			pstmt.setString(2, emp.getEmpName());
			pstmt.setString(3, emp.getEmpJob());
			pstmt.setInt(4, emp.getEmpSal());
			pstmt.setString(5, emp.getDeptId());
			row = pstmt.executeUpdate();
			if (row > 0) {
				System.out.println("사원 insert가 완료되었습니다.");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			SingletonHelper.close(pstmt);
		}
		return row;
	}
	
	
	//4. 부서 데이터 전체 수정
	//update emp set emp_name=?,emp_job=?,emp_sal=?,dept_id=?,emp_mgr_id=? where emp_id=?
	public int dataUpdate(Emp emp) {
		conn = null;
		pstmt = null;
		int row = 0;
		try {
			conn = SingletonHelper.getConnection("oracle");
			String sql = "update emp set emp_name=?,emp_job=?,emp_sal=?,dept_id=?,emp_mgr_id=? where emp_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, emp.getEmpName());
			pstmt.setString(2, emp.getEmpJob());
			pstmt.setInt(3, emp.getEmpSal());
			pstmt.setString(4, emp.getDeptId());
			pstmt.setInt(5, emp.getEmpMgrId());
			row = pstmt.executeUpdate();
			if (row > 0) {
				System.out.println("사원 update 완료되었습니다.");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			SingletonHelper.close(pstmt);
		}
		return row;
	}
	
	
	//5. 데이터 삭제
	//delete from emp where emp_id=?
	public int dataDelete(int emp_id) {
		conn = null;
		pstmt = null;
		int row = 0;
		try {
			conn = SingletonHelper.getConnection("oracle");
			String sql = "delete from emp where emp_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, emp_id);
			row = pstmt.executeUpdate();
			if (row > 0) {
				System.out.println("해당 사원이 삭제되었습니다.");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			SingletonHelper.close(pstmt);
		}
		return row;
	}
}
