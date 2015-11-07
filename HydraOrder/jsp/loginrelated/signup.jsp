<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SB Admin 2 - Bootstrap Admin Theme</title>
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
                        <h3 class="panel-title">Please Register </h3>
                    </div>
                    <div class="panel-body">
                        <form role="form" name="distributorregisterform" id="distributorregisterform" action="${pageContext.request.contextPath}/distributor-register-form-submit" method="post">
                            <fieldset>
                                <div class="form-group">
                                    <input class="form-control" placeholder="Distributor Name" name="distributorName" id="distributorName" >
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="Shop Name" name="shopName" id="shopName" value="">
                                </div>
                                 <div class="form-group">
                                    <input class="form-control" placeholder="Email Id" name="distributoremailId" id="distributoremailId" value="">
                                </div>
                                 <div class="form-group">
                                    <input class="form-control" placeholder="User Name" name="distributorUserName" id="distributorUserName" value="">
                                </div>
                                 <div class="form-group">
                                    <input class="form-control" placeholder="Password" type="password" name="distributorPassword" id="distributorPassword" value="">
                                </div>
                                 <div class="form-group">
                                    <input class="form-control" placeholder="Retype Password" type="password" name="distributorPassword" id="distributorPassword" value="">
                                    <input type="hidden" name="distributorformjsondata" id="distributorformjsondata">
                                </div>
                                
                                <!-- Change this to a button or input when using this as a form -->
                               <input type="button" id="distributorformbutton" class="btn btn-lg btn-success btn-block" value="Register"> 
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
	$("#distributorformbutton").click(function(){
		var serializeArray=$("#distributorregisterform").serializeArray();
		var formVal=new Object();
		$.each(serializeArray,function(k,v){
			formVal[v.name]=v.value;
		});
		
		$("#distributorformjsondata").val(JSON.stringify(formVal))
		$("#distributorregisterform").submit();
	})
	
})

</script>
</body>

</html>
