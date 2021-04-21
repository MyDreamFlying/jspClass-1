<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<style>
body{
	padding : 40px;
}
img{
	height : 100px;
	max-width : 150px;
}
.profile{
	width : 120px;
}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp" />
</head>
<body>
<table class="ui padded table">
	<thead>
		<tr>
			<th class="profile">프로필</th>
			<th>이름</th>
			<th>주소1</th>
			<th>전화번호</th>
			<th>학력</th>
			<th>성별</th>
			<th>이메일</th>
		</tr>
	</thead>
	<tbody>
		<c:choose>
			<c:when test="${not empty pagingVO.dataList }">
				<c:forEach items="${pagingVO.dataList }" var="alba">
					<tr>
						<c:url value="/albaView.do" var="viewURL">
							<c:param name="al_id" value="${alba.al_id}"></c:param>
						</c:url>
						<td>
							<a href="${viewURL}">
							<c:choose>
								<c:when test="${not empty alba.al_img}">
									<img src="${cPath}/profile/${alba.al_img}">
								</c:when>
								<c:otherwise>
									<img src="${cPath}/profile/default_${alba.al_gen}.jpg">
								</c:otherwise>
							</c:choose>
							</a>
						</td>
						<td>
							<a href="${viewURL}">${alba.al_name }</a>
						</td>
						<td>${alba.al_addr1 }</td>
						<td>${alba.al_hp }</td>
						<td>${alba.gr_name }</td>
						<td>${alba.al_gen eq "M" ? "남성" :"여성" }</td>
						<td>${alba.al_mail }</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="7">조회결과없음.</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="7">
				<div id="pagingArea">
					${pagingVO.pagingHTML}
				</div>
			</td>
		</tr>
		<tr>
			<td colspan="7">
				<form id="searchForm">
					<input type="hidden" name="searchType" value="${pagingVO.simpleSearch.searchType }"/>
					<input type="hidden" name="searchWord" value="${pagingVO.simpleSearch.searchWord }"/>
					<input type="hidden" name="page" />
				</form>
				<div id="searchUI">
					<button type="button" onclick="location.href='albaInsert.do';" class="positive ui button">알바등록</button>
					<select class="ui search dropdown" name="searchType">
						<option value>전체</option>
						<option value="name">이름</option>
						<option value="address">지역</option>
						<option value="career">경력사항</option>
					</select>
					<div class="ui icon input">
						<input type="text" class="prompt" placeholder="Search" name="searchWord" value="${pagingVO.simpleSearch.searchWord }"/>
						<i class="search icon"></i>
					</div>
					<input id="searchBtn" class="ui blue button" type="button" value="검색"/>
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