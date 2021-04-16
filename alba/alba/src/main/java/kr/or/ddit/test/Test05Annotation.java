package kr.or.ddit.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.mvc.annotation.Controller;
import kr.or.ddit.mvc.annotation.RequestMapping;
import kr.or.ddit.utils.db.ConnectionFactory;

@Controller
public class Test05Annotation extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@RequestMapping("/test05.do")
	public String test05(HttpServletResponse resp) throws IOException{
		resp.setContentType("text/html; charset=utf-8");
		try(
				Connection conn = ConnectionFactory.getConnection();
				PrintWriter out = resp.getWriter();
			){
				String sql = "select * from alba";
				Statement statement = conn.createStatement();
				ResultSet result = statement.executeQuery(sql);
				
				while(result.next()) {
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
					
					String resultString = String.format("%s %s %d %s %s %s %s %s %s %s %s %s %s %s",al_id,al_name,al_age,al_zip,al_addr1,al_addr2,al_hp,gr_code,al_gen,al_mail,al_career,al_spec,al_desc,al_img);
					out.write(resultString);
					out.write("<br>");
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return null;
	}
	
}