package kr.or.ddit.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.LicenseVO;

public interface OtherDAO {
	public List<Map<String, Object>> selectLicenseList();
	public List<Map<String, Object>> selectGradeList();
	public int insertLicense(LicenseVO license);
	public int updateLicense(LicenseVO license);
}
