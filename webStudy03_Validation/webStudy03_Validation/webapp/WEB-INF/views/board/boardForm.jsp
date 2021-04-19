<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
	th{
		width : 150px;
	}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp" />
</head>
<body>
<h4>게시판 글 작성</h4>
<form method="post" enctype="multipart/form-data">
	<input type="hidden" name="bo_type" value="${board.bo_type }">
	<input type="hidden" name="bo_no" value="${board.bo_no }">
	<table class="table">
		<tr>
			<th>글 제목</th>
			<td>
				<input type="text" name="bo_title" value="${board.bo_title }" required>
			</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>
				<input type="text" name="bo_writer" value="${board.bo_writer }" required>
			</td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td>
				<input type="password" name="bo_pass" required>
			</td>
		</tr>
		<tr>
			<th>글 내용</th>
			<td>
				<textarea name="bo_content" rows="8" cols="50"></textarea>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="file" name="bo_files"><br/>
				<input type="file" name="bo_files"><br/>
				<input type="file" name="bo_files">
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<button type="submit" class="btn btn-success">등록하기</button>
				<button type="button" class="btn btn-danger" onclick="location.href='${cPath}/board/boardList.do'">취소</button>
			</td>
		</tr>
	</table>
</form>
<jsp:include page="/includee/postScript.jsp"/>
</body>
</html>