package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DTO.AttendanceLog;
import DTO.CommuteSatusLog;
import UTILS.SingletonHelper;

public class AttendanceLogDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public AttendanceLogDao() {
		conn= null;
		pstmt= null;
		rs= null;
	}
	
	//1. 근태기록 정보조회 (디테일한 부분 제외) 
	//select attendance_log_id, attendance_id, attendance_check_date, emp_id, dept_id from attendance_log
	public List<AttendanceLog> getAttendanceLogAllList() {
		List<AttendanceLog> attendanceLogList = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = SingletonHelper.getConnection("oracle");
			String sql = "select attendance_log_id, attendance_id, attendance_check_date, emp_id, dept_id from attendance_log";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				AttendanceLog attendanceLog = new AttendanceLog();
				attendanceLog.setAttendanceLogId(rs.getInt("attendance_log_id"));
				attendanceLog.setAttendanceId(rs.getString("attendance_id"));
				attendanceLog.setAttendanceCheckDate(rs.getTimestamp("attendance_check_date"));
				attendanceLog.setEmpId(rs.getInt("emp_id"));
				attendanceLog.setDeptId(rs.getString("dept_id"));
				attendanceLogList.add(attendanceLog);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			SingletonHelper.close(rs);
			SingletonHelper.close(pstmt);
		}
		return attendanceLogList;
	}
	
	//2. [[출퇴근신청 조회]] 1000a = 출근, 1000b = 퇴근, 1000c = 조퇴
	//select commute_id, attendance_id, attendance_check_date, emp_id, dept_id, attendance_status from commute_satus_log where attendance_id in (?,?,?)
	public List<CommuteSatusLog> getCommuteSatusLogSearch() {
		List<CommuteSatusLog> commuteSatusLogList = new ArrayList<>();
		conn = null;
		pstmt = null;
		rs=null;
		PreparedStatement pstmt2 = null;
		ResultSet rs2 = null;
		try {
			conn = SingletonHelper.getConnection("oracle");
			String sql = "select commute_id, attendance_id, attendance_status from commute_satus_log where attendance_id in (?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "1000a");
			pstmt.setString(2, "1000b");
			pstmt.setString(3, "1000c");
			rs = pstmt.executeQuery();
			
			String sql2 = "select attendance_log_id, attendance_id, attendance_check_date, emp_id, dept_id from attendance_log where attendance_id in (?,?,?)";
			pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setString(1, "1000a");
			pstmt2.setString(2, "1000b");
			pstmt2.setString(3, "1000c");
			rs2 = pstmt2.executeQuery();
			
			while (rs.next() && rs2.next()) {
				CommuteSatusLog commuteSatusLog = new CommuteSatusLog();
				commuteSatusLog.setCommuteId(rs.getInt("commute_id"));
				commuteSatusLog.setAttendanceId(rs.getString("attendance_id"));
				commuteSatusLog.setAttendanceCheckDate(rs2.getDate("attendance_check_date"));
				commuteSatusLog.setEmpId(rs2.getInt("emp_id"));
				commuteSatusLog.setDeptId(rs2.getString("dept_id"));
				commuteSatusLog.setAttendanceStatus(rs.getString("attendance_status"));
				commuteSatusLogList.add(commuteSatusLog);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			SingletonHelper.close(rs);
			SingletonHelper.close(pstmt);
		}
		return commuteSatusLogList;
	}
	
	
	//3. 근태기록 데이터 삽입
	//insert ALL into attendance_log(attendance_log_id, attendance_id, attendance_check_date, emp_id, dept_id) values(TEST1_SEQ.nextval,?,?,?,?) into commute_satus_log(commute_id, attendance_id, attendance_status) values(TEST1_SEQ.currval,?,?)
	public int dataInsert(AttendanceLog attendanceLog, CommuteSatusLog commuteSatusLog) {
		conn = null;
		pstmt = null;
		int row = 0;
		try {
			conn = SingletonHelper.getConnection("oracle");
			String sql = "insert all into attendance_log(attendance_log_id, attendance_id, attendance_check_date, emp_id, dept_id) values(TEST1_SEQ.nextval,?,?,?,?) into commute_satus_log(commute_id, attendance_id, attendance_status) values(TEST1_SEQ.currval,?,?) select * from dual";
			pstmt = conn.prepareStatement(sql);
			// attendanceLog 값 넣기
			pstmt.setString(1, attendanceLog.getAttendanceId());
			pstmt.setTimestamp(2, attendanceLog.getAttendanceCheckDate());
			pstmt.setInt(3, attendanceLog.getEmpId());
			pstmt.setString(4, attendanceLog.getDeptId());
	
			// commuteSatusLog 값 넣기 
			pstmt.setString(5, commuteSatusLog.getAttendanceId());
			pstmt.setString(6, commuteSatusLog.getAttendanceStatus());
			row = pstmt.executeUpdate();
			if (row > 0) {
				System.out.println("근태기록 데이터가 insert 완료되었습니다.");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			SingletonHelper.close(pstmt);
		}
		return row;
	}

}
