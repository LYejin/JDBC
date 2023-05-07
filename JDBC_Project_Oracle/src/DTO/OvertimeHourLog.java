package DTO;

import lombok.Data;

@Data
public class OvertimeHourLog {
	private int overtimeID;
	private String attendanceId;
	private String overtimeReason;
	private int overtimeHour;
}
