package kr.or.ddit.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Test01Connection {
	
	public static void main(String[] args) {
		
		
		String user = "root";
		String password = "python";
		String url = "jdbc:mysql://localhost:3306?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false";
		try(
			Connection conn = DriverManager.getConnection(url, user, password);
		) {
			System.out.println(conn.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}