package kr.or.ddit.board.service;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import kr.or.ddit.board.dao.AttachDAOImpl;
import kr.or.ddit.board.dao.BoardDAOImpl;
import kr.or.ddit.board.dao.IAttachDAO;
import kr.or.ddit.board.dao.IBoardDAO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.utils.CryptoUtil;
import kr.or.ddit.vo.AttachVO;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

public class BoardServiceImpl implements IBoardService {
	
	private IBoardDAO boardDao = BoardDAOImpl.getInstance();
	private IAttachDAO attachDao = AttachDAOImpl.getInstance();
	private static BoardServiceImpl self;
	
	private BoardServiceImpl() {}
	public static BoardServiceImpl getInstance() {
		if(self == null) self = new BoardServiceImpl();
		return self;
	}
	
	private void encodePassword(BoardVO board) {
		try {
			String encPass = CryptoUtil.sha512(board.getBo_pass());
			board.setBo_pass(encPass);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
	private int process(BoardVO board) {
		String saveFolderUrl ="/Users/shane/Documents/GitHub/jspClass/attaches";
		File saveFolder = new File(saveFolderUrl);
		int cnt = 0;
		
		List<AttachVO> attachList = board.getAttachList();
		if(attachList != null && attachList.size()>0) {
			// 첨부파일들 metaData DB에 저장하기
			cnt += attachDao.insertAttaches(board);
			
			// 첨부파일들의 binary데이터를 파일시스템에 저장하기
			try {
				for(AttachVO attach : attachList) {
					attach.saveTo(saveFolder);
				}
				
			} catch(IOException e) {
				throw new RuntimeException();
			}
		}
		return cnt;
	}

	@Override
	public ServiceResult createBoard(BoardVO board) {
		ServiceResult result = ServiceResult.FAIL;
		
		// 비밀번호 암호화
		encodePassword(board);
		
		// board DB에 새글 등록
		int cnt = boardDao.insertBoard(board);
		
		
		if(cnt >0) {
			cnt += process(board);
			
			if(cnt > 0)
				result = ServiceResult.OK;
		}
		
		return result;
	}

	@Override
	public int retrieveBoardCount(PagingVO<BoardVO> pagingVO) {
		return boardDao.selectBoardCount(pagingVO);
	}

	@Override
	public List<BoardVO> retrieveBoardList(PagingVO<BoardVO> pagingVO) {
		return boardDao.selectBoardList(pagingVO);
	}

	@Override
	public BoardVO retrieveBoard(BoardVO search) {
		return boardDao.selectBoard(search);
	}

	@Override
	public ServiceResult modifyBoard(BoardVO board) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResult removeBoard(BoardVO search) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AttachVO download(int att_no) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean boardAuthenticate(BoardVO search) {
		BoardVO saved = boardDao.selectBoard(search);
		encodePassword(search);
		String savedPass = saved.getBo_pass();
		String inputPass = search.getBo_pass();
		return savedPass.equals(inputPass);
	}

}
