package kr.or.ddit.service;

import java.util.List;

import kr.or.ddit.dao.AlbaDAO;
import kr.or.ddit.dao.AlbaDAOImpl;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.AlbaVO;
import kr.or.ddit.vo.PagingVO;

public class AlbaServiceImpl implements AlbaService {
	private AlbaDAO dao = AlbaDAOImpl.getInstance();
	private static AlbaServiceImpl self;
	
	private AlbaServiceImpl() {}
	
	public static AlbaServiceImpl getInstance() {
		if(self == null) self = new AlbaServiceImpl();
		return self;
	}

	@Override
	public List<AlbaVO> retrieveAlbaList(PagingVO<AlbaVO> pagingVO) {
		return dao.selectAlbaList(pagingVO);
	}

	@Override
	public AlbaVO retrieveAlba(String al_id) {
		return dao.selectAlba(al_id);
	}

	@Override
	public ServiceResult insertAlba(AlbaVO alba) {
		int result = dao.insertAlba(alba);
		return result==1? ServiceResult.OK :ServiceResult.FAIL; 
	}

	@Override
	public ServiceResult updateAlba(AlbaVO alba) {
		int result = dao.updateAlba(alba);
		return result==1? ServiceResult.OK :ServiceResult.FAIL; 
	}

	@Override
	public int selectTotalRecord(PagingVO<AlbaVO> pagingVO) {
		return dao.selectTotalRecord(pagingVO);
	}

	@Override
	public ServiceResult deleteAlba(String al_id) {
		int result = dao.deleteAlba(al_id);
		return result==1? ServiceResult.OK :ServiceResult.FAIL; 
	}

}
