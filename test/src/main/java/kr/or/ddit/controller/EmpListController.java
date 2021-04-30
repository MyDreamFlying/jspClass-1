package kr.or.ddit.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.service.EmpService;
import kr.or.ddit.vo.EmployeeVO;

@Controller
public class EmpListController {
	
	@Inject
	private EmpService service;
	@Inject
	private WebApplicationContext container;
	private ServletContext application;
	@PostConstruct
	public void init() {
		application = container.getServletContext();
	}
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@RequestMapping(value="/empList.do", method = RequestMethod.GET)
	public String empList(
			Model model
		) {
		List<EmployeeVO> list = service.selectEmpList();
		model.addAttribute("empList",list);
		
		return "empList";
	}

}
