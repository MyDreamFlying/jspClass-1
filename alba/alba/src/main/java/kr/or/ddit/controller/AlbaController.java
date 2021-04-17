package kr.or.ddit.controller;

import javax.servlet.http.HttpServletRequest;

import kr.or.ddit.mvc.annotation.Controller;
import kr.or.ddit.mvc.annotation.RequestMapping;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.service.AlbaService;
import kr.or.ddit.service.AlbaServiceImpl;
import kr.or.ddit.vo.AlbaVO;

@Controller
public class AlbaController {
	private AlbaService service = AlbaServiceImpl.getInstance();
	
	@RequestMapping("/albaInsert.do")
	public String insertView() {
		return "alba/albaForm";
	}
	
	@RequestMapping(value="/albaInsert.do",method = RequestMethod.POST)
	public String albaInsert(
			@ModelAttribute("detailSearch") AlbaVO detailSearch
			,HttpServletRequest req
			) {
		
		return null;
	}
	
}
