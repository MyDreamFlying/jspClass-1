<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>05/cacheControl.jsp</title>
</head>
<body>
<h4>캐시 제어</h4>
<pre>
	캐시 제어를 위한 응답 헤더
	1) Cache-Control(Http 1.1) : public, private, no-cache(no-store)
								: max-age(초단위)
	2) Pragma(Http 1.0) : public, private, no-cache(no-store)
	3) Expires : 캐시 만료 시점(date)
	<%
		response.setHeader("Cache-Control", "no-cache");
		response.addHeader("Cache-Control", "no-store");
		response.setHeader("Pragma", "no-cache");
		response.setIntHeader("Expires", 0);
	%>
</pre>
<img src="<%=application.getContextPath() %>/images/IMG_8209.JPG">
</body>
</html>