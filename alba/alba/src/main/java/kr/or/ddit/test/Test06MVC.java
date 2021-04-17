package kr.or.ddit.test;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import kr.or.ddit.mvc.annotation.Controller;
import kr.or.ddit.mvc.annotation.RequestMapping;
import kr.or.ddit.service.AlbaService;
import kr.or.ddit.service.AlbaServiceImpl;
import kr.or.ddit.vo.AlbaVO;

@Controller
public class Test06MVC extends HttpServlet{
	private static final long serialVersionUID = 1L;
	AlbaService service = AlbaServiceImpl.getInstance();

	@RequestMapping("/test06.do")
	public String test05(HttpServletRequest req) throws IOException{
		List<AlbaVO> albaList = service.retrieveAlbaList(null);
		req.setAttribute("albaList", albaList);
		String view = "test/test06";
		
		return view;
	}
	
}