package com.meshyog.hydra.enity.distributor;

import java.sql.Date;

public class WCanOrderInfo {

	private String orderId;
	private Date orderRequestedTime;
	private Long orderRequestedCustormerId;
	private String orderRequestedCustomerName;
	private String orderRequestedFrom;
	private String orderRequestedTo;
	private Long orderRequestedToId;
	private Integer orderedItemId;
	private String orderedItemName;
	private int orderedItemQuantity;
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public Date getOrderRequestedTime() {
		return orderRequestedTime;
	}
	public void setOrderRequestedTime(Date orderRequestedTime) {
		this.orderRequestedTime = orderRequestedTime;
	}
	public Long getOrderRequestedCustormerId() {
		return orderRequestedCustormerId;
	}
	public void setOrderRequestedCustormerId(Long orderRequestedCustormerId) {
		this.orderRequestedCustormerId = orderRequestedCustormerId;
	}
	public String getOrderRequestedCustomerName() {
		return orderRequestedCustomerName;
	}
	public void setOrderRequestedCustomerName(String orderRequestedCustomerName) {
		this.orderRequestedCustomerName = orderRequestedCustomerName;
	}
	public String getOrderRequestedFrom() {
		return orderRequestedFrom;
	}
	public void setOrderRequestedFrom(String orderRequestedFrom) {
		this.orderRequestedFrom = orderRequestedFrom;
	}
	public String getOrderRequestedTo() {
		return orderRequestedTo;
	}
	public void setOrderRequestedTo(String orderRequestedTo) {
		this.orderRequestedTo = orderRequestedTo;
	}
	public Long getOrderRequestedToId() {
		return orderRequestedToId;
	}
	public void setOrderRequestedToId(Long orderRequestedToId) {
		this.orderRequestedToId = orderRequestedToId;
	}
	public Integer getOrderedItemId() {
		return orderedItemId;
	}
	public void setOrderedItemId(Integer orderedItemId) {
		this.orderedItemId = orderedItemId;
	}
	public String getOrderedItemName() {
		return orderedItemName;
	}
	public void setOrderedItemName(String orderedItemName) {
		this.orderedItemName = orderedItemName;
	}
	public int getOrderedItemQuantity() {
		return orderedItemQuantity;
	}
	public void setOrderedItemQuantity(int orderedItemQuantity) {
		this.orderedItemQuantity = orderedItemQuantity;
	}
	
	
	
}
