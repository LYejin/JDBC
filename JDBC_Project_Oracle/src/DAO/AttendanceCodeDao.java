package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DTO.AttendanceCode;
import UTILS.SingletonHelper;

public class AttendanceCodeDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public AttendanceCodeDao() {
		conn= null;
		pstmt= null;
		rs= null;
	}
	
	//1. 근태분류 정보 전체조회
	//select attendance_id, attendance_name from attendance_code
	public List<AttendanceCode> getAttendanceCodeAllList() {
		List<AttendanceCode> attendanceCodeList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = SingletonHelper.getConnection("oracle");
			String sql = "select attendance_id, attendance_name from attendance_code";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				AttendanceCode attendanceCode = new AttendanceCode();
				attendanceCode.setAttendanceId(rs.getString("attendance_id"));
				attendanceCode.setAttendanceName(rs.getString("attendance_name"));
				attendanceCodeList.add(attendanceCode);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			SingletonHelper.close(rs);
			SingletonHelper.close(pstmt);
		}
		return attendanceCodeList;
	}
	
	//2. 근태분류 코드를 통한 근태분류 조회 
	//select attendance_name from attendance_code where attendance_id=?
	public AttendanceCode getAttendanceCodeSearch(int num) {
		AttendanceCode attendanceCode = null;
		conn = null;
		pstmt = null;
		rs=null;
		try {
			conn = SingletonHelper.getConnection("oracle");
			String sql = "select attendance_name from attendance_code where attendance_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				attendanceCode = new AttendanceCode();
				attendanceCode.setAttendanceName(rs.getString("attendance_name"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			SingletonHelper.close(rs);
			SingletonHelper.close(pstmt);
		}
		return attendanceCode;
	}
	
	//3. 근태분류 데이터 삽입
	//insert into attendance_code(attendance_id, attendance_name) values(?,?)
	public int dataInsert(AttendanceCode emp) {
		conn = null;
		pstmt = null;
		int row = 0;
		try {
			conn = SingletonHelper.getConnection("oracle");
			String sql = "insert into attendance_code(attendance_id, attendance_name) values(?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, emp.getAttendanceId());
			pstmt.setString(2, emp.getAttendanceName());
			row = pstmt.executeUpdate();
			if (row > 0) {
				System.out.println("근태분류 insert가 완료되었습니다.");
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
	public int dataUpdate(AttendanceCode attendance_code) {
		conn = null;
		pstmt = null;
		int row = 0;
		try {
			conn = SingletonHelper.getConnection("oracle");
			String sql = "update attendance_code set attendance_name=? where attendance_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, attendance_code.getAttendanceId());
			pstmt.setString(2, attendance_code.getAttendanceName());
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
	
	
	//5. 근태분류 데이터 삭제
	//delete from attendance_code where attendance_id=?
	public int dataDelete(int attendance_id) {
		conn = null;
		pstmt = null;
		int row = 0;
		try {
			conn = SingletonHelper.getConnection("oracle");
			String sql = "delete from attendance_code where attendance_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, attendance_id);
			row = pstmt.executeUpdate();
			if (row > 0) {
				System.out.println("해당 근태분류 데이터가 삭제되었습니다.");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			SingletonHelper.close(pstmt);
		}
		return row;
	}
}
