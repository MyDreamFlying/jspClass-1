package kr.or.ddit.prod.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

/**
 *	상품관리를 위한 Business Logic Layer
 */
public interface IProdService {
	/**
	 * 상품 상세 조회
	 * @param prod_id
	 * @return 해당 상품이 존재하지 않는 경우, RuntimeException 발생
	 */
	public ProdVO retrieveProd(String prod_id);
	public List<ProdVO> retrieveProdList(PagingVO pagingVO);
	public ServiceResult createProd(ProdVO prod);
	public ServiceResult modifyProd(ProdVO prod);
	/**
	 * 페이징 처리를 위한 상품수 조회
	 * @param pagingVO TODO
	 * @return
	 */
	public int retrieveProdCount(PagingVO<ProdVO> pagingVO);
}
