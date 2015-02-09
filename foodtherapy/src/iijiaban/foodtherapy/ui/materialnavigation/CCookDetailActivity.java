package iijiaban.foodtherapy.ui.materialnavigation;

import iiijiaban.foodtherapy.db.CookDB;
import iiijiaban.foodtherapy.db.dao.CookDBDao;

import com.iiijiaban.foodtherapy.R;
import com.squareup.picasso.Picasso;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class CCookDetailActivity extends ActionBarActivity {
	private Toolbar bar;
	private ImageView cookImage;
	private TextView cookName;
	private TextView cookTag;
	private TextView cookFood;
	private TextView message;
	private long id;
	private CookDB cook;
	private CookDBDao dbHelper;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cookdetail_2);
		dbHelper = new CookDBDao(this);
		bar = (Toolbar) findViewById(R.id.toolbar_actionbars);
		setSupportActionBar(bar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		cookImage = (ImageView) findViewById(R.id.img_cookdetail);
		cookName = (TextView) findViewById(R.id.txt_cookdetail_cookname);
		cookTag = (TextView) findViewById(R.id.txt_cookdetail_tag);
		cookFood = (TextView) findViewById(R.id.txt_cookdetail_food);
		message = (TextView) findViewById(R.id.txt_cookdetail_message);
		id = getIntent().getLongExtra("id", 0);
		getData();
	}
	public void getData(){
		new AsyncTask<Void, String, CookDB>(){

			@Override
			protected CookDB doInBackground(Void... params) {
				cook = dbHelper.select(id);
				return cook;
			}

			@Override
			protected void onPostExecute(CookDB result) {
				super.onPostExecute(result);
				setData(result);
			}
			
		}.execute();
	
	}
	
	public void setData(CookDB cook){
		Picasso.with(getApplicationContext()).load("file://" + cook.getImg()).into(cookImage);
        cookName.setText(cook.getName());
        cookTag.setText(cook.getTag());
        cookFood.setText(cook.getTag());
        message.setText(Html.fromHtml(cook.getMessage()));
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_cdetail, menu);
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
			intent.putExtra(Intent.EXTRA_TEXT, "http://cook.yi18.net/show/"+id);
			intent.setType("text/plain");
			startActivity(intent);
			break; 
		default:
			break;
		}
		return false;
	}
	
	

}
