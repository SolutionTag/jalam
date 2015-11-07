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
<body>
<input type="hidden" name="stocktable" id="stocktable" value='${stocktable}'>
<div class="ui segment" style=" height: 60px;"></div>
<div class="ui secondary vertical pointing menu">
  <a class="item active" href="${pageContext.request.contextPath}/consumer">
    Distributors 
  </a>
  <a class="item">
    Request Status
  </a>
  <a class="item">
    Profile
  </a>
</div>
<div class="ui main text container">
<div class="ui items" id="distributorcans">
</div>
</div>
<head>
<script>
$(document).ready(function(){
	var imageArray=["daniel.jpg","elliot.jpg","helen.jpg"];
	 $.each($.parseJSON($("#stocktable").val()),function(key,value){
		 if(JSON.parse(value).canName !=""){
			 var data= "<div class=\"item\" data-id="+JSON.parse(value)._id.$oid+" data-distributorid="+JSON.parse(value).distributorid+">"+
			   " <div class=\"ui small image\">"+
			      "<img src=\"${path}/images/waterv1.jpg\">"+
			   " </div>"+
			   " <div class=\"middle aligned content\">"+
			     " <div class=\"header\">  "+JSON.parse(value).canName+" " +
			     
			     " </div>"+
			     " <div class=\"description\">"+
			        "<p>"+JSON.parse(value).canQuantity+"</p>"+
			        "<p>&#x20B9;"+JSON.parse(value).canPrice+"</p>"+
			     " </div>"+
			     " <div class=\"extra\">"+
			       " <div class=\"ui right floated button\" data-event=\"orderbutton\"> Order"+
			       " </div>"+
			     " </div>"+
			   " </div>"+
			 " </div>";
			 $("#distributorcans").append(data);
		 }
	 });
	 $("div[data-event=orderbutton]").click(function(ev){
		 var units=prompt("Enter the number of  units to order ");
		 var consumerReqobject=$(ev.target).closest(".item").data();
		 consumerReqobject["units"]=units;
		 $.ajax({
				url:""+currentHost+"/canwater/consumer-order-request?consumerreqobjects="+JSON.stringify(consumerReqobject)+"",
				type:"post",
				//async:false,
				contentType:"application/json",
				dataType:"json",
				success:function(data){
					if(data.status=="not deleted"){
						alert("not delted");
					}else if(data.status=="success"){
						$("i[data-id="+delteId+"]").closest(".col-lg-3").remove();
						$("option[value=\""+delteId+"\"]").remove();
						alert("delted");
					}
					
				},error:function(){
					
				}
			});
	 });
	
});
</script>

</head>
</body>
</html>