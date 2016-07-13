package com.lingshimall.lingshixiaomiao.db;

/**
 * 数据库
 * 
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class DataBaseHellper extends SQLiteOpenHelper {
	private static DataBaseHellper mDataBaseHelper;

	public synchronized static DataBaseHellper getInstance(Context context) {
		if (mDataBaseHelper == null) {
			mDataBaseHelper = new DataBaseHellper(context);
		}

		return mDataBaseHelper;
	}

	public DataBaseHellper(Context context) {
		super(context, "xiaomao.db", null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
//		int image[] = { R.drawable.yandan, R.drawable.bailianou,
//				R.drawable.qincai, R.drawable.zhalianhua, R.drawable.liyu,
//				R.drawable.shanyao };
		// 用户表
		db.execSQL("create table UserList (_id integer primary key autoincrement,userName ,image,PassWord,money double)");
		// 购物车表
		db.execSQL("create table ShoppingList (_id integer primary key autoincrement,userName,ProName,shopPrice double, proImg,proCount integer)");
		// 收藏表
		db.execSQL("create table CollectList (_id integer primary key autoincrement,userName,collectName ,collectPrice double,collectImg integer)");
		// 已经付款订单表
		// 待付款订单表
		db.execSQL("create table IndentList (_id integer primary key autoincrement,userName,indentName,indentPrice double,indentImage ,indentCount integer,indentSum double)");
		db.execSQL("create table WaitIndentList (_id integer primary key autoincrement,userName,indentName,indentPrice double,indentImage,indentCount integer,indentSum double)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
