package kr.or.ddit.board.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.junit.Test;

import kr.or.ddit.vo.BoardVO;

public class BoardDAOImplTest {
	IBoardDAO dao = BoardDAOImpl.getInstance();

	@Test
	public void testSelectReplyCount() {
		BoardVO board = new BoardVO();
		board.setBo_no(1438);
		assertEquals(1, dao.selectReplyCount(board));
	}

}
