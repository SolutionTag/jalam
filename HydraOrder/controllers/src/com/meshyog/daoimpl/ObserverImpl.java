package com.meshyog.daoimpl;

import java.util.ArrayList;
import java.util.List;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueBrowser;
import javax.jms.Session;

import org.apache.activemq.web.WebClient;
import org.json.JSONObject;

import com.meshyog.controllerservlet.ActiveMQConnection;
import com.meshyog.dao.DBObserver;

public class ObserverImpl implements DBObserver {

	public static List<String> messageList=new ArrayList<String>();
	private int listIndexId;
	public static int num=0;
	public ObserverImpl(){
		this.listIndexId=super.hashCode();
	}
	@SuppressWarnings("static-access")
	@Override
	public  List<String> notifyNewRecordInserted(String message) {
		System.out.println("notify called");
		Session session=null;
		Connection connection=null;
		try {
			JSONObject consumerRequestObj=new JSONObject(message);
			System.out.println(consumerRequestObj);
			session = ActiveMQConnection.loadActiveMqConnection();
			/*Destination destination = session.createQueue("topic.events");
			MessageProducer messageProducer = session.createProducer(destination);
			messageProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			TextMessage textMesage = session.createTextMessage("request from the useer");
			messageProducer.send(textMesage);*/
			WebClient wclient=new WebClient();
			String selector = "distributorid = \'"+consumerRequestObj.getString("distributorid")+"\'";
			Destination destination = session.createQueue("topic.events");
			QueueBrowser queryBrower= session.createBrowser((Queue) destination);
			//session.createBrowser(arg0)
			System.out.println(queryBrower.getMessageSelector());
			//MessageConsumer messageConsumer= wclient.getSession().createConsumer(destination,selector);
		
			MessageProducer messageProducer = session.createProducer(destination);
			QueueBrowser browser = session.createBrowser((Queue) destination, selector);
			
			messageProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			ObjectMessage textMesage = session.createObjectMessage();
			textMesage.setObject(consumerRequestObj.toString());
			textMesage.setStringProperty("distributorid",consumerRequestObj.getString("distributorid"));
			 consumerRequestObj.get("distributorid");
			 textMesage.setJMSDestination(destination);
			 textMesage.setObjectProperty("ID",consumerRequestObj.getString("distributorid"));
			 textMesage.setJMSMessageID(consumerRequestObj.getString("distributorid"));
			 textMesage.setStringProperty("ID",consumerRequestObj.getString("distributorid"));
			 //textMesage.setObjectProperty(arg0, arg1);
			//textMesage.setJMSMessageID(consumerRequestObj.getString("distributorid"));
			//textMesage.setObjectProperty("distributoriddd", "\'"+consumerRequestObj.getString("distributorid")+"\'");
			//textMesage.setStringProperty("distributorid","\'"+consumerRequestObj.getString("distributorid")+"\'");
			//"distributorid='5621f55bd0b71913fcc15f9b'"
			messageProducer.send(textMesage);
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return messageList;
		
	}
	public void refresh(){
         //some code to refresh the table
    }
	public int getListIndexId() {
		return listIndexId;
	}
	public void setListIndexId(int listIndexId) {
		this.listIndexId = listIndexId;
	}
	@Override
	  public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + listIndexId;
	    return result;
	  }
	  /* (non-Javadoc)
	   * @see java.lang.Object#equals(java.lang.Object)
	   */
	  @Override
	  public boolean equals(Object obj) {
	    if (this == obj)
	      return true;
	    if (obj == null)
	      return false;
	    if (getClass() != obj.getClass())
	      return false;
	    ObserverImpl other = (ObserverImpl) obj;
	    if (listIndexId != other.listIndexId)
	      return false;
	    return true;
	  }
	

}
