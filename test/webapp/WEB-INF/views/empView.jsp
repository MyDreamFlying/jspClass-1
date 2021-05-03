<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/includee/preScript.jsp" />
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="mb-3">
  <label for="exampleFormControlInput1" class="form-label">아이디</label>
  <input type="text" class="form-control" id="exampleFormControlInput1" value="${employee['employee_id'] }" disabled>
</div>
<div class="mb-3">
  <label for="exampleFormControlInput2" class="form-label">이름</label>
  <input type="text" class="form-control" id="exampleFormControlInput2 " value="${employee['employee_name']}" disabled>
</div>
<div class="mb-3">
  <label for="exampleFormControlInput3" class="form-label">이메일</label>
  <input type="text" class="form-control" id="exampleFormControlInput3" value="${employee['employee_email']}" disabled>
</div>
<div class="mb-3">
  <label for="exampleFormControlInput4" class="form-label">전화번호</label>
  <input type="text" class="form-control" id="exampleFormControlInput4" value="${employee['employee_phone']}" disabled>
</div>
<div>
	<button type="button" class="btn btn-warning">수정</button>
	<button type="button" class="btn btn-danger">삭제</button>
	<button type="button" class="btn btn-success">정지</button>
	<button type="button" onclick="window.close();" class="btn btn-primary">닫기</button>
</div>
</body>
</html>