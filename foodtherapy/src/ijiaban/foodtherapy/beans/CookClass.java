package ijiaban.foodtherapy.beans;
/**
 * ʳ�׷���
 * http://api.yi18.net/cook/cookclass
 * @author Administrator
 *
 */
public class CookClass {

	private long id;//ʳ�׷���ID
	private String name;//ʳ�׷�������
	private int cookclass;//
	
	
	public int getCookclass() {
		return cookclass;
	}
	public void setCookclass(int cookclass) {
		this.cookclass = cookclass;
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
}
