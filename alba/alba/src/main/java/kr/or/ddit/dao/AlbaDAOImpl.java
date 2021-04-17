package kr.or.ddit.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.utils.db.ConnectionFactory;
import kr.or.ddit.vo.AlbaVO;
import kr.or.ddit.vo.PagingVO;

public class AlbaDAOImpl implements AlbaDAO {
	private static AlbaDAOImpl self;
	private AlbaDAOImpl() {};
	
	public static AlbaDAOImpl getInstance() {
		if(self == null) self = new AlbaDAOImpl();
		return self;
	}
	
	@Override
	public List<AlbaVO> selectAlbaList(PagingVO<AlbaVO> pagingVO) {
		List<AlbaVO> albaList = new ArrayList<>();
		try(
			Connection conn = ConnectionFactory.getConnection();
		){
			String sql = "select * from alba";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);

			while(result.next()) {
				AlbaVO alba = new AlbaVO();
				
				String al_id = result.getString(1);
				String al_name = result.getString(2);
				int al_age = result.getInt(3);
				String al_zip = result.getString(4);
				String al_addr1 = result.getString("al_addr1");
				String al_addr2 = result.getString("al_addr2");
				String al_hp = result.getString("al_hp");
				String gr_code = result.getString("gr_code");
				String al_gen = result.getString("al_gen");
				String al_mail =result.getString("al_mail");
				String al_career = result.getString("al_career");
				String al_spec = result.getString("al_spec");
				String al_desc = result.getString("al_desc");
				String al_img = result.getString("al_img");
				
				alba.setAl_id(al_id);
				alba.setAl_name(al_name);
				alba.setAl_age(al_age);
				alba.setAl_zip(al_zip);
				alba.setAl_addr1(al_addr1);
				alba.setAl_addr2(al_addr2);
				alba.setAl_hp(al_hp);
				alba.setGr_code(gr_code);
				alba.setAl_gen(al_gen);
				alba.setAl_mail(al_mail);
				alba.setAl_career(al_career);
				alba.setAl_spec(al_spec);
				alba.setAl_desc(al_desc);
				alba.setAl_img(al_img);
				
				albaList.add(alba);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return albaList;
	}

	@Override
	public AlbaVO selectAlba(String al_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertAlba(AlbaVO alba) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateAlba(AlbaVO alba) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int selectTotalRecord(PagingVO<AlbaVO> pagingVO) {
		// TODO Auto-generated method stub
		return 0;
	}

}
