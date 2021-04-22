package kr.or.ddit.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.LicenseVO;

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

	@Override
	public int insertLicense(LicenseVO license) {
		try(
				SqlSession session = sessionFactory.openSession();
			){
				OtherDAO mapper = session.getMapper(OtherDAO.class);
				int cnt =  mapper.insertLicense(license);
				session.commit();
				return cnt;
			}
	}

	@Override
	public int updateLicense(LicenseVO license) {
		try(
				SqlSession session = sessionFactory.openSession();
			){
				OtherDAO mapper = session.getMapper(OtherDAO.class);
				int cnt =  mapper.updateLicense(license);
				session.commit();
				return cnt;
			}
	}

	@Override
	public LicenseVO selectLicense(LicenseVO search) {
		try(
				SqlSession session = sessionFactory.openSession();
			){
				OtherDAO mapper = session.getMapper(OtherDAO.class);
				return mapper.selectLicense(search);
			}
	}

}
