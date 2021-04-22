package kr.or.ddit.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.mvc.annotation.Controller;
import kr.or.ddit.mvc.annotation.RequestMapping;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.mvc.annotation.resolvers.RequestPart;
import kr.or.ddit.mvc.filter.wrapper.MultipartFile;
import kr.or.ddit.service.FileService;
import kr.or.ddit.service.FileServiceImpl;
import kr.or.ddit.vo.LicenseVO;

@Controller
public class FileController {
	FileService service = FileServiceImpl.getInstance();

	@RequestMapping(value="/licUpload.do", method = RequestMethod.POST)
	public String licUpload(@RequestPart("licensePic") MultipartFile lic_img
			,@RequestParam("lic_code") String lic_code
			,@RequestParam("al_id") String al_id
			,HttpServletResponse resp
			) throws IOException {
		
		LicenseVO license = new LicenseVO();
		license.setAl_id(al_id);
		license.setLic_code(lic_code);
		license.setLic_img(lic_img.getBytes());
		
		ServiceResult result= service.licUpload(license);
		
		String response = "FAIL";
		try(
			PrintWriter out = resp.getWriter();
		){
			if(ServiceResult.OK.equals(result)) {
				response = "OK";
			}
			out.write(response);
		}
		
		return null;
	}
	
	@RequestMapping(value="/showLicPicture.do")
	public String showLicImgage(
			@RequestParam("lic_code") String lic_code
			,@RequestParam("al_id") String al_id
			,HttpServletResponse resp
			) throws IOException {
		
		LicenseVO license = new LicenseVO();
		license.setAl_id(al_id);
		license.setLic_code(lic_code);
		license = service.selectLicense(license);
		
		byte[] img = license.getLic_img();
		
		try(
			OutputStream out = resp.getOutputStream();
		){
			if(img != null)
				out.write(img);
		}
		
		return null;
	}
	
	
}
