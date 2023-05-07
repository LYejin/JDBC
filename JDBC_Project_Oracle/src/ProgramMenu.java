import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import DAO.AttendanceLogDao;
import DAO.DeptDao;
import DAO.EmpDao;
import DTO.AttendanceLog;
import DTO.CommuteSatusLog;
import DTO.Dept;
import DTO.Emp;

public class ProgramMenu {
	Scanner sc = new Scanner(System.in);
	int empId; // 사번 
	String empName; // 사원명 
	String deptId; // 부서명 
	
	public boolean loginMenu() {
		System.out.println("--------------------------");
		System.out.println("------------로그인----------");
		System.out.println("--------------------------");
		System.out.print("사번을 입력해주세요 : "); // 사번, 부서
		empId = Integer.parseInt(sc.nextLine());
		System.out.print("이름을 입력해주세요 : ");
		empName = sc.nextLine();
		
		EmpDao empDao = new EmpDao();
		Emp emp = empDao.getDeptSearch(empId);
		if (emp.getEmpName().equals(empName)) {
			deptId=emp.getDeptId();
			System.out.println("로그인 되었습니다.");
			return true;
		} else {
			System.out.println("로그인에 실패하셨습니다.");
			return false;
		}
	}
	
	// 처음 전체 메뉴
	public void totalMenu() throws Exception {
		int num = -1;
		if (loginMenu()) {
			while(num != 5) {
				System.out.println("--------------------------");
				System.out.println("----------전체메뉴----------");
				System.out.println("--------------------------");
				System.out.println("1.사원 2.부서 3.근태분류 4.근태관리 5.프로그램종료");
				System.out.print("입력 번호: ");
				int temp = Integer.parseInt(sc.nextLine());
				switch (temp) {
				case 1 : 
					break;
				case 2 :
					DeptMenu();
					break;
				case 3 :
					break;
				case 4 :
					attendanceMenu();
					break;
				case 5 :
					System.exit(0);
					break;
				default :
					System.out.println("존재하는 메뉴를 선택해주세요.");
			}
			}
		}
	}
	
	//부서 메뉴
	public void DeptMenu() throws Exception {
		DeptDao deptDao = new DeptDao();
		int num = -1;
		while(num != 4) {
			System.out.println("--------------------------");
			System.out.println("----------부서메뉴----------");
			System.out.println("--------------------------");
			System.out.println("1.부서등록 2.부서전체조회 3.부서조건조회 4.부서정보수정 5.부서삭제");
			System.out.print("입력 번호: ");
			int temp = Integer.parseInt(sc.nextLine());
			switch (temp) {
			case 1 : 
				Dept dept1 = new Dept();
				System.out.println("\n 부서등록을 진행합니다");
				System.out.print("부서ID를 입력하세요 : ");
				dept1.setDeptId(sc.nextLine());
				System.out.print("부서명을 입력하세요 : ");
				dept1.setDeptName(sc.nextLine());
				System.out.print("부서지역을 입력하세요 : ");
				dept1.setDeptLoc(sc.nextLine());
				System.out.print("부서관리자ID 입력하세요 : ");
				dept1.setDeptMgrId(Integer.parseInt(sc.nextLine()));
				deptDao.dataInsert(dept1);
				System.out.println();
				break;
			case 2 :
				System.out.println("\n 부서전체기록을 읽습니다");
				List<Dept> deptDaoList = deptDao.getDeptAllList();
				for (Dept dept : deptDaoList) {
					System.out.println(dept);
				}
				System.out.println();
				break;
			case 3 :
				System.out.println("\n 부서조건조회 합니다");
				System.out.print("조회를 원하시는 부서의 ID를 입력하세요 : ");
				String deptId = sc.nextLine();
				System.out.println(deptDao.getDeptSearch(deptId));
				System.out.println();
				break;
			case 4 :
				System.out.println("\n 부서정보수정 합니다");
				System.out.print("수정을 원하시는 부서의 ID를 입력하세요 : ");
				System.out.println();
				break;
			case 5 :
				
				break;
			default :
				System.out.println("존재하는 메뉴를 선택해주세요.");
			}
		}
	}
	
	// 근태관리
	public void attendanceMenu() {
		AttendanceLogDao attDao = new AttendanceLogDao();
		int num = -1;
			while(num != 4) {
				System.out.println("--------------------------");
				System.out.println("----------근태관리----------");
				System.out.println("--------------------------");
				System.out.println("1.근태전체기록 읽기 2.출퇴근조퇴기록 읽기 3.출퇴근조퇴 신청 4.메뉴");
				System.out.print("입력 번호: ");
				int temp = Integer.parseInt(sc.nextLine());
				switch (temp) {
				case 1 : 
					System.out.println("\n 근태전체기록을 읽습니다");
					List<AttendanceLog> attLogList = attDao.getAttendanceLogAllList();
					for (AttendanceLog attendanceLog : attLogList) {
						System.out.println(attendanceLog);
					}
					System.out.println();
					break;
				case 2 :
					System.out.println("\n 출퇴근-조퇴기록을 읽습니다");
					List<CommuteSatusLog> attLog = attDao.getCommuteSatusLogSearch();
					for (CommuteSatusLog commuteSatusLog : attLog) {
						System.out.println(commuteSatusLog);
					}
					System.out.println();
					
					break;
				case 3 :
					commuteMenu();
					break;
				case 4 :
					break;
				default :
					System.out.println("존재하는 메뉴를 선택해주세요.");
				}
			}
	}
	
	// 출퇴근 신청
	public void commuteMenu() {
		AttendanceLogDao attLogDao = new AttendanceLogDao();
		int temp = -1;
		while(temp != 4) {
			System.out.println("--------------------------");
			System.out.println("--------출퇴근_조퇴메뉴-------");
			System.out.println("--------------------------");
			System.out.println("1.출근 2.퇴근 3.조퇴 (주의: 출퇴근조퇴는 한번만 사용하세요.)");
			System.out.print("입력 번호 : ");
			temp = Integer.parseInt(sc.nextLine());
			String state = ""; // 출근, 퇴근
			String attendanceCode = "";
			switch (temp) {
				case 1 : 
					state="출근";
					attendanceCode="1000a";
					break;
				case 2 :
					state="퇴근";
					attendanceCode="1000b";
					break;
				case 3 :
					state="조퇴";
					attendanceCode="1000c";
					break;
				default :
					System.out.println("존재하는 메뉴를 선택해주세요.");
		}
			long nowTime = System.currentTimeMillis();
			Timestamp timestamp = new Timestamp(nowTime);
			AttendanceLog attLog = new AttendanceLog(attendanceCode, timestamp, empId, deptId); // 근태코드, 시간, 사번, 부서
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			java.sql.Timestamp startTime = java.sql.Timestamp.valueOf( sdf.format(timestamp) + " 09:00:00.000");
			java.sql.Timestamp endTime = java.sql.Timestamp.valueOf(sdf.format(timestamp) + " 18:00:00.000");
			
			if (state.equals("출근")) {
				if (timestamp.before(startTime)) { // 기준 9시
					state="정상출근";
				} else {
					state="지각";
				}
			}	else if (state.equals("퇴근")) {
				if (timestamp.after(endTime)) {	// 기준 6시
					state="정상퇴근";
				} else {
					state="조퇴";
				}
			} else if (state.equals("조퇴")) {
				state="조퇴";
			} else {
				state = "결근";
			}
			
			CommuteSatusLog commuteSatusLog = new CommuteSatusLog(attendanceCode, state);
			attLogDao.dataInsert(attLog, commuteSatusLog);
		}
	}
}
