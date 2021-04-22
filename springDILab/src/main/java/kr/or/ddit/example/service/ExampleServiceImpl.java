package kr.or.ddit.example.service;

import kr.or.ddit.example.dao.ExampleDAOFactory;
import kr.or.ddit.example.dao.ExampleDAO_MySql;
import kr.or.ddit.example.dao.ExampleDAO_Oracle;
import kr.or.ddit.example.dao.IExampleDAO;

public class ExampleServiceImpl implements IExampleService {
/*
	 1. new 키워드로 인스턴스 직접 생성
	private IExampleDAO dao = new ExampleDAO_Oracle();
	private IExampleDAO dao = new ExampleDAO_MySql();
	
	2. Factory ObjectPattern
	private IExampleDAO dao = ExampleDAOFactory.getExampleDAO();
	
	 
	3. Strategy Pattern - > Dependency Injection
  	: 생성자 주입, setter 주입, 전략의 주입자가 필요함.
*/
	
	
	private IExampleDAO dao;
	public ExampleServiceImpl(IExampleDAO dao) {
		super();
		this.dao = dao;
	}

	public void setDao(IExampleDAO dao) {
		this.dao = dao;
	}

	@Override
	public String readData(String pk) {
		String rawData = dao.selectData(pk);
		String info = rawData + "를 가공한 information";
		return info;
	}

}
