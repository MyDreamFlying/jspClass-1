package kr.or.ddit.board.dao;

import kr.or.ddit.vo.AttachVO;
import kr.or.ddit.vo.BoardVO;

/**
 *	첨부파일 관리를 위한 persistence layer
 */
public interface IAttachDAO {
	public int insertAttaches(BoardVO board);
	public AttachVO selectAttaches(int att_no);
	public int deleteAttaches(BoardVO board);
}
