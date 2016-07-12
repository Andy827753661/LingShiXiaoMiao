package com.lingshimall.lingshixiaomiao.db;

/**
 * 
 * 收藏夹 服务
 */
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.lingshimall.lingshixiaomiao.beans.Collect;

public class CollectMessage {
	private DataBaseHellper dataBaseHellper;

	public void addCollect(Context context, Collect collect) {
		dataBaseHellper = new DataBaseHellper(context);
		SQLiteDatabase db = dataBaseHellper.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put("userName", collect.getUsername());
		contentValues.put("collectName", collect.getCollectName());
		contentValues.put("collectPrice", collect.getCollectPrice());
		contentValues.put("collectImg", collect.getCollectImage());
		if (db != null) {
			db.insert("CollectList", null, contentValues);
			db.close();
		}
	}

	// 删除数据
	public void delete(String username, String name, Context context) {
		dataBaseHellper = new DataBaseHellper(context);
		SQLiteDatabase db = dataBaseHellper.getWritableDatabase();
		db.delete("CollectList", "userName=? and collectName=?", new String[] {
				username, name });

	}

	public List<Collect> getCollect(String username, Context context) {
		List<Collect> list = new ArrayList<Collect>();
		dataBaseHellper = new DataBaseHellper(context);
		SQLiteDatabase db = dataBaseHellper.getReadableDatabase();
		if (db != null) {
			Cursor cursor = db.query("CollectList", null, "userName=?",
					new String[] { username }, null, null, null);
			while (cursor.moveToNext()) {
				Collect collect = new Collect();
				collect.setCollectName(cursor.getString(cursor
						.getColumnIndex("collectName")));
				collect.setCollectPrice(cursor.getDouble(cursor
						.getColumnIndex("collectPrice")));
				collect.setCollectImage(cursor.getString(cursor
						.getColumnIndex("collectImg")));
				collect.setUsername(cursor.getString(cursor
						.getColumnIndex("userName")));
				list.add(collect);
			}
			db.close();
			cursor.close();
		}
		return list;
	}
}
