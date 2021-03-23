<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>Welcome page!</h4>
<%
	String authId = (String) session.getAttribute("authId");
	if(authId != null && !authId.isEmpty()){
		%>
		<form name="logoutForm" method="post" action="<%=request.getContextPath() %>/login/logout.do"></form>
		<%=authId %>님 로그인 성공!
		<a href="#" onClick="clickHandler(event);">로그아웃</a>
		<script type="text/javascript">
			function clickHandler(event){
				event.preventDefault();
				document.logoutForm.submit();
				return false;
			}
		</script>
		<%
	}else{
		%>
		<a href="<%=request.getContextPath() %>/login/loginForm.jsp">로그인</a>
		<%
	}
%>
<a href="http://localhost/webStudy01/login/loginForm.jsp">로그인 페이지로 돌아가기</a>
</body>
</html>