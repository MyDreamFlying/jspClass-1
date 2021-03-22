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
<select id="blood">
	<%
		Map<String,String> bloodMap = (Map)application.getAttribute("bloodMap");
		for(Entry<String,String> entry : bloodMap.entrySet()){
			String key = entry.getKey();
			String value = entry.getValue();
			%>
			<option value="<%=key%>"><%=value %></option>
			<%
		}
	%>
</select>
<script type="text/javascript">
	var select = document.querySelector("#blood");
	select.onchange = function(event){
		let blood = event.target.value;
		
		location.href="<%=request.getContextPath()%>/blood/getContent.do?blood="+blood;
	}
</script>
</body>
</html>