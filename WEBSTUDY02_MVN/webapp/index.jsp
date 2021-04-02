<%@page import="kr.or.ddit.vo.MemberVO"%>
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
	MemberVO authMember = (MemberVO) session.getAttribute("authMember");
	String authId = "";
	String memName = "";
	String email = "";
	if(authMember != null){
		authId = authMember.getMem_id();
		memName = authMember.getMem_name();
		email = authMember.getMem_mail();
		if(authId != null && !authId.isEmpty()){
	}
		%>
		<form name="logoutForm" method="post" action="<%=request.getContextPath() %>/login/logout.do"></form>
		<a href="<%=request.getContextPath() %>/mypage.do"><%=memName %></a>님 로그인 성공!
		<a href="mailto:<%=email %>">이메일보내기</a>
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
		<a href="<%=request.getContextPath() %>/member/memberInsert.do">회원가입</a>
		<%
	}
%>
<a href="http://localhost/webStudy01/login/loginForm.jsp">로그인 페이지로 돌아가기</a>
</body>
</html>