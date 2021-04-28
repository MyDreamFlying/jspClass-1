<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
	img{
		max-width : 200px;
		max-height : 200px;
	}
	th{
		width : 250px
	}
	label.rep_writer{
		width : 150px;
	}
	label.rep_content{
		width : 300px;
	}
	label.rep_date{
		width : 200px;
	}
</style>
<h4>게시글 상세 조회</h4>
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
		<td id="recArea">${board.bo_rec }</td>
	</tr>
	<tr>
		<th>신고수</th>
		<td id="repArea">${board.bo_rep }</td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="button" onclick="recommend()" value="추천하기"/>
			<input type="button" onclick="report()" value="신고하기"/>
		</td>
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
	<tr>
		<td colspan="2">
			<div id="replies">
				<c:forEach items="${board.replyList}" var="reply">
					<div class="reply" data-rep_no="${reply.rep_no }">
						<label class="rep_writer">${reply.rep_writer }</label>
						<label class="rep_content">${reply.rep_content }</label>
						<label class="rep_date">${reply.rep_date }</label>
						<button type="button" class="repDelBtn btn btn-sm btn-danger">X</button>
					</div>
				</c:forEach>
			</div>
			<div>
				<input type="text" id="newRep_writer" placeholder="작성자">
				<input type="password" id="newRep_pass" placeholder="비밀번호">
				<input type="text" id="newRep_content" placeholder="내용">
				<button type="button" onclick="newReply()" class="btn btn-info">등록</button>
			</div>
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
			<input type="hidden" name="bo_type" value=${board.bo_type }>
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
	const bo_no = '${board.bo_no }';
	
	function recommend(){
		$.ajax({
			url : "${cPath}/board/recommend.do",
			data : {"bo_no" : bo_no},
			dataType : "json",
			success : function(resp) {
				if(resp.success){
					$('#recArea').text(resp.recommend);
				}else{
					alert(resp.message);
				}
			},
			error : function(xhr, error, msg) {
				console.log(xhr);
				console.log(error);
				console.log(msg);
			}
		})
	}
	
	function report(){
		$.ajax({
			url : "${cPath}/board/report.do",
			data : {"bo_no" : bo_no},
			dataType : "json",
			success : function(resp) {
				if(resp.success){
					$('#repArea').text(resp.report);
				}else{
					alert(resp.message);
				}
			},
			error : function(xhr, error, msg) {
				console.log(xhr);
				console.log(error);
				console.log(msg);
			}
		})
	}
	
	const newRep_writerInput = $('#newRep_writer');
	const newRep_passInput = $('#newRep_pass');
	const newRep_contentInput = $('#newRep_content');
	
	function newReply(){
		let rep_writer = newRep_writerInput.val();
		let rep_pass = newRep_passInput.val();
		let rep_content = newRep_contentInput.val();
		let bo_no = ${board.bo_no}
		
		$.ajax({
			url : "${cPath}/board/reply",
			method : "post",
			data : {
				"rep_writer" : rep_writer
				,"rep_pass" : rep_pass
				,"rep_content" : rep_content
				,"bo_no" : bo_no
			},
			dataType : "json",
			success : function(reply) {
				let replyDiv = '<div class="reply" data-rep_no="'+reply.rep_no+'">'
					+	'<label class="rep_writer">'+reply.rep_writer+'</label>'
					+	'<label class="rep_content">'+reply.rep_content+'</label>'
					+	'<label class="rep_date">'+reply.rep_date+'</label>'
					+	'<button type="button" class="btn btn-sm btn-danger">X</button>'
					+'</div>'
				$('#replies').append(replyDiv);
				newRep_writerInput.val('');
				newRep_passInput.val('');
				newRep_contentInput.val('');
			},
			error : function(xhr, error, msg) {
				console.log(xhr);
				console.log(error);
				console.log(msg);
			}
		})
		

	}
	
	$('#replies').on('click', '.repDelBtn', function(){
		let repDiv = $(this).parent('.reply')
		let delRepNo = repDiv.data('rep_no');
		let delRepPass = prompt("삭제하려면 비밀번호를 입력하세요.");
		
		$.ajax({
			url : "${cPath}/board/reply",
			method : "post",
			data : {
				"rep_no" : delRepNo
				,"rep_pass" : delRepPass
				,"_method" : 'DELETE'
			},
			dataType : "text",
			success : function(resp) {
				if(resp == 'OK'){
					repDiv.remove();
				}else{
					alert(resp);
				}
			},
			error : function(xhr, error, msg) {
				console.log(xhr);
				console.log(error);
				console.log(msg);
			}
		})
		
	});
	
</script>
