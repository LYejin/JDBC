package DTO;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class AttendanceLog {
	private int attendanceLogId;
	@NonNull
	private String attendanceId;
	@NonNull
	private Timestamp attendanceCheckDate;
	@NonNull
	private int empId;
	@NonNull
	private String deptId;
}
