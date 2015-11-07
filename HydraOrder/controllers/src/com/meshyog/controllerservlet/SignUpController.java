package com.meshyog.controllerservlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.AsyncContext;
import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpUpgradeHandler;
import javax.servlet.http.Part;

import org.bson.types.ObjectId;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.meshyog.daoimpl.DistributorDAOImpl;
import com.meshyog.hydra.enity.distributor.WCanDistributorInfo;
import com.meshyog.hydra.enity.distributor.WCanStockInfo;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

public class SignUpController  extends HttpServlet{
	/**
	 * 
	 */
	public List<WCanDistributorInfo> wCanDistributorList=new ArrayList<WCanDistributorInfo>(); 
	
	private static final long serialVersionUID = 1L;
	RequestDispatcher requestDispatcher=null;
	public void init(ServletConfig config){
		 WCanStockInfo wCanStockInfo=  new WCanStockInfo();
		 wCanStockInfo.setCanName("");
		config.getServletContext().setAttribute("wCanStockInfo", wCanStockInfo);
		
	}
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
		String url = request.getRequestURI();
		String responseUrl="";
		
		if(url.equals("/canwater/distributor-register")){
			responseUrl="jsp/loginrelated/signup.jsp";
			requestDispatcher=request.getRequestDispatcher(responseUrl);
		}else if(url.equals("/canwater/user-login")){
			responseUrl="jsp/loginrelated/userlogin.jsp";
			requestDispatcher=request.getRequestDispatcher(responseUrl);
		}
		else if(url.equals("/canwater/log-out")){
			responseUrl="jsp/loginrelated/userlogin.jsp";
			requestDispatcher=request.getRequestDispatcher(responseUrl);
			request.getSession().invalidate();
		}
		
		requestDispatcher.forward(request, response);
		}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String url = request.getRequestURI();
		String responseUrl = "";
		response.setContentType("applicaton/json");
		
		
		PrintWriter printWritter= response.getWriter();
		try {
			if (url.equals("/canwater/distributor-register-form-submit")) {
				String data = request.getParameter("distributorformjsondata");
				JSONObject jsonObject = new JSONObject(data);
			    DBObject registerUserObject=	(DBObject)JSON.parse(data);
				boolean isNewUser=new DistributorDAOImpl().save("WCanDistributorInfo", registerUserObject);
				ObjectId objectId=new ObjectId(registerUserObject.get("_id").toString());
				request.getSession().setAttribute("distributorid",objectId.toString());
				if(isNewUser)
				request.getSession().setAttribute("distributorInfo", jsonObject);
				responseUrl = "jsp/afterlogin/distributorpage.jsp";
				requestDispatcher=request.getRequestDispatcher(responseUrl);
				requestDispatcher.forward(request, response);
			}else if(url.equals("/canwater/ajax-login-user-validation")){
			System.out.println("ajax valdiation");
			String loginData=	request.getParameter("logindata");
			JSONObject loginJsonObject=new JSONObject(loginData);
			String userName=loginJsonObject.getString("username");
			String password=loginJsonObject.getString("password");
			JSONObject statusOfUser= loginValidation(userName,password);
			//{"distributorName":"","distributorUserName":"admin","distributorPassword":"admin","shopName":"","distributorformjsondata":"","distributoremailId":"","_id":{"$oid":"560fbed8210ef5fedd80a850"},"status":"VU"}
			statusOfUser.remove("distributorPassword");
			statusOfUser.remove("distributorUserName");
			ObjectId distributroIdObj=new ObjectId((String)statusOfUser.getJSONObject("_id").get("$oid"));
			request.getSession().setAttribute("distributorid",distributroIdObj.toString());
			request.getSession().setAttribute("distributorInfo", statusOfUser);
			printWritter.print(statusOfUser.toString());
			}
			else if(url.equals("/canwater/user-login-validation")){
				requestDispatcher=request.getRequestDispatcher("jsp/afterlogin/distributorpage.jsp");
				requestDispatcher.forward(request, response);
			}
			
		} catch (JSONException jsonException) {
			jsonException.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public boolean addDistributor(WCanDistributorInfo distributorJsonObject){
		wCanDistributorList.add(distributorJsonObject);
		return true;
	}
	public static Object convertJsonStringToObject(String  jsonString,Class<?> className){
		Gson gson=new Gson();
		return  gson.fromJson(jsonString, className);
	}
	public static JSONObject loginValidation(String credential1,String credential2) throws Exception{
		return new DistributorDAOImpl().findAvailableUser("WCanDistributorInfo",credential1,credential2);
	}
}
