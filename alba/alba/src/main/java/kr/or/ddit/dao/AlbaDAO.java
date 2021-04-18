package kr.or.ddit.dao;

import java.util.List;

import kr.or.ddit.vo.AlbaVO;
import kr.or.ddit.vo.PagingVO;

public interface AlbaDAO {
	/**
	 * @param pagingVO
	 * @return selected albaList
	 */
	public List<AlbaVO> selectAlbaList(PagingVO<AlbaVO> pagingVO);
	/**
	 * @param al_id
	 * @return selected AlbaVO
	 */
	public AlbaVO selectAlba(String al_id);
	/**
	 * @param alba
	 * @return inserted row count
	 */
	public int insertAlba(AlbaVO alba);
	/**
	 * @param alba
	 * @return inserted row count
	 */
	public int updateAlba(AlbaVO alba);
	/**
	 * @param al_id
	 * @return deleted row count
	 */
	public int deleteAlba(String al_id);
	/**
	 * @param pagingVO
	 * @return total record count
	 */
	public int selectTotalRecord(PagingVO<AlbaVO> pagingVO);
}
