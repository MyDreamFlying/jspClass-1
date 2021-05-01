package kr.or.ddit.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import kr.or.ddit.dao.EmpDao;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.utils.CryptoUtil;
import kr.or.ddit.vo.EmployeeVO;


@Service
public class EmpServiceImpl implements EmpService {
	private static final Logger logger = LoggerFactory.getLogger(EmpServiceImpl.class);
	
	@Inject
	private EmpDao dao;

	@Override
	public ServiceResult authenticate(EmployeeVO emp) {
		EmployeeVO savedEmp = dao.selectEmp(emp.getEmployee_id());
		ServiceResult result = null;
		if(savedEmp != null) {
			String inputPass = emp.getEmployee_pwd();
			try {
				String encodedPass = inputPass; // do not encrypt as column size is too small
				String savedPass = savedEmp.getEmployee_pwd();
				if(savedPass.equals(encodedPass)) {
					try {
						BeanUtils.copyProperties(emp, savedEmp);
					} catch (IllegalAccessException | InvocationTargetException e) {
						e.printStackTrace();
					}
					result = ServiceResult.OK;
				}else {
					result = ServiceResult.INVALIDPASSWORD;
				}
			}catch (Exception e) {
				logger.error("", e);
				result = ServiceResult.FAIL;
			}
		}else {
			result = ServiceResult.NOTEXIST;
		}
		return result;
	}

	@Override
	public List<EmployeeVO> selectEmpList() {
		return dao.selectEmpList();
	}

	@Override
	public EmployeeVO selectEmp(String employee_id) {
		return dao.selectEmp(employee_id);
	}
	
	

}
