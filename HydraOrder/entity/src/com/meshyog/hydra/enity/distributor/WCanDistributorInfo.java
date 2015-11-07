package com.meshyog.hydra.enity.distributor;

import java.util.List;

public class WCanDistributorInfo {

	private Long distributorId;
	private String distributorName;
	private String shopName;
	private String distributoremailId;
	private String officeAddress;
	private List<Long> distributorPhoneNumber;
	private String distributorUserName;
	private String distributorPassword;

	public Long getDistributorId() {
		return distributorId;
	}

	public void setDistributorId(Long distributorId) {
		this.distributorId = distributorId;
	}

	public String getDistributorName() {
		return distributorName;
	}

	public void setDistributorName(String distributorName) {
		this.distributorName = distributorName;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getDistributoremailId() {
		return distributoremailId;
	}

	public void setDistributoremailId(String distributoremailId) {
		this.distributoremailId = distributoremailId;
	}

	public String getOfficeAddress() {
		return officeAddress;
	}

	public void setOfficeAddress(String officeAddress) {
		this.officeAddress = officeAddress;
	}

	public List<Long> getDistributorPhoneNumber() {
		return distributorPhoneNumber;
	}

	public void setDistributorPhoneNumber(List<Long> distributorPhoneNumber) {
		this.distributorPhoneNumber = distributorPhoneNumber;
	}

	public String getDistributorUserName() {
		return distributorUserName;
	}

	public void setDistributorUserName(String distributorUserName) {
		this.distributorUserName = distributorUserName;
	}

	public String getDistributorPassword() {
		return distributorPassword;
	}

	public void setDistributorPassword(String distributorPassword) {
		this.distributorPassword = distributorPassword;
	}

}
