<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<style>
img{
	height : 100px;
}
.form-check-label{
	width : 150px;
}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp"/>
</head>
<body>
<h4>ALBA FORM</h4>
<form class="ui form" method="post" enctype="multipart/form-data">
	<input type="hidden" name="al_id" value="${alba.al_id }">
	<div class="field">
		<label for="al_name">이름:</label>
		<input type="text" class="form-control" placeholder="Enter name" name="al_name" value="${alba.al_name}" required>
	</div>
	<div class="field">
		<label for="profile">프로필 사진</label>
		<div id="preview">
			<c:choose>
				<c:when test="${not empty alba.al_img}">
					<img src="${cPath}/profile/${alba.al_img}">
				</c:when>
				<c:when test="${not empty alba}">
					<img src="${cPath}/profile/default_${alba.al_gen}.jpg">
				</c:when>
				<c:otherwise>
					<img src="${cPath}/profile/default_M.jpg">
				</c:otherwise>
			</c:choose>
		</div>
		<input type="file" accept="image/*" class="form-control-file" name="profile" id="profile">
	</div>
	<div class="field">
		<label for="al_age">나이:</label>
		<input type="number" class="form-control" placeholder="Enter age" name="al_age" value="${alba.al_age}" required>
	</div>
	<div class="field">
		<label for="al_zip">우편번호:</label>
		<input type="text" class="form-control" placeholder="35036" name="al_zip" value="${alba.al_zip}" required>
	</div>
	<div class="field">
		<label for="al_addr1">주소:</label>
		<input type="text" class="form-control" placeholder="대전광역시" name="al_addr1" value="${alba.al_addr1}" required>
	</div>
	<div class="field">
		<label for="al_addr2">상세주소:</label>
		<input type="text" class="form-control" placeholder="중구 대흥동 영민빌딩" name="al_addr2" value="${alba.al_addr2}" required>
	</div>
	<div class="field">
		<label for="al_hp">전화번호:</label>
		<input type="text" class="form-control" placeholder="Enter phone number" name="al_hp" value="${alba.al_hp}" required>
	</div>
	<div class="field">
		<label for="grade">학력:</label>
 		<select name="gr_code" required>
	     	<option value>학력 선택</option>
	     	<c:forEach items="${gradeList}" var="grade">
	     		<c:set var="selected" value="${grade.gr_code eq alba.gr_code ? 'selected' : ''}"></c:set>
	     		<option value="${grade.gr_code}" ${selected} >
	     			${grade.gr_name }
	     		</option>
	     	</c:forEach>
 		</select>
	</div>
	<div class="ui form">
		<div class="inline fields">
			<label for="fruit">성별:</label>
	    	<div class="field">
				<div class="ui radio checkbox">
	        		<input type="radio" name="al_gen" value="M" ${alba.al_gen eq "M" ? "checked" :""} tabindex="0" class="hidden">
	       			<label>남성</label>
				</div>
			</div>
			<div class="field">
				<div class="ui radio checkbox">
	        		<input type="radio" name="al_gen" value="F"  ${alba.al_gen eq "M" ? "" :"checked"} tabindex="0" class="hidden">
	       			<label>여성</label>
	      		</div>
	  		</div>
		</div>
	</div>
	<div class="field">
		<label for="al_mail">이메일:</label>
		<input type="text" class="form-control" placeholder="Enter email" name="al_mail" value="${alba.al_mail}">
	</div>
	<div class="field">
		<label for="al_career">경력:</label>
		<textarea class="form-control" placeholder="Your work experience" name="al_career" rows="4">${alba.al_career}</textarea>
	</div>
	<div class="ui form">
		<div class="inline field">
			<label>보유 자격증:</label>
	   	<%
	   		List<Map<String, Object>> licenseList = (List<Map<String, Object>>)request.getAttribute("licenseList");
	   		List<String> holdingLicenseList = (List<String>)request.getAttribute("holdingLicenseList");
	   		String checked = "";
	   		for( Map<String,Object> license : licenseList){
		   		String registerButton = "";
	   			if(holdingLicenseList != null){
	 	  			checked = holdingLicenseList.contains(license.get("lic_code")) ? "checked disabled" : "";
	 	  			registerButton = !checked.isEmpty()? "<button type='button' class='regLic small olive button'>사본등록</button>" : "";
	   			}
	   		%>	
	   			 <div class="inline field">
			   	 	<div class="license ui checkbox" data-lic_code="<%=license.get("lic_code") %>">
			     		 <input type="checkbox" name="<%=license.get("lic_code") %>" <%=checked %> tabindex="0" class="hidden">
			     		 <label><%=license.get("lic_name") %> <%=registerButton %></label>
			   		 </div>
			 	 </div>
	   		<%
	   		}
	   	%>
	   	</div>
   	</div>
	<div class="field">
		<label for="alba.al_spec">스펙:</label>
		<textarea class="form-control" placeholder="your spec" name="al_spec" rows="4">${alba.al_spec }</textarea>
	</div>
	<div class="field">
		<label for="al_desc">자기소개:</label>
		<textarea class="form-control" placeholder="Anything about you" name="al_desc" rows="4">${alba.al_desc }</textarea>
	</div>
	<div class="ui buttons">
	  <button onclick="location.href='albaList.do';" class="ui button">Cancel</button>
	  <div class="or"></div>
	  <button type="submit" class="ui positive button">Save</button>
	</div>
</form>
<form id="licForm" hidden="hidden">
	<input type="file" name="licensePic" id="licUpdate">히든파일폼
	<input type="text" name="al_id" value="${alba.al_id }">
	<input type="text" id="lic_code" name="lic_code">
</form>
<script type="text/javascript">
$(function(){
	
	// 이미지 업로드시 미리보기 이벤트
	$("#profile").on("change", handleImgsFilesSelect);
	
	// 자격증 사본 사진 등록 클릭시 이벤트
	$('.regLic').on("click", function(){
		lic_code = $(this).parent('div').data('lic_code');
		$('#lic_code').val(lic_code);
		$("#licUpdate").click();
	})
	
	// 자격증 사본 사진 등록시 비동기 업로드
	$("#licUpdate").on('change', function(){
		const form = $('#licForm')[0];
		let formData = new FormData(form);
		$.ajax({
			type : 'post',
			enctype : 'multipart/form-data',
			url : '${cPath}/licUpload.do',
			data : formData,
			dataType : 'text',
			processData : false,
			contentType : false,
			cache : false,
			timeout : 60000,
			success : function(resp){
				if(resp=='OK'){
					alert("등록 성공");
				}else{
					alert("등록 실패");
				}
			},
			error : function(xhr){
				alert("error code : " + xhr)
			}
		})
		
	})
	
	
})
let profile_file = [];
function handleImgsFilesSelect(e) {
	var files = e.target.files;
	var filesArr = Array.prototype.slice.call(files);
	
	filesArr.forEach(function(f) {
		if(!f.type.match("image.*")) {
			alert("이미지만 업로드 하실 수 있습니다.");
			return;
		}

		profile_file.push(f);

		var reader = new FileReader();
		reader.onload = function(e) {
			var img_html = '<img src=\"' + e.target.result + '\" />';
			$("#preview").html(img_html);
		}
		reader.readAsDataURL(f);
	});
	
}
$(function(){
	$('.ui.radio.checkbox').checkbox();
	$('.ui.checkbox').checkbox();
})

</script>
<jsp:include page="/includee/postScript.jsp"/>
</body>
</html>