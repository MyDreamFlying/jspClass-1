<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp" />
</head>
<body>
<h4>알바 목록 조회</h4>
<table class="table">
	<thead>
		<tr>
			<th>아이디</th>
			<th>이름</th>
			<th>나이</th>
			<th>우편번호</th>
			<th>주소1</th>
			<th>주소2</th>
			<th>전화번호</th>
			<th>학력</th>
			<th>성별</th>
			<th>이메일</th>
			<th>경력</th>
			<th>스펙</th>
			<th>상세정보</th>
			<th>사진</th>
		</tr>
	</thead>
	<tbody>
		<c:choose>
			<c:when test="${not empty pagingVO.dataList }">
				<c:forEach items="${pagingVO.dataList }" var="alba">
					<tr>
						<td>${alba.al_id }</td>
						<td>${alba.al_name }</td>
						<td>${alba.al_age }</td>
						<td>${alba.al_zip }</td>
						<td>${alba.al_addr1 }</td>
						<td>${alba.al_addr2 }</td>
						<td>${alba.al_hp }</td>
						<td>${alba.gr_code }</td>
						<td>${alba.al_gen }</td>
						<td>${alba.al_mail }</td>
						<td>${alba.al_career }</td>
						<td>${alba.al_spec }</td>
						<td>${alba.al_desc }</td>
						<td>${alba.al_img }</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="14">조회결과없음.</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="14">
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
						<option value="name">이름</option>
						<option value="address">지역</option>
					</select>
					<input type="text" name="searchWord" value="${pagingVO.simpleSearch.searchWord }"/>
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
</script>
<jsp:include page="/includee/postScript.jsp"/>
</body>
</html>