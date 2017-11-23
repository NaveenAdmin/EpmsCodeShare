<!--
Author		    :Prasad
creation Date	:30-09-2015
Descripition	:HPCL EMS application login page for accessing the application.
Modified Date	:
Modified By	    :
-->
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title>Main</title>

<%-- <link href= "<c:url value="/resources/images/hpcl-logo.ico" />" rel="icon" type="image/x-icon"/>
<link rel="icon" type="image/png" sizes="32x32" href="<c:url value="/resources/images/hpcl-logo.png" />">
<link rel="icon" type="image/png" sizes="96x96" href="<c:url value="/resources/images/hpcl-logo.png" />">
<link rel="icon" type="image/png" sizes="16x16" href="<c:url value="/resources/images/hpcl-logo.png" />"> --%>

<LINK REL="SHORTCUT icon" HREF="http://my.hpcl.co.in/j2ee/portal/favicon.ico">


<!-- Bootstrap Core CSS -->

<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet">
<!-- <link href="<c:url value="/resources/css/bootstrap-datetimepicker.css" />"	rel="stylesheet"> --!>
<!-- Custom Fonts -->
<link href=" <c:url value="/resources/css/font-awesome.min.css" />"
	rel="stylesheet" type="text/css">
<!-- Custom CSS -->
<link href="<c:url value="/resources/css/hpcl-design.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/hpcl-new-styles.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/jquery.bdt.css" />" rel="stylesheet">

<!-- <link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Open+Sans" /> -->

<link href="<c:url value="/resources/css/font-awesome.min.css" />" 	rel="stylesheet">
<!--<link href="http://www.jqueryscript.net/css/jquerysctipttop.css">-->

<link href="<c:url value="/resources/css/jquery.dataTables.min.css" />"
	rel="stylesheet">

	
<!-- --------datepicker css & js Begin-------------->
 <link href="<c:url value="/resources/css/jquery-ui.css"/>" rel="stylesheet" />
 
<script src="<c:url value="/resources/js/jquery-1.10.2.js"/>"></script> 
 
 


<!-- --------JS Pagination script Closed--------------> 




<script src="<c:url value="/resources/js/validation.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.min.js"/>"> </script>
<script src="<c:url value="/resources/js/bootstrap-datepicker.js"/>"></script>
    
  <script src="<c:url value="resources/js/jquery.dataTables.min.js"/>"></script>
  <script src="<c:url value="resources/js/jquery.dataTables.columnFilter.js"/>"></script> 
  <script src="<c:url value="resources/js/jquery.simple.timer.js"/>"></script> 
  <style type="text/css">
  #bootstrap-table_filter{display:none;}
  </style>
            <div id="divEMSHeader" class="logo-header" style="background-color: #0b67b2;">
            <div id="divHPCLLogo" class="col-md-2 col-sm-2 col-xs-2">
            <img id ="imgHPCLLogo" style="margin-top:15px" class="img-responsive" src="<c:url value="/resources/images/hplogo.jpg" />" alt="HP">
            </div>
            <div id ="divHeaderText" class="col-md-8 col-sm-8 col-xs-8">
            <h1 id="HeaderText" class="page-header text-center" style="color: white;">EARTH PIT MONITORING SYSTEM</h1>
            <p class="pull-right" style="font-size:small; color:#fff;"> Version:<%=session.getAttribute("version").toString() %> </p>
            </div>
            <div id="divEMSLogo" class="col-md-2 col-sm-2 col-xs-2">
            <img id ="imgEMSLogo" style="margin-top:15px" class="img-responsive pull-right" src="<c:url value="/resources/images/hpcl-right-logo.png" />" alt="HP">
            </div>
    		</div>
    
   
    