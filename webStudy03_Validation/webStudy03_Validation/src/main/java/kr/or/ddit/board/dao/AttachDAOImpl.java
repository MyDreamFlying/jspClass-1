package kr.or.ddit.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.AttachVO;
import kr.or.ddit.vo.BoardVO;

public class AttachDAOImpl implements IAttachDAO {
	private static AttachDAOImpl self;
	private AttachDAOImpl() {}
	
	public static AttachDAOImpl getInstance(){
		if(self == null) self = new AttachDAOImpl();
		return self;
	}
	
	private SqlSessionFactory sessionFactory = CustomSqlSessionFactoryBuilder.getSessionFactory();	

	@Override
	public int insertAttaches(BoardVO board, SqlSession session) {
		return session.insert("kr.or.ddit.board.dao.IAttachDAO.insertAttaches",board);
	}

	@Override
	public AttachVO selectAttaches(int att_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteAttaches(BoardVO board, SqlSession session) {
		return session.delete("kr.or.ddit.board.dao.IAttachDAO.deleteAttaches",board);
	}

	@Override
	public List<String> selectSaveNamesForDelete(BoardVO board) {
		try(
			SqlSession session = sessionFactory.openSession();
		){
			IAttachDAO mapper = session.getMapper(IAttachDAO.class);
			return mapper.selectSaveNamesForDelete(board);
		}
		
	}

}
