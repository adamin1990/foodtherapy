package ijiaban.foodtherapy.beans;
/**
 * ʳ������
 * 
 * http://api.yi18.net/cook/show?id=1
 * @author Administrator
 *
 */
public class Recipe {

	private long id;//ʳ��ID
	private String name;//ʳ������
	private String tag;//ʳ����Ч��Ҫ���� 
	private String bar;//ʳƷ ��Ҫ�Ĺ���
	private String img;//ͼƬ
	private String message;//ʳ�׵���Ҫ����
	private int count ;//�������
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
