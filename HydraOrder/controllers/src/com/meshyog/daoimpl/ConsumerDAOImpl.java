package com.meshyog.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.meshyog.controllerservlet.DataBaseStartupServlet;
import com.meshyog.dao.ConsumerDAO;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import com.mongodb.util.JSON;

public class ConsumerDAOImpl implements ConsumerDAO{

	@Override
	public String saveNewConsumer(String tableName, String profileData) {
		 DBObject dbObject=null;
		try{
			DB mongoDataBase= DataBaseStartupServlet.connectToMongo();
			DBCollection dbTable= mongoDataBase.getCollection(tableName);
		    dbObject= (DBObject)	JSON.parse(profileData);
		    dbObject.put("consumerId", ConsumerDAOImpl.gen(10));
		    WriteResult writeResult= dbTable.insert(dbObject);
		    dbObject.removeField("distributorformjsondata");
		    dbObject.removeField("consumerPassword");
		    dbObject.removeField("consumerEmail");
		    dbObject.toString();
		}catch(Exception e){
			e.printStackTrace();
		}
		return dbObject.toString();
	}

	@SuppressWarnings("unused")
	@Override
	public String consumerLoginValidation(String tableName, String data) {
		JSONObject validJsonObject=null;
		try{
			JSONObject loginData=new JSONObject(data);
			DBObject clause1 = new BasicDBObject("consumerEmail", loginData.get("consumerEmail"));
			DBObject clause2 = new BasicDBObject("consumerPassword", loginData.get("consumerPassword"));
			BasicDBList basicDbList = new BasicDBList();
			basicDbList.add(clause1);
			basicDbList.add(clause2);
			DBObject query = new BasicDBObject("$and", basicDbList);
			DB mongoDataBase = DataBaseStartupServlet.connectToMongo();
			DBObject validUserData = mongoDataBase.getCollection(tableName)
					.findOne(query);
			validUserData.removeField("distributorformjsondata");
			validUserData.removeField("consumerPassword");
			validUserData.removeField("consumerEmail");
			if (validUserData != null) {
				validJsonObject = new JSONObject(validUserData.toString());
				validJsonObject.put("status", "VU");
			} else {
				validJsonObject = new JSONObject();
				validJsonObject.put("status", "IVU");
			}
		}catch(Exception e){
			
		}
		return validJsonObject.toString();
	}

