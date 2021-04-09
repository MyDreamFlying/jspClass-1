package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.mvc.annotation.Controller;
import kr.or.ddit.mvc.annotation.RequestMapping;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.prod.dao.IOthersDAO;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

@Controller
public class ProdReadController{
	private IProdService service = ProdServiceImpl.getInstance();
	private IOthersDAO othersDAO = OthersDAOImpl.getInstance();
	
	private void addAttribute(HttpServletRequest req ) {
		List<Map<String, Object>> lprodList = othersDAO.selectLprodList();
		List<BuyerVO> buyerList = othersDAO.selectBuyerList(null);
		req.setAttribute("lprodList", lprodList);
		req.setAttribute("buyerList", buyerList);
	}
	
	@RequestMapping("/prod/prodList.do")
	public String list(
			@ModelAttribute("detailSearch") ProdVO detailSearch
			,@RequestParam(value="page", required=false, defaultValue="1") int currentPage
			,HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PagingVO<ProdVO> pagingVO = new PagingVO<>();
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setDetailSearch(detailSearch);
		
		int totalRecord = service.retrieveProdCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
				
		List<ProdVO> prodList =  service.retrieveProdList(pagingVO);
		pagingVO.setDataList(prodList);
		addAttribute(req);

		String accept = req.getHeader("Accept");
		String view = null;
		if(StringUtils.containsIgnoreCase(accept, "json")) {
			resp.setContentType("application/json;charset=UTF-8");
			ObjectMapper mapper = new ObjectMapper();
			try(
				PrintWriter out = resp.getWriter();
			){
				mapper.writeValue(out, pagingVO);
			}
		}else {
			req.setAttribute("pagingVO", pagingVO);
			view = "prod/prodList";
		}
		return view;
		
	}
	
	@RequestMapping("/prod/prodView.do")
	public String view(
			@RequestParam(value="what", required=true, defaultValue="1") String prod_id
			, HttpServletRequest req){
		
		ProdVO prod = service.retrieveProd(prod_id);
		req.setAttribute("prod", prod);
		
		return "prod/prodView";
	}
}
