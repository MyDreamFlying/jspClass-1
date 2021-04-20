package kr.or.ddit.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.vo.AttachVO;
import kr.or.ddit.vo.BoardVO;

/**
 *	첨부파일 관리를 위한 persistence layer
 */
public interface IAttachDAO {
	public int insertAttaches(BoardVO board, SqlSession session);
	public AttachVO selectAttaches(int att_no);
	public List<String> selectSaveNamesForDelete(BoardVO board);
	public int deleteAttaches(BoardVO board, SqlSession session);
}
