package kr.or.ddit.service;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import kr.or.ddit.enumpkg.ServiceResult;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:webapp/WEB-INF/spring/*-context.xml")
@WebAppConfiguration
public class EmpServiceTest {
	
	@Inject
	EmpService service;


	@Test
	public void test() {
		EmployeeVO emp = new EmployeeVO();
		emp.setEmployee_id("kkobuk");
		emp.setEmployee_pwd("1234");
		
		ServiceResult result = service.authenticate(emp);
		
		assertEquals(ServiceResult.OK, result);
		
	}

}
