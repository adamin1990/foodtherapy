package ijiaban.foodtherapy.fragment;

import java.util.ArrayList;

import ijiaban.foodtherapy.wedgits.pagertab.PagerSlidingTabStrip;

import com.iiijiaban.foodtherapy.R;  

import android.app.Fragment;
import android.os.Bundle;  
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CollectionMainFragment extends Fragment{

	private MyPagerAdapter adapter; 
	private PagerSlidingTabStrip tabs;
	private ViewPager pager;
	
	@Override
	public void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState);
		
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		// TODO ��ʼ
		View view = inflater.inflate(R.layout.fragment_maincollection, container,false);
		setRetainInstance(true);
		tabs = (PagerSlidingTabStrip) view.findViewById(R.id.maintabs);
		tabs.setIndicatorColor(getResources().getColor(R.color.actionbar));
		tabs.setBackgroundColor(getResources().getColor(R.color.white));
		tabs.setDividerColor(getResources().getColor(R.color.actionbar));
		tabs.setTextColor(getResources().getColor(R.color.actionbar));
		tabs.setTextSize(30);
		tabs.setUnderlineHeight(6);
		tabs.setIndicatorHeight(15);
		pager = (ViewPager) view.findViewById(R.id.apager);
		pager.setOffscreenPageLimit(1);
		adapter = new MyPagerAdapter(((ActionBarActivity)getActivity()).getSupportFragmentManager());
		pager.setAdapter(adapter); 
		final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics());
		pager.setPageMargin(pageMargin);
		tabs.setViewPager(pager);
		return view;
	}
	public static Fragment newInstance() {
		   Fragment fr=new CollectionMainFragment();
		   return fr;
	} 
	public class MyPagerAdapter extends FragmentStatePagerAdapter {
		private ArrayList<android.support.v4.app.Fragment> fragmentList;
		CCookListFragment cookListFragment = new CCookListFragment();
		CNewsListFragment newsListFragment = new CNewsListFragment();
		CFoodListFragment foodListFragment = new CFoodListFragment(); 
		private final String[] TITLES = { "����","ʳƷ","ʳ��" };

		public MyPagerAdapter(FragmentManager fm) {
			super(fm);
			fragmentList = new ArrayList<android.support.v4.app.Fragment>();
			fragmentList.add(newsListFragment);
			fragmentList.add(foodListFragment);
			fragmentList.add(cookListFragment); 
		} 
		@Override
		public int getItemPosition(Object object) { 
			return super.getItemPosition(object);
		} 
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			return super.instantiateItem(container, position);
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object){
			super.destroyItem(container, position, object);
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return TITLES[position];
		}

		@Override
		public int getCount() {
			return TITLES.length;
		} 
		
		@SuppressWarnings("unchecked")
		@Override
		public android.support.v4.app.Fragment getItem(int position) { 
			return fragmentList.get(position); 
		}
	}
}
