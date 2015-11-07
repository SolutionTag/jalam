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

	<link rel="stylesheet" href="${path}/dist/css/bootstrap-datetimepicker.min.css" /> </link>
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->


</head>
<body>
<input type="hidden" name="stockstable" id="stockstable" value='${stocktable}'>
 <div id="wrapper">

        <!-- Navigation -->
        <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index.html">SB Admin v2.0</a>
            </div>
            <!-- /.navbar-header -->

            <ul class="nav navbar-top-links navbar-right">
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-envelope fa-fw"></i>  <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-messages">
                        <li>
                            <a href="#">
                                <div>
                                    <strong>John Smith</strong>
                                    <span class="pull-right text-muted">
                                        <em>Yesterday</em>
                                    </span>
                                </div>
                                <div>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eleifend...</div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <strong>John Smith</strong>
                                    <span class="pull-right text-muted">
                                        <em>Yesterday</em>
                                    </span>
                                </div>
                                <div>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eleifend...</div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <strong>John Smith</strong>
                                    <span class="pull-right text-muted">
                                        <em>Yesterday</em>
                                    </span>
                                </div>
                                <div>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eleifend...</div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a class="text-center" href="#">
                                <strong>Read All Messages</strong>
                                <i class="fa fa-angle-right"></i>
                            </a>
                        </li>
                    </ul>
                    <!-- /.dropdown-messages -->
                </li>
                <!-- /.dropdown -->
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-tasks fa-fw"></i>  <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-tasks">
                        <li>
                            <a href="#">
                                <div>
                                    <p>
                                        <strong>Task 1</strong>
                                        <span class="pull-right text-muted">40% Complete</span>
                                    </p>
                                    <div class="progress progress-striped active">
                                        <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: 40%">
                                            <span class="sr-only">40% Complete (success)</span>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <p>
                                        <strong>Task 2</strong>
                                        <span class="pull-right text-muted">20% Complete</span>
                                    </p>
                                    <div class="progress progress-striped active">
                                        <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width: 20%">
                                            <span class="sr-only">20% Complete</span>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <p>
                                        <strong>Task 3</strong>
                                        <span class="pull-right text-muted">60% Complete</span>
                                    </p>
                                    <div class="progress progress-striped active">
                                        <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%">
                                            <span class="sr-only">60% Complete (warning)</span>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <p>
                                        <strong>Task 4</strong>
                                        <span class="pull-right text-muted">80% Complete</span>
                                    </p>
                                    <div class="progress progress-striped active">
                                        <div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100" style="width: 80%">
                                            <span class="sr-only">80% Complete (danger)</span>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a class="text-center" href="#">
                                <strong>See All Tasks</strong>
                                <i class="fa fa-angle-right"></i>
                            </a>
                        </li>
                    </ul>
                    <!-- /.dropdown-tasks -->
                </li>
                <!-- /.dropdown -->
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-bell fa-fw"></i>  <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-alerts">
                        <li>
                            <a href="#">
                                <div>
                                    <i class="fa fa-comment fa-fw"></i> New Comment
                                    <span class="pull-right text-muted small">4 minutes ago</span>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <i class="fa fa-twitter fa-fw"></i> 3 New Followers
                                    <span class="pull-right text-muted small">12 minutes ago</span>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <i class="fa fa-envelope fa-fw"></i> Message Sent
                                    <span class="pull-right text-muted small">4 minutes ago</span>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <i class="fa fa-tasks fa-fw"></i> New Task
                                    <span class="pull-right text-muted small">4 minutes ago</span>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <i class="fa fa-upload fa-fw"></i> Server Rebooted
                                    <span class="pull-right text-muted small">4 minutes ago</span>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a class="text-center" href="#">
                                <strong>See All Alerts</strong>
                                <i class="fa fa-angle-right"></i>
                            </a>
                        </li>
                    </ul>
                    <!-- /.dropdown-alerts -->
                </li>
                <!-- /.dropdown -->
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-user fa-fw"></i>  <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
                        <li><a href="#"><i class="fa fa-user fa-fw"></i> User Profile</a>
                        </li>
                        <li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a>
                        </li>
                        <li class="divider"></li>
                        <li><a href="${pageContext.request.contextPath}/log-out"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
                        </li>
                    </ul>
                    <!-- /.dropdown-user -->
                </li>
                <!-- /.dropdown -->
            </ul>
            <!-- /.navbar-top-links -->

            <div class="navbar-default sidebar" role="navigation">
                <div class="sidebar-nav navbar-collapse">
                    <ul class="nav" id="side-menu">
                        <li class="sidebar-search">
                            <div class="input-group custom-search-form">
                                <input type="text" class="form-control" placeholder="Search...">
                                <span class="input-group-btn">
                                    <button class="btn btn-default" type="button">
                                        <i class="fa fa-search"></i>
                                    </button>
                                </span>
                            </div>
                            <!-- /input-group -->
                        </li>
                        <li>
                            <a href="index.html"><i class="glyphicon glyphicon-arrow-right"></i>  ORDER REQUESTS</a>
                        </li>
                       <!--  <li>
                            <a href="#"><i class="glyphicon glyphicon-arrow-right"></i> Charts<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="flot.html">Flot Charts</a>
                                </li>
                                <li>
                                    <a href="morris.html">Morris.js Charts</a>
                                </li>
                            </ul>
                            /.nav-second-level
                        </li> -->
                        <li>
                            <a href="${pageContext.request.contextPath}/stocks-info-page"><i class="glyphicon glyphicon-arrow-right"></i>   STOCKS</a>
                        </li>
                        <li>
                            <a href="forms.html"><i class="glyphicon glyphicon-arrow-right"></i>    Forms</a>
                        </li>
                       <!--  <li>
                            <a href="#"><i class="fa fa-wrench fa-fw"></i> UI Elements<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="panels-wells.html">Panels and Wells</a>
                                </li>
                                <li>
                                    <a href="buttons.html">Buttons</a>
                                </li>
                                <li>
                                    <a href="notifications.html">Notifications</a>
                                </li>
                                <li>
                                    <a href="typography.html">Typography</a>
                                </li>
                                <li>
                                    <a href="icons.html"> Icons</a>
                                </li>
                                <li>
                                    <a href="grid.html">Grid</a>
                                </li>
                            </ul>
                            /.nav-second-level
                        </li> -->
                        <!-- <li>
                            <a href="#"><i class="fa fa-sitemap fa-fw"></i> Multi-Level Dropdown<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="#">Second Level Item</a>
                                </li>
                                <li>
                                    <a href="#">Second Level Item</a>
                                </li>
                                <li>
                                    <a href="#">Third Level <span class="fa arrow"></span></a>
                                    <ul class="nav nav-third-level">
                                        <li>
                                            <a href="#">Third Level Item</a>
                                        </li>
                                        <li>
                                            <a href="#">Third Level Item</a>
                                        </li>
                                        <li>
                                            <a href="#">Third Level Item</a>
                                        </li>
                                        <li>
                                            <a href="#">Third Level Item</a>
                                        </li>
                                    </ul>
                                    /.nav-third-level
                                </li>
                            </ul>
                            /.nav-second-level
                        </li> -->
                        <!-- <li class="active">
                            <a href="#"><i class="fa fa-files-o fa-fw"></i> Sample Pages<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a class="active" href="blank.html">Blank Page</a>
                                </li>
                                <li>
                                    <a href="login.html">Login Page</a>
                                </li>
                            </ul>
                            /.nav-second-level
                        </li> -->
                    </ul>
                </div>
                <!-- /.sidebar-collapse -->
            </div>
            <!-- /.navbar-static-side -->
        </nav>

        <!-- Page Content -->
        <div id="page-wrapper">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">STOCKS</h1>
                        
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
                <div class="row">
                
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Basic Tabs
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <!-- Nav tabs -->
                            <ul class="nav nav-tabs">
                                <li class="active"><a href="#addwatercan" data-toggle="tab" aria-expanded="false">ADD NEW WATER CAN</a>
                                </li>
                                <li class=""><a href="#displayallcan" data-toggle="tab" aria-expanded="true">SHOW ALL CAN</a>
                                </li>
                                <li class=""><a href="#stockavailable" data-toggle="tab" aria-expanded="false">STOCKS AVAILABLE</a>
                                </li>
                                <li class=""><a href="#settings" data-toggle="tab" aria-expanded="false">SETTINGS</a>
                                </li>
                            </ul>

                            <!-- Tab panes -->
                            <div class="tab-content">
                                <div class="tab-pane fade active in"  id="addwatercan">
                                   <div class="col-lg-6">
                                    <form role="form" name="addwatercanstocks" id="addwatercanstocks" action="${pageContext.request.contextPath}/addwatercan-stock-form-submit" method="post">
                                        <div class="form-group">
                                            <label>Can Name</label>
                                            <input class="form-control" name="canName" id="canName">
                                            <input type="hidden" id="distributorid" name="distributorid" value="${distributorid}">
                                            <p class="help-block"></p>
                                        </div>
                                        <div class="form-group">
                                            <label>Can Quantity</label>
                                            <input class="form-control" name="canQuantity" id="canQuantity">
                                            <p class="help-block"></p>
                                        </div>
                                        <div class="form-group">
                                            <label>Can Price</label>
                                            <input class="form-control" name="canPrice" id="canPrice">
                                            <p class="help-block"></p>
                                        </div>
                                        <div class="form-group">
                                            <label>Can Manufature Name </label>
                                            <input class="form-control" name="canManufactureName" id="canManufactureName">
                                            <p class="help-block"></p>
                                        </div>
                                        <div class="form-group">
                                            <label>Can Manufacture Location</label>
                                            <input class="form-control" name="canManufactureLocation" id="canManufactureLocation">
                                            <p class="help-block"></p>
                                        </div>
                                        <div class="form-group">
                                            <label>Can Expiry Date</label>
                                            <input class="form-control"  name="canExpiryDate" id="canExpiryDate">
                                              <input type="hidden" name="distributorformjsondata" id="distributorformjsondata">
                                            <p class="help-block"></p>
                                        </div>
                                        <input type="button" class="btn btn-default" id="stockformbutton" value="Register">
                                    </form>
                                </div>
                                </div>
                                <div class="tab-pane fade" id="displayallcan">
										<div class="panel-body">
											<!-- <div class="dataTable_wrapper">
												<table
													class="table table-striped table-bordered table-hover"
													id="cantable">
												</table>
											</div> -->
											<div class="row" id="canposterdiv"></div>
											<div class="row" id="modalwindowdiv">

												<div class="modal fade" id="watercanmodal" tabindex="-1"
													role="dialog" aria-labelledby="myModalLabel"
													aria-hidden="true" style="display: none;">
													<div class="modal-dialog">
					<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">×</button>
								<h4 class="modal-title" id="myModalLabel">Edit Can Information
									</h4>
							</div>
					<div class="modal-body">
					
                                    <form role="form" name="editwatercanstocks" id="editwatercanstocks" action="${pageContext.request.contextPath}/addwatercan-stock-form-submit" method="post">
                                        <div class="form-group">
                                            <label>Can Name</label>
                                            <input class="form-control" name="canName" id="canName">
                                             <input type="hidden" id="distributorid" name="distributorid" value="${distributorid}">
                                            <input type="hidden" name="id" id="id">
                                            <p class="help-block"></p>
                                        </div>
                                        <div class="form-group">
                                            <label>Can Quantity</label>
                                            <input class="form-control" name="canQuantity" id="canQuantity">
                                            <p class="help-block"></p>
                                        </div>
                                        <div class="form-group">
                                            <label>Can Price</label>
                                            <input class="form-control" name="canPrice" id="canPrice">
                                            <p class="help-block"></p>
                                        </div>
                                        <div class="form-group">
                                            <label>Can Manufature Name </label>
                                            <input class="form-control" name="canManufactureName" id="canManufactureName">
                                            <p class="help-block"></p>
                                        </div>
                                        <div class="form-group">
                                            <label>Can Manufacture Location</label>
                                            <input class="form-control" name="canManufactureLocation" id="canManufactureLocation">
                                            <p class="help-block"></p>
                                        </div>
                                        <div class="form-group">
                                            <label>Can Expiry Date</label>
                                            <input class="form-control"  name="canExpiryDate" id="canExpiryDate">
                                              <input type="hidden" name="distributorformjsondata" id="distributorformjsondata">
                                            <p class="help-block"></p>
                                        </div>
                                        <input type="button" class="btn btn-default" id="editstockformbutton" value="Update">
                                    </form>
                                
						</div>
						   <!-- 	<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">Close</button>
								<button type="button" class="btn btn-primary">Save
									changes</button>
							</div> -->
					</div>
														<!-- /.modal-content -->
													</div>
													<!-- /.modal-dialog -->
												</div>
											</div>
										</div>
									</div>
                                <div class="tab-pane fade" id="stockavailable">
                                <div class="row"><div class="form-group"></div></div>
                                <div class="row"><div class="form-group"></div></div>
                                <div class="row"><div class="form-group"></div></div>
                                <div class="row">
                                <div class="col-xs-3"></div>
	                                 <div class="col-xs-3">
											<div class="form-group">
	                                            <select class="form-control" id="canlists">
	                                                <option value="select">Select</option>
	                                            </select>
	                                        </div>
	                                  </div>
                                  <div class="col-xs-3"></div>
								</div>
                                   <div class="panel-body">
											<div class="container">
												<div class="row" id="stockimportdiv" style="display: none">
													<form action="#" id="stockentryform" name="stockentryform">
														<div class="col-xs-3">
															<div class="form-group " id="stkimportdatediv">
																<label for="stk">Stock Import Date</label> 
																<input type="text" class="form-control" id="stockimporteddate"
																	placeholder="Stock Import Date">
																	<input type="hidden" name="stockinforefid" id="stockinforefid">
																	 <input type="hidden" id="distributorid" name="distributorid" value="${distributorid}">
															</div>
															<div class="form-group">
																<label for="stk">Stock Size</label> <input type="text"
																	class="form-control" name="stocksize"  id="stocksize"
																	placeholder="Stock Size">
															</div>
														</div>
														<div class="col-xs-3">
															<div class="form-group">
																<label for="stk">Stock Imported From</label> <input
																	type="text" class="form-control" name="stockfrom" id="stockfrom"
																	placeholder="Stokc Imported From">
															</div>
															<div class="form-group" style="height: 77px;">
															</div>
															<div class="form-group">
																	<button type="button" id="stockentryformbutton" class="btn btn-default">Submit</button>
															</div>
															<!-- <div class="form-group">
																<label for="password">Password</label> <input
																	type="password" class="form-control" id="password"
																	placeholder="Password">
															</div> -->
														</div>
													</form>
												</div>
											</div>
										</div>
										<div class="dataTable_wrapper">
												<table class="table table-striped table-bordered table-hover" id="canstkentrytable">
												</table>
											</div>
                                </div>
                                <div class="tab-pane fade" id="settings">
                                    
                                </div>
                            </div>
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                </div>
                <!-- /.row -->
            </div>
            <!-- /.container-fluid -->
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

    <!-- jQuery -->
    <script src="${path}/jquery/dist/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="${path}/bootstrap/dist/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="${path}/metisMenu/dist/metisMenu.min.js"></script>
    
     <%-- <script src="${path}/datatable/dataTables.bootstrap.min.js"></script> --%>

	<script
		src="${path}/datatable/jquery.dataTables.js"></script>
	<script
		src="${path}/datatable/dataTables.bootstrap.min.js"></script>
	<script
		src="${path}/datatable/dataTables.bootstrap.min.js"></script>
	<script
		src="${path}/jquery/dist/jquery.loadJSON.js"></script>
         <script type="text/javascript" 
     src="${path}/dist/js/moment-with-locales.js"></script>
     <script type="text/javascript" 
     src="${path}/dist/js/bootstrap-datetimepicker.js"></script>
     
  
	<!-- Custom Theme JavaScript -->
    <script src="${path}/dist/js/sb-admin-2.js"></script>
    <script>
    var canObject=new Object();
