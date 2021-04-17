package kr.or.ddit.db.mybatis;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class CustomSqlSessionFactoryBuilder {
	private static SqlSessionFactory sessionFactory;
	
	static {
		String xmlRes = "mybatis/config.xml";
		try(
			Reader reader = Resources.getResourceAsReader(xmlRes);
			// 한글을 읽을 수도 있으니 굳이 리더로 해야한다.
		) {
			sessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public static SqlSessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
