<%@page import="java.util.Locale"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- <meta http-equiv="Refresh" content="10;url=http://www.naver.com"> -->
<title>05/cacheControl.jsp</title>
<script type ="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<h4>Auto Request header</h4>
1) Refresh 헤더를 이용한 방식.
<%--
	response.setIntHeader("Refresh", 1);
--%>
2) html의 meta 태그 이용
3) JS 의 스케쥴링 함수 이용 : setInterval, setTimeout
<h4>현재 서버의 시각 :<span id="serverTime"></span></h4>
<h4><span id="time"></span></h4>
매 1초마다 비동기 요청을 발생시키고,
클라이언트에게 사용 언어를 입력 받을 것.
클라이언트에게 시간대를 입력 받을 것.
<%
String locParam = request.getParameter("loc");
Locale selectedLocale = request.getLocale();
if(locParam != null && !locParam.isEmpty()){
	selectedLocale = Locale.forLanguageTag(locParam);
}
Calendar cal = Calendar.getInstance();
 %>
<form id ='selectCountry' method="post" onsubmit="loadTime()">
	<select id="loc" name="loc">
		<option disabled selected>select your language</option>
	<%
	Locale[] locales = Locale.getAvailableLocales();
	for(Locale tmp : locales){
		String country = tmp.getDisplayCountry();
		String language = tmp.getDisplayLanguage(tmp);
		String selected = tmp.equals(selectedLocale)? "selected" : "";
		if(!country.isEmpty()){
		%>
			<option <%=selected%> value="<%=tmp.toLanguageTag()%>" ><%= String.format("%s[%s]",country,language) %></option>
		<%
		}
	}
	%>
	</select>
</form>
<script type="text/javascript">
	$(function(){
		
		var timeForm = $("#selectCountry").on("submit", function(event){
			event.preventDefault();
			loadTime();
		});
		
	})
	
	$("[name='loc']").on("change", function(event){
		this.form.submit();
	})
	
	"use strict";
	var span = document.querySelector("#countdown");
	const INITTIME = 10;
	var timer = INITTIME;
	
	var jobId = setInterval(()=>{
		loadTime();
		if(timer==0) clearInterval(jobId);
	}, 1000);
/* 	setTimeout(function(){
		location.reload();
	}, 1000) */
	loadTime = function(){
		var locValue = $('#loc').val();
		var option = {
				url : "<%=request.getContextPath() %>/04/serverTime",
				method : 'get',
				error : function(xhr){
					alert("error code:" + xhr.status);
				},
				data : {"loc" : locValue},
				dataType : 'text'
			}
		option.success = function(timeres){
			$('#time').text(timeres);
		} 
		$.ajax(option);
	}
</script>
</body>
</html>