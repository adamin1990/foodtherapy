package ijiaban.foodtherapy.beans;

import android.text.Html;
/**
 * ʳ������
 * @author adamin
 *
 */
public class Cook {
	public long id;//ʳ��ID
	public String name;//ʳ������
	public String tag;//ʳ����Ч��Ҫ����
	public String bar;//ʳƷ ��Ҫ�Ĺ���
	public String img;//ͼƬ
	public String message;//ʳ�׵���Ҫ����
	public int count;//�������
	private String food;//
   
	//	private int rcount;
//	private int fcount;
//	public int getRcount() {
//		return rcount;
//	}
//	public void setRcount(int rcount) {
//		this.rcount = rcount;
//	}
//	public int getFcount() {
//		return fcount;
//	}
//	public void setFcount(int fcount) {
//		this.fcount = fcount;
//	}
	
    public String getFood() {
		return food;
	}
	public void setFood(String food) {
		this.food = food;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getBar() {
		return bar;
	}
	public void setBar(String bar) {
		this.bar = bar;
	}
	public String getImg() {
		return "http://www.yi18.net/"+img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getMessage() {

		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
}
