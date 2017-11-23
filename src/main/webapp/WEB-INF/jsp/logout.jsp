 <!--
Author		    :Kshamashree
creation Date	:30-09-2015
Descripition	:HPCL EMS application device page for accessing the application.
Modified Date	:30-10-15
Modified By	    :Prasad
-->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*"%>
<%@ page import="javax.servlet.*" %>
<%@ page import="javax.servlet.http.*" %>
<html>
<head>
<title>Logout</title>
</head>
<body>
  <% 
    HttpSession s = request.getSession(false);
    s.invalidate();
   %>
   <h4>Logout</h4>
 <p>You are now logged out.</p>
<a href="login.htm">Login</a>
</body>
</html>
