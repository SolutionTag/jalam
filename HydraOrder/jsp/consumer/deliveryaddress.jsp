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
<input type="hidden" name="consumerinfo" id="consumerinfo" value='${consumerinfo}'>
<input type="hidden" name="consumerdeliveryaddress" id="consumerdeliveryaddress" value='${consumerdeliveryaddress}'>
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


<div class="ui raised very padded text" style="margin-top:80px;">
<div class="ui secondary vertical pointing menu" style="margin-top: 79px;float: left;">
<a class="item " href="${pageContext.request.contextPath}/requestforcan" >
    Request Water Can 
  </a>
  <a class="item" href="${pageContext.request.contextPath}/distributor-config">
    Distributors 
  </a>
  <a class="item active">
    Delivery Addresses 
  </a>
  <a class="item">
    Profile
  </a>
</div>


<div class="row" style=" width: 500px;margin-left: 346px;">
<h3 class="ui block header" style="text-align: center;">
  Delivery Address
</h3>
</div> 
 <form class="ui form" style=" top: 69px;  left: 120px;" id="deliveryaddressform" method="post" action="${pageContext.request.contextPath}/save-consumer-deliveryaddress">
   <div class="row">
    <div class="fields">
        <div class="field">
          <input type="text" placeholder="Name" id="consumerName" name="consumerName">
        </div>
        <div class="field">
        <!--   <input type="text" placeholder="Street Address"> -->
          <textarea rows="4" cols="25" placeholder="Street Address" name="consumerVerifiedAddress" id="consumerVerifiedAddress"> </textarea>
        </div>
        <div class="field">
          <input type="text" placeholder="Landmark" name="consumerLandMark" id="consumerLandMark">
        </div>
          
      </div>
  </div>
    <div class="row">
    <div class="fields">
        <div class="field">
          
          <input type="text" placeholder="consumerDoorNo" name="consumerDoorNo" id="consumerDoorNo">
        </div>
        <div class="field">
          <input type="text" placeholder="consumerStreet" name="consumerStreet" id="consumerStreet"> 
        </div>
        <div class="field">
          <input type="text" placeholder="consumerAreaName" name="consumerAreaName" id="consumerAreaName"> 
        </div>
         <!--  <div class="field">
          <label>Pincode</label>
          <input type="text" placeholder="Last Name">
        </div> -->
         
      </div>
  </div>
  <div class="row">
    <div class="fields">
        <div class="field">
          
          <input type="text" placeholder="City" name="consumerCity" id="consumerCity">
        </div>
        <div class="field">
          <input type="text" placeholder="State" name="consumerState" id="consumerState"> 
        </div>
        <div class="field">
          <input type="text" placeholder="Country" name="consumerCountry" id="consumerCountry">
        </div>
         <!--  <div class="field">
          <label>Pincode</label>
          <input type="text" placeholder="Last Name">
        </div> -->
         
      </div>
  </div>
  <div class="row">
   <div class="fields">
   <div class="field">
          <input type="text" placeholder="Phone Number" name="consumerPhoneNo" id="consumerPhoneNo">
            <input type="hidden" name="distributorformjsondata" id="distributorformjsondata">
            <input type="hidden" name="consumerId" id="consumerId">
      </div>
   </div>
  </div>
  <div class="row"></div>
  <div class="row"></div>
  <div class="row"></div>
  <div class="row"><div class="field">
        <button class="large ui inverted orange button" style="margin-left:435px;" id="deliveryaddressbutton">Save Address</button>
        </div></div>
 </form>
 <div class="ui horizontal divider" style="margin-top:91px;">
    
  </div>
<div class="ui three stackable doubling cards" id="addresslist">


</div>
</div>


<head>
<script>
var consumerId="";
$(document).ready(function(){
	consumerId=	JSON.parse($("#consumerinfo").val()).consumerId;
	$("#consumerId").val(consumerId);
	$("#deliveryaddressbutton").click(function(){
		var serializeArray=$("#deliveryaddressform").serializeArray();
		var formVal=new Object();
		$.each(serializeArray,function(k,v){
			formVal[v.name]=v.value;
		});
		
		$("#distributorformjsondata").val(JSON.stringify(formVal))
		$("#deliveryaddressform").submit();
	})
	var consumerAddress=JSON.parse($("#consumerdeliveryaddress").val());
	if(consumerAddress.length > 0){
		$.each(consumerAddress,function(key,val){
			val=JSON.parse(val)
		var address="<div class=\"ui card\">"+
	 			"<div class=\"content\">"+
	   			"<div class=\"header\">"+val.consumerName+""+
	   			"<div class=\"ui slider checkbox\" style=\"float: right;\">"+
	   		    "<input type=\"radio\" name=\"public\" data-addressid="+val._id.$oid+" data-consumerid="+val.consumerId+">"+
	   		   "<label></label></div></div>"+
	    		"<div class=\"meta\">"+
	     		"<span>Phone Number</span>"+
	     		"<a>+91 "+val.consumerPhoneNo+"</a>"+
	   		    "</div>"+
		   		"<p> No : "+val.consumerDoorNo+"</p>"+
	  			"<p>"+val.consumerStreet+"</p>"+
	  			"<p>"+val.consumerAreaName+"</p>"+
	  			"<p>"+val.consumerCity+"</p>"+
	  			"<p>"+val.consumerState+"</p>"+
	 			"</div>"+
				"</div>"+
			    "</div>";
		$("#addresslist").append(address);
		$(".checkbox").checkbox().checkbox({
			onChecked:function(){
				var addressInfo={"consumerid":this.dataset.consumerid,"addressid":this.dataset.addressid,"status":"checked"}
				setDefaultAddrss(addressInfo);
			},
		onUnchecked:function(){
			var addressInfo={"consumerid":this.dataset.consumerid,"addressid":this.dataset.addressid,"status":"not_checked"}
			setDefaultAddrss(addressInfo);
		}
			});
		function setDefaultAddrss(addressInfo){
			 $.ajax({
					url:""+currentHost+"/canwater/set-default-address?addressInfo="+JSON.stringify(addressInfo),
					type:"post",
					//async:false,
					contentType:"application/json",
					dataType:"json",
					success:function(data){
						
					},error:function(){
						
					}
				});
		}
		});
		
	}
	
});

</script>

</head>
</body>
</html>