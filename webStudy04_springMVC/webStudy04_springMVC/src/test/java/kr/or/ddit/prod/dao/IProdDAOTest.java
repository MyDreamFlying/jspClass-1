package kr.or.ddit.prod.dao;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.TestWebAppConfiguration;
import kr.or.ddit.board.dao.IBoardDAO;
import kr.or.ddit.vo.ProdVO;

@RunWith(SpringJUnit4ClassRunner.class)
@TestWebAppConfiguration
public class IProdDAOTest {

	@Inject
	private IProdDAO dao;
	@Inject
	private WebApplicationContext container;
	
	@Test
	public void testSelectProd() {
		ProdVO prod = dao.selectProd("");
	}

}
