<%@page import="java.util.Objects"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
.error{
	color : red;
	font-weight : bold;
}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String failedId = (String) session.getAttribute("failedId");
	session.removeAttribute("failedId");
	String message = (String)request.getAttribute("message");
	if(message == null){
		message = (String)session.getAttribute("message");
	}
	if(message != null && !message.isEmpty()){
		%>
		<div class="error"><%=message %></div>
		<%
	}
%>
<form action="<%=request.getContextPath() %>/login/loginCheck.do" method="post">
	<input type="text" 
	value="<%= Objects.toString(failedId, "")%>" 
	name="mem_id" placeholder="id"/>
	<input type="text" name="mem_pass" placeholder="password"/>
	<input type="checkbox" name="saveId" value="saveId">아이디기억하기
	<input type="submit" value="로그인"/>
</form>
</body>
</html>