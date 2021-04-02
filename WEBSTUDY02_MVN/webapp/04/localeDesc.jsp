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
</head>
<body>
<h4>Locale </h4>
<pre>
	: 언어나 지역이나 문화적 특성들을 캡슐화 한 객체
	locale text : 언어코드_국가(지역)코드
	
</pre>
<span id="time">
<%
String locParam = request.getParameter("loc");
Locale selectedLocale = request.getLocale();
if(locParam != null && !locParam.isEmpty()){
	selectedLocale = Locale.forLanguageTag(locParam);
}
Calendar cal = Calendar.getInstance();
out.println(String.format(selectedLocale,"%tc", cal));
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
		String selected = tmp.equals(selectedLocale)? "selected" : "";
		if(!language.isEmpty() && !country.isEmpty()){
		%>
			<option <%=selected%> value="<%=tmp.toLanguageTag()%>" ><%= String.format("%s[%s]",country,language) %></option>
		<%
		}
	}
	%>
	</select>
</form>
<script type="text/javascript">
	$("[name='loc']").on("change", function(event){
		this.form.submit();
	})
</script>
</body>
</html>