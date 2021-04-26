package kr.or.ddit.vo;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.validator.BoardInsertGroup;
import kr.or.ddit.validator.UpdateGroup;
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
	@NotBlank
	private String bo_type;
	@NotNull(groups=UpdateGroup.class)
	@Min(value=1, groups=UpdateGroup.class)
	private Integer bo_no;
	@NotBlank
	private String bo_title;
	@NotBlank(groups = BoardInsertGroup.class)
	private String bo_writer;
	@NotBlank(groups = BoardInsertGroup.class)
	private String bo_pass;
	private String bo_content;
	private String bo_date;
	private Integer bo_hit;
	private Integer bo_rec;
	private Integer bo_rep;
	private String bo_sec;
	private Integer bo_parent;
	
	private int startAttNo;
	private List<AttachVO> attachList;
	private MultipartFile[] bo_files;
	public void setBo_files(MultipartFile[] bo_files) {
		this.bo_files = bo_files;
		if(bo_files != null) {
			List<AttachVO> attachList = new ArrayList<>();
			for(MultipartFile file : bo_files) {
				// BoardVO에 AttachVO들 등록
				if(!file.isEmpty()) {
					AttachVO attachFile = new AttachVO(file);
					attachList.add(attachFile);
				}
			}
			if(attachList.size() > 0)
				this.attachList = attachList;
		}
	}
	
	private int[] deleteAttachList;
	private List<Reply2VO> replyList;
	
	private String thumbnail;
}
