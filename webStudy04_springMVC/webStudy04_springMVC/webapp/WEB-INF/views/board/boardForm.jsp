<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<style>
	th{
		width : 150px;
	}
	.preview{
		max-width : 200px;
		max-height : 200px;
	}
</style>
<script type="text/javascript" src="${cPath}/js/ckeditor/ckeditor.js"></script>
<h4>게시판 글 작성</h4>
<form:form modelAttribute="board" method="post" enctype="multipart/form-data">
	<input type="hidden" name="bo_no" value="${board.bo_no }">
	<input type="hidden" name="bo_type" value="${board.bo_type }">
	<input type="hidden" name="bo_parent" value="${board.bo_parent }">
	<table class="table table-bordered">
		<tr>
			<th>제목</th>
			<td>
				<input type="text" name="bo_title" value="${board.bo_title }" >
				<form:errors path="bo_title" element="span" cssClass="error"/>
			</td>
		</tr>
		<c:if test="${board.bo_type eq 'BOARD' }">
		<tr>
			<th>작성자</th>
			<td>
				<input type="text" name="bo_writer" value="${board.bo_writer }" >
				<form:errors path="bo_writer" element="span" cssClass="error"/>
			</td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td>
				<input type="password" name="bo_pass" >
				<form:errors path="bo_pass" element="span" cssClass="error"/>
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
				<form:errors path="bo_content" element="span" cssClass="error"/>
			</td>
		</tr>
		<c:if test="${board.bo_type eq 'BOARD' }">
		<tr>
			<td class="files" colspan="2">
				<c:if test="${not empty board.attachList }">
					<c:forEach items="${board.attachList}" var="attach">
						<div>
							<img class="preview" src="${cPath}/board/image.do?fileName=${attach.att_savename}">
							${attach.att_filename}
							<button type="button" data-att_no="${attach.att_no}" class="delete btn btn-sm btn-danger">삭제</button>
							<br/>
						</div>
					</c:forEach>
				</c:if>
				<div class="uploadFiles">
					<div class="uploadFile">
						<span class="previewArea"></span>
						<input type="file" name="bo_files" class="fileUpload">
						<button type="button" class="moreFile btn btn-secondary">+</button><br/>
					</div>
				</div>
			</td>
		</tr>
		</c:if>
		<tr>
			<td colspan="2">
				<button type="submit" class="btn btn-success">등록하기</button>
				<button type="button" class="btn btn-danger" onclick="location.href='${cPath}/board/boardList.do'">취소</button>
			</td>
		</tr>
	</table>
</form:form>
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
	
	$(".uploadFiles").on("change", ".fileUpload" , function(){
		var previewArea = $(this).parent('.uploadFile').children('.previewArea');
		handleImgsFilesSelect(this,previewArea);
	});
	
	$('.uploadFiles').on('click', '.moreFile', function(){
		$('.uploadFiles').append(
				'<div class="uploadFile">'
				+'	<span class="previewArea"></span>'
				+'	<input type="file" name="bo_files" class="fileUpload">'
				+'	<button type="button" class="lessFile btn btn-secondary">-</button><br/>'
				+'</div>');
	});
	
	$('.uploadFiles').on('click', '.lessFile', function(){
		$(this).parents('.uploadFile').remove();
	});
	
})

function handleImgsFilesSelect(e , previewArea) {
	var files = e.files;
	var filesArr = Array.prototype.slice.call(files);
	
	filesArr.forEach(function(f) {
		if(!f.type.match("image.*")) {
			alert("이미지만 업로드 하실 수 있습니다.");
			return;
		}

		var reader = new FileReader();
		reader.onload = function(e) {
			var img_html = '<img class="preview" src=\"' + e.target.result + '\" />';
			previewArea.html(img_html);
		}
		reader.readAsDataURL(f);
	});
}
</script>
