package kr.or.ddit.board.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.BoardVO;

@Controller
@RequestMapping("/board")
public class BoardNumberingController {
	
	@Inject
	private IBoardService service;
	@Inject
	private WebApplicationContext container;
	private ServletContext application;
	@PostConstruct
	public void init() {
		application = container.getServletContext();
	}
	
	
	@RequestMapping(value="{numberingType}.do", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> recommend(
			@RequestParam(required=true) int bo_no
			,@PathVariable String numberingType
			){
		
		ServiceResult result = null;
		if("recommend".equals(numberingType)) {
			result = service.recommend(bo_no);
		}else if("report".equals(numberingType)) {
			result = service.report(bo_no);
		}
		Map<String, Object> resultMap = new HashMap<>();
		boolean success = ServiceResult.OK.equals(result);
		
		resultMap.put("success", success);
		if(success) {
			BoardVO board = service.retrieveBoard(BoardVO.builder().bo_no(bo_no).build());
			
			if("recommend".equals(numberingType)) {
				resultMap.put("recommend", board.getBo_rec());
			}else if("report".equals(numberingType)) {
				resultMap.put("report", board.getBo_rep());
			}
			
			
		}else {
			resultMap.put("message", "서버 오류로 인한 추천 실패");
		}
		
		return resultMap;
	}
	

}
