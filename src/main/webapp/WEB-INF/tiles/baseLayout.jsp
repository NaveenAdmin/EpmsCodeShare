<!--
Author		    :Prasad
creation Date	:30-09-2015
Descripition	:HPCL EMS application baselayout page .
Modified Date	:02-10-2015
Modified By	    :Prasad
-->
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Main</title>
    <!-- Bootstrap Core CSS -->
    
   <%--  <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
     <!-- Custom Fonts -->
    <link href=" <c:url value="/resources/css/font-awesome.min.css" />" rel="stylesheet" type="text/css">
    <!-- Custom CSS -->
    <link href="<c:url value="/resources/css/hpcl-design.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/jquery.bdt.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/font-awesome.min.css" />" rel="stylesheet">
	    <link href="http://www.jqueryscript.net/css/jquerysctipttop.css">
	
	<!-- jQuery -->
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
 --%></head>
    
   <body style="background-color:#f8f8f8">
    
    <div class="header-block">
    	<tiles:insertAttribute name="header" />
    	</div>
    	
    	
    	
    	<div class="content-area">
    	
    	<div class="side-block"> 
    	<tiles:insertAttribute name="menu" />
    	</div>
    	
    	<div class="body-block"> 
    	<tiles:insertAttribute name="body" />
    	</div>
    	</div>
    	<!-- <div class="clear"> </div> -->
  
    	
    	<div class="clearfix"></div>
    	<div class="footer-block"> 
    	<tiles:insertAttribute name="footer" />
    	</div>
    
    

    </body> 
     
</html>
