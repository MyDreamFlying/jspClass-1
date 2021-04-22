package kr.or.ddit.service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.LicenseVO;

public interface FileService {
	/**
	 * @param license
	 * @return OK, FAIL
	 */
	public ServiceResult licUpload(LicenseVO license);
	/**
	 * @param license
	 * @return OK, FAIL
	 */
	public LicenseVO selectLicense(LicenseVO search);
		
}
