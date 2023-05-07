package DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Emp {
	@NonNull
	private int empId;
	@NonNull
	private String empName;
	@NonNull
	private String empJob;
	@NonNull
	private int empSal;
	@NonNull
	private String deptId;
	private int EmpMgrId;
}
