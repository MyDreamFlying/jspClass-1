<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp" />
</head>
<h4>게시글 상세 조회</h4>
<body>
<table class="table table-dark table-striped">
	<tr>
		<th>글번호</th>
		<td>${board.bo_no }</td>
	</tr>
	<tr>
		<th>제목</th>
		<td>${board.bo_title }</td>
	</tr>
	<tr>
		<th>작성자</th>
		<td>${board.bo_writer }</td>
	</tr>
	<tr>
		<th>작성일</th>
		<td>${board.bo_date }</td>
	</tr>
	<tr>
		<th>조회수</th>
		<td>${board.bo_hit }</td>
	</tr>
	<tr>
		<th>추천수</th>
		<td>${board.bo_rec }</td>
	</tr>
	<tr>
		<th>내용</th>
		<td>${board.bo_content}</td>
	</tr>
</table>
<jsp:include page="/includee/postScript.jsp"/>
</body>
</html>