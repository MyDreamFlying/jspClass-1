package kr.or.ddit.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.AlbaVO;
import kr.or.ddit.vo.PagingVO;

public interface AlbaService {
	/**
	 * @param pagingVO
	 * @return selected albaList
	 */
	public List<AlbaVO> retrieveAlbaList(PagingVO<AlbaVO> pagingVO);
	/**
	 * @param al_id
	 * @return selected AlbaVO
	 */
	public AlbaVO retrieveAlba(String al_id);
	/**
	 * @param alba
	 * @return OK, FAIL, PKDUPLICATED
	 */
	public ServiceResult insertAlba(AlbaVO alba);
	/**
	 * @param alba
	 * @return OK, FAIL, NOTEXIST
	 */
	public ServiceResult updateAlba(AlbaVO alba);
	public int selectTotalRecord(PagingVO<AlbaVO> pagingVO);
}
