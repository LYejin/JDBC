package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DTO.Dept;
import UTILS.SingletonHelper;

public class DeptDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public DeptDao() {
		conn= null;
		pstmt= null;
		rs= null;
	}
	
	//2. 부서 정보 전체조회
	//select dept_id, dept_name, dept_loc, dept_mgr_id from dept
	public List<Dept> getDeptAllList() {
		//여러건의 데이터 (Dept 객체 여러개)
		List<Dept> deptlist = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = SingletonHelper.getConnection("oracle");
			String sql = "select dept_id, dept_name, dept_loc, dept_mgr_id from dept";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Dept dept = new Dept();
				dept.setDeptId(rs.getString("dept_id"));
				dept.setDeptName(rs.getString("dept_name"));
				dept.setDeptLoc(rs.getString("dept_loc"));
				dept.setDeptMgrId(rs.getInt("dept_mgr_id"));
				deptlist.add(dept);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			SingletonHelper.close(rs);
			SingletonHelper.close(pstmt);
		}
		return deptlist;
	}
	
	//2. 부서 id를 통한 부서조건조회 
	//select dept_id, dept_name, dept_loc, dept_mgr_id from dept where dept_id=?
	public Dept getDeptSearch(String str) {
		Dept dept = null;
		conn = null;
		pstmt = null;
		rs=null;
		try {
			conn = SingletonHelper.getConnection("oracle");
			String sql = "select dept_id, dept_name, dept_loc, dept_mgr_id from dept where dept_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, str);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				dept = new Dept();
				dept.setDeptId(rs.getString("dept_id"));
				dept.setDeptName(rs.getString("dept_name"));
				dept.setDeptLoc(rs.getString("dept_loc"));
				dept.setDeptMgrId(rs.getInt("dept_mgr_id"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			SingletonHelper.close(rs);
			SingletonHelper.close(pstmt);
		}
		return dept;
	}
	
	
	//3. 부서 데이터 삽입
	//insert into dept(dept_id,dept_name,dept_loc,dept_mgr_id) values(?,?,?,?)
	public int dataInsert(Dept dept) throws Exception {
		conn = null;
		pstmt = null;
		int row = 0;
		try {
			conn = SingletonHelper.getConnection("oracle");
			String sql = "insert into dept(dept_id,dept_name,dept_loc,dept_mgr_id) values(?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dept.getDeptId());
			pstmt.setString(2, dept.getDeptName());
			pstmt.setString(3, dept.getDeptLoc());
			pstmt.setInt(4, dept.getDeptMgrId());
			row = pstmt.executeUpdate();
			if (row > 0) {
				System.out.println("부서 insert가 완료되었습니다.");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			SingletonHelper.close(pstmt);
		}
		return row;
	}
	
	
	//4. 부서 데이터 전체 수정
	//update dept set dept_name=?, dept_loc=?, dept_mgr_id=? where dept_id=?
	public int dataUpdate(Dept dept) {
		conn = null;
		pstmt = null;
		int row = 0;
		try {
			conn = SingletonHelper.getConnection("oracle");
			String sql = "update dept set dept_name=?, dept_loc=?, dept_mgr_id=? where dept_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dept.getDeptName());
			pstmt.setString(2, dept.getDeptLoc());
			pstmt.setInt(3, dept.getDeptMgrId());
			pstmt.setString(4, dept.getDeptId());
			row = pstmt.executeUpdate();
			if (row > 0) {
				System.out.println("부서 update 완료되었습니다.");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			SingletonHelper.close(pstmt);
		}
		return row;
	}
	
	
	//5. 데이터 삭제
	//delete from dept where dept_id=?
	public int dataDelete(int dept_id) {
		conn = null;
		pstmt = null;
		int row = 0;
		try {
			conn = SingletonHelper.getConnection("oracle");
			String sql = "delete from dept where dept_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dept_id);
			row = pstmt.executeUpdate();
			if (row > 0) {
				System.out.println("해당 부서가 삭제되었습니다.");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			SingletonHelper.close(pstmt);
		}
		return row;
	}
}
