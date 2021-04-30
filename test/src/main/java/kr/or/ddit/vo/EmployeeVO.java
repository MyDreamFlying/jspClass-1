package kr.or.ddit.vo;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
@EqualsAndHashCode(of={"employee_id"})
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String employee_id;
	private String employee_pwd;
	private String employee_name;
	private String employee_phone;
	private String employee_email;
	private String employee_authority;
	private String employee_picture;
	
}