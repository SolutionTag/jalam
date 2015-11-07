package com.meshyog.dao;

import java.net.UnknownHostException;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.JsonArray;
import com.mongodb.DBObject;

public interface DataBaseDAO {
	
	public boolean save(String tableName, DBObject  dbObjectValue)throws UnknownHostException, Exception;

	public boolean delete(String tableName, String identifer)throws Exception ;

	public boolean delete(String tableName, Integer identifer) throws Exception ;

	public boolean update(String tableName, DBObject dbObject)throws Exception ;

	public JSONObject findOne(String tableName, String identifer)throws Exception ;

	public JSONObject findOne(String tableName, Integer identifer)throws Exception ;

	public JsonArray findAll(String tableName)throws Exception ;

	boolean update(String tableName, JSONObject tableValue)throws Exception ;
	
	public  JSONArray findDistributorForConsumers(String tableName)throws Exception;
	

}
