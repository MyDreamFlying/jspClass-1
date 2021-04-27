package kr.or.ddit.prod.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.prod.dao.IOthersDAO;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.validator.InsertGroup;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.ProdVO;

@Controller
public class ProdController {
	@Inject
	private IProdService service;
	@Inject
	private IOthersDAO othersDAO;
	
	@Inject
	private WebApplicationContext container;
	private ServletContext application;
	
	@PostConstruct
	public void init() {
		application = container.getServletContext();
	}
	
	private void addAttribute(HttpServletRequest req ) {
		List<Map<String, Object>> lprodList = othersDAO.selectLprodList();
		List<BuyerVO> buyerList = othersDAO.selectBuyerList(null);
		req.setAttribute("lprodList", lprodList);
		req.setAttribute("buyerList", buyerList);
	}
	
	private void addCommandAttribute(HttpServletRequest req) {
		req.setAttribute("command", "update");
	}
	
	@RequestMapping("/prod/prodInsert.do")
	public String prodInsertForm(
			HttpServletRequest req) throws ServletException, IOException {
		addAttribute(req);
		return "prod/prodForm";
	}
	
	@RequestMapping(value="/prod/prodInsert.do", method=RequestMethod.POST)
	public String prodInsert(
			@Validated(InsertGroup.class) @ModelAttribute("prod") ProdVO prod
			, @RequestPart("prod_image") MultipartFile prod_image
			,Errors errors
			,Model model) throws IOException {
		model.addAttribute("prod", prod);
		
		//EDD : Event Driven Development  TDD : Test Driven Development
		
		String saveFolderUrl = "/prodImages";
		File saveFolder = new File(application.getRealPath(saveFolderUrl));
		if(!prod_image.isEmpty()) {
			prod_image.transferTo(saveFolder);
			String saveName = UUID.randomUUID().toString();
			prod.setProd_img(saveName);
		}
		
		String view = null;
		String message = null;
		
		boolean valid = !errors.hasErrors();
		
		if(valid) {
			ServiceResult result = service.createProd(prod);
			if(ServiceResult.OK.equals(result)) {
				String newProdId = prod.getProd_id();
				view = "redirect:/prod/prodView.do?what="+newProdId;
			}else {
				message = "서버 오류. 잠시후 다시 시도하세요";
				view = "prod/prodForm";
			}
			model.addAttribute("message", message);
		}else {
			view = "prod/prodForm";
		}
		return view;
		
	}
	
	@RequestMapping("/prod/prodUpdate.do")
	public String prodUpdateForm(
			@RequestParam(value="what") String prodId 
			,HttpServletRequest req
			) throws ServletException, IOException {
		addCommandAttribute(req);
		addAttribute(req);
		
		ProdVO prod = service.retrieveProd(prodId);
		req.setAttribute("prod", prod);
				
		return "prod/prodForm";
	}
	
	@RequestMapping(value="/prod/prodUpdate.do", method=RequestMethod.POST)
	public String prodUpdate(
			@ModelAttribute("prod") ProdVO prod,
			@RequestPart(value="prod_image", required=false) MultipartFile prod_image
			,Errors errors
			,Model model) throws IOException {
		
		model.addAttribute("prod", prod);
		
		String saveFolderUrl = "/prodImages";
		File saveFolder = new File(application.getRealPath(saveFolderUrl));
		if(prod_image!=null && !prod_image.isEmpty()) {
			prod_image.transferTo(saveFolder);
			String saveName = UUID.randomUUID().toString();
			prod.setProd_img(saveName);
		}
		
		boolean valid = !errors.hasErrors();
		
		String view = null;
		String message = null;
		
		if(valid) {
			ServiceResult result = service.modifyProd(prod);
			switch(result) {
			case OK :
				view = "redirect:/prod/prodView.do?what="+prod.getProd_id();
				break;
			default :
				view = "prod/prodForm";
				message = "서버 오류, 잠시 후 다시 시도하세요.";
				break;
			}
		}else {
			view = "prod/prodForm";
		}
		
		model.addAttribute("message", message);
		return view;
		
	}
	
	
}
