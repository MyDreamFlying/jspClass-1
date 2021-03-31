<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.error{
		color : red;
	}
</style>
</head>
<body>
<h4>가입 양식</h4>
<jsp:useBean id="member" class="kr.or.ddit.vo.MemberVO" scope="request"></jsp:useBean>
<jsp:useBean id="errors" class="java.util.LinkedHashMap" scope="request"></jsp:useBean>
<form method="post">
	<table>
		<tr>
			<th>회원아이디</th>
			<td>
				<input type="text" name="mem_id"/>
				<span class="error"><%=errors.get("mem_id") %></span>
			</td>
		</tr>
		
		<tr>
			<td colspan="2">
				<input type="submit" value="저장"/>
			</td>
		</tr>
	</table>
</form>
</body>
</html>