package com.meshyog.controllerservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.UnknownHostException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.meshyog.dao.DBObserver;
import com.meshyog.daoimpl.ConsumerOrderDAOImpl;
import com.meshyog.daoimpl.ObserverImpl;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

public class ConsumerOrderRequestController  extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	RequestDispatcher requestDispatcher=null;
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		String url = request.getRequestURI();
		PrintWriter printWritter=null;
		String responseUrl="";
		if(url.equals("/canwater/consumer-order-request")){
			
			
		}
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response){
		String url = request.getRequestURI();
		PrintWriter printWritter=null;
		String responseUrl="";
		if(url.equals("/canwater/consumer-order-request")){
			String consumerRequestObj=  request.getParameter("consumerreqobjects");
			try {
				
				new ConsumerOrderRequestController().addConsumerRequest(consumerRequestObj,request,response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
   public boolean addConsumerRequest(String  consumerRequestedObject,HttpServletRequest reqeust, HttpServletResponse response) throws UnknownHostException, Exception{
	   String tableName="consumerOrderRequest";
	  
	   reqeust.setAttribute("currentRequest", reqeust);
	   
	   Integer listIndexId=(Integer)reqeust.getSession().getAttribute("consumerreqobserver");
	   if(listIndexId==null){
		   ObserverImpl observerImpl=   new ObserverImpl();
		   DBObserver dbObserver=(observerImpl);
		   new ConsumerOrderDAOImpl().addObserver(dbObserver);
		   new ConsumerOrderDAOImpl().setObserver(dbObserver);
		   reqeust.getSession().setAttribute("consumerreqobserver",observerImpl.getListIndexId());
	   }
	   else
	   {
		   ObserverImpl observerImpl=   new ObserverImpl();
		   observerImpl.setListIndexId(listIndexId);
		   DBObserver dbObserver=(observerImpl);
		   int index=new ConsumerOrderDAOImpl().getObservers().indexOf(dbObserver); 
		   new ConsumerOrderDAOImpl().setObserver(new ConsumerOrderDAOImpl().getObservers().get(index));
	   }
	   DBObject consumerReqObject= (DBObject) JSON.parse(consumerRequestedObject);
	   
	   return new ConsumerOrderDAOImpl().save(tableName,consumerReqObject);
   }
}
