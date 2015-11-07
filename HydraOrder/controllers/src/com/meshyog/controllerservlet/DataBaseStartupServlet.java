package com.meshyog.controllerservlet;

import com.mongodb.DB;
import com.mongodb.MongoClient;

public class DataBaseStartupServlet  {
	
	
	public static DB mongoDataBase=null;
	private static MongoClient client = null;

	/*public  static boolean startDatabase() {
		boolean isConnected=true;
		try{
			if(mongoDataBase==null){
				Mongo mongoDb=new Mongo("localhost", 27017);
				mongoDataBase= mongoDb.getDB("canwater");
				CommandResult commandResult= mongoDataBase.getStats();
				System.out.println(commandResult);
			}
		}
		catch(MongoException mongoException){
			mongoException.printStackTrace();
			isConnected=false;
		}catch(UnknownHostException unKnownHstException){
			unKnownHstException.printStackTrace();
			isConnected=false;
		}
		catch(IOException ioException){
			ioException.printStackTrace();
			isConnected=false;
		}
		return isConnected;
	}*/
	
	

	@SuppressWarnings("deprecation")
	public static DB connectToMongo() throws Exception {
	    if (null != client) {
	        return client.getDB("canwater");
	    }       
	    client = new MongoClient("localhost",27017);                
	    return client.getDB("canwater");    
	}
	public void closeConnection(){
		client.close();
	}
}
