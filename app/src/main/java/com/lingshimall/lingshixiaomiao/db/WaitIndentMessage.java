package com.lingshimall.lingshixiaomiao.db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.lingshimall.lingshixiaomiao.beans.Indent;

public class WaitIndentMessage {
	/**
	 * 代付款订单 服务
	 * 
	 */

	private DataBaseHellper dataBaseHellper;

	/**
	 * 插入 数据
	 * 
	 * @param context
	 * @param indent
	 */
	public void addIndent(Context context, Indent indent) {
		dataBaseHellper = new DataBaseHellper(context);
		SQLiteDatabase db = dataBaseHellper.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put("userName", indent.getUserName());
		contentValues.put("indentName", indent.getIndentName());
		contentValues.put("indentPrice", indent.getIndentPrice());
		contentValues.put("indentCount", indent.getIndentCount());
		contentValues.put("indentImage", indent.getIndentImage());
		contentValues.put("indentSum", indent.getIndentSum());
		if (db != null) {
			db.insert("WaitIndentList", null, contentValues);
			db.close();
		}
	}

	// 删除数据
	public void delete(String _id, String username, Context context) {

		dataBaseHellper = new DataBaseHellper(context);
		SQLiteDatabase db = dataBaseHellper.getWritableDatabase();
		db.delete("WaitIndentList", " _id = ? and userName = ?", new String[] {
				_id, username });

	}

	/**
	 * 
	 * 查询数据
	 * 
	 * @param context
	 * @return
	 */
	public List<Indent> getIndent(String useName, Context context) {
		List<Indent> list = new ArrayList<Indent>();
		dataBaseHellper = new DataBaseHellper(context);
		SQLiteDatabase db = dataBaseHellper.getReadableDatabase();
		if (db != null) {
			Cursor cursor = db.query("WaitIndentList", null, "userName=?",
					new String[] { useName }, null, null, null);
			while (cursor.moveToNext()) {
				Indent indent = new Indent();
				indent.setId(cursor.getInt(cursor.getColumnIndex("_id")));
				indent.setUserName(cursor.getString(cursor
						.getColumnIndex("userName")));
				indent.setIndentName(cursor.getString(cursor
						.getColumnIndex("indentName")));
				indent.setIndentPrice(cursor.getDouble(cursor
						.getColumnIndex("indentPrice")));
				indent.setIndentCount(cursor.getInt(cursor
						.getColumnIndex("indentCount")));
				indent.setIndentImage(cursor.getString(cursor
						.getColumnIndex("indentImage")));
				indent.setIndentSum(cursor.getDouble(cursor
						.getColumnIndex("indentSum")));
				list.add(indent);
			}
			db.close();
			cursor.close();
		}
		return list;
	}
}
