<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/includee/preScript.jsp" />
<meta charset="UTF-8">
<style>
#nav { 
	padding : 20px;
	margin : 0px;
	width: 300px; 
	height : 700px;
	background: #202020; 
	background-image : linear-gradient(90deg,#202020,black);
	float: left; 
	color : white;
	font-size : 1.5em;
} 
#header, #footer{ 
	height : 70px; 
	font-size : 2.0em;
} 
#content { 
	width: calc(100% - 350px); 
	margin-left : 30px;
	height : 100%;
	float: right; 
} 
#footer { 
	background: #fefefe; 
	clear: both; 
	color : black; 
	text-align : center; 
}  
.menu{
	font-size : 2.0em;
	margin-left : 30px;
}
.loginInfo{
	margin-top : 30px;
}

</style>
<title>Insert title here</title>
</head>
<body>
<%
	Cookie[] cookies = request.getCookies();
	String loginId = "";
	String loginTel = "";
	String loginEmail = "";
	Cookie savedIdCookie = null;
	if(cookies != null){
		for(Cookie tmp : cookies){
			if("loginId".equals(tmp.getName())){
				loginId = URLDecoder.decode(tmp.getValue(),"utf-8");
			}else if("loginTel".equals(tmp.getName())){
				loginTel = URLDecoder.decode(tmp.getValue(),"utf-8");
			}else if("loginEmail".equals(tmp.getName())){
				loginEmail = URLDecoder.decode(tmp.getValue(),"utf-8");
			}
		}
	}
	pageContext.setAttribute("loginId", loginId);
	pageContext.setAttribute("loginTel", loginTel);
	pageContext.setAttribute("loginEmail", loginEmail);
