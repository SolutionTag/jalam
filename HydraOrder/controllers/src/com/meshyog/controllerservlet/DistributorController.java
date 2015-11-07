package com.meshyog.controllerservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.types.ObjectId;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.meshyog.daoimpl.StocksDAOImpl;
import com.meshyog.hydra.enity.distributor.WCanStockInfo;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

public class DistributorController  extends HttpServlet{
	/**
	 * 
	 */
	public List<WCanStockInfo> canStockInfoList=new ArrayList<WCanStockInfo>(); 
	RequestDispatcher requestDispatcher=null;
	public void init(ServletConfig config){
		 WCanStockInfo wCanStockInfo=  new WCanStockInfo();
		 wCanStockInfo.setCanName(" ");
		config.getServletContext().setAttribute("wCanStockInfo", wCanStockInfo);
		
	}
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
		String url = request.getRequestURI();
		PrintWriter printWritter=null;
		String responseUrl="";
		if(url.equals("/canwater/distributor-register")){
			responseUrl="jsp/loginrelated/signup.jsp";
			requestDispatcher=request.getRequestDispatcher(responseUrl);
			requestDispatcher.forward(request, response);
		}
		else if(url.equals("/canwater/stocks-info-page")){
			try {
				setStockPagesData(request);
			} catch (Exception e) {
				e.printStackTrace();
			}
			responseUrl="jsp/afterlogin/stocks.jsp";
			requestDispatcher=request.getRequestDispatcher(responseUrl);
			requestDispatcher.forward(request, response);
		}else if(url.equals("/canwater/get-stck-entry-jsons")){
			String stkId=request.getParameter("stkId");
			System.out.println(stkId);
			try {
				if(getStokcEntryArray(stkId).length() != 0){
					response.setContentType("application/json");
					response.setCharacterEncoding("utf-8");
					System.out.println(getStokcEntryArray(stkId));
					printWritter=response.getWriter();
					printWritter.print(getStokcEntryArray(stkId).toString());
				}else{
					
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		}
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
		String url = request.getRequestURI();
		PrintWriter printWriter=response.getWriter();
		JSONObject resultJson=null;
		String responseUrl="";
		if(url.equals("/canwater/addwatercan-stock-form-submit")){
			String data=request.getParameter("distributorformjsondata");
			try {
				addCanInfo(data);
				setStockPagesData(request);
				responseUrl="jsp/afterlogin/stocks.jsp";
				RequestDispatcher requestDispatcher=request.getRequestDispatcher(responseUrl);
				requestDispatcher.forward(request, response);
			} catch ( Exception e) {
				e.printStackTrace();
			}
		}else if(url.equals("/canwater/update-can-info-page")){
			String data=request.getParameter("updatedcaninfodata");
			resultJson=new JSONObject();
			try {
			if(updateCanInfo(data)){
				resultJson.put("status", "success");
				resultJson.put("updateddata", data);
				setStockPagesData(request);
				printWriter.print(resultJson.toString());
			}else{
				resultJson.put("status", "not updated");
				printWriter.print(resultJson.toString());
			}
			
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}else if(url.equals("/canwater/delete-can-info")){
			resultJson=new JSONObject();
			String deleteId=request.getParameter("deletecanid");
			try {
				if(deleteCanInfo(deleteId)){
					resultJson.put("status", "success");
					printWriter.print(resultJson.toString());
				}else{
					resultJson.put("status", "not deleted");
					printWriter.print(resultJson.toString());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(url.equals("/canwater/stock-entry-form")){
			String stokcentrydata = request.getParameter("stokcentrydata");
			//System.out.println("==="+stokcentrydata);
			try {
				resultJson=new JSONObject();
				if(addStockEntry(stokcentrydata)){
					resultJson.put("status", "success");
					printWriter.print(resultJson.toString());
				}else{
					resultJson.put("status", "not success");
					printWriter.print(resultJson.toString());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
	}
	public void setStockPagesData(HttpServletRequest request) throws Exception{
		ObjectId distributorId=new ObjectId(request.getSession().getAttribute("distributorid").toString());
		request.getSession().setAttribute("stocktable", new StocksDAOImpl().findAllStockCans("WCanStockInfo",distributorId.toString()));
	}
	public boolean addDistributor(WCanStockInfo canStockInfo){
		canStockInfoList.add(canStockInfo);
		return true;
	}
	public static Object convertJsonStringToObject(String  jsonString,Class<?> className) throws UnknownHostException{
		Gson gson=new Gson();
		return  gson.fromJson(jsonString, className);
	}
	public static boolean addCanInfo(String  caninfo) throws Exception{
		boolean status=new StocksDAOImpl().save("WCanStockInfo", (DBObject) JSON.parse(caninfo));
		return status;
	}
	public static boolean updateCanInfo(String  caninfo) throws Exception{
		boolean status=new StocksDAOImpl().update("WCanStockInfo", (DBObject) JSON.parse(caninfo));
		return status;
	}
	public static boolean deleteCanInfo(String  id) throws Exception{
		boolean status=new StocksDAOImpl().deleteCanInfo("WCanStockInfo", id);
		return status;
	}
	public static boolean addStockEntry(String stockEntryData) throws Exception{
		String tableName="wCanStockEntries";
		DBObject dbObject= (DBObject) JSON.parse(stockEntryData);
		boolean status=new StocksDAOImpl().save(tableName, dbObject);
		return status;
	}
	public static JSONArray getStokcEntryArray(String stkId) throws Exception{
		
		String tableName="wCanStockEntries";
		DBObject dbObject= (DBObject) JSON.parse("{\"stockinforefid\":\""+stkId+"\"}");
		return new StocksDAOImpl().findAll(tableName, dbObject);
	}
}
