package kr.or.ddit.prod.service;

import kr.or.ddit.member.ProdNotFoundException;
import kr.or.ddit.prod.dao.IProdDAO;
import kr.or.ddit.prod.dao.ProdDAOImpl;
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

}
