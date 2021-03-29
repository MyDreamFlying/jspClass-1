package kr.or.ddit.servlet08;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.db.ConnectionFactory;

public class JdbcDescDAO {
	public List<Map<String, Object>> selectDataBaseProperties(Map<String, Object> pMap) {
		String[] headers = null;
		List<Map<String, Object>> dataList = new ArrayList<>();
		String property_name = (String) pMap.get("property_name");
		property_name = property_name==null? "" : property_name;
		
		try(
			Connection conn = ConnectionFactory.getConnection();
			Statement stmt = conn.createStatement();
		){
			String sql = "select property_name, property_value, description from database_properties";
			
			if(property_name != null && !property_name.isEmpty()) {
				sql += " WHERE INSTR(PROPERTY_NAME, '"+property_name+"') > 0 ";
			}
			ResultSet rs = stmt.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();
			headers = new String[count];
			pMap.put("headers", headers);
			for(int i=1; i<=count; i++){
				headers[i-1] = rsmd.getColumnName(i);
			}
			
			while(rs.next()){
				Map<String, Object> record = new LinkedHashMap<>();
				for(String colName : headers){
					record.put(colName, rs.getString(colName));
				}
				dataList.add(record);
			}
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		return dataList;
		
	}
}
