package menu;
/*
 	IDX       NOT NULL NUMBER         
	MENUNAME  NOT NULL VARCHAR2(100)  
	MENUIMAGE NOT NULL VARCHAR2(4000) 
	CATEGORY           VARCHAR2(100) 
 */
public class MenuDTO {
	private int idx;
	private String menuName;
	private String menuImage;
	private String category;
	
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuImage() {
		return menuImage;
	}
	public void setMenuImage(String menuImage) {
		this.menuImage = menuImage;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	
}
