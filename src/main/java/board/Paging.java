package board;

public class Paging {
	
	private int page;			// 요청받은 페이지
	private int perPage;		// 페이지 당 출력할 게시글 수
	private int boardCount;		// 총 게시글 개수 (삭제 및 검색 반영)	
	private int offset;			// sql에서 사용할 offset 값 (건너뛸 개수)
	private int fetch;			// offset 이후 불러올 게시글 수 (==perPage)

	private int pageCount;		// 전체 페이지 개수
	private int section;		// 페이지를 10개씩 묶어서 하나의 구역으로 설정하고 0부터 시작
	private boolean prev;		// 이전 구역이 있으면 true
	private boolean next;		// 다음 구역이 있으면 true
	private int begin;			// 구역의 시작 페이지
	private int end;			// 구역의 끝 페이지 (pageCount보다 크면 안된다)
	
	// 생성자를 대신할 static method
	public static Paging newInstance(int page, int boardCount) {
		return new Paging(page, boardCount); 
	}
	
	private Paging(int page, int boardCount) { // boardCount에서 page내용만 불러오기
		this.page = page;
		this.boardCount = boardCount;
		
		perPage = 10;		// 여기 값을 바꾸면 화면에 출력되는 게시글의 개수가 바뀐다 !!
		offset = (page - 1) * perPage;
		fetch = perPage;
		
		pageCount = boardCount / perPage;
		pageCount += (boardCount % perPage != 0) ? 1 : 0;
		section = (page - 1) / 10;
		begin = section * 10 + 1;
		end = begin + 9;
		prev = section != 0;
		next = pageCount > end; 
		
		if(end > pageCount) {
			end = pageCount;
			next = false;
		}
	}
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPerPage() {
		return perPage;
	}
	public void setPerPage(int perPage) {
		this.perPage = perPage;
	}
	public int getBoardCount() {
		return boardCount;
	}
	public void setBoardCount(int boardCount) {
		this.boardCount = boardCount;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public int getFetch() {
		return fetch;
	}
	public void setFetch(int fetch) {
		this.fetch = fetch;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getSection() {
		return section;
	}
	public void setSection(int section) {
		this.section = section;
	}
	public boolean isPrev() {
		return prev;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}

	public int getBegin() {
		return begin;
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}
	
	
	
	
	
}
