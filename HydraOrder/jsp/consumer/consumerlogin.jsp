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
  $(document)
    .ready(function() {
      $('.ui.form')
        .form({
          fields: {
            email: {
              identifier  : 'email',
              rules: [
                {
                  type   : 'empty',
                  prompt : 'Please enter your e-mail'
                },
                {
                  type   : 'email',
                  prompt : 'Please enter a valid e-mail'
                }
              ]
            },
            password: {
              identifier  : 'password',
              rules: [
                {
                  type   : 'empty',
                  prompt : 'Please enter your password'
                },
                {
                  type   : 'length[6]',
                  prompt : 'Your password must be at least 6 characters'
                }
              ]
            }
          }
        })
      ;
    })
  ;
  </script>
</head>

<body>

<div class="ui middle aligned center aligned grid">
  <div class="column">
    <h2 class="ui teal image header">
     <!--  <img src="assets/images/logo.png" class="image"> -->
      <div class="content">
        Log-in to your account
      </div>
    </h2>
    <form class="ui large form" name="consumerloginform" method="get" id="consumerloginform" action="${pageContext.request.contextPath}/requestforcan">
      <div class="ui stacked segment">
        <div class="field">
            <i class="user icon"></i>
          <div class="ui left icon input">
            <input type="text" name="consumerEmail" id="consumerEmail" placeholder="E-mail address">
          </div>
        </div>
        <div class="field">
          <div class="ui left icon input">
            <i class="lock icon"></i>
            <input type="password" name="consumerPassword" id="consumerPassword" placeholder="Password">
            <input type="hidden" name="isValid" id="isValid" placeholder="Password">
          </div>
        </div>
        <div class="ui fluid large teal  button" id="consumerloginformbutton">Login</div>
      </div>
      <div class="ui error message"></div>


    </form>
      New to us? <a href="#">Sign Up</a>

    <div class="ui message">
    </div>
  </div>
</div>

</body>
<script>
$(document).ready(function(){
	$("#consumerloginformbutton").click(function(){
		var serializeArray=$("#consumerloginform").serializeArray();
		var formVal=new Object();
		$.each(serializeArray,function(k,v){
			formVal[v.name]=v.value;
		});
		$.ajax({
			url:""+currentHost+"/canwater/ajax-consumer-login-user-validation?logindata="+JSON.stringify(formVal)+"",
			type:"post",
			contentType:"application/json",
			dataType:"json",
			success:function(data){
				if(data.status=="VU"){
					$("#consumerEmail").remove()
					$("#consumerPassword").remove()
					$("#isValid").val("yes");
					//$("#consumerloginform input").remove()
					$("#userloginformjsondata").val(JSON.stringify(formVal))
					$("#consumerloginform").submit();
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
</html>