	@Override
	public String consumerProfileUpdate(String tableName, String data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String saveConsumerCanDeliveryAddress(String tableName, String id,
			String data) {
		 DBObject dbObject=null;
			try{
				DB mongoDataBase= DataBaseStartupServlet.connectToMongo();
				DBCollection dbTable= mongoDataBase.getCollection(tableName);
			    dbObject= (DBObject)	JSON.parse(data);
			    dbObject.removeField("distributorformjsondata");
			    WriteResult writeResult= dbTable.insert(dbObject);
			    dbObject.toString();
			}catch(Exception e){
				e.printStackTrace();
			}
			return dbObject.toString();
	}

	@Override
	public String saveConsumerDistributors(String tableName,
			String distributorId, String consumerId,JSONArray crntConsumerDistIdArray)  {
		 DBObject dbObject=null;
		 String updatedCommonDistributors=null;
		 List<ObjectId> consumerDistributorIdsArray=null;
		 try{
		 if(crntConsumerDistIdArray.length()!=0){
			 consumerDistributorIdsArray=new ArrayList<ObjectId>();
			 for(int s=0;s<crntConsumerDistIdArray.length();s++){
					ObjectId objectId=new ObjectId( crntConsumerDistIdArray.get(s).toString());
					consumerDistributorIdsArray.add(objectId);
			 }
		 }
		DB mongoDataBase= DataBaseStartupServlet.connectToMongo();
		DBCollection dbTable= mongoDataBase.getCollection(tableName);
		dbObject=new BasicDBObject();
		dbObject.put("consumerId", consumerId );
		dbObject.put("distributorId", distributorId);
		updatedCommonDistributors=getOnlyCommonDistributors("WCanDistributorInfo",consumerDistributorIdsArray);
		 WriteResult writeResult= dbTable.insert(dbObject);
		 }
		 catch(Exception e){
			 e.printStackTrace();
		 }
		return updatedCommonDistributors;
	}
	 /*public static int gen() {
	        Random r = new Random(System.currentTimeMillis());
	        return 1000000000 + r.nextInt(2000000000);
	    }*/
	 public final static String gen(long len) {
		    if (len > 18)
		        throw new IllegalStateException("To many digits");
		    long tLen = (long) Math.pow(10, len - 1) * 9;

		    long number = (long) (Math.random() * tLen) + (long) Math.pow(10, len - 1) * 1;

		    String tVal = number + "";
		    if (tVal.length() != len) {
		        throw new IllegalStateException("The random number '" + tVal + "' is not '" + len + "' digits");
		    }
		    return tVal;
		}

	@Override
	public JSONObject findConsumerDistributors(String tableName, String id) {
		JSONArray consumerDistributor=null;
		List<ObjectId> consumerDistributorIdsArray=null;
		JSONObject outputData=new JSONObject();
		try{
			//id="5621f639d0b71913fcc15f9d";
			System.out.println();
			DBObject query = new BasicDBObject("consumerId", id);
			DB mongoDataBase = DataBaseStartupServlet.connectToMongo();
			DBCursor dbCursor = mongoDataBase.getCollection(tableName).find(query);
			System.out.println(dbCursor == null);
	        if (dbCursor != null) {
	        	consumerDistributor=new JSONArray();
	        	consumerDistributorIdsArray=new ArrayList<ObjectId>();
	            while (dbCursor.hasNext()) {
	            	 DBObject currentData =dbCursor.next();
	            	
	            	ObjectId objectId = new ObjectId(currentData.get("distributorId").toString());
	            	consumerDistributorIdsArray.add(objectId);
	            	DBObject idObject=new BasicDBObject(); //(DBObject)JSON.parse("{$oid:"+id+"}");
	        		idObject.put("_id",objectId);
	        		consumerDistributor.put(mongoDataBase.getCollection("WCanDistributorInfo").findOne(idObject).toString());
	            }
	            
	            outputData.put("commondistributors",getOnlyCommonDistributors("WCanDistributorInfo",consumerDistributorIdsArray));
	        }
	        outputData.put("consumerdistributors", consumerDistributor);
	        
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return outputData;
	}

	@Override
	public String getOnlyCommonDistributors(String tableName,List<ObjectId> distributorIdsArray) {
		JSONArray commanDistributors=null;
		try{
			BasicDBObject query = new BasicDBObject();
			query.put("_id", new BasicDBObject("$nin", distributorIdsArray));
			DB mongoDataBase = DataBaseStartupServlet.connectToMongo();
			DBCursor resultsCursor =  mongoDataBase.getCollection(tableName).find(query);
			commanDistributors=new JSONArray();
			while (resultsCursor.hasNext()) {
           	 DBObject currentData =resultsCursor.next();
           	currentData.removeField("distributorUserName");
           	currentData.removeField("distributorPassword");
           	currentData.removeField("distributorformjsondata");
           	commanDistributors.put(currentData.toString());
           }
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return commanDistributors.toString();
	}

	@Override
	public String deleteDistributorByConsumer(String tableName,
			String consumerId, String distributorId) {
		JSONObject  updatedDistributorList=null;
		DBObject clause1 = new BasicDBObject("consumerId", consumerId);
		DBObject clause2 = new BasicDBObject("distributorId", distributorId);
		BasicDBList basicDbList = new BasicDBList();
		basicDbList.add(clause1);
		basicDbList.add(clause2);
		DBObject query = new BasicDBObject("$and", basicDbList);
		try{
			DB mongoDataBase = DataBaseStartupServlet.connectToMongo();
			WriteResult validUserData = mongoDataBase.getCollection(tableName)
					.remove(query);
			  updatedDistributorList=  findConsumerDistributors( tableName, consumerId);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return updatedDistributorList.toString();
	}

	@Override
	public JSONArray findAllConsumerDeliveryAddress(String tableName,
			String consumerId) {
		JSONArray consumerDeliverAddressArray=null;
		try{
			
			DBObject query = new BasicDBObject("consumerId", consumerId);
			DB mongoDataBase = DataBaseStartupServlet.connectToMongo();
			DBCursor dbCursor = mongoDataBase.getCollection(tableName).find(query);
			System.out.println(dbCursor == null);
	        if (dbCursor != null) {
	        	consumerDeliverAddressArray=new JSONArray();
	            while (dbCursor.hasNext()) {
	            	 DBObject currentData =dbCursor.next();
	            	 consumerDeliverAddressArray.put(currentData.toString());
	            }
	            
	           
	        }
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return consumerDeliverAddressArray;
	}

	@Override
	public String changeDefaultAddress(String tableName, String consumerId,
			String addressId,String status) {
		JSONObject  updatedDistributorList=null;
		DBObject clause1 = new BasicDBObject("consumerId", consumerId);
		ObjectId objectId=new ObjectId(addressId);
		DBObject clause2 = new BasicDBObject("_id", objectId);
		BasicDBList basicDbList = new BasicDBList();
		basicDbList.add(clause1);
		basicDbList.add(clause2);
		DBObject query = new BasicDBObject("$set", basicDbList);
		try{
			DB mongoDataBase = DataBaseStartupServlet.connectToMongo();
			DBObject defaultAddress=new BasicDBObject();
			defaultAddress.put("$set", new BasicDBObject("status",status));
			WriteResult validUserData =  mongoDataBase.getCollection(tableName).update(clause2,defaultAddress);
			/*while(validUserData.hasNext()){
			DBObject dbob=	 validUserData.next();
			System.out.println(dbob.toString());
			}*/
			  updatedDistributorList=  findConsumerDistributors( tableName, consumerId);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

}
