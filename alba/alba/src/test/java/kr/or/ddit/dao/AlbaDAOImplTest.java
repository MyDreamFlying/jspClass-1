package kr.or.ddit.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import kr.or.ddit.vo.AlbaVO;

public class AlbaDAOImplTest {
	private AlbaDAO dao = AlbaDAOImpl.getInstance();
	
	@Test
	public void testSelectAlbaList() {
		List<AlbaVO> list = dao.selectAlbaList(null);
		assertNotNull(list);
		assertEquals(2, list.size());
		for(AlbaVO alba : list) {
			System.out.println(alba);
		}
	}

}
