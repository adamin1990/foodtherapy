package iijiaban.foodtherapy.ui.materialnavigation;

import ijiaban.foodtherapy.fragment.CollectionMainFragment;
import ijiaban.foodtherapy.fragment.CookClassFragment;
import ijiaban.foodtherapy.fragment.FoodClassFragment;
import ijiaban.foodtherapy.fragment.NewsListFragment;
import ijiaban.foodtherapy.fragment.SearchFragment;

import com.iiijiaban.foodtherapy.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.SearchView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity implements NavigationDrawerCallbacks ,OnQueryTextListener{

    private Toolbar mToolbar;
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private String searchtype="news";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);  
        mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager().findFragmentById(R.id.fragment_drawer);
        mNavigationDrawerFragment.setup(R.id.fragment_drawer, (DrawerLayout) findViewById(R.id.drawer), mToolbar);
 
        getFragmentManager().beginTransaction().replace(R.id.container, new NewsListFragment()).commit(); 
 
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
//    	Intent intent = new Intent(this,SearchActivity.class);
//    	searchtype = getIntent().getStringExtra("searchtype");
//		intent.putExtra("searchtype", searchtype);
//		startActivity(intent); 
    	return super.onOptionsItemSelected(item);
    } 
     
    @Override 
    public void onNavigationDrawerItemSelected(int position) { 
    	switch (position) {
    	case 0:
    		getFragmentManager().beginTransaction().replace(R.id.container, NewsListFragment.newInstance()).commit();
    		break;
		case 1:
			 getFragmentManager().beginTransaction().replace(R.id.container, FoodClassFragment.newInstance()).commit();
			 break;
		case 2:
			 getFragmentManager().beginTransaction().replace(R.id.container, CookClassFragment.newInstance()).commit();
			 break;
		case 3:
			getFragmentManager().beginTransaction().replace(R.id.container, CollectionMainFragment.newInstance()).commit();
			 break;
		default:
			break;
		}
        Toast.makeText(this, "Menu item selected -> " + position, Toast.LENGTH_SHORT).show();
    }

	@Override
	public boolean onQueryTextChange(String newText) {
		return true;
	}

	@Override
	public boolean onQueryTextSubmit(String query) { 
		Intent intent = new Intent(this,SearchActivity.class);
		intent.putExtra("searchtype", getIntent().getStringExtra("searchtype"));
		intent.putExtra("keywords", query);
		startActivity(intent);
		return true;
	}
}