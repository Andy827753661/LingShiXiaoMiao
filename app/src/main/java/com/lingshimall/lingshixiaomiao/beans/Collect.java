package com.lingshimall.lingshixiaomiao.beans;

/**
 * 
 * 收藏夹实体类
 * 
 */
public class Collect {

	private String collectName;
	private double collectPrice;
	private String collectImage;
	private String username;

	public Collect() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Collect(String collectName, double collectPrice, String collectImage,
			String username) {
		super();
		this.collectName = collectName;
		this.collectPrice = collectPrice;
		this.collectImage = collectImage;
		this.username = username;
	}

	public String getCollectName() {
		return collectName;
	}

	public void setCollectName(String collectName) {
		this.collectName = collectName;
	}

	public double getCollectPrice() {
		return collectPrice;
	}

	public void setCollectPrice(double collectPrice) {
		this.collectPrice = collectPrice;
	}


	public String getCollectImage() {
		return collectImage;
	}

	public void setCollectImage(String collectImage) {
		this.collectImage = collectImage;
	}
}
