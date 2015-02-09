package ijiaban.foodtherapy.fragment;

import iijiaban.foodtherapy.ui.materialnavigation.NewsDetailActivity;
import ijiaban.foodtherapy.beans.NewsList;
import ijiaban.foodtherapy.consants.Constants;
import ijiaban.foodtherapy.dao.NewsDao;
import ijiaban.foodtherapy.listener.QuickReturnType;
import ijiaban.foodtherapy.listener.SpeedyQuickReturnListViewOnScrollListener;
import ijiaban.foodtherapy.utils.adapters.CommonViewAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import utils.QuickReturnUtils;
import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.iiijiaban.foodtherapy.R;
import com.squareup.picasso.Picasso;

public class NewsListFragment extends Fragment{
	private ListView listView;
	private TextView mText;
	private ImageView mImage;
	List<NewsList> beans = new ArrayList<NewsList>();
	CommonViewAdapter adapter;
	private int page = 1;
	private int perpage = 20;
	private String searchtype = "news";
	private ProgressBar progressbar;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.newsfragment, container, false);
		listView = (ListView) view.findViewById(R.id.newslistview);
//		getActivity().getActionBar().setTitle("��������");
		progressbar = (ProgressBar) view.findViewById(R.id.progressbar_news);
		mImage = (ImageView) view.findViewById(R.id.quick_return_footer_iv);
		initAdapter();
		getdata();
		listView.setAdapter(adapter);
		getActivity().getIntent().putExtra("searchtype", searchtype);
		mImage.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				listView.setSelection(0);
			}
		});
		return view;
	} 
	private void initAdapter() {
		adapter = new CommonViewAdapter(beans, getActivity()) {

			@Override
			public View setView(View convertView, int position, ViewGroup parent) {
				ViewHolder holder;
				if (convertView == null) {
					convertView = getActivity().getLayoutInflater().inflate(R.layout.item_newsfragment, null);
					holder = new ViewHolder();
					holder.count = (TextView) convertView.findViewById(R.id.newscount);
					holder.image = (ImageView) convertView.findViewById(R.id.newsimage);
					holder.title = (TextView) convertView.findViewById(R.id.newstitle);
					holder.time = (TextView) convertView.findViewById(R.id.publictime);
					convertView.setTag(holder);
				} else {
					holder = (ViewHolder) convertView.getTag();
				}
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				java.util.Date date = new java.util.Date();
				date = beans.get(position).getTime();
				String str = sdf.format(date);
				holder.count.setText(String.valueOf(beans.get(position).getCount()));
				holder.title.setText(beans.get(position).getTitle());
				holder.time.setText("����ʱ�䣺"+str);
				Picasso.with(getActivity())
						.load(beans.get(position).getImg())
						.centerCrop()
						.resize(QuickReturnUtils.dp2px(getActivity(), 346),QuickReturnUtils.dp2px(getActivity(), 320))
						.error(getResources().getColor(R.color.accent_material_dark))
						.into(holder.image);

				return convertView;
			}

			@Override
			public long setItemId(int position) {
				return 0;
			}

			@Override
			public void refreshData(List<?> datas) {
				super.refreshData(datas);
			}

		};
	}

	public static Fragment newInstance() {
		Fragment fr = new NewsListFragment();
		return fr;
	}

	@SuppressLint("NewApi")
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
				Intent intent = new Intent();
				intent.putExtra("id", beans.get(arg2).getId());
				intent.setClass(getActivity(), NewsDetailActivity.class);
				startActivity(intent);

			}
		});
		ArrayList<View> footerViews = new ArrayList<View>();

		mImage.setTag(R.id.scroll_threshold_key, 4);
		footerViews.add(mImage);

		SpeedyQuickReturnListViewOnScrollListener scrollListener = new SpeedyQuickReturnListViewOnScrollListener(
				getActivity(), QuickReturnType.GOOGLE_PLUS, null, footerViews) {

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// TODO Auto-generated method stub
				super.onScrollStateChanged(view, scrollState);
			}

			@Override
			public void onScroll(AbsListView listview, int firstVisibleItem,int visibleItemCount, int totalItemCount) {
				super.onScroll(listview, firstVisibleItem, visibleItemCount,totalItemCount);
				if (firstVisibleItem + visibleItemCount == totalItemCount) {
					getdata();
				}
			}

		};
		scrollListener.setSlideHeaderUpAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.slide_header_up));
		scrollListener.setSlideHeaderDownAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.slide_header_down));
		scrollListener.setSlideFooterUpAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.slide_footer_up));
		scrollListener.setSlideFooterDownAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.slide_footer_down));

		listView.setOnScrollListener(scrollListener);
	}

	private void getdata() {
		new AsyncTask<Void, Integer, List<NewsList>>() {
			@Override
			protected List<NewsList> doInBackground(Void... params) {
				// TODO ��������
				List<NewsList> list = new ArrayList<NewsList>();
				list = NewsDao.getnews(Constants.NEWS_COMMON_URL
						+ String.valueOf(page) + Constants.NEWS_LIMIT
						+ String.valueOf(perpage) + Constants.NEWS_TYPE + "id"
						+ Constants.NEWS_ID + "5");
				return list;
			}
			protected void onPostExecute(List<NewsList> result) {
				progressbar.setVisibility(View.GONE);
				beans.addAll(result);
				page += 1;
				adapter.refreshData(beans);
			};
		}.execute();
	} 
	class ViewHolder {
		public TextView title;
		public ImageView image;
		public TextView time;
		public TextView count;
	}
} 
