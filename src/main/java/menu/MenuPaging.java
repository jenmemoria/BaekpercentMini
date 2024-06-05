package menu;

import menu.MenuPaging;

public class MenuPaging {
	
	private int page;		
	private int perPage;		
	private int boardCount;		
	private int offset;			
	private int fetch;			

	private int pageCount;	
	private int section;	
	private boolean prev;		
	private boolean next;		
	private int begin;		
	private int end;		
	
	public static MenuPaging newInstance(int page, int boardCount) {
		return new MenuPaging(page,boardCount);
	}
	
	private MenuPaging(int page, int boardCount) { 
		this.page = page;				
		this.boardCount = boardCount;	
		
		perPage = 8;		
		offset = (page - 1) * perPage;	
		fetch = perPage;			
		
		pageCount = boardCount / perPage;	
		pageCount += (boardCount % perPage != 0) ? 1 : 0;	
		section = (page - 1) / 8;			
		begin = section * 8 + 1;			
		end = begin + 7;					
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
