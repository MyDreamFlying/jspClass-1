package kr.or.ddit.board.service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.Reply2VO;

public interface IReplyService {
	
	public ServiceResult insertReply(Reply2VO reply);
	public ServiceResult deleteReply(Reply2VO search);

}