%>
	<div id = "nav">
		<div>
			<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-smartwatch" viewBox="0 0 16 16">
			  <path d="M9 5a.5.5 0 0 0-1 0v3H6a.5.5 0 0 0 0 1h2.5a.5.5 0 0 0 .5-.5V5z"/>
			  <path d="M4 1.667v.383A2.5 2.5 0 0 0 2 4.5v7a2.5 2.5 0 0 0 2 2.45v.383C4 15.253 4.746 16 5.667 16h4.666c.92 0 1.667-.746 1.667-1.667v-.383a2.5 2.5 0 0 0 2-2.45V8h.5a.5.5 0 0 0 .5-.5v-2a.5.5 0 0 0-.5-.5H14v-.5a2.5 2.5 0 0 0-2-2.45v-.383C12 .747 11.254 0 10.333 0H5.667C4.747 0 4 .746 4 1.667zM4.5 3h7A1.5 1.5 0 0 1 13 4.5v7a1.5 1.5 0 0 1-1.5 1.5h-7A1.5 1.5 0 0 1 3 11.5v-7A1.5 1.5 0 0 1 4.5 3z"/>
			</svg>
			Shane's Admin System
		</div>
		<div class="loginInfo">
			${loginId }<br/>
			tel : ${loginTel }<br/>
			email: ${loginEmail }<br/>
			<button type="button" onClick="location.href='/test/'" class="btn btn-danger">logout</button>
		</div>
		<div class="navMenu">
			<a href="#">
				<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-file-earmark-person-fill" viewBox="0 0 16 16">
				  <path d="M9.293 0H4a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h8a2 2 0 0 0 2-2V4.707A1 1 0 0 0 13.707 4L10 .293A1 1 0 0 0 9.293 0zM9.5 3.5v-2l3 3h-2a1 1 0 0 1-1-1zM11 8a3 3 0 1 1-6 0 3 3 0 0 1 6 0zm2 5.755V14a1 1 0 0 1-1 1H4a1 1 0 0 1-1-1v-.245S4 12 8 12s5 1.755 5 1.755z"/>
				</svg>
				회원관리
			</a><br/>
			<a href="#">
				<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-chat" viewBox="0 0 16 16">
				  <path d="M2.678 11.894a1 1 0 0 1 .287.801 10.97 10.97 0 0 1-.398 2c1.395-.323 2.247-.697 2.634-.893a1 1 0 0 1 .71-.074A8.06 8.06 0 0 0 8 14c3.996 0 7-2.807 7-6 0-3.192-3.004-6-7-6S1 4.808 1 8c0 1.468.617 2.83 1.678 3.894zm-.493 3.905a21.682 21.682 0 0 1-.713.129c-.2.032-.352-.176-.273-.362a9.68 9.68 0 0 0 .244-.637l.003-.01c.248-.72.45-1.548.524-2.319C.743 11.37 0 9.76 0 8c0-3.866 3.582-7 8-7s8 3.134 8 7-3.582 7-8 7a9.06 9.06 0 0 1-2.347-.306c-.52.263-1.639.742-3.468 1.105z"/>
				</svg>
				자유게시판
			</a><br/>
			<a href="#">
				<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-inboxes-fill" viewBox="0 0 16 16">
				  <path d="M4.98 1a.5.5 0 0 0-.39.188L1.54 5H6a.5.5 0 0 1 .5.5 1.5 1.5 0 0 0 3 0A.5.5 0 0 1 10 5h4.46l-3.05-3.812A.5.5 0 0 0 11.02 1H4.98zM3.81.563A1.5 1.5 0 0 1 4.98 0h6.04a1.5 1.5 0 0 1 1.17.563l3.7 4.625a.5.5 0 0 1 .106.374l-.39 3.124A1.5 1.5 0 0 1 14.117 10H1.883A1.5 1.5 0 0 1 .394 8.686l-.39-3.124a.5.5 0 0 1 .106-.374L3.81.563zM.125 11.17A.5.5 0 0 1 .5 11H6a.5.5 0 0 1 .5.5 1.5 1.5 0 0 0 3 0 .5.5 0 0 1 .5-.5h5.5a.5.5 0 0 1 .496.562l-.39 3.124A1.5 1.5 0 0 1 14.117 16H1.883a1.5 1.5 0 0 1-1.489-1.314l-.39-3.124a.5.5 0 0 1 .121-.393z"/>
				</svg>
				자료실
			</a><br/>
		</div>
	</div>
	<div id = "content">
		<div class="upperMenu">
			<svg xmlns="http://www.w3.org/2000/svg" width="40" height="40" fill="currentColor" class="bi bi-menu-up" viewBox="0 0 16 16">
			  <path d="M7.646 15.854a.5.5 0 0 0 .708 0L10.207 14H14a2 2 0 0 0 2-2V3a2 2 0 0 0-2-2H2a2 2 0 0 0-2 2v9a2 2 0 0 0 2 2h3.793l1.853 1.854zM1 9V6h14v3H1zm14 1v2a1 1 0 0 1-1 1h-3.793a1 1 0 0 0-.707.293l-1.5 1.5-1.5-1.5A1 1 0 0 0 5.793 13H2a1 1 0 0 1-1-1v-2h14zm0-5H1V3a1 1 0 0 1 1-1h12a1 1 0 0 1 1 1v2zM2 11.5a.5.5 0 0 0 .5.5h8a.5.5 0 0 0 0-1h-8a.5.5 0 0 0-.5.5zm0-4a.5.5 0 0 0 .5.5h11a.5.5 0 0 0 0-1h-11a.5.5 0 0 0-.5.5zm0-4a.5.5 0 0 0 .5.5h6a.5.5 0 0 0 0-1h-6a.5.5 0 0 0-.5.5z"/>
			</svg>
			<a class="menu" href="#">회원관리</a>
			<a class="menu" href="#">자유게시판</a>
			<a class="menu" href="#">자료실</a>
		</div>
		<h2><br/>회원 리스트</h2>
		<table class="table">
		  <thead class="thead-light">
		    <tr>
		      <th scope="col">순번</th>
		      <th scope="col">아이디</th>
		      <th scope="col">패스워드</th>
		      <th scope="col">이메일</th>
		      <th scope="col">전화번호</th>
		    </tr>
		  </thead>
		  <tbody>
		  	<c:forEach items="${empList}" var="employee" varStatus="vs" begin="0">
		    <tr>
		      <td>${vs.index+1}</td>
		      <td>${employee['employee_id'] }</td>
		      <td>${employee['employee_pwd']}</td>
		      <td>${employee['employee_email']}</td>
		      <td>${employee['employee_phone']}</td>
		    </tr>
		  	</c:forEach>
		  </tbody>
		</table>
		<div id="searchUI">
			<button type="button" class="btn btn-success">회원등록</button>
			<select name="searchType">
				<option value>검색구분</option>
				<option value="name">이름</option>
				<option value="auth">권한</option>
			</select>
			<input type="text" name="searchWord" value="${pagingVO.simpleSearch.searchWord }"/>
			<button type="button" class="btn btn-info">
				<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
				  <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"/>
				</svg>
			</button>
		</div>
	</div>

	<div id = "footer">
		2021 DDIT shane
	</div>
</body>
</html>