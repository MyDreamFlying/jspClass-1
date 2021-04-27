package kr.or.ddit.board.service;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import kr.or.ddit.board.dao.IReplyDAO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.Reply2VO;

@Service
public class ReplyServiceImpl implements IReplyService {
	private static final Logger logger = LoggerFactory.getLogger(BoardServiceImpl.class);
	
	@Inject
	private IReplyDAO dao;

	@Override
	public ServiceResult insertReply(Reply2VO reply) {
		return dao.insertReply(reply) == 1 ? ServiceResult.OK : ServiceResult.FAIL;
	}

	@Override
	public ServiceResult deleteReply(Reply2VO search) {
		
		Reply2VO reply = dao.selectReply(search.getRep_no());
		
		if(reply.getRep_pass().equals(search.getRep_pass())) {
			return dao.deleteReply(reply.getRep_no()) == 1 ? ServiceResult.OK : ServiceResult.FAIL;
		}else {
			return ServiceResult.INVALIDPASSWORD;
		}
		
	}

}
