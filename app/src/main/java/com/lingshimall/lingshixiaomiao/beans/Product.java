package com.lingshimall.lingshixiaomiao.beans;

/**
 * 产品实体类
 * 
 */
public class Product {
	private String name;// 产品名字
	private double price;// 价格
	private String image;// 图片
	private String introduce;// 介绍
	private int count;// 数量

	public Product(String name, double price, String image, String introduce,
			int count) {
		super();
		this.name = name;
		this.price = price;
		this.image = image;
		this.introduce = introduce;
		this.count = count;
	}

	public Product() {
		super();
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getImage() {
		return image;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Product(String name, double price, String image, int count) {
		super();
		this.name = name;
		this.price = price;
		this.image = image;
		this.count = count;
	}

	@Override
	public String toString() {
		return "Product [name=" + name + ", price=" + price + ", image="
				+ image + ", introduce=" + introduce + ", count=" + count + "]";
	}

}
