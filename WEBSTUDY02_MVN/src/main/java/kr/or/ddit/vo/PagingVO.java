package kr.or.ddit.vo;

public class PagingVO {
	private int totalRecord;
	private int screenSize;
	private int blockSize;
	private int currentPage;
	
	private int totalPage;
	private int startRow;
	private int endRow;
	private int startPage;
	private int endPage;
	
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
		totalPage = totalRecord % screenSize == 0 ? totalRecord / screenSize : totalRecord / screenSize + 1;
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
		startRow = (currentPage - 1) * screenSize;
		endRow = currentPage * screenSize;
		
		endPage = (currentPage + (blockSize -1)) / blockSize * blockSize;
		startPage = endPage - (blockSize - 1);
		
	}
	
}
