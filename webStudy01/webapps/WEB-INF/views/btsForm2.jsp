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
<%
	pageContext.include("/includee/preScript.jsp");
%>
</head>
<body>
<form method="post">
	<%
	Map<String,List<String>> btsMap = (Map)application.getAttribute("btsMap");
	%>
	<select id="bts" name="member" onchange=$(this.form).submit(); >
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
<div id = "resultArea"></div>
<script type="text/javascript">
 	let resultArea = $('#resultArea');
 	$("form").formToAjax({
		dataType : 'html',
		success : function(resp){
			resultArea.html(resp)
		}
	})

</script>
</body>
</html>