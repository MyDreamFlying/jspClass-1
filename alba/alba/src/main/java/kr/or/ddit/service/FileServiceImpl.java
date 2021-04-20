package kr.or.ddit.service;

import kr.or.ddit.dao.OtherDAO;
import kr.or.ddit.dao.OtherDAOImpl;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.LicenseVO;

public class FileServiceImpl implements FileService {
	private OtherDAO dao = OtherDAOImpl.getInstance();
	private static FileServiceImpl self;
	
	private FileServiceImpl() {}
	
	public static FileServiceImpl getInstance() {
		if(self == null) self = new FileServiceImpl();
		return self;
	}

	@Override
	public ServiceResult licUpload(LicenseVO license) {
		return dao.updateLicense(license)==1? ServiceResult.OK : ServiceResult.FAIL;
	}

}
