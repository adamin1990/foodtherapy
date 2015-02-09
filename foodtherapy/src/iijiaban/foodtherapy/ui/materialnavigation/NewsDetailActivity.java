package iijiaban.foodtherapy.ui.materialnavigation;

import iiijiaban.foodtherapy.db.NewsDB;
import iiijiaban.foodtherapy.db.dao.NewsDBDao;
import ijiaban.foodtherapy.beans.NewDetail;
import ijiaban.foodtherapy.dao.NewsDao;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

import utils.QuickReturnUtils;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.iiijiaban.foodtherapy.R;
import com.squareup.picasso.Picasso;

public class NewsDetailActivity extends ActionBarActivity {
	private Toolbar mToolbar;
	private long id;
	private TextView title;
	private TextView time;
	private TextView author;
	private TextView count;
	
	private ImageView image;
	private TextView message;
	private NewDetail detail; 
	private String fileName;
	private final static String ALBUM_PATH  = Environment.getExternalStorageDirectory() + "/Foodthepy/";  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.newsdetailactivity);
		mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbars);
		setSupportActionBar(mToolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		Intent intent = getIntent();
		id = intent.getLongExtra("id", 1);
		initView();
		getdata();
	}

	private void getdata() {
		new AsyncTask<Void, String, NewDetail>() {

			@Override
			protected NewDetail doInBackground(Void... params) {
				detail = new NewDetail();
				try {
					detail = NewsDao.getnewdetail(id);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return detail;
			} 
			@Override
			protected void onPostExecute(NewDetail result) {
				super.onPostExecute(result);
				setData(result);
			}
		}.execute();
	}

	protected void setData(NewDetail result) {
		title.setText(result.getTitle());
        
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date date = new java.util.Date();
		date = result.getTime();
		String str = sdf.format(date);
		time.setText("����ʱ�䣺"+str);
		author.setText("���ߣ�"+result.getAuthor());
		count.setText("�������"+result.getCount());
		message.setText(Html.fromHtml(result.getMessage()));
		Picasso.with(this)
				.load(result.getImg())
				.centerCrop()
				.resize(QuickReturnUtils.dp2px(this, 346),QuickReturnUtils.dp2px(this, 320)).into(image);
	}

	private void initView() {
		
		title = (TextView) findViewById(R.id.news_title);
		time = (TextView) findViewById(R.id.display_time);
		author = (TextView) findViewById(R.id.display_author);
		count = (TextView) findViewById(R.id.display_count);
		image = (ImageView) findViewById(R.id.post_iv);
		message = (TextView) findViewById(R.id.message_tv);

	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO ��ʼ��menu
		
		getMenuInflater().inflate(R.menu.menu_detail, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			break; 
		case R.id.menu_share:
			Intent intent = new Intent();
			intent.setAction(Intent.ACTION_SEND);
			intent.putExtra(Intent.EXTRA_TEXT, "http://news.yi18.net/show/"+id);
			intent.setType("text/plain");
			startActivity(intent);
			break;
		case R.id.menu_collect: 
			NewsDBDao newsDBDao = new NewsDBDao(NewsDetailActivity.this);
			if(newsDBDao.isExits(detail.getId())){
				Toast.makeText(NewsDetailActivity.this, "���ղ�", Toast.LENGTH_SHORT).show();
			}else{
				NewsDB newsDB = new NewsDB();
				if (detail != null) {
					newsDB.setAuthor(detail.getAuthor());
					newsDB.setCount(detail.getCount());
					newsDB.setFocal(detail.getFocal());
					newsDB.setId(detail.getId());
					if(!detail.getImg().equals("http://www.yi18.net/null")){
					try {
						image.setDrawingCacheEnabled(true);
						Bitmap bitmap = Bitmap
								.createBitmap(image.getDrawingCache());
						image.setDrawingCacheEnabled(false);
						if (bitmap != null) {
							File dirFile = new File(ALBUM_PATH);
							if (!dirFile.exists()) {
								dirFile.mkdir();
							}
							int fileNameStart = detail.getImg().lastIndexOf("/");
							fileName = detail.getImg().substring(fileNameStart + 1);
							File myCaptureFile = new File(ALBUM_PATH + fileName);
							FileOutputStream fos = new FileOutputStream(myCaptureFile);
							BufferedOutputStream bos = new BufferedOutputStream(fos);
							bitmap.compress(Bitmap.CompressFormat.JPEG, 80, bos);
							bos.flush();
							bos.close();
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
					newsDB.setImg(ALBUM_PATH + fileName);
					}
					newsDB.setMessage(String.valueOf(detail.getMessage()));
					newsDB.setTag(String.valueOf(detail.getTag()));
					newsDB.setTime(detail.getTime());
					newsDB.setTitle(String.valueOf(detail.getTitle()));
				}
				newsDBDao.insert(newsDB);
				Toast.makeText(NewsDetailActivity.this, "�ղ�", Toast.LENGTH_SHORT).show();
			} 
			break;
		default:
			break;
		}
		return false;
	}
}
