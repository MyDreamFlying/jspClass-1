package kr.or.ddit.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.mybatis.CustomSqlSessionFactoryBuilder;

public class OtherDAOImpl implements OtherDAO {
	private static OtherDAOImpl self;
	private OtherDAOImpl() {}
	private SqlSessionFactory sessionFactory = CustomSqlSessionFactoryBuilder.getSessionFactory();

	public static OtherDAOImpl getInstance() {
		if(self==null) self = new OtherDAOImpl();
		return self;
	}

	@Override
	public List<Map<String, Object>> selectLicenseList() {
		try(
			SqlSession session = sessionFactory.openSession();
		){
			OtherDAO mapper = session.getMapper(OtherDAO.class);
			return mapper.selectLicenseList();
		}
	}

	@Override
	public List<Map<String, Object>> selectGradeList() {
		try(
				SqlSession session = sessionFactory.openSession();
			){
				OtherDAO mapper = session.getMapper(OtherDAO.class);
				return mapper.selectGradeList();
			}
	}

}
