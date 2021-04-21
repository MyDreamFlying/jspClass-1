package kr.or.ddit.board.service;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.board.dao.AttachDAOImpl;
import kr.or.ddit.board.dao.BoardDAOImpl;
import kr.or.ddit.board.dao.IAttachDAO;
import kr.or.ddit.board.dao.IBoardDAO;
import kr.or.ddit.db.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.CustomException;
import kr.or.ddit.utils.CryptoUtil;
import kr.or.ddit.vo.AttachVO;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

public class BoardServiceImpl implements IBoardService {
	
	private IBoardDAO boardDao = BoardDAOImpl.getInstance();
	private IAttachDAO attachDao = AttachDAOImpl.getInstance();
	private SqlSessionFactory sessionFactory = CustomSqlSessionFactoryBuilder.getSessionFactory();	
	private static BoardServiceImpl self;
	
	private BoardServiceImpl() {}
	public static BoardServiceImpl getInstance() {
		if(self == null) self = new BoardServiceImpl();
		return self;
	}
	
	private void encodePassword(BoardVO board) {
		if(StringUtils.isBlank(board.getBo_pass())) return;
		try {
			String encPass = CryptoUtil.sha512(board.getBo_pass());
			board.setBo_pass(encPass);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
	private int process(BoardVO board, SqlSession session) {
		String saveFolderUrl ="/Users/shane/Documents/GitHub/jspClass/attaches";
		File saveFolder = new File(saveFolderUrl);
		int cnt = 0;
		
		List<AttachVO> attachList = board.getAttachList();
		if(attachList != null && attachList.size()>0) {
			// 첨부파일들 metaData DB에 저장하기
			cnt += attachDao.insertAttaches(board, session);
			
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
		encodePassword(board); // 비밀번호 암호화
		
		try(
			SqlSession session = sessionFactory.openSession();
		){
			// board DB에 새글 등록
			int cnt = boardDao.insertBoard(board, session);
			
			if(cnt >0) {
				cnt += process(board, session);
				
				if(cnt > 0) {
					result = ServiceResult.OK;
					session.commit();
				}
			}
		}// TRY END
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
		try(
			SqlSession session = sessionFactory.openSession();
		){
			ServiceResult result = ServiceResult.FAIL;
			
			if(board.getDeleteAttachList()!=null && board.getDeleteAttachList().length > 0) {
				List<String> saveNames = attachDao.selectSaveNamesForDelete(board);
				// 첨부 파일의 메타 데이터 삭제
				attachDao.deleteAttaches(board, session);
				// 이진 데이터 삭제
				String saveFolder ="/Users/shane/Documents/GitHub/jspClass/attaches";
				for(String saveName : saveNames) {
					File saveFile = new File(saveFolder, saveName);
					saveFile.delete();
				}
			}
			
			int cnt = boardDao.updateBoard(board, session);
			if(cnt >0) {
				cnt += process(board, session);
				
				if(cnt > 0) {
					result = ServiceResult.OK;
					session.commit();
				}
			}
			return result;
			
		}
	}

	@Override
	public ServiceResult removeBoard(BoardVO search) {
		BoardVO board = boardDao.selectBoard(search);
		ServiceResult result = ServiceResult.FAIL;
		int cnt = 0;

		try(
				SqlSession session = sessionFactory.openSession();
		){
			// 해당글의 첨부파일 메타데이터 및 이진데이터 먼저 모두 삭제
			List<AttachVO> attList = board.getAttachList();
			if(attList != null) {
				final int SIZE = attList.size();
				int[] delAttNos = new int[SIZE];
				for(int i=0; i<SIZE; i++) {
					delAttNos[i] = attList.get(i).getAtt_no();
				}
				board.setDeleteAttachList(delAttNos);
						
				if(board.getDeleteAttachList()!=null && board.getDeleteAttachList().length > 0) {
					List<String> saveNames = attachDao.selectSaveNamesForDelete(board);
					// 첨부 파일의 메타 데이터 삭제
					attachDao.deleteAttaches(board, session);
					// 이진 데이터 삭제
					String saveFolder ="/Users/shane/Documents/GitHub/jspClass/attaches";
					for(String saveName : saveNames) {
						File saveFile = new File(saveFolder, saveName);
						saveFile.delete();
					}
				}
			}
			
			cnt = boardDao.deleteBoard(board, session);
			
			if(cnt >0) {
				result = ServiceResult.OK;
			}
			
			session.commit();
		}
		
		return result;
	}

	@Override
	public AttachVO download(int att_no) {
		AttachVO attach = attachDao.selectAttaches(att_no);
		if(attach == null)
			throw new CustomException(att_no+"에 해당하는 파일이 없음.");
		return attach;
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
