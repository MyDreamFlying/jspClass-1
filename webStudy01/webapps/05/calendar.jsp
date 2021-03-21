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
	<option disabled selected>year</option>
<%
	for(int i=2000; i<2025; i++){
		%>
		<option><%=i%></option>
		<%
	}
%>
</select>
<select id="month">
	<option disabled selected>month</option>
	<option>1</option>
	<option>2</option>
	<option>3</option>
	<option>4</option>
	<option>5</option>
	<option>6</option>
	<option>7</option>
	<option>8</option>
	<option>9</option>
	<option>10</option>
	<option>11</option>
	<option>12</option>
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
	})
	
	var printCalendar = function(){
		const yearVal = $('#year').val();
		const monthVal = $('#month').val();
		console.log(year);
		let newCalendar = `
			<table border='1px solid black'>
				<caption> ${yearVal} 년 ${monthVal} 월 달력</caption>
				<tr>
					<th>SUN</th>
					<th>MON</th>
					<th>TUE</th>
					<th>WED</th>
					<th>THU</th>
					<th>FRI</th>
					<th>SAT</th>
				</tr>
			</table>
		`
		console.log(newCalendar)
		$('#calendar').html(newCalendar);
	}
	

</script>
</html>