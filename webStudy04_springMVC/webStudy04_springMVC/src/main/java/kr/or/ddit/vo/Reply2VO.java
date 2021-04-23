package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(of="rep_no")
@ToString(exclude= {"attatchList","replyList"})
public class Reply2VO implements Serializable{
	private Integer bo_no;
	private Integer rep_no;
	private String rep_writer;
	private String rep_pass;
	private String rep_content;
	private String rep_date;
	
	private List<AttachVO> attatchList;
	private List<Reply2VO> replyList;
}
