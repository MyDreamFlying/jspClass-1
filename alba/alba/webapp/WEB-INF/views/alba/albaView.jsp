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
th{
	width : 200px;
}
img{
	height : 100px;
}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp" />
</head>
<body>
<button type="button" onclick="location.href='albaList.do';" class="btn btn-info">알바목록으로 돌아가기</button>
<button type="button" onclick="location.href='albaUpdate.do?al_id=${alba.al_id }';"class="btn btn-warning">${alba.al_name} 정보수정</button>
<button type="button" onclick="deleteAlba()" class="btn btn-danger">${alba.al_name} 삭제</button>
<h4>
	<br/>알바 상세 정보
</h4>
<table class="table">
		<tr>
			<th>프로필</th>
			<td>
				<c:if test="${not empty alba.al_img}">
					<img src="${cPath}/profile/${alba.al_img}">
				</c:if>
			</td>
		</tr>
		<tr>
			<th>아이디</th>
			<td>${alba.al_id }</td>
		</tr>
		<tr>
			<th>이름</th>
			<td>${alba.al_name }</td>
		</tr>
		<tr>
			<th>나이</th>
			<td>${alba.al_age }</td>
		</tr>
		<tr>
			<th>우편번호</th>
			<td>${alba.al_zip }</td>
		</tr>
		<tr>
			<th>주소1</th>
			<td>${alba.al_addr1 }</td>
		</tr>
		<tr>
			<th>주소2</th>
			<td>${alba.al_addr2 }</td>
		</tr>
		<tr>
			<th>전화번호</th>
			<td>${alba.al_hp }</td>
		</tr>
		<tr>
			<th>학력</th>
			<td>${alba.gr_name }</td>
		</tr>
		<tr>
			<th>성별</th>
			<td>${alba.al_gen eq "M" ? "남성" :"여성" }</td>
		</tr>
		<tr>
			<th>이메일</th>
			<td>${alba.al_mail }</td>
		</tr>
		<tr>
			<th>경력</th>
			<td>${alba.al_career }</td>
		</tr>
		<tr>
			<th>스펙</th>
			<td>${alba.al_spec }</td>
		</tr>
		<tr>
			<th>상세정보</th>
			<td>${alba.al_desc }</td>
		</tr>
		<tr>
			<th>보유자격증</th>
			<td>
				   	<c:forEach items="${alba.licenseList}" var="license">
						 ${license.lic_name }<br/>
				   	</c:forEach>
			</td>
		</tr>
</table>
<jsp:include page="/includee/postScript.jsp"/>
<script type="text/javascript">
	var deleteAlba = function(){
		if (confirm('${alba.al_name}에 대한 정보를 정말로 삭제하겠습니까?')) {
			location.href='albaDelete.do?al_id=${alba.al_id }';
		}
	}
</script>
</body>
</html>