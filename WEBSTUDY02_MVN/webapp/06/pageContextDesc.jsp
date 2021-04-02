<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
pre{
	font-size : 1.2em;
}
</style>
<meta charset="UTF-8">
<title>06/pageContextDesc.jsp</title>
</head>
<body>
<h4>PageContext</h4>
<pre>
	: 하나의 JSP에 대한 모든 정보를 가진 객체.
	: 가장 먼저 생성되는 기본 객체로 나머지 기본객체에 대한 참조를 소유함.<br/>
	request==pageContext.getRequest() : <%=request==pageContext.getRequest() %><br/>
	pageContext.getRequest() instanceof HttpServletRequest : 
	<%=pageContext.getRequest() instanceof HttpServletRequest %><br/>
	((HttpServletRequest)pageContext.getRequest()).getRequestURI() : 
	<%=((HttpServletRequest)pageContext.getRequest()).getRequestURI() %><br/>
	application == pageContext.getServletContext() : 
	<%=application == pageContext.getServletContext() %><br/>
	${pageContext.request.contextPath}
	
	1. 영역(scope)제어 
	2. 에러 데이터 확보
	3. 페이지 이동(dispatch 방식의 이동)
	
	<%
		String relativeUrlPath = "/04/localeDesc.jsp";
// 		pageContext.forward(relativeUrlPath);
		pageContext.include(relativeUrlPath);
// 		request.getRequestDispatcher(relativeUrlPath).include(request,response);
	%>
	include 이후의 text
</pre>
</body>
</html>