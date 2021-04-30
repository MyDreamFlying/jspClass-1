package kr.or.ddit.dao;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.vo.EmployeeVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:webapp/WEB-INF/spring/*-context.xml")
@WebAppConfiguration
public class EmpDaoTest {
	
	@Inject
	private EmpDao dao;
	@Inject
	private WebApplicationContext container;


	@Test
	public void testSelectEmp() {
		EmployeeVO vo = dao.selectEmp("kkobuk");
		System.out.println(vo);
		assertNotNull(vo);
	}

}
