<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="kr.or.ddit.db.ConnectionFactory"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>성능 체크</h4>
<pre>
	소요(반응)시간 = processing time + latency time
	<a href="oneConnOneProcess.jsp">한번 연결하고 한번 처리 한 소요시간</a> : 130ms / OracleDataSource : 102ms
	<a href="100Conn100Process.jsp">백번 연결하고 백번 처리 한 소요시간</a> : 26470ms / 11493ms
	<a href="1Conn100Process.jsp">한번 연결하고 백번 처리 한 소요시간</a> : 1248ms / 954ms
	
	Pooling 후 
	장점 : 소요시간감소, 효율적인 메모리사용, 서버가 감당할 요청갯수가 일정수 이하로만 발생
	<a href="oneConnOneProcess.jsp">한번 연결하고 한번 처리 한 소요시간</a> : 21ms
	<a href="100Conn100Process.jsp">백번 연결하고 백번 처리 한 소요시간</a> : 1619ms
	<a href="1Conn100Process.jsp">한번 연결하고 백번 처리 한 소요시간</a> : 826ms
</pre>
</body>
</html>