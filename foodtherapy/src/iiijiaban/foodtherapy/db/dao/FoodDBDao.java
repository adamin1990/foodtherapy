package iiijiaban.foodtherapy.db.dao;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import iiijiaban.foodtherapy.db.DatabaseHelper;
import iiijiaban.foodtherapy.db.FoodDB;

public class FoodDBDao extends BaseDao<FoodDB>{


	
	public FoodDBDao(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.dbHelper = new DatabaseHelper(context);
	} 
	@Override
	public FoodDB build(Cursor c) {
		// TODO Auto-generated method stub
		int columnid = c.getColumnIndex("id");
		int columnname = c.getColumnIndex("name");
		int columnmenu = c.getColumnIndex("menu");
		int columnbar = c.getColumnIndex("bar");
		int columnimg = c.getColumnIndex("img"); 
		int columncount = c.getColumnIndex("count");
		int columnsummary  = c.getColumnIndex("summary");
		int columndetailText  = c.getColumnIndex("detailText");
		int columnrcount  = c.getColumnIndex("rcount");
		int columnfcount  = c.getColumnIndex("fcount");
		FoodDB fooddb = new FoodDB(); 
		fooddb.setCount(c.getInt(columncount));
		fooddb.setId(c.getLong(columnid));
		fooddb.setImg(c.getString(columnimg)); 
		fooddb.setName(c.getString(columnname));
		fooddb.setBar(c.getString(columnbar));
		
		fooddb.setSummary(c.getString(columnsummary));
		fooddb.setDetailText(c.getString(columndetailText));
		fooddb.setFcount(c.getInt(columnfcount));
		fooddb.setMenu(c.getString(columnmenu));
		fooddb.setRcount(c.getInt(columnrcount));
		
		
		return fooddb; 
	}

	@Override
	public ContentValues deconstruct(FoodDB t) {
		ContentValues values = new ContentValues();
		values.put("id", t.getId());
		values.put("name", t.getName());
		values.put("menu", t.getMenu());
		values.put("bar", t.getBar());
		values.put("img", t.getImg());
		values.put("summary", t.getSummary());
		values.put("count", t.getCount());
		values.put("detailText", t.getDetailText());
		values.put("rcount", t.getRcount());
		values.put("fcount", t.getFcount());
		return values;
	} 
	@Override
	public void insert(FoodDB t) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		ContentValues values = deconstruct(t);
		db.insert("foodtable", null, values); 
		db.close();
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		db.delete("foodtable", "id = " + id, null);
		db.close();
	}

	@Override
	public FoodDB select(long id) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		FoodDB foodDB = new FoodDB();
		Cursor cursor = db.query("foodtable", null, "id = " + id, null, null, null, null);
		cursor.moveToFirst();
		while(!cursor.isAfterLast()){
			foodDB = build(cursor);
			cursor.moveToNext();
		} 
		db.close();
		return foodDB;
	}

	@Override
	public ArrayList<FoodDB> findAll() {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		ArrayList<FoodDB> list = new ArrayList<FoodDB>();
		Cursor cursor = db.query("foodtable", null, null, null, null, null, null);
		cursor.moveToFirst();
		while(!cursor.isAfterLast()){
			FoodDB foodDB = new FoodDB();
			foodDB = build(cursor);
			list.add(foodDB);
			cursor.moveToNext();
		} 
		db.close();
		return list;
	}
	@Override
	public boolean isExits(long id) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		FoodDB foodDB = null;
		Cursor cursor = db.query("foodtable", null, "id = " + id, null, null, null, null);
		cursor.moveToFirst();
		while(!cursor.isAfterLast()){
			foodDB = build(cursor);
			cursor.moveToNext();
		} 
		db.close();
		if(foodDB!=null){
			return true;
		}else{
			return false;
		} 
	}

}
