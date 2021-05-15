<%@page import="java.net.URLDecoder"%>
<%@page import="java.util.Objects"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
.error{
	color : red;
	font-weight : bold;
}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
// 	CookieUtils utils = new CookieUtils(request);
// 	String idCookie = null;
// 	if(utils.exists("idCookie")){
// 		idCookie = utils.getCookieValue("idCookie");
// 	}

	// 아이디 저장 관련 쿠키 검증부
	Cookie[] cookies = request.getCookies();
	String savedId = "";
	String checked = "";
	Cookie savedIdCookie = null;
	if(cookies != null){
		for(Cookie tmp : cookies){
			String value = URLDecoder.decode(tmp.getValue(),"utf-8");
			if("savedId".equals(tmp.getName())){
				savedId = URLDecoder.decode(tmp.getValue(),"utf-8");
				checked = "".equals(savedId)? "" : "checked";
				break;
			}
		}
	}
	
	String message = (String)request.getAttribute("message");
	if(message == null){
		message = (String)session.getAttribute("message");
	}
	if(message != null && !message.isEmpty()){
		%>
		<div class="error"><%=message %></div>
		<%
		session.removeAttribute("message");
	}
%>
<form action="<%=request.getContextPath() %>/login/loginCheck.do" method="post">
	<input type="text" 
	value="<%= Objects.toString(savedId, "")%>" 
	name="mem_id" placeholder="id"/>
	<input type="text" name="mem_pass" placeholder="password"/>
	<input type="checkbox" name="saveId" <%=checked %> value="saveId">아이디기억하기
	<input type="submit" value="로그인"/>
</form>
</body>
</html>