package kr.or.ddit.container.auto.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.container.auto.dao.ISampleDAO;

@Service("sampleService")
public class SampleServiceImpl implements ISampleService {
	
//	@Resource(type = ISampleDAO.class)
	private ISampleDAO dao;
	
	public SampleServiceImpl() {
		super();
	}

	@Inject
//	@Autowired
	public SampleServiceImpl(ISampleDAO dao) {
		super();
		this.dao = dao;
	}

	@Override
	public String readData(String pk) {
		String raw = dao.selectData(pk);
		String info = raw + "를 가공한 Information";
		return info;
	}

}
