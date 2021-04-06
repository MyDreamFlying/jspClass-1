<%@page import="kr.or.ddit.vo.PagingVO"%>
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
			<th>No.</th>
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
			PagingVO<ProdVO> pagingVO = (PagingVO)request.getAttribute("pagingVO");
		
			List<ProdVO> prodList = pagingVO.getDataList();
			
			if(prodList.size() > 0){
				for(ProdVO vo : prodList){
					%>
						<tr>
							<td><%=vo.getRnum() %></a></td>
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
			}else{
				%>
				<tr>
					<td colspan="8"> 조회할 상품이 없습니다</td>
				</tr>
				<%
			}
		%>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="8">
				<%=pagingVO.getPagingHTML() %>
			</td>
		</tr>
	</tfoot>
	
</table>
</body>
</html>