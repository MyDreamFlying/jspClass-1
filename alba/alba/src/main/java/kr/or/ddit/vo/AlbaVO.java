package kr.or.ddit.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(of="al_id")
@ToString
public class AlbaVO {
	private String al_id;
	private String al_name;
	private Integer al_age;
	private String al_zip;
	private String al_addr1;
	private String al_addr2;
	private String al_hp;
	private String gr_code;
	private String al_gen;
	private String al_mail;
	private String al_career;
	private String al_spec;
	private String al_desc;
	private String al_img;
}
