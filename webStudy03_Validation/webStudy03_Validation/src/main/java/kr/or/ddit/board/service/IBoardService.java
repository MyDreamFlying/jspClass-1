package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.AttachVO;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

public interface IBoardService {
	public ServiceResult createBoard(BoardVO board);
	public int retrieveBoardCount(PagingVO<BoardVO> pagingVO);
	public List<BoardVO> retrieveBoardList(PagingVO<BoardVO> pagingVO);
	/**
	 * @param search
	 * @return 존재하지 않는다면 custom exception
	 */
	public BoardVO retrieveBoard(BoardVO search);
	public ServiceResult modifyBoard(BoardVO board);
	public ServiceResult removeBoard(BoardVO search);
	
	public AttachVO download(int att_no);
	
}
