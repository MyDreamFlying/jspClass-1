package kr.or.ddit.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.EmployeeVO;

public interface EmpService {
	
	public ServiceResult authenticate(EmployeeVO emp);
	
	public List<EmployeeVO> selectEmpList();
	
	public EmployeeVO selectEmp(String employee_id);

}
