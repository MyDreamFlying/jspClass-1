<%@page import="java.util.List"%>
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
<form method="post" action="<%=request.getContextPath()%>/bts">
	<%
	Map<String,List<String>> btsMap = (Map)application.getAttribute("btsMap");
	%>
	<select id="bts" name="member" >
		<option disabled selected>BTS</option>	
	<%
	for(Entry<String,List<String>> entry : btsMap.entrySet()){
		String id = entry.getKey();
		String name = entry.getValue().get(0);
		%>
		<option value="<%=id%>"><%=name %></option>
		<%
	}
	%>
	</select>
</form>
<script type="text/javascript">
	var select = document.querySelector("#bts");
	select.onchange = function(event){
		let member = event.target.value;
		event.target.form.submit();
	}
</script>
</body>
</html>