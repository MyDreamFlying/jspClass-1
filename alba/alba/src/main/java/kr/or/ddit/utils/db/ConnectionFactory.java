package kr.or.ddit.utils.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

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
	private static String connectionMessage;
	private static DataSource ds;
	
	static {
//		Properties properties = new Properties();
//		try(
//			InputStream is = ConnectionFactory.class.getResourceAsStream("dbinfo.properties");
//		) {
//			properties.load(is);
			ResourceBundle bundle = ResourceBundle.getBundle("dbinfo");
		
			driverClassName = bundle.getString("driverClassName");
			url = bundle.getString("url");
			user = bundle.getString("user");
			password = bundle.getString("password");
			connectionMessage = bundle.getString("connectionMessage");
			
			int initialSize = Integer.parseInt(bundle.getString("initialSize"));
			int maxTotal = Integer.parseInt(bundle.getString("maxTotal"));
			long maxWait = Integer.parseInt(bundle.getString("maxWait"));
			
			ds = new BasicDataSource();
			((BasicDataSource)ds).setDriverClassName(driverClassName);
			((BasicDataSource)ds).setUrl(url);
			((BasicDataSource)ds).setUsername(user);
			((BasicDataSource)ds).setPassword(password);
			
			// 풀링을 하려면, 몇개를 만들어 둘지 미리 셋팅을 해야함.
			((BasicDataSource)ds).setMaxTotal(initialSize);
			((BasicDataSource)ds).setInitialSize(maxTotal);
			((BasicDataSource)ds).setMaxWaitMillis(maxWait);
			
	}
	
	public static Connection getConnection() throws SQLException{
		System.out.println(connectionMessage);
		return ds.getConnection();
//		return DriverManager.getConnection(url, user, password);
	}
	
}

