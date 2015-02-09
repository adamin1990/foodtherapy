package ijiaban.foodtherapy.fragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import iijiaban.foodtherapy.ui.materialnavigation.FoodListActivity;
import iijiaban.foodtherapy.ui.materialnavigation.SearchActivity;
import ijiaban.foodtherapy.beans.Bar;
import ijiaban.foodtherapy.beans.Menu;
import ijiaban.foodtherapy.dao.FoodDao;
import ijiaban.foodtherapy.utils.adapters.CommonViewAdapter;
import ijiaban.foodtherapy.utils.http.HttpUtil;

import com.iiijiaban.foodtherapy.R;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;

public class FoodClassFragment extends Fragment implements OnQueryTextListener{ 
	private GridView menugrid; //��Ч�б�
	private GridView bargrid;  //�����б�
	
	private CommonViewAdapter menuadapter;
	private CommonViewAdapter baradapter;
	private List<Menu> menulist = new ArrayList<Menu>();
	private List<Bar> barlist = new ArrayList<Bar>();
	private String searchtype = "food";
	private ProgressBar progressbar_menuclass;
	private ProgressBar progressbar_barclass;
	
	
	 @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO ��ʼ������
		View view = inflater.inflate(R.layout.fragment_foodclass, container,false);
		getActivity().getIntent().putExtra("searchtype", searchtype);
		menugrid = (GridView) view.findViewById(R.id.menulist);
		bargrid  =  (GridView) view.findViewById(R.id.barlist);
		progressbar_menuclass = (ProgressBar) view.findViewById(R.id.progressbar_menuclass);
		progressbar_barclass = (ProgressBar) view.findViewById(R.id.progressbar_barclass);
		initMenuAdapter();
		initBarAdapter();
		setMenuData();
		setBarData();
		menugrid.setAdapter(menuadapter);
		bargrid.setAdapter(baradapter);
		
		
		return view;
	}
	 public static Fragment newInstance() {
		   Fragment fr=new FoodClassFragment();
		   return fr;
	}
	void initMenuAdapter(){
		 menuadapter = new CommonViewAdapter(menulist,getActivity()) {
			
			@Override
			public View setView(View convertView, final int position, ViewGroup parent) {
				// TODO ��ʼ����
				Button button; 
				if(convertView==null){
					convertView = getActivity().getLayoutInflater().inflate(R.layout.item_class_grid, null);
					button = (Button) convertView.findViewById(R.id.myclasses);
					convertView.setTag(button);
				}else{
					button = (Button) convertView.getTag();
				}
				button.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO ���
						Menu menu = (Menu) common_datas.get(position);
						Intent intent = new Intent(getActivity(), FoodListActivity.class);
						intent.putExtra("type", 2);
						intent.putExtra("searchtype", searchtype);
						intent.putExtra("myclass", "food/menulist");
						intent.putExtra("smallclass",menu.getId());
						startActivity(intent);
					}
				});
				button.setText( ((Menu) common_datas.get(position)).getName());
				return convertView;
			}
			@Override
			public long setItemId(int position){ 
				return position;
			}
		};
	}
	
	void initBarAdapter(){
		 baradapter = new CommonViewAdapter(barlist,getActivity()) {
			
			@Override
			public View setView(View convertView, final int position, ViewGroup parent) {
				// TODO ��ʼ����
				Button button; 
				if(convertView==null){
					convertView = getActivity().getLayoutInflater().inflate(R.layout.item_class_grid, null);
					button = (Button) convertView.findViewById(R.id.myclasses);
					convertView.setTag(button);
				}else{
					button = (Button) convertView.getTag();
				}
				button.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO ���
						Bar menu = (Bar) common_datas.get(position);
						Intent intent = new Intent(getActivity(), FoodListActivity.class);
						intent.putExtra("myclass", "food/barlist");
						intent.putExtra("searchtype", searchtype);
						intent.putExtra("type", 2);
						intent.putExtra("smallclass",menu.getId());
						startActivity(intent);
					}
				});
				button.setText( ((Bar) common_datas.get(position)).getName());
				return convertView;
			}
			@Override
			public long setItemId(int position) { 
				return position;
			}
		};
	} 	 
	
	void setMenuData(){
		new AsyncTask<Void, Integer, List<Menu>>() {

			@Override
			protected List<Menu> doInBackground(Void... params) {
				// TODO ��������
				String menuurl = "http://api.yi18.net/food/menu";  
				List<Menu> list = new ArrayList<Menu>();
				try {
					String menujson = HttpUtil.getStringFromUrl(menuurl); 
					list = FoodDao.MenuJsonToList(menujson);
				} catch (IOException e) {
					// TODO �������
					e.printStackTrace();
				} 
				return list;
			}
			protected void onPostExecute(List<Menu> result) {
				menulist = result;
				progressbar_menuclass.setVisibility(View.GONE);
				menuadapter.refreshData(menulist);
			};
		}.execute();
	}
	
	void setBarData(){
		new AsyncTask<Void, Integer, List<Bar>>() {

			@Override
			protected List<Bar> doInBackground(Void... params) {
				// TODO ��������
				String barurl = "http://api.yi18.net/food/bar";  
				List<Bar> list = new ArrayList<Bar>();
				try {
					String barjson = HttpUtil.getStringFromUrl(barurl); 
					list = FoodDao.BarJsonToList(barjson);
				} catch (IOException e) {
					// TODO �������
					e.printStackTrace();
				} 
				return list;
			}
			protected void onPostExecute(List<Bar> result) {
				barlist = result;
				progressbar_barclass.setVisibility(View.GONE);
				baradapter.refreshData(barlist);
			};
		}.execute();
	}
	@Override
	public void onCreateOptionsMenu(android.view.Menu menu,
			MenuInflater inflater) {
		// TODO �˵�
		inflater.inflate(R.menu.main, menu); 
		SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
	    searchView.setOnQueryTextListener(this);
		super.onCreateOptionsMenu(menu, inflater);
	} 
@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub 
		return super.onOptionsItemSelected(item);
	}
@Override
public boolean onQueryTextChange(String newText) {
	// TODO Auto-generated method stub
	return false;
}
@Override
public boolean onQueryTextSubmit(String query) {
	// TODO Auto-generated method stub
	Intent intent = new Intent(getActivity(),SearchActivity.class);
	intent.putExtra("searchtype", searchtype);
	intent.putExtra("keywords", query);
	startActivity(intent);
	return false;
} 
}
