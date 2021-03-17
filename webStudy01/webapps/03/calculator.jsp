<%@page import="kr.or.ddit.enumpkg.OperatorType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<style>
.num, .result, .operator,.calc{
	font-size : 2.0em;
}
.num{
	width : 150px
}
.result{
	width : 400px
}
.operator{
	width : 50px;
}
</style>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type ="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
	$(function(){
		$("#calForm").on("submit",function(event){
			event.preventDefault();
			var action = this.action;
			var method = this.method;
			var data = $(this).serialize();
			console.log(data);
			$.ajax({
				url : action,
				method : method,
				data : data,
				dataType : "json",
				success:function(resp){
					$('.result').val(resp.result);
				},
				error:function(xhr, error, msg){
					console.log(xhr);
					console.log(error);
					console.log(msg);
				}
			})
		})
	})
</script>
</head>
<pre>
1. left, right의 피 연산자와 operator 라는 이름의 연산자를 포함한 필수 파라미터 입력.
2. 연산의 종류는, 사칙연산 지원
3. 입력 데이터는 실수형.
4. 파라미터 전송 : /03/calculator의 방향으로 전송.(전송 데이터는 비노출)
5. 연산의 결과 : ex ) 3 * 4 = 12 와 같은 형태로 제공.
</pre>
<body>
	<form id="calForm" method="post" action="<%=request.getContextPath() %>/03/calculator">
		<input name = "left" class="num" type=text>
		<%
			for(OperatorType tmp : OperatorType.values()){
				%>
				<label><input type="radio" name="operator" value='<%=tmp.name()%>'class="operator"/><%=tmp.getSign()%></label>
				<%
			}
		%>
		<input name = "right" class="num" type=text>
		<input type="submit" class="calc" value=" = ">
		<br><input class="result" type=text>
	</form>
</body>
</html>