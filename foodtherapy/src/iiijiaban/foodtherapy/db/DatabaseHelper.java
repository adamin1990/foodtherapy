package iiijiaban.foodtherapy.db;

import java.util.Date;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{
	
	private static final String DB_NAME = "foodtherapy.db";
	private static final int VERSION = 1;

	public DatabaseHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}

	public DatabaseHelper(Context context) {
		//TODO ��ʼ�����ݿ�
		super(context, DB_NAME, null, VERSION);
	} 
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO �������ݿ��
		String foodtable = "create table foodtable (id long primary key ,name varchar(45),img varchar(45),menu varchar(255),bar varchar(255),count integer,rcount integer ,fcount integer,summary varchar(255),detailText varchar(255))";
		String cooktable = "create table cooktable (id long primary key ,name varchar(45),tag varchar(245),bar TEXT,img varchar(45),message varchar(255),count integer,food varchar(255))";
		String newstable = "create table newstable (id long primary key ,title varchar(245),tag varchar(245),message varchar(255),img varchar(45),count integer,author varchar(45),focal integer,time datetime)";
		db.execSQL(foodtable);
		db.execSQL(cooktable);
		db.execSQL(newstable);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO �������ݿ�
		db.execSQL("drop table if exists foodtable");
		db.execSQL("drop table if exists cooktable");
		db.execSQL("drop table if exists newstable");
		onCreate(db);
		
	}

}
