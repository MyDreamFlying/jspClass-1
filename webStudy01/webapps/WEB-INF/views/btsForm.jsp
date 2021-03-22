<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method="post" action="">
	<select id="bts">
	<%
		Map<String,String> BtsMap = (Map)application.getAttribute("BtsMap");
		for(Entry<String,String> entry : BtsMap.entrySet()){
			String key = entry.getKey();
			String value = entry.getValue();
			%>
			<option value="<%=key%>"><%=value %></option>
			<%
		}
	%>
	</select>
</form>
</body>
</html>