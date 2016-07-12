package com.lingshimall.lingshixiaomiao.db;

/**
 * 
 * 主页产品服务
 * 
 */
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.lingshimall.lingshixiaomiao.beans.Product;

public class ProductMessage {
	/**
	 * 向数据库写数据
	 * 
	 * @param product
	 * @param context
	 */
	public void addProdect(Product product, Context context) {

		SQLiteDatabase db = DataBaseHellper.getInstance(context)
				.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put("name", product.getName());
		contentValues.put("price", product.getPrice());
		contentValues.put("image", product.getImage());
		contentValues.put("count", product.getCount());
		contentValues.put("introduce", product.getIntroduce());
		// 向数据库存数据
		db.insert("ProductList", null, contentValues);
	}

	// 删除数据
	public void delete(String name, Context context) {

		SQLiteDatabase db = DataBaseHellper.getInstance(context)
				.getWritableDatabase();

		db.delete("ProductList", "name=?", new String[] { name });

	}

	// 修改表中数据
	public void update(int image, Product product, Context context) {

		SQLiteDatabase db = DataBaseHellper.getInstance(context)
				.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put("name", product.getName());
		values.put("price", product.getPrice());
		values.put("image", product.getImage());
		values.put("count", product.getCount());
		db.update("ProductList", values, "image=?", new String[] { image + "" });

	}

	/**
	 * 从数据库里取数据
	 * 
	 * @param context
	 * @return
	 */
	public List<Product> getProdect(Context context) {
		List<Product> list = new ArrayList<Product>();
		SQLiteDatabase db = DataBaseHellper.getInstance(context)
				.getReadableDatabase();
		if (db != null) {
			Cursor cursor = db.query("ProductList", null, null, null, null,
					null, null);
			while (cursor.moveToNext()) {
				Product product = new Product();
				product.setName(cursor.getString(cursor.getColumnIndex("name")));
				product.setPrice(cursor.getDouble(cursor
						.getColumnIndex("price")));
				product.setImage(cursor.getString(cursor.getColumnIndex("image")));
				product.setCount(cursor.getInt(cursor.getColumnIndex("count")));
				product.setIntroduce(cursor.getString(cursor
						.getColumnIndex("introduce")));
				list.add(product);
			}
			db.close();
			cursor.close();
		}
		return list;
	}
}