package com.meshyog.daoimpl;

import java.util.Iterator;

import org.bson.types.ObjectId;
import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.JsonArray;
import com.meshyog.controllerservlet.DataBaseStartupServlet;
import com.meshyog.dao.DataBaseDAO;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import com.mongodb.util.JSON;

public class StocksDAOImpl implements DataBaseDAO {

	@Override
	public boolean save(String tableName,  DBObject dbObject)
			throws Exception {
		DB mongoDataBase= DataBaseStartupServlet.connectToMongo();
		DBCollection dbTable= mongoDataBase.getCollection(tableName);
	    WriteResult writeResult=dbTable.save(dbObject);
		return writeResult.getN()==1;
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

	@SuppressWarnings("static-access")
	@Override
	public boolean update(String tableName, DBObject dbObject) throws Exception {
		
		DB mongoDataBase= DataBaseStartupServlet.connectToMongo();
		DBCollection dbTable= mongoDataBase.getCollection(tableName);
		String id=(String) dbObject.get("id");
		ObjectId objectId = new ObjectId(id);
		DBObject idObject=new BasicDBObject(); //(DBObject)JSON.parse("{$oid:"+id+"}");
		idObject.put("_id",objectId);
		WriteResult writeResult =dbTable.update(idObject,dbObject);
		System.out.println(writeResult.getN());
		return writeResult.getN()==1;
	}
public JSONArray findAll(String tableName, DBObject dbObject) throws Exception {
		JSONArray jsonArray=new JSONArray();
		DB mongoDataBase= DataBaseStartupServlet.connectToMongo();
		DBCollection dbTable= mongoDataBase.getCollection(tableName);
		DBCursor dbCursor= dbTable.find(dbObject);
		System.out.println(dbCursor);
		int m=1;
		while (dbCursor.hasNext()) {
			DBObject stkEntryObj=  dbCursor.next();
			stkEntryObj.put("id",m);
			jsonArray.put(stkEntryObj.toString());
			m++;
		}
		return jsonArray;
		
	}
public boolean deleteCanInfo(String tableName, String id)throws Exception{
	DB mongoDataBase= DataBaseStartupServlet.connectToMongo();
	DBCollection dbTable= mongoDataBase.getCollection(tableName);
	ObjectId objectId = new ObjectId(id);
	DBObject idObject=new BasicDBObject(); //(DBObject)JSON.parse("{$oid:"+id+"}");
	idObject.put("_id",objectId);
	WriteResult writeResult =dbTable.remove(idObject);
	return writeResult.getN()==1;
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

    public JSONArray findAllStockCans(String tableName,String distributorId) throws Exception{
    	JSONArray jsonArray=new JSONArray();
    	DB mongoDataBase= DataBaseStartupServlet.connectToMongo();
		DBCollection dbTable= mongoDataBase.getCollection(tableName);
		DBObject distributordbObject= (DBObject) JSON.parse("{\"distributorid\":\""+distributorId+"\"}");
		DBCursor dbCursor=  dbTable.find(distributordbObject);
		Iterator<DBObject> cursorIterator= dbCursor.iterator();
		while (cursorIterator.hasNext()) {
			DBObject dbObject=  cursorIterator.next();
			jsonArray.put(dbObject.toString());
		}
		return jsonArray;
    }

	@Override
	public boolean update(String tableName, JSONObject tableValue) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public JSONArray findDistributorForConsumers(String tableName)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
