<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Consumer Login </title>



  <link rel="stylesheet" type="text/css" href="${path}/loginandlogoutcss/reset.css">
  <link rel="stylesheet" type="text/css" href="${path}/loginandlogoutcss/site.css">
  <link rel="stylesheet" type="text/css" href="${path}/loginandlogoutcss/container.css">
  <link rel="stylesheet" type="text/css" href="${path}/loginandlogoutcss/grid.css">
  <link rel="stylesheet" type="text/css" href="${path}/loginandlogoutcss/header.css">
  <link rel="stylesheet" type="text/css" href="${path}/loginandlogoutcss/image.css">
  <link rel="stylesheet" type="text/css" href="${path}/loginandlogoutcss/menu.css">

  <link rel="stylesheet" type="text/css" href="${path}/loginandlogoutcss/divider.css">
  <link rel="stylesheet" type="text/css" href="${path}/loginandlogoutcss/segment.css">
  <link rel="stylesheet" type="text/css" href="${path}/loginandlogoutcss/form.css">
  <link rel="stylesheet" type="text/css" href="${path}/loginandlogoutcss/input.css">
  <link rel="stylesheet" type="text/css" href="${path}/loginandlogoutcss/button.css">
  <link rel="stylesheet" type="text/css" href="${path}/loginandlogoutcss/list.css">
  <link rel="stylesheet" type="text/css" href="${path}/loginandlogoutcss/message.css">
  <link rel="stylesheet" type="text/css" href="${path}/loginandlogoutcss/icon.css">

  <script src="${path}/jquery/dist/jquery.min.js"></script>
  <script src="${path}/loginandlogoutcss/form.js"></script>
  <script src="${path}/loginandlogoutcss/transition.js"></script>
  <script src="${path}/dist/js/properties.js" type="text/javascript"></script>
   <style type="text/css">
    body {
      background-color: #DADADA;
    }
    body > .grid {
      height: 100%;
    }
    .image {
      margin-top: -100px;
    }
    .column {
      max-width: 450px;
    }
  </style>
  <script>
  $(document).ready(function() {
	  	$("#consumerloginformbutton").click(function(){
	  		var serializeArray=$("#consumersignupform").serializeArray();
	  		var formVal=new Object();
	  		$.each(serializeArray,function(k,v){
	  			formVal[v.name]=v.value;
	  		});
	  		
	  		$("#distributorformjsondata").val(JSON.stringify(formVal))
	  		$("#consumersignupform").submit();
	  	})
  });
  </script>
</head>
<body>
<!-- "consumerName" : "", "consumerUserName" : "", "consumerPassword" : "", "consumerPhoneNo" : "", "consumerEmail" : "", "consumerImage" : "" -->
<div class="ui middle aligned center aligned grid">
  <div class="column">
    <h2 class="ui teal image header">
      <img src="assets/images/logo.png" class="image">
      <div class="content">
        Log-in to your account
      </div>
    </h2>
    <form class="ui large form" id="consumersignupform" method="post" action="${pageContext.request.contextPath}/requestforcan">
      <div class="ui stacked segment">
        <div class="field">
          <div class="ui left icon input">
            <i class="user icon"></i>
            <input type="text" name="consumerName" id="consumerName" placeholder="consumer  name">
            <input type="hidden" name="distributorformjsondata" id="distributorformjsondata">
          </div>
        </div>
        
          <div class="field">
          <div class="ui left icon input">
            <i class="user icon"></i>
            <input type="text" name="consumerUserName" id="consumerUserName" placeholder="consumer user name">
          </div>
        </div>
        
          <div class="field">
          <div class="ui left icon input">
            <i class="user icon"></i>
            <input type="text" name="consumerPassword" id="consumerPassword" placeholder="consumer password">
          </div>
        </div>
        
          <div class="field">
          <div class="ui left icon input">
            <i class="user icon"></i>
            <input type="text" name="consumerEmail" id="consumerEmail" placeholder="consumer email">
          </div>
        </div>
        <div class="ui fluid large teal submit button" id="consumerloginformbutton">SignUp</div>
      </div>

      <div class="ui error message"></div>

    </form>

    <div class="ui message">
      Exiting User? <a href="#">Login Here</a>
    </div>
  </div>
</div>
</body>
</html>