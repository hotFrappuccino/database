package perfectEscaper;

import java.sql.Date;

public class EscaperVO {
	private int num;
	private String name; 
	private String content;
	private Date esdate;

	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getEsdate() {
		return esdate;
	}
	public void setEsdate(Date esdate) {
		this.esdate = esdate;
	}
	@Override
	public String toString() {
		return "EscaperVO [num=" + num + ", name=" + name + ", content=" + content + ", esdate=" + esdate + "]";
	}
	


}
