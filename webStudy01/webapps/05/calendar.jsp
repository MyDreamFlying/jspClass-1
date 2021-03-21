<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type ="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<select id="year">
<%! Date today = new Date(); %>
<%
	for(int i=2000; i<2025; i++){
		String selVar="";
		int yearNow = today.getYear()+1900;
		if(i == yearNow)
			selVar = "selected";
		%>
		<option <%=selVar%>><%=i%></option>
		<%
	}
%>
</select>
<select id="month">
<%
	for(int i=1; i<=12; i++){
		String selVar="";
		int monthNow = today.getMonth()+1;
		if(i == monthNow )
			selVar = "selected";
		%>
		<option <%=selVar%>><%=i%></option>
		<%
	}
%>
</select>
<div id="calendar"></div>
</body>
<script type="text/javascript">
	'use strict';
	
	$(function(){
		$('#month').on('change',function(){
			if( $('#year').val() == null){
				return false;
			}
			printCalendar();
		})
		$('#year').on('change',function(){
			if( $('#month').val() == null){
				return false;
			}
			printCalendar();
		})
		printCalendar();
	})
	
	var printCalendar = function(){
		const yearVal = $('#year').val();
		const monthVal = $('#month').val();
		console.log(yearVal);
		let newCalendar = `
			<table border='1px solid black'>
				<caption> ${yearVal} year ${monthVal} month calendar</caption>
				<tr>
					<th>SUN</th>
					<th>MON</th>
					<th>TUE</th>
					<th>WED</th>
					<th>THU</th>
					<th>FRI</th>
					<th>SAT</th>
				</tr>
				<tr>
					<td><%=new Date().getDay()==0? "1" : " " %></td>
					<td><%=new Date().getDay()==1? "1" : " " %></td>
					<td><%=new Date().getDay()==2? "1" : " " %></td>
					<td><%=new Date().getDay()==3? "1" : " " %></td>
					<td><%=new Date().getDay()==4? "1" : " " %></td>
					<td><%=new Date().getDay()==5? "1" : " " %></td>
					<td><%=new Date().getDay()==6? "1" : " " %></td>
				</tr>
			</table>
		`
		console.log(newCalendar)
		$('#calendar').html(newCalendar);
	}
	

</script>
</html>