$(document).ready(function(){
	loadStockTable();
	 $('#stockimporteddate').datetimepicker();
	$("#stockformbutton").click(function(){
		var serializeArray=$("#addwatercanstocks").serializeArray();
		var formVal={};
		$.each(serializeArray,function(k,v){
			formVal[v.name]=v.value;
		});
	
		$("#distributorformjsondata").val(JSON.stringify(formVal))
		$("#addwatercanstocks").submit();
		$("#distributorformjsondata").val("");
	});
	$('#canstkentrytable').DataTable({
        destroy: true,
        bSort: false,
        aoColumns: [ { sWidth: "100%" }, { sWidth: "100%" }, { sWidth: "100%", bSearchable: false, bSortable: false } ],
  //  "scrollY":        "200px",
   // "scrollCollapse": true,
    //"info":           true,
    "paging":         true,
    "aoColumnDefs": [
        	{"mDataProp":"id","sTitle":"Id","aTargets": [0]},
        	{"mDataProp":"stocksize","sTitle":"Stock Size","aTargets": [1]},
        	{"mDataProp":"stockfrom","sTitle":"Stock From","aTargets": [2]},
        	{"mDataProp":"stockimporteddate","sTitle":"Stock Imported Date","aTargets": [3]}
        	]
});
	$("#stockentryformbutton").click(function(){
		var serializeArray=$("#stockentryform").serializeArray();
		var formVal={};
		$.each(serializeArray,function(k,v){
			formVal[v.name]=v.value;
		});
	formVal["stockimporteddate"]=$("#stockimporteddate").val();
	
		//$("#distributorformjsondata").val(JSON.stringify(formVal))
		
		$.ajax({
			url:""+currentHost+"/canwater/stock-entry-form?stokcentrydata="+JSON.stringify(formVal),
			type:"post",
			//async:false,
			contentType:"application/json",
			dataType:"json",
			success:function(data){
				if(data.status=="success"){
					alert("saved suucessfully");
				}else{
					
				}
			},error:function(){
				
			}
		});
		$(':input','#stockentryform')
		 .not(':button, :submit, :reset, :hidden')
		 .val('')
		 .removeAttr('checked')
		 .removeAttr('selected')
	})
	$("#editstockformbutton").click(function(eve){
		var serializeArray=$("#editwatercanstocks").serializeArray();
		var formVal={};
		$.each(serializeArray,function(k,v){
			formVal[v.name]=v.value;
		});
		
		$.ajax({
			url:""+currentHost+"/canwater/update-can-info-page?updatedcaninfodata="+JSON.stringify(formVal)+"",
			type:"post",
			//async:false,
			contentType:"application/json",
			dataType:"json",
			success:function(data){
				if(data.status=="success"){
					$("i[data-id="+JSON.parse(data.updateddata).id+"]").closest(".col-lg-3").remove();
					$("#canlists option").not("option[value=\"select\"]").remove();
					var canposter=	 "<div class=\"col-lg-3 col-md-6\">"+
				    " <div class=\"panel panel-primary\">"+
				         "<div class=\"panel-heading\">"+
				            "<div class=\"row\">"+
				               " <div class=\"col-xs-3\">"+
				                    " <i class=\"fa fa-bitbucket fa-5x\"></i>"+
				                 "</div>"+
				                 "<div class=\"col-xs-9 text-right\">"+
				                    " <div class=\"huge\">"+JSON.parse(data.updateddata).canName.toUpperCase()+"</div>"+
				                    " <div>"+JSON.parse(data.updateddata).canName.toUpperCase()+"</div>"+
				                 "</div>"+
				             "</div>"+
				         "</div>"+
				         "<a href=\"#\">"+
				             "<div class=\"panel-footer\">"+
				                 "<span class=\"pull-left\"><i data-id="+JSON.parse(data.updateddata).id+" class=\"fa fa-trash-o\"></i></span>"+
				                 "<span class=\"pull-right\"><i  data-id="+JSON.parse(data.updateddata).id+" data-toggle=\"modal\" data-target=\"#watercanmodal\" class=\"fa fa-edit\"></i></span>"+
				                " <div class=\"clearfix\"></div>"+
				             "</div>"+
				         "</a>"+
				     "</div>"+
				 "</div>";
				 var canoption="<option value="+JSON.parse(data.updateddata).id+">"+JSON.parse(data.updateddata).canName.toUpperCase()+"</option>"
				$("#canlists").append(canoption);
				$("#canposterdiv").append(canposter);
				$("#watercanmodal").modal("hide");
				}
				else if(data.status=="not updated"){
					alert("data Not updated");
				}
				
			},error:function(){
				
			}
		})
		
	});
	$("#canlists").on("change",function(eve){
		$('#canstkentrytable').dataTable().fnClearTable();
		var currentValue=this.options[this.selectedIndex].value;
		if(currentValue=="select")
		$("#stockimportdiv").hide();
		else{
			$("#stockimportdiv").show();
			$("#stockimportdiv").attr("data-formid",currentValue);
			$("#stockinforefid").val(currentValue);
			 $.ajax({
					url:""+currentHost+"/canwater/get-stck-entry-jsons?stkId="+currentValue,
					type:"get",
					contentType:"application/json",
					dataType:"json",
					success:function(data){
						var returnedArray=[];
						$.each( data, function( key, val ) {
							returnedArray.push(JSON.parse(this));
						  });
						$('#canstkentrytable').DataTable({
								"aaData":returnedArray,
						        destroy: true,
						        bSort: false,
					            aoColumns: [ { sWidth: "45%" }, { sWidth: "45%" }, { sWidth: "10%", bSearchable: false, bSortable: false } ],
					        "scrollY":        "200px",
					        "scrollCollapse": true,
					        "info":           true,
					        "paging":         true,
						    "aoColumnDefs": [
						        	{"mDataProp":"id","sTitle":"Id","aTargets": [0]},
						        	{"mDataProp":"stocksize","sTitle":"Stock Size","aTargets": [1]},
						        	{"mDataProp":"stockfrom","sTitle":"Stock From","aTargets": [2]},
						        	{"mDataProp":"stockimporteddate","sTitle":"Stock Imported Date","aTargets": [3]}
						        	]
						});
						        	/*, {"mDataProp":"stockimporteddate","mDataTitle":"Imported Date"}
						        }]
								"columnsDef":[{"title":"my data title"}],
								//"aoColumns":[{"name":"ID","field":"id"},{"name":"canName","field":"canName"},{"name":"canQuantity","field":"canQuantity"},{"name":"canPrice","field":"canPricecanPrice"},{"name":"canManufactureName","field":"canManufactureName"}],
								"aoColumns":[{"mDataProp":"id","mDataTitle":"Id"},{"mDataProp":"stocksize","mDataTitle":"Stock Size"},{"mDataProp":"stockfrom","mDataTitle":"Stock From"}/*, {"mDataProp":"stockimporteddate","mDataTitle":"Imported Date"} */
						
					},error:function(){
						
					}
				});
			
		}
		setupEventSource();
	})
})

