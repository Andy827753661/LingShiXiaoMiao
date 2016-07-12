package com.lingshimall.lingshixiaomiao.db;

/**
 *
 * 用户服务
 * 
 * 
 */
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.lingshimall.lingshixiaomiao.beans.User;


public class UserMessage {
	/**
	 * 向数据库里写数据
	 * 
	 * 
	 */

	private DataBaseHellper dataBaseHellper;

	public void addUser(Context context, User user) {
		dataBaseHellper = new DataBaseHellper(context);
		SQLiteDatabase db = dataBaseHellper.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put("userName", user.getUserName());
		contentValues.put("image", user.getImg_url());
		contentValues.put("passWord", user.getPassword());
//		contentValues.put("money", user.getMoney());

		db.insert("UserList", null, contentValues);
		db.close();

	}

	// 修改表中数据
	public void update(int image, User user, Context context) {

		SQLiteDatabase db = DataBaseHellper.getInstance(context)
				.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put("userName", user.getUserName());
		values.put("image", user.getImg_url());
		values.put("passWord", user.getPassword());
//		values.put("money", user.getMoney());

		db.update("UserList", values, "image=?", new String[] { image + "" });

	}

	public List<User> getUser(Context context) {
		/**
		 * 
		 * 从数据库里取数据
		 * 
		 */
		List<User> list = new ArrayList<User>();
		dataBaseHellper = new DataBaseHellper(context);
		SQLiteDatabase db = dataBaseHellper.getReadableDatabase();
		if (db != null) {
			Cursor cursor = db.query("UserList", null, null, null, null, null,
					null);
			while (cursor.moveToNext()) {
				User user = new User();
				user.setUserName(cursor.getString(cursor
						.getColumnIndex("userName")));
				user.setImg_url(cursor.getString(cursor.getColumnIndex("image")));
				user.setPassword(cursor.getString(cursor
						.getColumnIndex("PassWord")));
//				user.setMoney(cursor.getDouble(cursor.getColumnIndex("money")));

				list.add(user);
			}
			db.close();
			cursor.close();
		}
		return list;
	}

	public void delete(String name, Context context) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = DataBaseHellper.getInstance(context)
				.getWritableDatabase();

		db.delete("UserList", "userName=?", new String[] { name });
	}
}