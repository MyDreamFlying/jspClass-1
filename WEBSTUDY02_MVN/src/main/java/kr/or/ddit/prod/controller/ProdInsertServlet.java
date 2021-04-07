package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.prod.dao.IOthersDAO;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.ProdVO;

@WebServlet("/prod/prodInsert.do")
public class ProdInsertServlet extends HttpServlet{
	private IProdService service = ProdServiceImpl.getInstance();
	private IOthersDAO othersDAO = OthersDAOImpl.getInstance();
	
	private void addAttribute(HttpServletRequest req ) {
		List<Map<String, Object>> lprodList = othersDAO.selectLprodList();
		List<BuyerVO> buyerList = othersDAO.selectBuyerList(null);
		req.setAttribute("lprodList", lprodList);
		req.setAttribute("buyerList", buyerList);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		addAttribute(req);
		
		String view = "/WEB-INF/views/prod/prodForm.jsp";
		
		boolean redirect = view.startsWith("redirect:");
		if(redirect) {
			view = view.substring("redirect:".length());
			resp.sendRedirect(req.getContextPath() + view);
		}else {
			req.getRequestDispatcher(view).forward(req,resp);		
		}	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		ProdVO prod = new ProdVO();
		req.setAttribute("prod", prod);
		
		try {
			BeanUtils.populate(prod, req.getParameterMap());
		}catch(IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
		
		Map<String, String> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);
		
		boolean valid = validate(prod, errors);
		
		String view = null;
		String message = null;
		
		if(valid) {
			ServiceResult result = service.createProd(prod);
			if(ServiceResult.OK.equals(result)) {
				String newProdId = prod.getProd_id();
				view = "redirect:/prod/prodView.do?what="+newProdId;
			}else {
				message = "서버 오류. 잠시후 다시 시도하세요";
				view = "/WEB-INF/views/prod/prodForm.jsp";
			}
			req.setAttribute("message", message);
		}else {
			view = "/WEB-INF/views/prod/prodForm.jsp";
		}
		
		
		boolean redirect = view.startsWith("redirect:");
		
		if(redirect) {
			view = view.substring("redirect:".length());
			resp.sendRedirect(req.getContextPath() + view);
		}else {
			req.getRequestDispatcher(view).forward(req,resp);		
		}	
		
	}

	private boolean validate(ProdVO prod, Map<String, String> errors) {
		boolean valid = true;
		
		return valid;
	}
}










