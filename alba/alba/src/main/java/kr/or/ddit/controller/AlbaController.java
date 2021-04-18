package kr.or.ddit.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import kr.or.ddit.dao.OtherDAO;
import kr.or.ddit.dao.OtherDAOImpl;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.mvc.annotation.Controller;
import kr.or.ddit.mvc.annotation.RequestMapping;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.BadRequestException;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.mvc.annotation.resolvers.RequestPart;
import kr.or.ddit.mvc.filter.wrapper.MultipartFile;
import kr.or.ddit.service.AlbaService;
import kr.or.ddit.service.AlbaServiceImpl;
import kr.or.ddit.vo.AlbaVO;
import kr.or.ddit.vo.LicenseVO;

@Controller
public class AlbaController {
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
	
	@RequestMapping("/albaInsert.do")
	public String insertView(HttpServletRequest req) {
		addAttribute(req);
		return "alba/albaForm";
	}
	
	@RequestMapping(value="/albaInsert.do",method = RequestMethod.POST)
	public String albaInsert(
			@RequestPart(value="profile", required=false) MultipartFile al_image,
			@ModelAttribute("detailSearch") AlbaVO alba
			,HttpServletRequest req
			) throws IOException {
		
		String saveFolderUrl = "profile";
		File saveFolder = new File(req.getServletContext().getRealPath(saveFolderUrl));
		
		if(al_image != null && !al_image.isEmpty()) {
			String mime = al_image.getContentType();
			if(!mime.startsWith("image/")) {
				throw new BadRequestException("프로필 사진은 이미지 파일만 등록 가능 합니다.");
			}
			al_image.saveTo(saveFolder);
			alba.setAl_img(al_image.getUniqueSaveName());
		}
		
		String view = null;
		
		ServiceResult result = service.insertAlba(alba);
		if(ServiceResult.OK.equals(result)) {
			String newAl_id = alba.getAl_id();
			
			// Check licenses whether they are checked or not
			for(Map<String, Object> map : licenseList) {
				String code = map.get("lic_code").toString();
				if("on".equals(req.getParameter(code))) {
					// If specipic license is checked as new member has, register on LIC_ALBA table.
					LicenseVO license = new LicenseVO();
					license.setAl_id(newAl_id);
					license.setLic_code(code);
					otherDao.insertLicense(license);
				}
			}
			
			view = "redirect:/albaView.do?al_id="+newAl_id;
		}else {
			view = "alba/albaForm";
		}
		
		return view;
	}
	
}
