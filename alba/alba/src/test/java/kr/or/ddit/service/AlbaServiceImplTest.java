package kr.or.ddit.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import kr.or.ddit.vo.AlbaVO;

public class AlbaServiceImplTest {
	AlbaService service = AlbaServiceImpl.getInstance();
	@Test
	public void testRetrieveAlbaList() {
		List<AlbaVO> list = service.retrieveAlbaList(null);
		assertEquals(list.size(),2);
	}

}
