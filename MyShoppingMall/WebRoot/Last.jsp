<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'Last.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
   <h1>No3</h1>
   <%
	out.println("request============<br/>");
	out.println(request.getAttribute("requestName")+"<br/>");
	out.println(request.getAttribute("requestPassword")+"<br/>");
	
	out.println("session============<br/>");
	out.println(session.getAttribute("sessionName")+"<br/>");
	out.println(session.getAttribute("sessionPassword")+"<br/>");
	   
   
    %>
  </body>
</html>
