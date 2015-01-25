package com.sduc.db;

import com.sduc.db.DBContract.Table;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper{

	public DBHelper(Context context) {
		super(context, DBContract.DB_NAME, null, DBContract.DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		for (Table t : Table.values()) {
			db.execSQL(t.getSQLCreate());
		}		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		for (Table t : Table.values()) {
			db.execSQL(t.getSQLDelete());
		}
	    onCreate(db);
	}
	
}
