package com.meshyog.dao;

import java.util.List;

import org.bson.types.ObjectId;
import org.json.JSONArray;
import org.json.JSONObject;

public interface ConsumerDAO {

	public String saveNewConsumer(String tableName,String profileData);
	public String consumerLoginValidation(String tableName,String data);
	public String consumerProfileUpdate(String tableName,String data);
	public String saveConsumerCanDeliveryAddress(String tableName,String id,String data);
	public String saveConsumerDistributors(String tableName,String distributorId,String consumerId,JSONArray crntConsumerDistIdArray);
	public JSONObject findConsumerDistributors(String tableName, String id);
	public String getOnlyCommonDistributors(String tableName,List<ObjectId> objectArrayList);
	public String deleteDistributorByConsumer(String tableName,String consumerId,String distributorId);
	public JSONArray findAllConsumerDeliveryAddress(String tableName,String consumerId);
	public String changeDefaultAddress(String tableName,String consumerId,String addressId,String status);
}
