package kr.or.ddit.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.AlbaVO;
import kr.or.ddit.vo.PagingVO;

public class AlbaDAOImpl implements AlbaDAO {
	private static AlbaDAOImpl self;
	private AlbaDAOImpl() {};
	
	private SqlSessionFactory sessionFactory = CustomSqlSessionFactoryBuilder.getSessionFactory();
	
	public static AlbaDAOImpl getInstance() {
		if(self == null) self = new AlbaDAOImpl();
		return self;
	}
	
	@Override
	public List<AlbaVO> selectAlbaList(PagingVO<AlbaVO> pagingVO) {
		try(
			SqlSession session = sessionFactory.openSession();
		){
			AlbaDAO mapper = session.getMapper(AlbaDAO.class);
			return mapper.selectAlbaList(pagingVO);
		}
	}

	@Override
	public AlbaVO selectAlba(String al_id) {
		try(
				SqlSession session = sessionFactory.openSession();
			){
				AlbaDAO mapper = session.getMapper(AlbaDAO.class);
				return mapper.selectAlba(al_id);
			}
	}

	@Override
	public int insertAlba(AlbaVO alba) {
		try(
				SqlSession session = sessionFactory.openSession();
			){
				AlbaDAO mapper = session.getMapper(AlbaDAO.class);
				return mapper.insertAlba(alba);
			}
	}

	@Override
	public int updateAlba(AlbaVO alba) {
		try(
				SqlSession session = sessionFactory.openSession();
			){
				AlbaDAO mapper = session.getMapper(AlbaDAO.class);
				return mapper.updateAlba(alba);
			}
	}

	@Override
	public int selectTotalRecord(PagingVO<AlbaVO> pagingVO) {
		try(
				SqlSession session = sessionFactory.openSession();
			){
				AlbaDAO mapper = session.getMapper(AlbaDAO.class);
				return mapper.selectTotalRecord(pagingVO);
			}
	}

}
