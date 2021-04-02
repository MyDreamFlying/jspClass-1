package kr.or.ddit.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.enumpkg.MimeType;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;

@WebServlet("/member/idCheck.do")
public class IdCheckServlet extends HttpServlet{
	private IMemberService service = new MemberServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, Object> resultMap = new HashMap<>();
		req.setCharacterEncoding("UTF-8");
		String mem_id = req.getParameter("mem_id");
		
		if(mem_id == null || mem_id.isEmpty()) {
			resp.sendError(400);
			return;
		}
		
		try {
			service.retrieveMember(mem_id);
			resultMap.put("result", ServiceResult.FAIL);
		}catch(Exception e) {
			resultMap.put("result", ServiceResult.OK);
		}
		
		resp.setContentType(MimeType.JSON.getMime());
		try(
			PrintWriter out = resp.getWriter();
		){
			ObjectMapper mapper= new ObjectMapper();
			mapper.writeValue(out, resultMap);
		}
		
	}
}
