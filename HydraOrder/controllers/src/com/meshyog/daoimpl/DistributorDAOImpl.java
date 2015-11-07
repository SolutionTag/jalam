package com.meshyog.daoimpl;

import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.JsonArray;
import com.meshyog.controllerservlet.DataBaseStartupServlet;
import com.meshyog.dao.DataBaseDAO;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

public class DistributorDAOImpl implements DataBaseDAO {

	
	
	@Override
	public boolean save(String tableName, DBObject dbObject) throws Exception {
		/*@SuppressWarnings("rawtypes")
		HashMap mapObject= new Gson().fromJson(tableValue.toString(), HashMap.class);*/
		DB mongoDataBase= DataBaseStartupServlet.connectToMongo();
		DBCollection dbTable= mongoDataBase.getCollection(tableName);
	/*	DBObject dbObject=new BasicDBObject(mapObject);*/
		WriteResult writeResult= dbTable.insert(dbObject);
		writeResult.getN();
		DBCursor dbCursor= dbTable.find();
		Iterator<DBObject> iterator=	dbCursor.iterator();
		while(iterator.hasNext()){
		 DBObject dbObject2= iterator.next();
		 System.out.println(dbObject2.toMap().toString());
		}
		//mongoDataBase.getMongo().close();
		return true;
	}

	@Override
	public boolean delete(String tableName, String identifer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(String tableName, Integer identifer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(String tableName, JSONObject tableValue) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public JSONObject findOne(String tableName, String identifer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject findOne(String tableName, Integer identifer) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public JsonArray findAll(String tableName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONArray findDistributorForConsumers(String tableName) throws Exception{
		DB mongoDataBase= DataBaseStartupServlet.connectToMongo();
		DBCollection dbTable= mongoDataBase.getCollection(tableName);
		DBObject dbObject=new BasicDBObject();
		dbObject.put("_id", 1);
		dbObject.put("distributorName", 1);
		dbObject.put("shopName", 1);
		DBCursor dbCursor= dbTable.find(new BasicDBObject(),dbObject);
		JSONArray jsonArray=new JSONArray();
		while (dbCursor.hasNext()) {
			DBObject stkEntryObj=  dbCursor.next();
			jsonArray.put(stkEntryObj.toString());
		}
		return jsonArray;
	}
	

	public JSONObject findAvailableUser(String tableName, String userId,
			String userPword) throws Exception {
		JSONObject validJsonObject = null;
		DBObject clause1 = new BasicDBObject("distributorUserName", userId);
		DBObject clause2 = new BasicDBObject("distributorPassword", userPword);
		BasicDBList basicDbList = new BasicDBList();
		basicDbList.add(clause1);
		basicDbList.add(clause2);
		DBObject query = new BasicDBObject("$and", basicDbList);
		DB mongoDataBase = DataBaseStartupServlet.connectToMongo();
		DBObject validUserData = mongoDataBase.getCollection(tableName)
				.findOne(query);
		if (validUserData != null) {
			validJsonObject = new JSONObject(validUserData.toString());
			validJsonObject.put("status", "VU");
		} else {
			validJsonObject = new JSONObject();
			validJsonObject.put("status", "IVU");
		}
		//mongoDataBase.getMongo().close();
		return validJsonObject;
	}

	@Override
	public boolean update(String tableName, DBObject dbObject) {
		// TODO Auto-generated method stub
		return false;
	}

}
