package ijiaban.foodtherapy.utils.adapters;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

public abstract class CommonPagerAdapter extends PagerAdapter {

	/**
	 * Ҫ���ص�����
	 */
	public List<?> common_datas = new ArrayList<Object>();

	protected LayoutInflater common_inflater;
	/**
	 * ������
	 */
	public Context common_context;

	public CommonPagerAdapter(List<?> list, Context context) {
		// TODO
		this.common_datas = list;
		this.common_context = context;
		common_inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO pager����
		return common_datas.size();
	} 
	
	@Override
	public void destroyItem(View container, int position, Object object) {
		// TODO Auto-generated method stub
		((ViewPager) container).removeView((View) object);  
	} 
	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0.equals(arg1);
	} 
	
	public void refreshData(List<?> datas) {
		this.common_datas = datas;
		notifyDataSetChanged();
	} 
	@Override
	public Object instantiateItem(View container, int position) {
		return setinstantiateItem(container,position);
	}
	
	public abstract Object setinstantiateItem(View container, int position) ;
	 
}
