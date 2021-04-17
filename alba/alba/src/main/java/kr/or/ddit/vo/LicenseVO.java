package kr.or.ddit.vo;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(exclude="lic_img")
public class LicenseVO {
	@NotBlank
	private String al_id;
	@NotBlank
	private String lic_code;
	@NotBlank
	private String lic_name;
	private String lic_date;
	private transient byte[] lic_img;
}
