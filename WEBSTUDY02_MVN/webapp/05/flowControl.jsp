<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>05/flowControl.jsp</title>
</head>
<body>
<h4>웹 어플리케이션에서 흐름 제어방법(페이지 이동)</h4>
<pre>
1. 서버내에서의 위임구조, RequeestDispatcher 사용
	- 원본 요청(request)를 도착점에서 사용할 수 있는 구조.
	1) forward
	2) include
	<%--
		String dest = "/04/localeDesc.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(dest);
		rd.include(request, response);
	--%>
2. Redirect : Body 없이, 상태코드(302)로 구성된 line과 Location 헤더를 가지고 응답이 전송됨.
			HTTP의 Stateless 특성에 따라 원본 요청에 대한 정보는 사라짐.
	<%
		String location = request.getContextPath() + "/04/localeDesc.jsp";
		response.sendRedirect(location);
	%>
</pre>
</body>
</html>