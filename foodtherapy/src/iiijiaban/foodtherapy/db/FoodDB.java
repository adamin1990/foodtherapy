package iiijiaban.foodtherapy.db;

import java.io.Serializable;

import android.text.Html;
/**
 * ʳƷ����
 * http://api.yi18.net/food/show?id=1
 * @author Administrator
 *
 */
public class FoodDB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;//����
	private String img;//ͼƬ
	private String menu;//��Ч
	private String bar;//����
	private int count;//�������
	private long id;//ʳƷid
	private int rcount;
	private int fcount;
	private String summary;//ժҪ
	private String detailText;//���� 
	
	public int getRcount() {
		return rcount;
	}
	public void setRcount(int rcount) {
		this.rcount = rcount;
	}
	public int getFcount() {
		return fcount;
	}
	public void setFcount(int fcount) {
		this.fcount = fcount;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImg() {
		return  img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getMenu() {
		return menu;
	}
	public void setMenu(String menu) {
		this.menu = menu;
	}
	public String getBar() {
		return bar;
	}
	public void setBar(String bar) {
		this.bar = bar;
	}
	 
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getDetailText() {
		return detailText;
	}
	public void setDetailText(String detailText) {
		this.detailText = detailText;
	} 
}
