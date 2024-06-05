package board;

import java.sql.Date;

//IDX       NOT NULL NUMBER         
//BOARD_IDX NOT NULL NUMBER         
//WRITER    NOT NULL VARCHAR2(100)  
//CONTENT   NOT NULL VARCHAR2(4000) 
//WRITEDATE          DATE  

public class ReplyDTO {
	
	private int idx;
	private int board_idx;
	private String writer;
	private String content;
	private Date writeDate;
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public int getBoard_idx() {
		return board_idx;
	}
	public void setBoard_idx(int board_idx) {
		this.board_idx = board_idx;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}
	
	
	
	
	
}
