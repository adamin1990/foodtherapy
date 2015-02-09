package iiijiaban.foodtherapy.db.dao;

import java.util.ArrayList;
import java.util.List;

import iiijiaban.foodtherapy.db.CookDB;
import iiijiaban.foodtherapy.db.DatabaseHelper;
import ijiaban.foodtherapy.beans.Cook;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class CookDBDao extends BaseDao<CookDB>{
	 
	
	public CookDBDao(Context context) {
		// TODO ��ȡ���ݿ�
		this.context = context;
		this.dbHelper = new DatabaseHelper(context); 
	}

	public void insert(CookDB cookdb){
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		ContentValues values = deconstruct(cookdb);
		db.insert("cooktable", null, values); 
		db.close();
	} 
	
	public void delete(long id){
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		db.delete("cooktable", "id = " + id, null);
		db.close();
	}
	
	public CookDB select(long id){
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		CookDB cookDB = new CookDB();
		Cursor cursor = db.query("cooktable", null, "id = " + id, null, null, null, null);
		cursor.moveToFirst();
		while(!cursor.isAfterLast()){
			cookDB = build(cursor);
			cursor.moveToNext();
		} 
		db.close();
		return cookDB;
	}
	
	public  ArrayList<CookDB> findAll(){
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		ArrayList<CookDB> list = new ArrayList<CookDB>();
		Cursor cursor = db.query("cooktable", null, null, null, null, null, null);
		cursor.moveToFirst();
		while(!cursor.isAfterLast()){
			CookDB cookDB = new CookDB();
			cookDB = build(cursor);
			list.add(cookDB);
			cursor.moveToNext();
		} 
		db.close();
		return list;
	}
	
	@Override
	public CookDB build(Cursor c) {
		int columnid = c.getColumnIndex("id");
		int columnname = c.getColumnIndex("name");
		int columntag = c.getColumnIndex("tag");
		int columnbar = c.getColumnIndex("bar");
		int columnimg = c.getColumnIndex("img");
		int columnmessage = c.getColumnIndex("message");
		int columncount = c.getColumnIndex("count");
		int columnfood  = c.getColumnIndex("food");
		CookDB cookdb = new CookDB();
		cookdb.setBar(c.getString(columnbar));
		cookdb.setCount(c.getInt(columncount));
		cookdb.setFood(c.getString(columnfood));
		cookdb.setId(c.getLong(columnid));
		cookdb.setImg(c.getString(columnimg));
		cookdb.setMessage(c.getString(columnmessage));
		cookdb.setName(c.getString(columnname));
		cookdb.setTag(c.getString(columntag));
		return cookdb;
	}

	@Override
	public ContentValues deconstruct(CookDB t) { 
		ContentValues values = new ContentValues();
		values.put("id", t.getId());
		values.put("name", t.getName());
		values.put("tag", t.getTag());
		values.put("bar", t.getBar());
		values.put("img", t.getImg());
		values.put("message", t.getMessage());
		values.put("count", t.getCount());
		values.put("food", t.getFood());
		return values;
	}

	@Override
	public boolean isExits(long id) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		CookDB cookDB = null ;
		Cursor cursor = db.query("cooktable", null, "id = " + id, null, null, null, null);
		cursor.moveToFirst();
		while(!cursor.isAfterLast()){
			cookDB = build(cursor);
			cursor.moveToNext();
		} 
		db.close();
		if(cookDB!=null){
			return true;
		}else{
			return false;
		} 
	}

}
