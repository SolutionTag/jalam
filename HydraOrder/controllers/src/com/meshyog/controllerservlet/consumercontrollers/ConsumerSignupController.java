package com.meshyog.controllerservlet.consumercontrollers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.meshyog.controllerservlet.ConsumerController;
import com.meshyog.dao.ConsumerDAO;
import com.meshyog.daoimpl.ConsumerDAOImpl;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

@Controller
public class ConsumerSignupController extends HttpServlet{

	/**
	 * 
	 */
	ConsumerSignupController(){
	}
	
	private static final long serialVersionUID = 1L;
	public ConsumerDAO consumerDao=null;
	public String profileTable="consumerProfileInfo";
	// "consumerName" : "", "consumerUserName" : "", "consumerPassword" : "", "consumerPhoneNo" : "",
	//"consumerEmail" : "", "consumerImage" : ""
	public String distributorsTable="consumerDistributors";
	//{consumerId:"",distributorId:""}
	//({consumerId:"",consumerName:"",consumerDoorNo:"",consumerStreet:"",consumerAreaName:"",
	public String delieveryAddressTable="consumerDeleiveryAddress";
		//consumerCity:"",consumerState:"",consumerVerifiedAddress:"",consumerLocationLat:"",
		//consumerLocationLong:""}
	
	/*@RequestMapping(value="/mydata")
	public String mydata(){
	System.out.println("");
		return "consumer/consumersignup";
	}*/
	
