package ijiaban.foodtherapy.beans;

import java.io.Serializable;
import java.util.Date;

/**
 * ��Ѷ����
 * http://api.yi18.net/news/show?id=7090
 * @author adamin
 *
 */
public class NewDetail implements Serializable{
	private static final long serialVersionUID = 1L;
	private long id;//��Ѷid
	private String title; //��Ѷ����
	private String tag ;//��Ѷ��ǩ tag
	private String message ;//��Ѷ ��ϸ����
	private String img ;//ͼƬ
	private int count ;//�����ʱ
	private String author; //����
	private int focal;  //�Ƿ񽹵� 
	private Date time ;//����ʱ�� 
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
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
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
