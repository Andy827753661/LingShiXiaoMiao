package com.lingshimall.lingshixiaomiao.beans;

import android.content.Intent;

import java.net.URL;

/**
 * 购物车实体类
 * 
 */

public class ShoppingCar {
	private String name;
	private int image;
	private double price;
	private int count;
	private String userName;

	public String getName() {
		return name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public ShoppingCar() {
		super();
	}

	public void setName(String name) {
		this.name = name;
	}


	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}


	public int getImage() {
		return image;
	}

	public void setImage(int image) {
		this.image = image;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public ShoppingCar(String name, int image, double price, int count,
			String userName) {
		super();
		this.name = name;
		this.image = image;
		this.price = price;
		this.count = count;
		this.userName = userName;
	}

}
