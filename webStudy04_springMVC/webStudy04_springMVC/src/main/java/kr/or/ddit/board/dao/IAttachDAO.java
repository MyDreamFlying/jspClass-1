package kr.or.ddit.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import kr.or.ddit.vo.AttachVO;
import kr.or.ddit.vo.BoardVO;

/**
 *	첨부파일 관리를 위한 persistence layer
 */
@Repository
@Mapper
public interface IAttachDAO {
	public int insertAttaches(BoardVO board);
	public AttachVO selectAttaches(int att_no);
	public List<String> selectSaveNamesForDelete(BoardVO board);
	public int deleteAttaches(BoardVO board);
}
