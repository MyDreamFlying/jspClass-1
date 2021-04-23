package kr.or.ddit.servlet08.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.servlet08.JdbcDescDAO;

public class JdbcDescServiceImpl implements IjdbcDescService {
	JdbcDescDAO dao = new JdbcDescDAO();
	
	@Override
	public List<Map<String, Object>> retrieveDataBaseProperties(Map<String, Object> pMap) {
		List<Map<String, Object>> dataList = dao.selectDataBaseProperties(pMap);
		for(Map<String,Object> record : dataList) {
			String pName = (String)record.get("PROPERTY_NAME");
			pName.toLowerCase();
			record.put("property_name", pName.toLowerCase());
		}
		return dataList;
	}

}
