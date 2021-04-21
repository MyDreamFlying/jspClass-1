<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp" />
<style>
	img{
		max-width : 400px
	}
	th{
		width : 250px
	}
</style>
</head>
<h4>게시글 상세 조회</h4>
<body>
<button type="button" onclick="location.href=`${cPath}/board/boardList.do`" class="btn btn-info">목록으로</button>
<button type="button" onclick="location.href=`${cPath}/board/noticeList.do`" class="btn btn-primary">공지목록</button>
<c:url value="/board/boardInsert.do" var="insertURL">
	<c:param name="parent" value="${board.bo_no}"/>
</c:url>
<button type="button" onclick="location.href='${insertURL}'" class="btn btn-dark">답글쓰기</button>
<c:url value="/board/boarUpdate.do" var="updateURL">
	<c:param name="bo_no" value="${board.bo_no}"/>
</c:url>
<button type="button" onclick="location.href='${updateURL}'" class="btn btn-warning">수정</button>
<button type="button" id="deleteBtn" class="btn btn-danger" data-toggle="modal" data-target="#exampleModal">삭제</button>
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
	<tr>
		<td colspan="2">
			<c:if test="${not empty board.attachList }">
				<c:forEach items="${board.attachList}" var="attach">
					<img src="${cPath}/board/image.do?fileName=${attach.att_savename}">
					<c:url value="/board/download.do" var="downloadURL">
						<c:param name="what" value="${attach.att_no }"></c:param>
					</c:url>
					<a href="${downloadURL }">${attach.att_filename}</a>
					<br/>
				</c:forEach>
			</c:if>
		</td>
	</tr>
</table>

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">글을 삭제하려면 비밀번호를 입력하세요.</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form id="delForm" action="${cPath }/board/boardDelete.do" method="post">
			<input type="hidden" name="bo_no" value=${board.bo_no }>
			<input type="password" id="hiddenPass" name="bo_pass" required>&nbsp;
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
	        <button type="submit" class="btn btn-danger">삭제</button>
		</form>
      </div>
    </div>
  </div>
</div>

<jsp:include page="/includee/postScript.jsp"/>
<script type="text/javascript">
	$(function(){
		
	})
</script>
</body>
</html>