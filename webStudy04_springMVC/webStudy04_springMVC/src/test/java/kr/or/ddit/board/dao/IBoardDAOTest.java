package kr.or.ddit.board.dao;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.vo.BoardVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:webapp/WEB-INF/spring/*-context.xml")
@WebAppConfiguration
public class IBoardDAOTest {

	@Inject
	private IBoardDAO dao;
	@Inject
	private WebApplicationContext container;
		
	@Test
	public void testSelectBoard() {
		BoardVO search = new BoardVO();
		search.setBo_no(1448);
		BoardVO board = dao.selectBoard(search);
		assertEquals(1, board.getAttachList().size());
	}

	

}
