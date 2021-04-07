<%@page import="kr.or.ddit.vo.BuyerVO"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp"/>
</head>
<body>
<h4>PROD FORM</h4>
<jsp:useBean id="prod" class="kr.or.ddit.vo.ProdVO" scope="request"></jsp:useBean>
<form method="post" id="prodForm">
	<table>
		<tr>
			<th>제품명</th>
			<td>
				<input type="text" name="prod_name"/>
			</td>
		</tr>
		<tr>
			<th>제품분류</th>
			<td>
				<select name="prod_lgu">
					<%
						List<Map<String, Object>> lprodList = 
							(List<Map<String, Object>>)request.getAttribute("lprodList");
						for( Map<String, Object> lprod : lprodList){
							%>
								<option value="<%=lprod.get("lprod_gu") %>"><%=lprod.get("lprod_nm") %></option>
							<%
						}
					%>
				</select>
			</td>
		</tr>
		<tr>
			<th>구입처</th>
			<td>
				<select name="prod_buyer">
					<%
						List<BuyerVO> buyerList = (List<BuyerVO>)request.getAttribute("buyerList");
						for(BuyerVO buyer : buyerList){
							%>
								<option class="<%=buyer.getBuyer_lgu() %>" value=<%=buyer.getBuyer_id() %>><%=buyer.getBuyer_name() %></option>
							<%
						}
					%>
				</select>
			</td>
		</tr>		
		<tr>
			<th>구매가격</th>
			<td>
				<input type="number" name="prod_cost"/>
			</td>
		</tr>
		<tr>
			<th>판매가격</th>
			<td>
				<input type="number" name="prod_price"/>
			</td>
		</tr>
		<tr>
			<th>할인가격</th>
			<td>
				<input type="number" name="prod_sale"/>
			</td>
		</tr>
		<tr>
			<th>간단설명</th>
			<td>
				<input type="text" name="prod_outline"/>
			</td>
		</tr>
		<tr>
			<th>상세설명</th>
			<td>
				<input type="text" name="prod_detail"/>
			</td>
		</tr>
		<tr>
			<th>사진</th>
			<td>
				<input type="text" name="prod_img"/>
			</td>
		</tr>
		<tr>
			<th>상세재고</th>
			<td>
				<input type="number" name="prod_totalstock"/>
			</td>
		</tr>
		<tr>
			<th>입고일</th>
			<td>
				<input type="date" name="prod_insdate"/>
			</td>
		</tr>
		<tr>
			<th>적정재고</th>
			<td>
				<input type="number" name="prod_properstock"/>
			</td>
		</tr>
		<tr>
			<th>사이즈</th>
			<td>
				<input type="text" name="prod_size"/>
			</td>
		</tr>
		<tr>
			<th>색상</th>
			<td>
				<input type="text" name="prod_color"/>
			</td>
		</tr>
		<tr>
			<th>주의사항</th>
			<td>
				<input type="text" name="prod_delivery"/>
			</td>
		</tr>
		<tr>
			<th>단위</th>
			<td>
				<input type="text" value="EA" name="prod_unit"/>
			</td>
		</tr>
		<tr>
			<th>QTYIN</th>
			<td>
				<input type="number" name="prod_qtyin"/>
			</td>
		</tr>
		<tr>
			<th>QTYSALE</th>
			<td>
				<input type="number" name="prod_qtysale"/>
			</td>
		</tr>
		<tr>
			<th>마일리지</th>
			<td>
				<input type="number" name="prod_mileage"/>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<button type="submit">등록</button>
				<button type="button">취소</button>
			</td>
		</tr>
	</table>
</form>

</body>
</html>