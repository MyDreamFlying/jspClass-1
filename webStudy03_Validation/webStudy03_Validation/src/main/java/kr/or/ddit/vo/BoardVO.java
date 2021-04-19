package kr.or.ddit.vo;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 공지글 / 게시글을 공통으로 관리할 domain layer
 */
@Data
@EqualsAndHashCode(of="bo_no")
@ToString(exclude={"attachList","replyList"})
public class BoardVO {
	private Integer bo_sort;
	private String bo_type;
	private Integer bo_no;
	private String bo_title;
	private String bo_writer;
	private String bo_pass;
	private String bo_content;
	private String bo_date;
	private Integer bo_hit;
	private Integer bo_rec;
	private Integer bo_rep;
	private String bo_seq;
	private Integer bo_parent;
	
	private int startAttNo;
	private List<AttachVO> attachList;
	private List<Reply2VO> replyList;
}
