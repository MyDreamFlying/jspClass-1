package kr.or.ddit.board.dao;

import java.util.List;

import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

/**
 *	게시글 관리를 위한 persistence layer
 */
public interface IBoardDAO {
	public int insertBoard(BoardVO board);
	public int selectBoardCount(PagingVO<BoardVO> pagingVO);
	public List<BoardVO> selectBoardList(PagingVO<BoardVO> pagingVO);
	public BoardVO selectBoard(BoardVO search);
	public int updateBoard(BoardVO board);
	public int deleteBoard(BoardVO search);
}
