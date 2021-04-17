package kr.or.ddit.dao;

import java.util.List;
import java.util.Map;

public interface OtherDAO {
	List<Map<String, Object>> selectLicenseList();
	List<Map<String, Object>> selectGradeList();

}
