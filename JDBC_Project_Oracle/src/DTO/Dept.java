package DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dept {
	private String deptId;
	private String deptName;
	private String deptLoc;
	private int deptMgrId;
}