    @RequestMapping(value="/consumer-sign-up" , method=RequestMethod.GET)
	public String hitServlet(HttpServletRequest request,HttpServletResponse response) throws Exception{
	System.out.println();
	new ConsumerController().setDistibutorList(request);
		return "consumer/requestforcan";
	}
    @RequestMapping(value="/requestforcan" , method=RequestMethod.GET)
   	public String successConsumerSubmit(HttpServletRequest request,HttpServletResponse response) throws Exception{
    	new ConsumerController().setDistibutorList(request);
    	ConsumerDAO consumerDAO=new ConsumerDAOImpl();
    	String consumerData =(String) request.getSession().getAttribute("consumerinfo");
    	JSONObject consumerDistributorsList= consumerDAO.findConsumerDistributors(distributorsTable,new JSONObject(consumerData).get("consumerId").toString());
    	request.getSession().setAttribute("consumerDistributorsList", consumerDistributorsList.get("consumerdistributors"));
    	request.getSession().setAttribute("commonDistributorList",consumerDistributorsList.get("commondistributors"));
    	request.getSession().setAttribute("consumerinfo", consumerData);
   		return "consumer/requestforcan";
   	}
    @RequestMapping(value="/distributor-config" , method=RequestMethod.GET)
   	public String consumerFormSubmit(HttpServletRequest request,HttpServletResponse response) throws Exception{
   /*	System.out.println("consumer form submit...");
   	String data = request.getParameter("distributorformjsondata");
	ConsumerDAO consumerDAO=new ConsumerDAOImpl();
	String consumerData= consumerDAO.saveNewConsumer(profileTable, data);*/
    String consumerData =(String) request.getSession().getAttribute("consumerinfo");
    ConsumerDAO consumerDAO=new ConsumerDAOImpl();
	JSONObject consumerDistributorsList= consumerDAO.findConsumerDistributors(distributorsTable,new JSONObject(consumerData).get("consumerId").toString());
	request.getSession().setAttribute("commonDistributorList",consumerDistributorsList.get("commondistributors"));
	request.getSession().setAttribute("consumerDistributorsList", consumerDistributorsList.get("consumerdistributors"));
	request.getSession().setAttribute("consumerinfo", consumerData);
   	return "consumer/distributorcrud";
   	}
    @RequestMapping(value="/consumer-login" , method=RequestMethod.GET)
	public String existingConsumerLogin(HttpServletRequest request,HttpServletResponse response){
		return "consumer/consumerlogin";
	}
    @RequestMapping(value="/consumer-profile-data" , method=RequestMethod.POST)
	public String consumerProfileData(HttpServletRequest request,HttpServletResponse response){
	 System.out.println();
		return "afterlogin/stocks";
	}
    @RequestMapping(value="/save-distributor-to-consumer" , method=RequestMethod.POST)
	public void saveDistributorToConsumer(HttpServletRequest request,HttpServletResponse response) throws JSONException, IOException{
   PrintWriter printWritter=null;
    	String loginData=	request.getParameter("consumerdistributors");
    	String crntconsumerdistid=	request.getParameter("crntconsumerdistid");
    	JSONArray  crntConsumerDistIdArray=new JSONArray(crntconsumerdistid);
    JSONObject json=  new JSONObject(loginData);
    String consumerId= json.get("consumerid").toString();
    JSONObject distributorJson= json.getJSONObject("_id");
    String distributorId=  distributorJson.get("$oid").toString();
	ConsumerDAO consumerDAO=new ConsumerDAOImpl();
	String updatedCommonDistributros= consumerDAO.saveConsumerDistributors(distributorsTable, distributorId, consumerId,crntConsumerDistIdArray);
	 if(!updatedCommonDistributros.equals("")){
		 printWritter=response.getWriter();
		 printWritter.print(updatedCommonDistributros);
	 }
		//return "afterlogin/stocks";
	}
    @RequestMapping(value="/save-delievery-address" , method=RequestMethod.POST)
	public String saveDelieveryAddress(HttpServletRequest request,HttpServletResponse response){
	System.out.println();
		return "afterlogin/stocks";
	}
    @RequestMapping(value="/ajax-consumer-login-user-validation" , method=RequestMethod.POST)
    public void ajaxConsumerLoginValidation(HttpServletRequest request,HttpServletResponse response) throws IOException, JSONException{
    	PrintWriter printWritter=response.getWriter();
    	String loginData=	request.getParameter("logindata");
    	ConsumerDAO consumerDAO=new ConsumerDAOImpl();
    	String consumerLoginValid= consumerDAO.consumerLoginValidation(profileTable, loginData);
    	JSONObject consumerDistributorsList= consumerDAO.findConsumerDistributors(distributorsTable,new JSONObject(consumerLoginValid).get("consumerId").toString());
    	request.getSession().setAttribute("consumerDistributorsList", consumerDistributorsList.get("consumerdistributors"));
    	request.getSession().setAttribute("commonDistributorList",consumerDistributorsList.get("commondistributors"));
    	request.getSession().setAttribute("consumerinfo", consumerLoginValid);
    	printWritter.print(consumerLoginValid);
    		//return "afterlogin/stocks";
    	}
    @RequestMapping(value="/consumer-request-for-can" , method=RequestMethod.POST)
    public void consumerRequestForCan(HttpServletRequest request,HttpServletResponse response){
    	
    }
    @RequestMapping(value="/delete-distributor-by-consumer" , method=RequestMethod.POST)
    public void deleteDistributor(HttpServletRequest request,HttpServletResponse response) throws IOException{
    	PrintWriter printWritter=response.getWriter();
    	String consumerId= request.getParameter("consumerId");
    	String distributorId= request.getParameter("distributorid");
    	ConsumerDAO consumerDAO=new ConsumerDAOImpl();
    	String updatedCommonDistributros=consumerDAO.deleteDistributorByConsumer(distributorsTable, consumerId, distributorId);
    	 if(!updatedCommonDistributros.equals("")){
    		 printWritter=response.getWriter();
    		 printWritter.print(updatedCommonDistributros);
    	 }
    }
    @RequestMapping(value="/consumer-delivery-address")
    public String consumerDeliveryAddressPage(HttpServletRequest request,HttpServletResponse response) throws JSONException{
    	String consumerInfo= (String) request.getSession().getAttribute("consumerinfo");
    		JSONObject consumerObject=new JSONObject(consumerInfo);
    		ConsumerDAO consumerDAO=new ConsumerDAOImpl();
    		request.getSession().setAttribute("consumerdeliveryaddress",consumerDAO.findAllConsumerDeliveryAddress(delieveryAddressTable, consumerObject.getString("consumerId")));
    	return "consumer/deliveryaddress";
    }
    @RequestMapping(value="/save-consumer-deliveryaddress",method=RequestMethod.POST)
    public String saveConsumerDeliveryAddress(HttpServletRequest request,HttpServletResponse response) throws JSONException{
    	String data = request.getParameter("distributorformjsondata");
		ConsumerDAO consumerDAO=new ConsumerDAOImpl();
		consumerDAO.saveConsumerCanDeliveryAddress(delieveryAddressTable, "", data);
		JSONObject consumerObject=new JSONObject(data);
		request.getSession().setAttribute("consumerdeliveryaddress",consumerDAO.findAllConsumerDeliveryAddress(delieveryAddressTable, consumerObject.getString("consumerId")));
    	return "consumer/deliveryaddress";
    }
    @RequestMapping(value="/set-default-address")
    public void  setDefaultAddress(HttpServletRequest request,HttpServletResponse response) throws IOException, JSONException{
    	 //set-default-address
    	ConsumerDAO consumerDAO=new ConsumerDAOImpl();
    	PrintWriter printWritter=response.getWriter();
    	String addressInfo= request.getParameter("addressInfo");
    	JSONObject addressInfoJson=new JSONObject(addressInfo);
    	String consumerid= addressInfoJson.getString("consumerid");
    	String addressid= addressInfoJson.getString("addressid");
    	String status=addressInfoJson.getString("status");
    	consumerDAO.changeDefaultAddress(delieveryAddressTable, consumerid, addressid,status);
    	//System.out.println(data);
    	
    
    }
   
    /*@RequestMapping(value="save-new-consumer" , method=RequestMethod.POST)
	public String saveNewConsumer(HttpServletRequest request,HttpServletResponse response){
	System.out.println();
		return "consumer/consumersignup";
    @RequestMapping(value="consumer-login" , method=RequestMethod.GET)
	}*/
}