function getStckEntryDataForCrspondingCan(stkId){
	var returnedArray=[];
	$.getJSON( ""+currentHost+"/canwater/get-stck-entry-jsons?stkId="+stkId,{async:false}, function( data ) {
	}).done(function( data ) {
		$.each( data, function( key, val ) {
			returnedArray.push(JSON.parse(this));
		  });
	});
	return returnedArray;
}
$("#watercanmodal").on("show.bs.modal",function(eve){
	$("#editwatercanstocks").loadJSON(canObject[eve.relatedTarget.dataset.id]);
})

function loadStockTable(){
	 var canTableArray=[];
	 $.each($.parseJSON($("#stockstable").val()),function(key,value){
	 data=JSON.parse(this);
	 canObject[JSON.parse(this)._id.$oid]=JSON.parse(this);
	 canObject[JSON.parse(this)._id.$oid].id=JSON.parse(this)._id.$oid;
	 data.id=key;
	 
	 canTableArray.push(data)
	var canposter=	 "<div class=\"col-lg-3 col-md-6\">"+
    " <div class=\"panel panel-primary\">"+
         "<div class=\"panel-heading\">"+
            "<div class=\"row\">"+
               " <div class=\"col-xs-3\">"+
                    " <i class=\"fa fa-bitbucket fa-5x\"></i>"+
                 "</div>"+
                 "<div class=\"col-xs-9 text-right\">"+
                    " <div class=\"huge\">"+data.canName.toUpperCase()+"</div>"+
                    " <div>"+data.canName.toUpperCase()+"</div>"+
                 "</div>"+
             "</div>"+
         "</div>"+
         "<a href=\"#\">"+
             "<div class=\"panel-footer\">"+
                 "<span class=\"pull-left\"><i data-event=\"delete\" data-id="+JSON.parse(this)._id.$oid+" class=\"fa fa-trash-o\"></i></span>"+
                 "<span class=\"pull-right\"><i  data-id="+JSON.parse(this)._id.$oid+" data-toggle=\"modal\" data-target=\"#watercanmodal\" class=\"fa fa-edit\"></i></span>"+
                " <div class=\"clearfix\"></div>"+
             "</div>"+
         "</a>"+
     "</div>"+
 "</div>";
 var canoption="<option value="+JSON.parse(this)._id.$oid+">"+data.canName.toUpperCase()+"</option>"
	$("#canlists").append(canoption);
 $("#canposterdiv").append(canposter);
 $("i[data-event=delete]").click(function(eve){
	 var delteId=eve.target.dataset.id;
	 $.ajax({
			url:""+currentHost+"/canwater/delete-can-info?deletecanid="+delteId+"",
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
	 eve.stopImmediatePropagation();
	 eve.stopPropagation()
	 eve.preventDefault();
	 return false;
 })
	 	})
	
	$('#cantable').DataTable({"aaData":canTableArray,
		"columnsDef":[{"title":"my data title"}],
		"aoColumns":[{"name":"ID","field":"id"},{"name":"canName","field":"canName"},{"name":"canQuantity","field":"canQuantity"},{"name":"canPrice","field":"canPricecanPrice"},{"name":"canManufactureName","field":"canManufactureName"}],
		"aoColumns":[{"mDataProp":"id","mDataTitle":"sNo"},{"mDataProp":"canName","mDataTitle":"can name"},{"mDataProp":"canQuantity"},{"mDataProp":"canPrice"},{"mDataProp":"canManufactureName"},{"mDataProp":"canManufactureLocation"}],
        
});
	
}
</script>
</body>
</html>