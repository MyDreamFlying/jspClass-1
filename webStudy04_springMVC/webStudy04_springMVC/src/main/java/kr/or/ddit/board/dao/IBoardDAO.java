package kr.or.ddit.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

/**
 *	게시글 관리를 위한 persistence layer
 */
@Repository
public interface IBoardDAO {
	public int insertBoard(BoardVO board);
	public int selectBoardCount(PagingVO<BoardVO> pagingVO);
	public List<BoardVO> selectBoardList(PagingVO<BoardVO> pagingVO);
	public BoardVO selectBoard(BoardVO search);
	public int updateBoard(BoardVO board);
	public int deleteBoard(BoardVO search);
	public int selectReplyCount(BoardVO search);
	
	/**
	 * 추천수 증가
	 * @param bo_no
	 * @return
	 */
	public int recommend(int bo_no);
	public int report(int bo_no);
	public int hit(int bo_no);
}
