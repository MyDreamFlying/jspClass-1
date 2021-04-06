package kr.or.ddit.prod.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

public class ProdDAOImpl implements IProdDAO {
	private static ProdDAOImpl self;
	private ProdDAOImpl() {}
	
	public static ProdDAOImpl getInstance() {
		if(self==null) self = new ProdDAOImpl();
		return self;
	}
	
	private SqlSessionFactory sessionFactory = CustomSqlSessionFactoryBuilder.getSessionFactory();			

	@Override
	public ProdVO selectProd(String prod_id) {
		try(
			SqlSession session = sessionFactory.openSession();
		){
			IProdDAO mapper = session.getMapper(IProdDAO.class);
			return mapper.selectProd(prod_id);
		}
	}

	@Override
	public List<ProdVO> selectProdList(PagingVO pagingVO) {
		try(
				SqlSession session = sessionFactory.openSession();
			){
				IProdDAO mapper = session.getMapper(IProdDAO.class);
				return mapper.selectProdList(pagingVO);
			}
	}

	@Override
	public int insert(ProdVO prod) {
		try(
				SqlSession session = sessionFactory.openSession();
			){
				IProdDAO mapper = session.getMapper(IProdDAO.class);
				return mapper.insert(prod);
			}
	}

	@Override
	public int updateProd(ProdVO prod) {
		try(
				SqlSession session = sessionFactory.openSession();
			){
				IProdDAO mapper = session.getMapper(IProdDAO.class);
				return mapper.updateProd(prod);
			}
	}

	@Override
	public int selectTotalRecord() {
		try(
				SqlSession session = sessionFactory.openSession();
			){
				IProdDAO mapper = session.getMapper(IProdDAO.class);
				return mapper.selectTotalRecord();
			}
	}

}
