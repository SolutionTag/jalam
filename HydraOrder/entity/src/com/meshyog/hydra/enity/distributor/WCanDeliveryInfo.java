package com.meshyog.hydra.enity.distributor;

public class WCanDeliveryInfo {

	private Long deliveryId;
	private String deliverdBy;
	private String deliveryTo;
	private String orderId;
	private Integer orderRequesterId;
	private WCanOrderInfo orderInfo;
	public Long getDeliveryId() {
		return deliveryId;
	}
	public void setDeliveryId(Long deliveryId) {
		this.deliveryId = deliveryId;
	}
	public String getDeliverdBy() {
		return deliverdBy;
	}
	public void setDeliverdBy(String deliverdBy) {
		this.deliverdBy = deliverdBy;
	}
	public String getDeliveryTo() {
		return deliveryTo;
	}
	public void setDeliveryTo(String deliveryTo) {
		this.deliveryTo = deliveryTo;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public Integer getOrderRequesterId() {
		return orderRequesterId;
	}
	public void setOrderRequesterId(Integer orderRequesterId) {
		this.orderRequesterId = orderRequesterId;
	}
	public WCanOrderInfo getOrderInfo() {
		return orderInfo;
	}
	public void setOrderInfo(WCanOrderInfo orderInfo) {
		this.orderInfo = orderInfo;
	}
	
}
