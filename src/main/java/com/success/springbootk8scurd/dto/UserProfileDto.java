package com.success.springbootk8scurd.dto;

import java.util.List;

public class UserProfileDto {

	private int id;
	
	private String userName;
	
	private String lastName;
	
	private String firstName;
	
	private List<OrderDto> orderList;
	
	public UserProfileDto(){
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<OrderDto> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<OrderDto> orderList) {
		this.orderList = orderList;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	

}
