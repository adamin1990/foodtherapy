package iijiaban.foodtherapy.ui.materialnavigation;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import iiijiaban.foodtherapy.db.FoodDB;
import iiijiaban.foodtherapy.db.NewsDB;
import iiijiaban.foodtherapy.db.dao.FoodDBDao;
import iiijiaban.foodtherapy.db.dao.NewsDBDao;
import ijiaban.foodtherapy.beans.Food;
import ijiaban.foodtherapy.dao.FoodDao;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.iiijiaban.foodtherapy.R;
import com.squareup.picasso.Picasso;

public class FoodDetailActivity extends ActionBarActivity{
	private Toolbar mToolbar;
	private ImageView detailImage;
//	private TextView detailName;
	private TextView menu_bartext;
    private TextView content;
    private TextView summaryView;
    private Food food;
    private long id;   //ʳ��id   
    private String fileName;
    private final static String ALBUM_PATH  = Environment.getExternalStorageDirectory() + "/Foodthepy/";
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fooddetail_2);
		mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbars);
		setSupportActionBar(mToolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		detailImage = (ImageView) findViewById(R.id.img_activity_fooddetail);
		menu_bartext = (TextView) findViewById(R.id.txt_fooddetail_content); 
		summaryView = (TextView) findViewById(R.id.txt_summary);
		content = (TextView) findViewById(R.id.txt_content); 
		id=getIntent().getLongExtra("id", 0);
		getData();
	}
	private void getData() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					food = FoodDao.getFood(id);
					Message m = new Message();
					m.what = 0x123;
					handler.sendMessage(m);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 0x123: 
				setData();
				break; 
			default:
				break;
			}
		}
	};
	private void setData() {
		// ��ʾ����
		Picasso.with(this).load(food.getImg()).into(detailImage); 
		menu_bartext.setText(food.getMenu() + "\\\n" + food.getBar());
		summaryView.setText(Html.fromHtml(food.getSummary()));
		content.setText(Html.fromHtml(food.getDetailText()));
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) { 
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
			intent.putExtra(Intent.EXTRA_TEXT, "http://food.yi18.net/show/"+id);
			intent.setType("text/plain");
			startActivity(intent);
			break;
		case R.id.menu_collect:  
			FoodDBDao foodDBDao = new FoodDBDao(FoodDetailActivity.this);
			if(foodDBDao.isExits(food.getId())){
				Toast.makeText(FoodDetailActivity.this, "���ղ�",Toast.LENGTH_SHORT).show();
			}else{
				FoodDB foodDB = new FoodDB();
				if (food != null) { 
					foodDB.setBar(food.getBar());
					foodDB.setCount(food.getCount());
					foodDB.setDetailText(food.getDetailText());
					foodDB.setFcount(food.getFcount());
					foodDB.setId(food.getId());
					foodDB.setMenu(food.getMenu());
					foodDB.setName(food.getName());
					foodDB.setRcount(food.getCount());
					foodDB.setSummary(food.getSummary()); 
					try {
						detailImage.setDrawingCacheEnabled(true);
						Bitmap bitmap = Bitmap.createBitmap(detailImage.getDrawingCache());
						detailImage.setDrawingCacheEnabled(false);
						if (bitmap != null) {
							File dirFile = new File(ALBUM_PATH);
							if (!dirFile.exists()) {
								dirFile.mkdir();
							}
							int fileNameStart = food.getImg().lastIndexOf("/");
							fileName = food.getImg().substring(fileNameStart + 1);
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
				}
				foodDB.setImg(ALBUM_PATH + fileName);
				foodDBDao.insert(foodDB);
				Toast.makeText(FoodDetailActivity.this, "�ղ�",Toast.LENGTH_SHORT).show();
			} 
			break;
		default:
			break;
		}
		return false;
	}

}
