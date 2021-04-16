package kr.or.ddit.board.dao;

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
	public int insertAttaches(BoardVO board) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public AttachVO selectAttaches(int att_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteAttaches(BoardVO board) {
		// TODO Auto-generated method stub
		return 0;
	}

}
