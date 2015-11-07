<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
<title>consumer</title>
<script src="${path}/dist/js/properties.js" type="text/javascript"></script>
<script src="${path}/jquery/dist/jquery.min.js"></script>
<script src="${path}/dist/js/semantic.min.js" type="text/javascript"></script>
<link href="${path}/dist/css/semantic.min.css" rel="stylesheet"></link>
</head>
<body style="background-color: #DADADA;">
<input type="hidden" name="alldistributors" id="alldistributors" value='${alldistributors}'>
<input type="hidden" name="consumerinfo" id="consumerinfo" value='${consumerinfo}'>
<input type="hidden" name="consumerDistributorsList" id="consumerDistributorsList" value='${consumerDistributorsList}'>
<input type="hidden" name="commonDistributorList" id="commonDistributorList" value='${commonDistributorList}'>


<div class="ui fixed menu" style="background-color: rgba(100, 53, 201, 0.5);">
    <div class="ui container">
      <div href="#" class="header item">
       <!--  <img class="logo" src="assets/images/logo.png"> -->
      Water
      </div>
      <a href="#" class="item">Home</a>
      <a href="#" class="ui simple dropdown item">
        Dropdown <i class="dropdown icon"></i>
        <div class="menu">
          <div class="item">Link Item</div>
          <div class="item">Link Item</div>
          <div class="divider"></div>
          <div class="header">Header Item</div>
          <div class="item">
            <i class="dropdown icon"></i>
            Sub Menu
            <div class="menu">
              <div class="item">Link Item</div>
              <div class="item">Link Item</div>
            </div>
          </div>
          <div class="item">Link Item</div>
        </div>
      </a>
    </div>
  </div>
<div class="ui secondary vertical pointing menu" style=" margin-top: 79px;">
<a class="item " href="${pageContext.request.contextPath}/requestforcan">
    Request Water Can 
  </a>
  <a class="item active">
    Distributors 
  </a>
  <a class="item" href="${pageContext.request.contextPath}/consumer-delivery-address">
    Delivery Addresses 
  </a>
  <a class="item">
    Profile
  </a>
</div>
<div class="ui main text container">
<div class="ui three column grid" id="distributorgrid">
  <div class="ui top attached tabular menu">
 
  <a class="active item" data-tab="first">My Distributors</a>
  <a class="item" data-tab="second">Add New Distributor</a>
  <a class="item" data-tab="third">Settings</a>
</div>
<div class="ui bottom attached active tab segment" data-tab="first">
  <div class="ui three column grid" id="distributorlist">
  </div>
</div>
<div class="ui bottom attached tab segment" data-tab="second">
  <div class="ui search">
  <div class="ui icon input">
    <input class="prompt" type="text" placeholder="Search Distributors">
    <i class="search icon"></i>
  </div>
  <div class="results"></div>
</div>
</div>
<div class="ui bottom attached tab segment" data-tab="third">
  third
</div>
 <%--  <div class="column">
    <div class="ui fluid card">
      <div class="image">
        <img src="${pageContext.request.contextPath}/images/helen.jpg">
      </div>
      <div class="content">
        <a class="header">Helen Troy</a>
      </div>
    </div>
  </div>
  <div class="column">
    <div class="ui fluid card">
      <div class="image">
        <img src="${pageContext.request.contextPath}/images/elliot.jpg">
      </div>
      <div class="content">
        <a class="header">Elliot Fu</a>
      </div>
    </div>
  </div> --%>
</div>
</div>


