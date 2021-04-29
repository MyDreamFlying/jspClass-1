package kr.or.ddit.member.service;

import static org.junit.Assert.assertNotNull;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import kr.or.ddit.vo.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:webapp/WEB-INF/spring/*-context.xml")
@WebAppConfiguration
public class IAuthenticateServiceTest {
	
	@Inject
	private IAuthenticateService service;

	@Test
	public void testAuthenticate() {
		MemberVO member = new MemberVO();
		member.setMem_id("b001");
		member.setMem_pass("java");
		
		assertNotNull(service.authenticate(member));
		
		
	}

}
