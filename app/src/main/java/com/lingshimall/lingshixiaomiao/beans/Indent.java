package com.lingshimall.lingshixiaomiao.beans;

import java.net.URL;

/**
 * 
 * 订单实体类
 * 
 * 
 */
public class Indent {
	private String userName;
	private String indentName;
	private double indentPrice;
	private int indentCount;
	private double indentSum;
	private String indentImage;
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public String getIndentImage() {
		return indentImage;
	}

	public void setIndentImage(String indentImage) {
		this.indentImage = indentImage;
	}

	public Indent() {
		super();
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Indent(String userName, String indentName, double indentPrice,
			int indentCount, double indentSum, String indentImage) {
		super();
		this.userName = userName;
		this.indentName = indentName;
		this.indentPrice = indentPrice;
		this.indentCount = indentCount;
		this.indentSum = indentSum;
		this.indentImage = indentImage;
	}

	public String getIndentName() {
		return indentName;
	}

	public void setIndentName(String indentName) {
		this.indentName = indentName;
	}

	public double getIndentPrice() {
		return indentPrice;
	}

	public void setIndentPrice(double indentPrice) {
		this.indentPrice = indentPrice;
	}

	public int getIndentCount() {
		return indentCount;
	}

	public void setIndentCount(int indentCount) {
		this.indentCount = indentCount;
	}

	public double getIndentSum() {
		return indentSum;
	}

	public void setIndentSum(double indentSum) {
		this.indentSum = indentSum;
	}

}
