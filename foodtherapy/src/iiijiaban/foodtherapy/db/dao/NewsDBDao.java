package iiijiaban.foodtherapy.db.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import iiijiaban.foodtherapy.db.DatabaseHelper;
import iiijiaban.foodtherapy.db.FoodDB;
import iiijiaban.foodtherapy.db.NewsDB;

public class NewsDBDao extends BaseDao<NewsDB> {

	public NewsDBDao(Context context) {
		this.context = context;
		this.dbHelper = new DatabaseHelper(context);
	}

	// private long id;//��Ѷid
	// private String title; //��Ѷ����
	// private String tag ;//��Ѷ��ǩ tag
	// private String message ;//��Ѷ ��ϸ����
	// private String img ;//ͼƬ
	// private int count ;//�����ʱ
	// private String author; //����
	// private int focal; //�Ƿ񽹵�
	// private Date time ;//����ʱ��
	@Override
	public NewsDB build(Cursor c) {
		int columnid = c.getColumnIndex("id");
		int columntitle = c.getColumnIndex("title");
		int columntag = c.getColumnIndex("tag");
		int columnmessage = c.getColumnIndex("message");
		int columnimg = c.getColumnIndex("img");
		int columncount = c.getColumnIndex("count");
		int columnauthor = c.getColumnIndex("author");
		int columnfocal = c.getColumnIndex("focal");
		int columntime = c.getColumnIndex("time"); 
		NewsDB fooddb = new NewsDB();
		fooddb.setId(c.getLong(columnid));
		fooddb.setTitle(c.getString(columntitle));
		fooddb.setTag(c.getString(columntag));
		fooddb.setMessage(c.getString(columnmessage));
		fooddb.setImg(c.getString(columnimg));
		fooddb.setCount(c.getInt(columncount));

		fooddb.setAuthor(c.getString(columnauthor));
		fooddb.setFocal(c.getInt(columnfocal));
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
		Date date = null;
		try {
			date = sdf.parse(c.getString(columntime));
		} catch (ParseException e) { 
			e.printStackTrace();
		}
		fooddb.setTime(date);  
		return fooddb;
	}

	@Override
	public ContentValues deconstruct(NewsDB t) {
		ContentValues values = new ContentValues();
		values.put("id", t.getId());
		values.put("title", t.getTitle());
		values.put("tag", t.getTag());
		values.put("message", t.getMessage());
		values.put("img", t.getImg()); 
		values.put("count", t.getCount());
		values.put("author", t.getAuthor());
		values.put("focal", t.getFocal());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
		String date = sdf.format(t.getTime());
		values.put("time", date); 
		return values;
	}

	@Override
	public void insert(NewsDB t) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		ContentValues values = deconstruct(t);
		db.insert("newstable", null, values);
		db.close();

	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		db.delete("newstable", "id = " + id, null);
		db.close();
	}

	@Override
	public NewsDB select(long id) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		NewsDB newsDB = new NewsDB();
		Cursor cursor = db.query("newstable", null, "id = " + id, null, null,
				null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			newsDB = build(cursor);
			cursor.moveToNext();
		}
		db.close();
		return newsDB;
	}
	@Override
	public ArrayList<NewsDB> findAll() {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		ArrayList<NewsDB> list = new ArrayList<NewsDB>();
		Cursor cursor = db.query("newstable", null, null, null, null, null,
				null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			NewsDB newsDB = new NewsDB();
			newsDB = build(cursor);
			list.add(newsDB);
			cursor.moveToNext();
		}
		db.close();
		return list;
	}

	@Override
	public boolean isExits(long id) { 
		
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		NewsDB newsDB = null;
		Cursor cursor = db.query("newstable", null, "id = " + id, null, null,null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			newsDB = build(cursor);
			cursor.moveToNext();
		}
		db.close();
		if(newsDB!=null){
			return true;
		}else{
			return false;
		}
	}
}
