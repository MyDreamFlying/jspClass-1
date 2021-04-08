package kr.or.ddit.prod.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.mvc.annotation.Controller;
import kr.or.ddit.mvc.annotation.RequestMapping;
import kr.or.ddit.mvc.annotation.RequestMethod;

@Controller
public class ProdUpdateController {
	
	@RequestMapping("/prod/prodUpdate.do")
	public String prodForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		return "prod/prodForm";
	}
	
	@RequestMapping(value="/prod/prodUpdate.do", method=RequestMethod.POST)
	public String prodUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		return null;
	}
}
