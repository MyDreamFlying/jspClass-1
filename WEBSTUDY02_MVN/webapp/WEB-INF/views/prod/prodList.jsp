<%@page import="kr.or.ddit.vo.ProdVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
table, th, td{
	border : 1px solid black;	
}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<button type="button" onClick="window.reload=<%=request.getContextPath()%>/prod/prodInsert.do">상품 추가</button>
<table>
	<thead>
		<tr>
			<th>상품코드</th>
			<th>상품분류명</th>
			<th>상품명</th>
			<th>거래처명</th>
			<th>구매가</th>
			<th>판매가</th>
			<th>마일리지</th>
		</tr>
	</thead>
	<tbody>
		<%
			List<ProdVO> prodList = (List) request.getAttribute("prodList");
			for(ProdVO vo : prodList){
				%>
					<tr>
						<td><a href=""><%=vo.getProd_id() %></a></td>
						<td><%=vo.getLprod_nm() %></td>
						<td><%=vo.getProd_name() %></td>
						<td><%=vo.getBuyer().getBuyer_name() %></td>
						<td><%=vo.getProd_cost() %></td>
						<td><%=vo.getProd_price() %></td>
						<td><%=vo.getProd_mileage() %></td>
					</tr>
				<%
			}
		%>
	</tbody>
	
</table>
</body>
</html>