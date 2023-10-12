package com.success.springbootk8scurd.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OrderDto implements Serializable{

	private int id;
	
	private String orderNumber;
	
	private double orderAmount;
	
	private UserProfileDto userProfile;
	
	private List<ProductDto> productList = new ArrayList();
	
	public OrderDto() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public double getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(double orderAmount) {
		this.orderAmount = orderAmount;
	}

	public UserProfileDto getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfileDto userProfile) {
		this.userProfile = userProfile;
	}

	public List<ProductDto> getProductList() {
		return productList;
	}

	public void setProductList(List<ProductDto> productList) {
		this.productList = productList;
	}



}
