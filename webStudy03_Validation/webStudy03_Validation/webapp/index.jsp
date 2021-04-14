<%@page import="java.util.ArrayList"%>
<%@page import="kr.or.ddit.Constants"%>
<%@page import="java.util.Base64"%>
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
<pre>
	총 방문자수 : <%=application.getAttribute(Constants.SESSION_COUNT_ATTR_NAME) %>
	<%
		ArrayList<HttpSession> conList = 
			(ArrayList<HttpSession>) application.getAttribute(Constants.SESSION_CONNECT_LIST);
		int cnt = 0;
		for(HttpSession se : conList){
			MemberVO mem = (MemberVO) se.getAttribute("authMember");
			if(mem != null){
				%>
				접속자 <%=++cnt %> : <%= mem.getMem_id() %>
				<%
			}
		}
		out.println("총 동시접속자 수 : " + cnt);

	%>
</pre>
<%
	MemberVO authMember = (MemberVO) session.getAttribute("authMember");
	String authId = "";
	String memName = "";
	String email = "";
	String imgUrl = "";
	if(authMember != null){
		authId = authMember.getMem_id();
		memName = authMember.getMem_name();
		email = authMember.getMem_mail();
		if(authMember.getMem_img() != null){
			byte[] img = authMember.getMem_img();
			imgUrl = "data:image/jpg;base64,"+Base64.getEncoder().encodeToString(img);
		}
		if(authId != null && !authId.isEmpty()){
		}
		%>
		<form name="logoutForm" method="post" action="<%=request.getContextPath() %>/login/logout.do"></form>
		<a href="<%=request.getContextPath() %>/mypage.do"><%=memName %></a>님[<%=authMember.getMem_role() %>] 로그인 성공!
		
		<a href="mailto:<%=email %>">이메일보내기</a>
		<a href="#" onClick="clickHandler(event);">로그아웃</a>
		<br/><img src="<%=imgUrl%>">
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
<a href="<%=request.getContextPath() %>/login/loginForm.jsp">로그인 페이지로 돌아가기</a>
	
</body>
</html>