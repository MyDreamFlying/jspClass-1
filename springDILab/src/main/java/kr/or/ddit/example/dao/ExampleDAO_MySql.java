package kr.or.ddit.example.dao;

public class ExampleDAO_MySql implements IExampleDAO {

	@Override
	public String selectData(String pk) {
		return String.format("%s로 MySql에서 조회된 rawData", pk);
	}

}
