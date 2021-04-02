<%@page import="kr.or.ddit.enumpkg.OSType"%>
<%@page import="kr.or.ddit.enumpkg.BrowserType"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
	String agent = request.getHeader("user-agent");
	String MSGPTRN = "당신의 os는 %s입니다. 당신의 브라우저는 %s입니다.";
	String message = String.format(MSGPTRN,
						OSType.getOSName(agent),
						BrowserType.getBrowserName(agent));
%>

<html>
<head>
<script>
	alert('<%= message %>');
</script>
<meta charset="UTF-8">
<title>02/userAgent.jps</title>
</head>
<body>
<h4>User Agent</h4>
<!-- 클라이언트의 브라우저를 확인하고,  -->
<!-- iexplorer 라면 "당신의 브라우저는 익스플로러 입니다." -->
<!-- chrome 이면 "당신의 브라우저는 크롬 입니다." -->
<!-- edge라면 "당신의 브라우저는 엣지입니다." -->
<!-- 라는 메시지를 alert 창으로 띄울 것. -->
</body>
</html>