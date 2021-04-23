package kr.or.ddit.board.dao;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class AttachDAOImplTest {
	IAttachDAO dao = AttachDAOImpl.getInstance();

	@Test
	public void testSelectAttaches() {
		System.out.println(dao.selectAttaches(0));
		assertNotNull(dao.selectAttaches(0));
	}

}
