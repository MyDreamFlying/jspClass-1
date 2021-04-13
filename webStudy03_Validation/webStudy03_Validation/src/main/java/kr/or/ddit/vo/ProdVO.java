package kr.or.ddit.vo;
import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import kr.or.ddit.validator.InsertGroup;
import kr.or.ddit.validator.UpdateGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(of="prod_id")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(of= {"prod_id", "prod_name", "prod_lgu"})
public class ProdVO implements Serializable {
	private int rnum;
	@NotBlank(groups=UpdateGroup.class)
	private String prod_id;
	@NotBlank
	private String prod_name;
	@NotBlank
	private String prod_lgu;
	private String lprod_nm;
	@NotBlank
	private String prod_buyer;
	@NotNull
	@Min(0)
	private int prod_cost;
	@Min(0)
	private int prod_price;
	@Min(0)
	private int prod_sale;
	private String prod_outline;
	private String prod_detail;
	@NotBlank(groups = InsertGroup.class)
	private String prod_img;
	@Min(0)
	private int prod_totalstock;
	private String prod_insdate;
	@Min(0)
	private int prod_properstock;
	private String prod_size;
	private String prod_color;
	private String prod_delivery;
	private String prod_unit;
	@Min(0)
	private int prod_qtyin;
	@Min(0)
	private int prod_qtysale;
	private int prod_mileage;
	private BuyerVO buyer;	// has a(1:1) 관계
	
	
}