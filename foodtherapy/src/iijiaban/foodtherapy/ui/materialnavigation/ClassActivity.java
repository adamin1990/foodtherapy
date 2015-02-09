package iijiaban.foodtherapy.ui.materialnavigation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import ijiaban.foodtherapy.beans.Bar;
import ijiaban.foodtherapy.beans.CookClass;
import ijiaban.foodtherapy.dao.RecipeDao;
import ijiaban.foodtherapy.utils.adapters.CommonViewAdapter;
import ijiaban.foodtherapy.utils.http.HttpUtil;
import ijiaban.foodtherapy.wedgits.expandlistview.MyGridView; 
import ijiaban.foodtherapy.wedgits.expandlistview.SlideExpandableListView;

import com.iiijiaban.foodtherapy.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Telephony.Sms.Conversations;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

public class ClassActivity extends Activity{

	private SlideExpandableListView listview;
	List<CookClass> lists = new ArrayList<CookClass>();
	private CommonViewAdapter myadapter; 
	List<Map<CookClass, List<CookClass>>> cookclasses = new ArrayList<Map<CookClass,List<CookClass>>>();//ʳ�׷���
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO �����ʼ��
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_class_expandable_list);
		listview = (SlideExpandableListView) findViewById(R.id.list);
		initialAdapter();
		getData();
		listview.setAdapter(myadapter);
	}
	/**
	 * ��ʼ��adapter
	 */
	public void initialAdapter() {
		myadapter = new CommonViewAdapter(cookclasses,this) {
			@Override
			public View setView(View convertView, int position, ViewGroup parent) {
				// TODO ��ʼ��listview
				ViewHolder viewholder ;
				if(convertView == null){
					viewholder = new ViewHolder();
					convertView = getLayoutInflater().inflate(R.layout.item_class_expandable, null);
					viewholder.textview = (TextView) convertView.findViewById(R.id.text);
					viewholder.gridview =(MyGridView) convertView.findViewById(R.id.expandable);
					convertView.setTag(viewholder); 
				}else{
					viewholder = (ViewHolder) convertView.getTag();
				}
				CookClass cls = new CookClass();
//				List<CookClass> lists = new ArrayList<CookClass>();
				Iterator<Entry<CookClass, List<CookClass>>> it1 = ((Map<CookClass, List<CookClass>>)common_datas.get(position)).entrySet().iterator();
//				Iterator<List<CookClass>> it2 = (Iterator<List<CookClass>>) ((Map<CookClass, List<CookClass>>)common_datas.get(position)).values();
				while(it1.hasNext()){
					Entry<CookClass, List<CookClass>> entry = it1.next();
					cls = entry.getKey(); 
					lists = entry.getValue();
				} 
				viewholder.textview.setText(cls.getName()); 
				CommonViewAdapter comm = new CommonViewAdapter(lists,getApplicationContext()) {
					
					@Override
					public View setView(View convertView, final int position, ViewGroup parent) {
						// TODO 
						Button button ;
						if(convertView==null){
							convertView = common_inflater.inflate(R.layout.item_class_grid, null);
							button = (Button) convertView.findViewById(R.id.myclasses);
							convertView.setTag(button);
						}else{
							button = (Button) convertView.getTag();
						}
						button.setOnClickListener(new OnClickListener() {
							
							@Override
							public void onClick(View v) {
								// TODO ���
								CookClass menu = (CookClass) common_datas.get(position);
								Intent intent = new Intent(common_context, FoodListActivity.class);
								intent.putExtra("myclass", "cook/list");
								intent.putExtra("smallclass",menu.getId());
								startActivity(intent);
							}
						});
						button.setText(((CookClass)common_datas.get(position)).getName());
						return convertView;
					} 
					@Override
					public long setItemId(int position) {
						// TODO  
						return position;
					}
				};
				viewholder.gridview.setAdapter(comm);
				comm.notifyDataSetChanged();
				return convertView;
			}
			@Override
			public long setItemId(int position) {
				return position;
			}
		}; 
	}
	
	void getData(){
		new AsyncTask<Void, Integer, List<Map<CookClass, List<CookClass>>>>() {

			@Override
			protected List<Map<CookClass, List<CookClass>>> doInBackground(Void... params) {
				// TODO ��������
				String url ="http://api.yi18.net/cook/cookclass";
				List<Map<CookClass, List<CookClass>>> listmap = new ArrayList<Map<CookClass,List<CookClass>>>();
				try {
					String json = HttpUtil.getStringFromUrl(url);
					listmap = RecipeDao.getAllClass(json);
				} catch (IOException e) {
					// TODO �������
					e.printStackTrace();
				} 
				return listmap;
			}
			protected void onPostExecute(java.util.List<java.util.Map<CookClass,java.util.List<CookClass>>> result) {
				cookclasses = result;
				myadapter.refreshData(cookclasses);
			};
		}.execute();
	}
	public static  class ViewHolder{
		TextView textview;
		MyGridView gridview; 
	} 
}
