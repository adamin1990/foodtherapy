package ijiaban.foodtherapy.beans;

import java.util.Date;

/**
 * ʳƷ��Ѷ�б� 
 * http://api.yi18.net/news/list?page=1&limit=10&type=id&id=5
 * @author adamin
 *
 */
public class NewsList {
	private long id;//��Ѷid
	private String title;//��Ѷ����
	private String tag;//��Ѷ��ǩtag
	private String img;//��ѶͼƬ
	private int count;//������� 
	private String author;//���� 
	private int focal;//�Ƿ��ǽ������ţ�0��һ�����ţ�1,����������
	private Date time;  //����ʱ�� 
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getImg() {
		return "http://www.yi18.net/"+img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getFocal() {
		return focal;
	}
	public void setFocal(int focal) {
		this.focal = focal;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	
	
	

}
