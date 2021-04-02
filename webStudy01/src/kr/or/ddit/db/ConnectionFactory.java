package kr.or.ddit.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import oracle.jdbc.pool.OracleDataSource;

/**
 * Factory Object[Method] Pattern
 *	: 객체 생성을 전담하는 객체를 운영하는 구조.
 * @author shane
 */
public class ConnectionFactory {
	private static String driverClassName;
	private static String url;
	private static String user;
	private static String password;
	private static DataSource ds;
	
	static {
		Properties properties = new Properties();
		try(
			InputStream is = ConnectionFactory.class.getResourceAsStream("dbinfo.properties");
		) {
			properties.load(is);
//			driverClassName = properties.getProperty("driverClassName");
			url = properties.getProperty("url");
			user = properties.getProperty("user");
			password = properties.getProperty("password");
			
			ds = new OracleDataSource();
			((OracleDataSource)ds).setURL(url);
			((OracleDataSource)ds).setUser(user);
			((OracleDataSource)ds).setPassword(password);
			
//			Class.forName(driverClassName);
		} catch ( IOException | SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static Connection getConnection() throws SQLException{
		return ds.getConnection();
//		return DriverManager.getConnection(url, user, password);
	}
	
}