<head>
<script>
var crntConsumerDistributorsId=[] , imageArray=[];
var consumerInfo="",consumerDistributorList,commonDistributorList;
$(document).ready(function(){
 consumerDistributorList =	$.parseJSON("["+$("#consumerDistributorsList").val()+"]");
 commonDistributorList   =	$.parseJSON("["+$("#commonDistributorList").val()+"]");
	var content = [];
	$('.menu .item').tab();
	 consumerInfo=$("#consumerinfo").val();
	
	  imageArray=["daniel.jpg","elliot.jpg","helen.jpg"];
	 if(typeof commonDistributorList[0]!="undefined" && commonDistributorList[0].length > 0)
	 $.each(commonDistributorList[0],function(key,value){
		 if(JSON.parse(value).distributorName !=""){
		content.push({ title: JSON.parse(value).distributorName,data:JSON.parse(value) });
		 }
	 }); 
	 if(typeof consumerDistributorList[0]!="undefined" && consumerDistributorList[0].length > 0)
	 $.each(consumerDistributorList[0],function(key,value){
		 if(JSON.parse(value).distributorName !=""){
			 var data=" <div class=\"column\" data-distributorid="+JSON.parse(value)._id.$oid+">"+
			   " <div class=\"ui fluid card\">"+
			      "<div class=\"image\">"+
			       " <img src=\"${path}/images/"+imageArray[Math.floor(Math.random() * 2) + 1  ]+"\">"+
			     " </div>"+
			      "<div class=\"content\">"+
			       " <a class=\"header\" href=${pageContext.request.contextPath}\\display-water-can?distributorid="+JSON.parse(value)._id.$oid+"\>"+JSON.parse(value).distributorName+"</a>"+
			       "<div class=\"meta\">"+
			       "<i class=\"marker icon\"></i> Chennai"+
			       "</div>"+
			       "<div class=\"description\">"+
			         " We are doing service very fast"+
			       "</div>"+
			     " </div>"+
			     "<div class=\"extra content\">"+
			     "<span class=\"right floated\">"+
			     "<i class=\"edit icon\"></i>"+
			    " </span>"+
			    " <span>"+
			    " <i class=\"trash icon\" style=\"cursor: pointer;\" onclick=\"callDeleteDistributor(this)\" data-distributorid="+JSON.parse(value)._id.$oid+" data-consumerid="+JSON.parse(consumerInfo).consumerId+"></i>"+
			   "  </span>"+
			   "  </div>"+
			    "</div>"+
			 "</div>";
			 crntConsumerDistributorsId.push(JSON.parse(value)._id.$oid)
			 $("#distributorlist").append(data);
		 }
	 }); 
	 initDistributorSearch(content)
	
	
});
function initDistributorSearch(source){
	// $('.ui .search').search("destroy");
	 $('.ui .search').search({
		    source: source,
		    onSelect:function(result, response){
		    	var answer=confirm("Are you sure you want to add this distributor in your account?")
		    	if(answer){
		    		 var createdStructuredata=" <div class=\"column\" data-distributorid="+result.data._id.$oid+">"+
					   " <div class=\"ui fluid card\">"+
					      "<div class=\"image\">"+
					       " <img src=\"${path}/images/"+imageArray[Math.floor(Math.random() * 2) + 1  ]+"\">"+
					     " </div>"+
					      "<div class=\"content\">"+
					       " <a class=\"header\" href=${pageContext.request.contextPath}\\display-water-can?distributorid="+result.data._id.$oid+"\>"+result.data.distributorName+"</a>"+
					       "<div class=\"meta\">"+
					       "<i class=\"marker icon\"></i> Chennai"+
					       "</div>"+
					       "<div class=\"description\">"+
					          "We are doing service very fast"+
					       "</div>"+
					     " </div>"+
					     "<div class=\"extra content\">"+
					     "<span class=\"right floated\">"+
					     "<i class=\"edit icon\"></i>"+
					    " </span>"+
					    " <span>"+
					    " <i class=\"trash icon\" style=\"cursor: pointer;\" onclick=\"callDeleteDistributor(this)\" data-distributorid="+result.data._id.$oid+" data-consumerid="+JSON.parse(consumerInfo).consumerId+"></i>"+
					   "  </span>"+
					   "  </div>"+
					    "</div>"+
					 "</div>";
					 crntConsumerDistributorsId.push(result.data._id.$oid);
					 result.data.consumerid=JSON.parse(consumerInfo).consumerId;
					 $.ajax({
							url:""+currentHost+"/canwater/save-distributor-to-consumer?consumerdistributors="+JSON.stringify(result.data)+"&crntconsumerdistid="+JSON.stringify(crntConsumerDistributorsId),
							type:"post",
							//async:false,
							contentType:"application/json",
							dataType:"json",
							success:function(data){
								
								commonDistributorList   =	$.parseJSON("["+data+"]");
								
								if(typeof commonDistributorList[0]!="undefined" && commonDistributorList.length > 0){
									var content = [];
									$.each(commonDistributorList,function(key,value){
										 if(value.distributorName !=""){
										content.push({ title: value.distributorName,data:value });
										 }
									 });
									//initDistributorSearch(content);
									//$("#distributorlist").append(createdStructuredata);
									  $("#distributorlist").append(createdStructuredata);
								}
								  if(typeof consumerDistributorList[0]!="undefined" && commonDistributorList.length == 0)
									  $("#distributorlist").append(createdStructuredata);
							},error:function(){
								
							}
						});
					 
					
		    	}else{
		    		
		    	}
		    }
		  });
}
function callDeleteDistributor(deleteEleObj){
	var distributorId=deleteEleObj.dataset.distributorid;
	 $.ajax({
			url:""+currentHost+"/canwater/delete-distributor-by-consumer?consumerId="+deleteEleObj.dataset.consumerid+"&distributorid="+deleteEleObj.dataset.distributorid,
			type:"post",
			//async:false,
			contentType:"application/json",
			dataType:"json",
			success:function(data){
				data=data.commondistributors;
				commonDistributorList=JSON.parse(data);
				if(typeof commonDistributorList[0]!="undefined" && commonDistributorList.length > 0){
					var content = [];
					$.each(commonDistributorList,function(key,value){
						 if(JSON.parse(value).distributorName !=""){
						content.push({ title: JSON.parse(value).distributorName,data:JSON.parse(value) });
						 }
					 });
					alert("distributor has been deleted")
					//initDistributorSearch(content);
					$("#distributorlist div[data-distributorid="+distributorId+"]").remove();
				}
				  //if(typeof consumerDistributorList[0]!="undefined" && commonDistributorList.length == 0)
					 // $("#distributorlist").append(data);
			},error:function(){
				
			}
		});
}
</script>

</head>
</body>
</html>