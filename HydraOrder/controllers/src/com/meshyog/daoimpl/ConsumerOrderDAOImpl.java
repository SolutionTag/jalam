package com.meshyog.daoimpl;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.JsonArray;
import com.meshyog.controllerservlet.DataBaseStartupServlet;
import com.meshyog.dao.DBObserver;
import com.meshyog.dao.DataBaseDAO;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

public class ConsumerOrderDAOImpl implements DataBaseDAO {
	public static List<DBObserver> observers=new ArrayList<DBObserver>();
	public static DBObserver observer;
	@Override
	public boolean save(String tableName, DBObject dbObject)
			throws UnknownHostException, Exception {
		DB mongoDataBase= DataBaseStartupServlet.connectToMongo();
		DBCollection dbTable= mongoDataBase.getCollection(tableName);
		WriteResult writeResult= dbTable.insert(dbObject);
		if(writeResult.getN()==1){
			this.notifyObservers("");
			System.out.println("consumer request has been received");
		}
		this.notifyObservers(dbObject.toString());
		
		return writeResult.getN()==1;
	}

	@Override
	public boolean delete(String tableName, String identifer) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(String tableName, Integer identifer) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(String tableName, DBObject dbObject) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public JSONObject findOne(String tableName, String identifer)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject findOne(String tableName, Integer identifer)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JsonArray findAll(String tableName) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(String tableName, JSONObject tableValue)
			throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public JSONArray findDistributorForConsumers(String tableName)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

    public void addObserver(DBObserver observer){
        this.observers.add(observer);
    }


    public DBObserver  removeObserver(DBObserver observer){
        this.observers.remove(observer);
        return observer;
    }
    private void notifyObservers(String dbObject){
    	 int index=new ConsumerOrderDAOImpl().getObservers().indexOf(this.getObserver()); 
    	 //ObserverImpl observer= (ObserverImpl) new ConsumerOrderDAOImpl().getObservers().get(index);
		 //observer.num++;
		 new ConsumerOrderDAOImpl().getObservers().get(index).notifyNewRecordInserted(dbObject);
		 
        }

	public List<DBObserver> getObservers() {
		return observers;
	}

	public void setObservers(List<DBObserver> observers) {
		this.observers = observers;
	}

	public DBObserver getObserver() {
		return observer;
	}

	public void setObserver(DBObserver observer) {
		this.observer = observer;
	}
     
}
