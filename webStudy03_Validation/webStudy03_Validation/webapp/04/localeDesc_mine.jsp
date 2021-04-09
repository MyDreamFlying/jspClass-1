<%@page import="java.util.Calendar"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.Locale"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>04/localeDesc.jsp</title>
<script type ="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
	$(function(){
		$('form select').on('change',function(){
			$('#selectCountry').submit();
		})
		
		var countryForm = $('#selectCountry').on("submit", function(event){
			event.preventDefault();
			selectedLangueTag = $('#selectCountry').children('select').val();
			const options = { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric' };
			const localDate = new Date().toLocaleDateString(selectedLangueTag,options);
			$('#time').text(localDate);
		})
		
	})
</script>
</head>
<body>
<h4>Locale </h4>
<pre>
	: 언어나 지역이나 문화적 특성들을 캡슐화 한 객체
	locale text : 언어코드_국가(지역)코드
	
	<%= request.getHeader("accept-language") %>
	<%
		Locale locale = request.getLocale();
		out.println(locale.getDisplayLanguage());
		out.println(locale.getDisplayCountry());
	%>
</pre>
<span id="time">
	<%
	Calendar cal = Calendar.getInstance();
	out.println(String.format(locale,"%tc", cal));
	 %>
</span>
<form id ='selectCountry' >
	<select name="loc">
		<option disabled selected>select your language</option>
	<%
	Locale[] locales = Locale.getAvailableLocales();
	for(Locale tmp : locales){
		String language = tmp.getDisplayLanguage(tmp);
		String country = tmp.getDisplayCountry();
		if(!language.isEmpty() && !country.isEmpty()){
		%>
			<option value="<%=tmp.toLanguageTag()%>" ><%= String.format("%s[%s]",country,language) %></option>
		<%
		}
	}
	%>
	</select>
</form>
</body>
</html>