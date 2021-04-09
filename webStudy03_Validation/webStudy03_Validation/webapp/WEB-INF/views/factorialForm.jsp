<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>팩토리얼 연산 처리</h4>
동기처리 연산 수행.
비동기 처리 연산 수행(JSON, HTML)
<form method="post">
	<%
		double number =request.getAttribute("number")==null? 0: (Double)request.getAttribute("number");
	%>
	<input type="number" name="single" value="<%=(int)number%>" />
	<input type="submit" value="=">
<%= (String)request.getAttribute("result")==null? "":(String)request.getAttribute("result")
%>
</form>
</body>
</html>