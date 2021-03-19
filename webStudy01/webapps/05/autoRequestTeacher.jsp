<%@page import="java.util.TimeZone"%>
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
<h4>현재 서버의 시각 :<span id="serverTimer"></span></h4>
<h4>현재 클라이언트의 시각 : <span id="clientTimer"></span></h4>
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
out.println(String.format(selectedLocale,"%tc", cal));
 %>
<form id ='selectCountry' method="post" onsubmit="loadTime()">
	<select name="loc">
		<option value="en-US" selected>select your language</option>
	<%
	Locale[] locales = Locale.getAvailableLocales();
	for(Locale tmp : locales){
		String country = tmp.getDisplayCountry();
		String language = tmp.getDisplayLanguage(tmp);
		if(!country.isEmpty()){
		%>
			<option value="<%=tmp.toLanguageTag()%>" ><%= String.format("%s[%s]",country,language) %></option>
		<%
		}
	}
	%>
	</select>
	
	<select name = "timeZone">
		<%
			for(String tmpId: TimeZone.getAvailableIDs()){
				TimeZone tmpZone = TimeZone.getTimeZone(tmpId);
				%>
				<option value="<%=tmpId %>"><%=tmpZone.getDisplayName() %></option>
				<%
			}
		%>
	</select>
</form>
<script type="text/javascript">
	var span = document.querySelector("#countdown");
	var locSel = $("[name='loc']");
	var tzSel = $("[name='timeZone']");
	var serverTimer = $("#serverTimer");
	var clientTimer = $("#clientTimer");
	const INITTIME = 10;
	var timer = INITTIME;
	// count down
	
	setInterval(function(){
		var data = {
			loc : locSel.val()
			,zone : tzSel.val()
		}
		
		var df =  new Intl.DateTimeFormat(
				new Intl.Locale(data["loc"]),{
					dataStyle : "long"
					, timeStyle : "long"
				}
		);
		
		clientTimer.text(df.format(new Date()));
		$.ajax({
			url : "<%=request.getContextPath() %>/04/serverTimeTeacher",
			data : data,
			dataType : "text",
			success : function(resp) {
				serverTimer.text(resp);
			},
			error : function(xhr, error, msg) {
				console.log(xhr);
				console.log(error);
				console.log(msg);
			}
		})	
	},1000);
	
</script>
</body>
</html>