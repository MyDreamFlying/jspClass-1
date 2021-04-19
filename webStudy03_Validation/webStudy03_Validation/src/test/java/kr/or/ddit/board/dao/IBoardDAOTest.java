package kr.or.ddit.board.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import kr.or.ddit.vo.BoardVO;

public class IBoardDAOTest {
	
	private IBoardDAO dao = BoardDAOImpl.getInstance();
	
	@Test
	public void testSelectBoard() {
		BoardVO search = new BoardVO();
		search.setBo_no(1425);
		BoardVO board = dao.selectBoard(search);
		
		assertEquals(3, board.getAttachList().size());
		
		search.setBo_no(1423);
		board = dao.selectBoard(search);
		
		assertEquals(0, board.getAttachList().size());
		
	}

}
