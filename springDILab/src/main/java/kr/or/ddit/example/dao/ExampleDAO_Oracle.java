package kr.or.ddit.example.dao;

public class ExampleDAO_Oracle implements IExampleDAO{

	@Override
	public String selectData(String pk) {
		return String.format("%s로 Oracle에서 조회된 rawData", pk);
	}
}
