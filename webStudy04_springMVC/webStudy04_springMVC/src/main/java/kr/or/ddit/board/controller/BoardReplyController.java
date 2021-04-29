package kr.or.ddit.board.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.board.service.IReplyService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.Reply2VO;

@RestController
@RequestMapping(value="/board/reply", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
public class BoardReplyController {
	@Inject
	private IReplyService service;
	@Inject
	private WebApplicationContext container;
	private ServletContext application;
	@PostConstruct
	public void init() {
		application = container.getServletContext();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Reply2VO newReply(
			@ModelAttribute("reply") Reply2VO reply
			) {
		ServiceResult result = service.insertReply(reply);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:sss");
		String now = sf.format(new Date());
		reply.setRep_date(now);
		return ServiceResult.OK.equals(result) ? reply : null;
		
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public String delReply(
			@ModelAttribute("reply") Reply2VO reply
			,HttpServletRequest req
		) {
		
		ServiceResult result = service.deleteReply(reply);
		switch(result) {
		case OK:
			return "OK";
		case INVALIDPASSWORD:
			return "INVALID PASSWORD";
		default:
			return "FAIL";
		}
	}

}
