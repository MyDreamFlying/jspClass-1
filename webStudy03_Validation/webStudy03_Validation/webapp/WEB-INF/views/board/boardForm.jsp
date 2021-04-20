<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<c:if test="${not empty message }">
	<script type="text/javascript">
		alert("${message}")
	</script>
	<c:remove var="message" scope="session"/>
</c:if>
<jsp:include page="/includee/preScript.jsp" />
<script type="text/javascript" src="${cPath}/js/ckeditor/ckeditor.js"></script>
</head>
<body>
<h4>게시판 글 작성</h4>
<form method="post" enctype="multipart/form-data">
	<input type="hidden" name="bo_no" value="${board.bo_no }">
	<input type="hidden" name="bo_type" value="${board.bo_type }">
	<input type="hidden" name="bo_parent" value="${board.bo_parent }">
	<table class="table table-bordered">
		<tr>
			<th>제목</th>
			<td>
				<input type="text" name="bo_title" value="${board.bo_title }" required>
			</td>
		</tr>
		<c:if test="${board.bo_type eq 'BOARD' }">
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
		</c:if>
		<tr>
			<th>내용</th>
			<td>
				<textarea id="bo_content" name="bo_content" rows="8" cols="50">${board.bo_content }</textarea>
				<script>CKEDITOR.replace('bo_content',{
					filebrowserImageUploadUrl: '${cPath}/board/boardImage.do?type=Image',
				});</script>
			</td>
		</tr>
		<tr>
			<td class="files" colspan="2">
				<c:if test="${not empty board.attachList }">
					<c:forEach items="${board.attachList}" var="attach">
						<div>
							<img src="${cPath}/board/image.do?fileName=${attach.att_savename}">
							${attach.att_filename}
							<button type="button" data-att_no="${attach.att_no}" class="delete btn btn-sm btn-danger">삭제</button>
							<br/>
						</div>
					</c:forEach>
				</c:if>
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
<script type="text/javascript">
$(function(){
	$('.files').on('click', '.delete', function(){
		let thisAttachDiv = $(this).parent('div')
		let att_no = $(this).data("att_no");
		let answer = confirm("정말로 첨부파일을 삭제하겠습니까?");
		if(answer == true){
			thisAttachDiv.remove();
			$('form').prepend('<input type="hidden" name="att_no" value="'+att_no+'">');
		}
	});
	
})
</script>
<jsp:include page="/includee/postScript.jsp"/>
</body>
</html>