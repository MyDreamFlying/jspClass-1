package kr.or.ddit.board.dao;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.Reply2VO;

@Repository
public interface IReplyDAO {
	public int insertReply(Reply2VO reply);
	public int deleteReply(int rep_no);
	public Reply2VO selectReply(int rep_no);
}
