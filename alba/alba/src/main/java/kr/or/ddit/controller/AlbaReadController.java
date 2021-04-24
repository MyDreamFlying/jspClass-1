package kr.or.ddit.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import kr.or.ddit.dao.OtherDAO;
import kr.or.ddit.dao.OtherDAOImpl;
import kr.or.ddit.mvc.annotation.Controller;
import kr.or.ddit.mvc.annotation.RequestMapping;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.service.AlbaService;
import kr.or.ddit.service.AlbaServiceImpl;
import kr.or.ddit.vo.AlbaVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;

@Controller
public class AlbaReadController {
	private AlbaService service = AlbaServiceImpl.getInstance();
	private OtherDAO otherDao = OtherDAOImpl.getInstance();
	private List<Map<String, Object>> licenseList;
	List<Map<String, Object>> gradeList;
	
	{
		licenseList = otherDao.selectLicenseList();
		gradeList = otherDao.selectGradeList();
	}
	
	private void addAttribute(HttpServletRequest req) {
		req.setAttribute("licenseList",licenseList);
		req.setAttribute("gradeList",gradeList);
	}
	
	@RequestMapping("/albaList.do")
	public String albaList(
			@ModelAttribute("detailSearch") AlbaVO detailSearch
			,@ModelAttribute(value = "SearchVO") SearchVO searchVO
			,@RequestParam(value="page", required=false, defaultValue = "1") int currentPage
			,HttpServletRequest req) {
		addAttribute(req);
		
		PagingVO<AlbaVO> pagingVO = new PagingVO<>();
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setSimpleSearch(searchVO);
		pagingVO.setDetailSearch(detailSearch);
		
		int totalRecord = service.selectTotalRecord(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		
		List<AlbaVO> albaList = service.retrieveAlbaList(pagingVO);
		pagingVO.setDataList(albaList);
		
		req.setAttribute("pagingVO", pagingVO);
		return "alba/albaList";
	}
	
	@RequestMapping("/albaView.do")
	public String albaView(@RequestParam(value="al_id") String al_id
			,HttpServletRequest req) {
		
		AlbaVO alba = service.retrieveAlba(al_id);
		req.setAttribute("alba", alba);
		
		return "alba/albaView";
	}
	
}









