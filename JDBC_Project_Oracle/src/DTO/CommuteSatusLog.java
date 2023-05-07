package DTO;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class CommuteSatusLog {
	private int commuteId;
	@NonNull
	private String attendanceId;
	private Date attendanceCheckDate;
	private int empId;
	private String deptId;
	@NonNull
	private String attendanceStatus;
}
