package kr.or.ddit.servlet08.service;

import java.util.List;
import java.util.Map;

public interface IjdbcDescService {
	public List<Map<String, Object>> retrieveDataBaseProperties (Map<String, Object> pMap);
}
