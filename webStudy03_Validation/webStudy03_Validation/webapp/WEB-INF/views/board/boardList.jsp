<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<style>
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp" />
</head>
<body>
<h4>게시판 목록 조회</h4>
<table class="table table-bordered">
	<thead>
		<tr>
			<th>글 종류</th>
			<th>글 번호</th>
			<th>글 제목</th>
			<th>작성자</th>
			<th>글 내용</th>
			<th>작성일</th>
			<th>조회수</th>
			<th>추천수</th>
			<th>신고수</th>
		</tr>
	</thead>
	
	<tbody id="listBody">
	
	<c:choose>
		<c:when test="${not empty pagingVO.dataList }">
			<c:forEach items="${pagingVO.dataList}" var="board">
				<tr>
					<td>${board.bo_type}</td>
					<td>${board.bo_no}</td>
					<td>
						<c:url value="/board/boardRead.do" var="viewURL">
							<c:param name="what" value="${board.bo_no }"></c:param>
						</c:url>
						<a idx="${board.bo_no}" href="${viewURL}" data-toggle="popover" title="Popover title" >
							${board.bo_title}
						</a>
					</td>
					<td>${board.bo_writer}</td>
					<td>${board.bo_content}</td>
					<td>${board.bo_date}</td>
					<td>${board.bo_hit}</td>
					<td>${board.bo_rec}</td>
					<td>${board.bo_rep}</td>
				</tr>
			</c:forEach>
		</c:when>
	
		<c:otherwise>
			<tr>
				<td colspan="9">작성글 없음.</td>
			</tr>
		</c:otherwise>
	</c:choose>
	
	</tbody>
	
	<tfoot>
		<tr>
			<td colspan="9">
				<form id="searchForm">
					<input type="hidden" name="searchType" value="${pagingVO.simpleSearch.searchType }"/>
					<input type="hidden" name="searchWord" value="${pagingVO.simpleSearch.searchWord }"/>
					<input type="hidden" name="page" />
					<input type="text" hidden="hidden" name="startDate"/> 
					<input type="text" hidden="hidden" name="endDate"/> 
				</form>
				<div id="searchUI">
					<select name="searchType">
						<option value>전체</option>
						<option value="title">글 제목</option>
						<option value="writer">작성자</option>
						<option value="content">글내용</option>
					</select>
					<input type="text" name="searchWord" value="${pagingVO.simpleSearch.searchWord }"/>
					<input type="date" name="startDate"> ~ 
					<input type="date" name="endDate"> 검색기간
					<input id="searchBtn" type="button" value="검색"/>
				</div>
				
				<div id="pagingArea">
					${pagingVO.pagingHTML}
				</div>
			</td>
		</tr>
	</tfoot>
</table>
<script type="text/javascript">
	let searchForm = $("#searchForm");
	let searchUI = $("#searchUI");
	searchUI.find("[name='searchType']").val("${pagingVO.simpleSearch.searchType }");
	
	$('#searchBtn').on("click", function(){
		let inputs = $("#searchUI").find(":input[name]");
		$(inputs).each(function(idx, input){
			let name = $(this).attr("name");
			let sameInput = $("#searchForm").find("[name='"+name+"']");
			$(sameInput).val($(this).val());
		})
		searchForm.submit();
	});
	
	$("#pagingArea").on("click", "a", function(event){
		event.preventDefault();
		let page = $(this).data("page");
		if(page){
			searchForm.find("[name='page']").val(page);
			searchForm.submit();
		}
		return false;
	})
	
	$(function(){
		$("#listBody a").hover(function(){
			idx = $(this).attr("idx");
			$(this).popover({
				html:true,
				content:function(){
					$.ajax({
						url : "${cPath}/board/boardView.do",
						data : {'idx' : idx},
						dataType : "text",
						success : function(resp) {
							retValue=resp;
						},
						async : false,
						cache : true,
						error : function(xhr, error, msg) {
							console.log(xhr);
							console.log(error);
							console.log(msg);
						}
					});
					return retValue;
				}
				}).popover("toggle")
			});
		});
</script>
<jsp:include page="/includee/postScript.jsp"/>
</body>
</html>