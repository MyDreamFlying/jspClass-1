package kr.or.ddit.vo;

import java.util.Set;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(of="al_id")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AlbaVO {
	@NotBlank
	private String al_id;
	@NotBlank
	private String al_name;
	@NotBlank
	private Integer al_age;
	@NotBlank
	private String al_zip;
	@NotBlank
	private String al_addr1;
	@NotBlank
	private String al_addr2;
	@NotBlank
	private String al_hp;
	@NotBlank
	private String gr_code;
	@NotBlank
	private String gr_name;
	@NotBlank
	private String al_gen;
	@NotBlank
	private String al_mail;
	private String al_career;
	private String al_spec;
	private String al_desc;
	private String al_img;
	
	private Set<LicenseVO> licenses;
	
}
