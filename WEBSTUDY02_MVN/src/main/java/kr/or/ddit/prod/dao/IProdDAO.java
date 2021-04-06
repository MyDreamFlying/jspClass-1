package kr.or.ddit.prod.dao;

import java.util.List;

import kr.or.ddit.vo.ProdVO;

/**
 *	상품 관리를 위한 Persistence Layer
 */
public interface IProdDAO {
	public ProdVO selectProd(String prod_id);
	public List<ProdVO> selectProdList();
	/**
	 * @param prod
	 * @return inserted row count
	 */
	public int insert(ProdVO prod);
	/**
	 * @param prod
	 * @return updated row count
	 */
	public int updateProd(ProdVO prod);
}
