package kr.or.ddit.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import kr.or.ddit.dao.OtherDAO;
import kr.or.ddit.dao.OtherDAOImpl;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.mvc.annotation.Controller;
import kr.or.ddit.mvc.annotation.RequestMapping;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.BadRequestException;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
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
				throw new BadRequestException("You can only upload Image Files as your profile picture");
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
	
	@RequestMapping("/albaDelete.do")
	public String albaDelete(
			@RequestParam(value="al_id") String al_id
			) {
		ServiceResult result = service.deleteAlba(al_id);
		switch(result) {
		case OK:
			break;
		default:
			throw new BadRequestException();
		}
		return "redirect:/albaList.do";
	}
	
	@RequestMapping("/albaUpdate.do")
	public String albaUpdateForm(@RequestParam(value="al_id") String al_id
			, HttpServletRequest req) {
		addAttribute(req);
		AlbaVO alba = service.retrieveAlba(al_id);
		req.setAttribute("alba", alba);
		
		Set<LicenseVO> holdingLicenseSet = alba.getLicenseList();
		List<String> holdingLicenseList = new ArrayList<>();
		for(LicenseVO vo : holdingLicenseSet) {
			holdingLicenseList.add(vo.getLic_code());
		}
		req.setAttribute("holdingLicenseList", holdingLicenseList);
		
		return "alba/albaForm";
	}
	
	@RequestMapping(value="/albaUpdate.do", method=RequestMethod.POST)
	public String albaUpdate(
			@ModelAttribute("alba") AlbaVO alba,
			@RequestPart(value="profile", required=false) MultipartFile al_image
			, HttpServletRequest req) throws IOException {
		req.setAttribute("alba", alba);
		
		String saveFolderUrl = "profile";
		File saveFolder = new File(req.getServletContext().getRealPath(saveFolderUrl));
		
		if(al_image != null && !al_image.isEmpty()) {
			String mime = al_image.getContentType();
			if(!mime.startsWith("image/")) {
				throw new BadRequestException("You can only upload Image Files as your profile picture");
			}
			al_image.saveTo(saveFolder);
			alba.setAl_img(al_image.getUniqueSaveName());
		}
		
		// Check what qualifications the alba has and what qualifications are added
		AlbaVO originalAlba = service.retrieveAlba(alba.getAl_id());
		Set<LicenseVO> oldLicenseSet = originalAlba.getLicenseList();
		List<String> oldLicenseList = new ArrayList<>();
		for(LicenseVO vo : oldLicenseSet) {
			oldLicenseList.add(vo.getLic_code());
		}
		
		String view = null;
		ServiceResult result = service.updateAlba(alba);
		if(ServiceResult.OK.equals(result)) {
			
			// Check licenses whether they are checked or not
			for(Map<String, Object> map : licenseList) {
				String code = map.get("lic_code").toString();
				if("on".equals(req.getParameter(code)) && !oldLicenseList.contains(code) ) {
					LicenseVO license = new LicenseVO();
					license.setAl_id(alba.getAl_id());
					license.setLic_code(code);
					otherDao.insertLicense(license);
				}
			}
			
			view = "redirect:/albaView.do?al_id="+alba.getAl_id();
		}else {
			view = "alba/albaForm";
		}
		
		return view;
	}
	
	
}
