package kr.or.ddit.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.EmployeeVO;

@Repository
public interface EmpDao {
	public EmployeeVO selectEmp(String employee_id);
	public List<EmployeeVO> selectEmpList();
}
