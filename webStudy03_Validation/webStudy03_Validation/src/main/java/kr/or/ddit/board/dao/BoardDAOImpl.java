package kr.or.ddit.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

public class BoardDAOImpl implements IBoardDAO {
	private static BoardDAOImpl self;
	private BoardDAOImpl() {}
	
	public static BoardDAOImpl getInstance(){
		if(self == null) self = new BoardDAOImpl();
		return self;
	}
	
	private SqlSessionFactory sessionFactory = CustomSqlSessionFactoryBuilder.getSessionFactory();	
	

	@Override
	public int insertBoard(BoardVO board, SqlSession session) {
			return session.insert("kr.or.ddit.board.dao.IBoardDAO.insertBoard", board);
	}

	@Override
	public int selectBoardCount(PagingVO<BoardVO> pagingVO) {
		try(
			SqlSession session = sessionFactory.openSession();
		){
			IBoardDAO mapper = session.getMapper(IBoardDAO.class);
			return mapper.selectBoardCount(pagingVO);
		}
	}

	@Override
	public List<BoardVO> selectBoardList(PagingVO<BoardVO> pagingVO) {
		try(
			SqlSession session = sessionFactory.openSession();
		){
			IBoardDAO mapper = session.getMapper(IBoardDAO.class);
			return mapper.selectBoardList(pagingVO);
		}
	}

	@Override
	public BoardVO selectBoard(BoardVO search) {
		try(
				SqlSession session = sessionFactory.openSession();
			){
				IBoardDAO mapper = session.getMapper(IBoardDAO.class);
				return mapper.selectBoard(search);
			}
	}

	@Override
	public int updateBoard(BoardVO board, SqlSession session) {
		return session.update("kr.or.ddit.board.dao.IBoardDAO.updateBoard",board);
}

	@Override
	public int deleteBoard(BoardVO search) {
		// TODO Auto-generated method stub
		return 0;
	}

}
