package kr.or.ddit.prod.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.CustomException;
import kr.or.ddit.prod.ProdNotFoundException;
import kr.or.ddit.prod.dao.IProdDAO;
import kr.or.ddit.prod.dao.ProdDAOImpl;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

public class ProdServiceImpl implements IProdService {
	private IProdDAO dao = ProdDAOImpl.getInstance();
	private static ProdServiceImpl self;
	
	private ProdServiceImpl() {}
	public static ProdServiceImpl getInstance() {
		if(self == null) self = new ProdServiceImpl();
		return self;
	}

	@Override
	public ProdVO retrieveProd(String prod_id) {
		ProdVO prod = dao.selectProd(prod_id);
		if(prod == null) {
			throw new ProdNotFoundException("제품 번호에 해당하는 제품이 존재하지 않음.");
		}
		return prod;
	}
	@Override
	public List<ProdVO> retrieveProdList(PagingVO pagingVO) {
		List<ProdVO> list = dao.selectProdList(pagingVO);
		return list;
	}
	@Override
	public ServiceResult createProd(ProdVO prod) {
		int result = dao.insert(prod);
		if(result == 1) {
			return ServiceResult.OK;
		}else {
			return ServiceResult.FAIL;
		}
	}
	@Override
	public ServiceResult modifyProd(ProdVO prod) {
		try {
			int result = dao.updateProd(prod);
			if(result == 1) {
				return ServiceResult.OK;
			}else {
				return ServiceResult.FAIL;
			}
		}catch (Exception e) {
			throw new CustomException(e);
		}
	}
	@Override
	public int retrieveProdCount() {
		return dao.selectTotalRecord();
	}

}
