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
img.profileImg{
	height : 100px;
}
img.licenseImg{
	max-height : 500px;
}
.license{
	width : 150px;
}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp" />
</head>
<body>
<div class="ui inverted segment">
	<button type="button" class="ui inverted primary basic button" onclick="location.href='albaList.do';">알바목록으로 돌아가기</button>
	<button type="button" class="ui inverted yellow basic button" onclick="location.href='albaUpdate.do?al_id=${alba.al_id }';"class="btn btn-warning">${alba.al_name} 정보수정</button>
	<button type="button" class="ui inverted red basic button" onclick="deleteAlba()">${alba.al_name} 삭제</button>
</div>
<h4>
	<br/>알바 상세 정보
</h4>
<table class="ui celled striped table">
		<tr>
			<th>프로필</th>
			<td>
			<c:choose>
				<c:when test="${not empty alba.al_img}">
					<img class="profileImg" src="${cPath}/profile/${alba.al_img}">
				</c:when>
				<c:otherwise>
					<img src="${cPath}/profile/default_${alba.al_gen}.jpg">
				</c:otherwise>
			</c:choose>
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
				<div id="licenseList">
				   	<c:forEach items="${alba.licenseList}" var="license">
				   		<c:choose>
				   			<c:when test="${not empty license.lic_name }">
								<label class="license">${license.lic_name }</label> <button data-al_id="${alba.al_id}" data-lic_code="${license.lic_code}" type="button" class="btn btn-dark btn-sm">보기</button> <br/>
				   			</c:when>
					   		<c:otherwise>
					   			보유 자격증 없음
					   		</c:otherwise>
				   		</c:choose>
				   	</c:forEach>
				</div>
			</td>
		</tr>
</table>
<!--  license picture modal -->
<div class="ui basic modal">
  <div class="image content">
    <img id="licenseImg" class="image licenseImg" src="http://blog.jinbo.net/attach/615/200937431.jpg">
  </div>
  <div class="actions">
    <div class="ui red basic cancel inverted button">
      <i class="remove icon"></i>
      Close
    </div>
  </div>
</div>
<!--  license picture modal End -->
<jsp:include page="/includee/postScript.jsp"/>
<script type="text/javascript">
	$(function(){
		$('#licenseList').on('click', 'button', function(){
			let al_id = $(this).data('al_id');
			let lic_code = $(this).data('lic_code');
			
			// set img src of img modal with result image
			let contextPath = '${pageContext.request.contextPath}';
			let imgSrc = contextPath+"/showLicPicture.do?al_id="+al_id+"&lic_code="+lic_code;
			$('#licenseImg').attr("src",imgSrc);
			
			$('.ui.basic.modal').modal('show');
		})
	})

	var deleteAlba = function(){
		if (confirm('${alba.al_name}에 대한 정보를 정말로 삭제하겠습니까?')) {
			location.href='albaDelete.do?al_id=${alba.al_id }';
		}
	}
</script>
</body>
</html>