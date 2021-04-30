package kr.or.ddit.controller;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.service.EmpService;
import kr.or.ddit.vo.EmployeeVO;

@Controller
public class LoginController {
	
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
	
	@RequestMapping(value="/login/loginCheck.do", method = RequestMethod.POST)
	public String loginCheck(
			@RequestParam String employee_id
			,@RequestParam String employee_pwd
			,@RequestParam(value="saveId", required=false) String saveId
			,HttpSession session
			,HttpServletRequest req
			,HttpServletResponse resp
			) {
		

		if("saveId".equals(saveId)) {	// 쿠키에 아이디 하루동안 저장
			Cookie savedIdCookie = new Cookie("savedId",employee_id);
			savedIdCookie.setPath(String.format("%s",application.getContextPath()));
			savedIdCookie.setMaxAge(1*24*60*60);
			resp.addCookie(savedIdCookie);
		}else {	// 저장된 아이디 쿠키 삭제
			Cookie savedIdCookie = new Cookie("savedId",null);
			savedIdCookie.setPath(String.format("%s",application.getContextPath()));
			savedIdCookie.setMaxAge(0);
			resp.addCookie(savedIdCookie);
		}
		
		boolean valid = validate(employee_id, employee_pwd);
		
		EmployeeVO emp = new EmployeeVO();
		emp.setEmployee_id(employee_id);
		emp.setEmployee_pwd(employee_pwd);
		
		String view = null;
		String message = "";
		
		if(valid) {
		// 인증 (id == password)
			ServiceResult result = service.authenticate(emp);
			if(logger.isInfoEnabled())
				logger.info("인증 후 : {}",emp);
			switch(result) {
			case OK:
				Cookie idCookie = new Cookie("loginId",emp.getEmployee_id());
				idCookie.setMaxAge(1*24*60*60);
				idCookie.setPath(String.format("%s",application.getContextPath()));
				resp.addCookie(idCookie);
				Cookie telCookie = new Cookie("loginTel",emp.getEmployee_phone());
				telCookie.setMaxAge(1*24*60*60);
				telCookie.setPath(String.format("%s",application.getContextPath()));
				resp.addCookie(telCookie);
				Cookie emailCookie = new Cookie("loginEmail",emp.getEmployee_email());
				emailCookie.setMaxAge(1*24*60*60);
				emailCookie.setPath(String.format("%s",application.getContextPath()));
				resp.addCookie(emailCookie);
				
				view = "redirect:/empList.do";
				session.setAttribute("authEmp", emp);
				resp.addCookie(emailCookie);
				break;
			case NOTEXIST:
				view = "redirect:/";
				message = "아이디 오류";
				break;
			case INVALIDPASSWORD:
			default:
				// 인증 실패
				view = "redirect:/";
				message = "비번 오류";
				break;
			}
			
		}else {
			// 1)검증
			view = "redirect:/";
			message = "아이디나 비번 누락";
		}
		
		session.setAttribute("message", message);
		
		return view;
	}
	
	private boolean validate(String id, String pass) {
		boolean valid = true;
		valid = !(id == null || id.isEmpty() || pass==null || pass.isEmpty());
		return valid;
	}
	

}
