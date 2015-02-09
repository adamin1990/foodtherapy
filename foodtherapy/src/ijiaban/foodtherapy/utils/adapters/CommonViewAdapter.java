package ijiaban.foodtherapy.utils.adapters;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class CommonViewAdapter extends BaseAdapter{

	/**
	 * Ҫ���ص�����
	 */
	public List<?> common_datas = new ArrayList<Object>(); 
	
	public LayoutInflater      common_inflater;
	/**
	 * ������
	 */
	public Context common_context; 
	
	public CommonViewAdapter(List<?> list,Context context) {
		// TODO ��ʼֵ
		this.common_datas = list; 
		this.common_context = context;
//		this.common_inflater = LayoutInflater.from(context);
	} 
	@Override
	public int getCount() {
		// TODO ��ȡ����
		return common_datas.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO  ��ȡĳ��item������
		return common_datas.get(position);
	} 
	@Override
	public long getItemId(int position) {
		// TODO ��ȡ��ʶ
		return setItemId(position);
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO ��ʼ��ÿһ��item
		return setView(convertView, position, parent);
	}
	/**
	 * ��ʼ�� getView()
	 * @param convertView
	 * @param position
	 * @param parent
	 * @return
	 */
	public abstract View setView(View convertView, int position,ViewGroup parent);
	/**
	 * ��ȡitemid
	 * @param position
	 * @return
	 */
	public abstract long setItemId(int position );
	
	public void refreshData(List<?> datas) {
		this.common_datas = datas;
		notifyDataSetChanged();
	}  
}
