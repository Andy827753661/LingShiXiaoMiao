package com.lingshimall.lingshixiaomiao.db;

/**
 * 购物车 服务
 * 
 */
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.lingshimall.lingshixiaomiao.beans.ShoppingCar;

public class ShoppingMessage {
	/**
	 * 向数据库写数据
	 */
	public void addShopping(ShoppingCar shopping, Context context) {

		SQLiteDatabase db = DataBaseHellper.getInstance(context)
				.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put("ProName", shopping.getName());
		contentValues.put("shopPrice", shopping.getPrice());
		contentValues.put("proImg", shopping.getImage());
		contentValues.put("proCount", shopping.getCount());
		contentValues.put("userName", shopping.getUserName());
		// 向数据库存数据
		db.insert("ShoppingList", null, contentValues);
	}

	// 清空表中所有数据
	public void deleteAll(String username, Context context) {

		SQLiteDatabase db = DataBaseHellper.getInstance(context)
				.getWritableDatabase();

		db.delete("ShoppingList", "userName=?", new String[] { username });

	}

	// 删除数据
	public void delete(String name, String username, Context context) {

		SQLiteDatabase db = DataBaseHellper.getInstance(context)
				.getWritableDatabase();

		db.delete("ShoppingList", "ProName = ? and username=?", new String[] {
				name, username });

	}

	// 修改表中数据
	public void update(String name, String username, ShoppingCar shopping,
			Context context) {

		SQLiteDatabase db = DataBaseHellper.getInstance(context)
				.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put("ProName", shopping.getName());
		values.put("shopPrice", shopping.getPrice());
		values.put("proImg", shopping.getImage());
		values.put("proCount", shopping.getCount());
		values.put("userName", shopping.getUserName());
		db.update("ShoppingList", values, "ProName=? and username=?",
				new String[] { name, username });

	}

	/**
	 * 从数据库里取数据
	 * 
	 * @param context
	 * @return
	 */
	public List<ShoppingCar> getShopping(String username, Context context) {
		List<ShoppingCar> list = new ArrayList<ShoppingCar>();
		SQLiteDatabase db = DataBaseHellper.getInstance(context)
				.getReadableDatabase();
		if (db != null) {
			Cursor cursor = db.query("ShoppingList", null, "userName=?",
					new String[] { username }, null, null, null);
			while (cursor.moveToNext()) {
				ShoppingCar shopping = new ShoppingCar();
				shopping.setName(cursor.getString(cursor
						.getColumnIndex("ProName")));
				shopping.setPrice(cursor.getDouble(cursor
						.getColumnIndex("shopPrice")));
				shopping.setImage(cursor.getInt(cursor.getColumnIndex("proImg")));
				shopping.setCount(cursor.getInt(cursor
						.getColumnIndex("proCount")));
				shopping.setUserName(cursor.getString(cursor
						.getColumnIndex("userName")));
				list.add(shopping);
			}
			db.close();
			cursor.close();
		}
		return list;
	}
}
