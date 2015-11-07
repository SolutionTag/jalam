<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SB Admin 2 - Bootstrap Admin Theme</title>
     <link href="${path}/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
     
	<script src="${path}/dist/js/properties.js" type="text/javascript"></script>
    <!-- Bootstrap Core CSS -->
    <link href="${path}/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="${path}/metisMenu/dist/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="${path}/dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="${path}/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

    <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Please Sign In</h3>
                    </div>
                    <div class="panel-body">
                        <form role="form" method="post" name="userloginform" id="userloginform" action="${pageContext.request.contextPath}/user-login-validation">
                            <fieldset>
                                <div class="form-group">
                                    <input class="form-control" placeholder="E-mail" name="username" type="email" autofocus>
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="Password" name="password" type="password" value="">
                                      <input type="hidden" name="userloginformjsondata" id="userloginformjsondata">
                                </div>
                                <div class="newuser" style="float: right;">
                                    <label>
                                      <a href="${pageContext.request.contextPath}/distributor-register">New User</a>
                                    </label>
                                </div>
                                 <div class="forgotpw">
                                    <label>
                                      <a href="forgotpw">Forgot Password</a>
                                    </label>
                                </div>
                                <!-- Change this to a button or input when using this as a form -->
                                <input type="button" id="userloginbutton" class="btn btn-lg btn-success btn-block" value="Login"> 
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- jQuery -->
    <script src="${path}/jquery/dist/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="${path}/bootstrap/dist/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="${path}/metisMenu/dist/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="${path}/dist/js/sb-admin-2.js"></script>
<script>
$(document).ready(function(){
	$("#userloginbutton").click(function(){
		var serializeArray=$("#userloginform").serializeArray();
		var formVal=new Object();
		$.each(serializeArray,function(k,v){
			formVal[v.name]=v.value;
		});
		$.ajax({
			url:""+currentHost+"/canwater/ajax-login-user-validation?logindata="+JSON.stringify(formVal)+"",
			type:"post",
			contentType:"application/json",
			dataType:"json",
			success:function(data){
				if(data.status=="VU"){
					$("#userloginformjsondata").val(JSON.stringify(formVal))
					$("#userloginform").submit();
				}
				else if(data.status=="IVU"){
					alert("wrong user");
				}
				
			},error:function(){
				
			}
		})
		
	})
	
})

</script>
</body>

</html>