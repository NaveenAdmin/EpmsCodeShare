

<!--
Author		    :Kshamashree
creation Date	:30-09-2015
Descripition	:HPCL EMS application device page.
Modified Date	:28-12-15
Modified By	    :Prasad-->

<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isErrorPage="true" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title>login</title>
<!-- Bootstrap Core CSS -->


<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet">
<!-- Custom Fonts -->
<link href=" <c:url value="/resources/css/font-awesome.min.css" />"
	rel="stylesheet" type="text/css">
<!-- Custom CSS -->
<link href="<c:url value="/resources/css/hpcl-design.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/hpcl-new-styles.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/jquery.bdt.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/font-awesome.min.css" />"
	rel="stylesheet">
<link href="/resources/css/jquerysctipttop.css">
<!-- <link href="http://www.jqueryscript.net/css/jquerysctipttop.css"> -->

<!-- jQuery -->

<!-- <script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script> 
<script	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>-->

<!-- Modified by Naveen on 25-11-2015 -->
<script src="/resources/js/jquery/1.11.3/jquery.min.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>

<script>
	function clearData() {
		$("#alert").empty();
		$(".error").empty();
	}
</script>
<!-- till the above line -->
<style>
.error {
	color: red;
	font-weight: bold;
	margin-top: 20%;
}
</style>
</head>
<body style="background-color: #D5D5D5">
	<header>
		<div id="divEMSHeader" class="logo-header"
			style="background-color: #0b67b2;">
			<div id="divHPCLLogo" class="col-md-2 col-sm-2">
				<img id="imgHPCLLogo" style="margin-top: 15px"
					class="img-responsive pull-left"
					src="<c:url value="/resources/images/hplogo.jpg" />" alt="HP">
			</div>

			<div id="divHeaderText" class="col-md-8 col-sm-8">
				<h1 id="HeaderText" class="page-header text-center"
					style="color: white;">EARTH PIT MONITORING SYSTEM</h1>
				<p class="pull-right" style="font-size: small; color: #fff;">
					<core:if test="${not empty version}">

					</core:if>
					Version:${version}
				</p>
			</div>

			<div id="divEMSLogo" class="col-md-2 col-sm-2">
				<img id="imgEMSLogo" style="margin-top: 15px"
					class="img-responsive pull-right"
					src="<c:url value="/resources/images/hpcl-right-logo.png" />"
					alt="HP">
			</div>
		</div>

	</header>

	<div id="divEMSBody" class="container login-body">
		<div class="row ">
			

<br>
<h1>Opps...</h1>
<table width="100%" border="1">
<tr valign="top">
<td width="40%"><b>Error:</b></td>
<td>${pageContext.exception}</td>
</tr>
<tr valign="top">
<td><b>URI:</b></td>
<td>${pageContext.errorData.requestURI}</td>
</tr>
<tr valign="top">
<td><b>Status code:</b></td>
<td>${pageContext.errorData.statusCode}</td>
</tr>
<tr valign="top">
<td><b>Stack trace:</b></td>
<td>
<core:forEach var="trace" 
         items="${pageContext.exception.stackTrace}">
<p>${trace}</p>
</core:forEach>
</td>
</tr>
</table>
				
			
		</div>
	</div>
	<!-- Footer -->
	<footer class="login-footer">
		<div id="divEMSFooter" class="footer-block ">
			<div class="col-md-12">
				<p class="copyright">Copyright © 2015 / All rights reserved.</p>
			</div>
		</div>
	</footer>
</body>
</html>

