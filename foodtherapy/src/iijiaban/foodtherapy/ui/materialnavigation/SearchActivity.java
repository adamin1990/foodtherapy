package iijiaban.foodtherapy.ui.materialnavigation;

import ijiaban.foodtherapy.fragment.CookClassFragment;
import ijiaban.foodtherapy.fragment.CookSearchFragment;
import ijiaban.foodtherapy.fragment.FoodClassFragment;
import ijiaban.foodtherapy.fragment.NewsListFragment;
import ijiaban.foodtherapy.fragment.NewsSearchFragment;
import ijiaban.foodtherapy.fragment.SearchFragment;

import com.iiijiaban.foodtherapy.R;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;
import android.widget.SearchView.OnQueryTextListener;

public class SearchActivity extends ActionBarActivity  implements OnQueryTextListener {

	private String searchtype = "";
	Fragment fragment;
	private Toolbar mToolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);

		mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbars);
		setSupportActionBar(mToolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		searchtype = getIntent().getStringExtra("searchtype");
		if (searchtype.equals("cook")) {
			fragment = new CookSearchFragment();
		} else if (searchtype.equals("food")) {
			fragment = new SearchFragment();
		} else {
			fragment = new NewsSearchFragment();
		}
		getFragmentManager().beginTransaction().replace(R.id.fragment_search, fragment).commit();
	}
	
	void updatefragment(){
		if (searchtype.equals("cook")) {
			fragment = new CookSearchFragment();
		} else if (searchtype.equals("food")) {
			fragment = new SearchFragment();
		} else {
			fragment = new NewsSearchFragment();
		}
		getFragmentManager().beginTransaction().replace(R.id.fragment_search, fragment).commit();
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
        searchView.setOnQueryTextListener(this);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO �˵�

		switch (item.getItemId()) { 
		case android.R.id.home:
			finish();
			break; 
		default:
			break;
		}
		return super.onOptionsItemSelected(item);

	}

	@Override
	public boolean onQueryTextChange(String newText) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onQueryTextSubmit(String query) {
		// TODO ��ѯ
		getIntent().putExtra("keywords", query);
		updatefragment(); 
		return false;
	}

}
