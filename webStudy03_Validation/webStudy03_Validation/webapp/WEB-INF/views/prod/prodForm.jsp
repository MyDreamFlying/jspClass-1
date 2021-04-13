<%@page import="org.apache.commons.lang3.StringUtils"%>
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
<%
	String message = (String)request.getAttribute("message");
	if(StringUtils.isNotBlank(message)){
		%>
		<script type="text/javascript">
			alert("<%=message%>");
		</script>
		<%
	}
%>
</head>
<body>
<h4>PROD FORM</h4>
<jsp:useBean id="prod" class="kr.or.ddit.vo.ProdVO" scope="request"></jsp:useBean>
<%
	String command = (String)request.getAttribute("command");
	if("update".equals(command)){
		out.println(String.format("%s 제품의 정보를 수정합니다.",prod.getProd_id()));
	}
%>
<form method="post" id="prodForm" enctype="multipart/form-data">
	<table>
		<tr>
			<th>제품명</th>
			<td>
				<input type="hidden" name="prod_id" value="<%=prod.getProd_id()%>"/>
				<input type="text" name="prod_name" value="<%=prod.getProd_name()%>"/>
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
							String selected = "";
							if(lprod.get("lprod_gu").equals(prod.getProd_lgu())){
								selected = "selected";
							}
							%>
								<option <%=selected%> value="<%=lprod.get("lprod_gu") %>"><%=lprod.get("lprod_nm") %></option>
							<%
						}
					%>
				</select>
			</td>
		</tr>
		<tr>
			<th>거래처</th>
			<td>
				<select name="prod_buyer">
					<option value>거래처 선택</option>
					<%
						List<BuyerVO> buyerList = (List<BuyerVO>)request.getAttribute("buyerList");
						for(BuyerVO buyer : buyerList){
							String selected = "";
							if(buyer.getBuyer_id().equals(prod.getProd_buyer())){
								selected = "selected";
							}
							%>
								<option <%=selected%> class="<%=buyer.getBuyer_lgu() %>" value=<%=buyer.getBuyer_id() %>><%=buyer.getBuyer_name() %></option>
							<%
						}
					%>
				</select>
			</td>
		</tr>		
		<tr>
			<th>구매가격</th>
			<td>
				<input type="number" name="prod_cost" value="<%=prod.getProd_cost()%>"/>
			</td>
		</tr>
		<tr>
			<th>판매가격</th>
			<td>
				<input type="number" name="prod_price" value="<%=prod.getProd_price()%>"/>
			</td>
		</tr>
		<tr>
			<th>할인가격</th>
			<td>
				<input type="number" name="prod_sale" value="<%=prod.getProd_sale()%>"/>
			</td>
		</tr>
		<tr>
			<th>간단설명</th>
			<td>
				<input type="text" name="prod_outline" value="<%=prod.getProd_outline()%>"/>
			</td>
		</tr>
		<tr>
			<th>상세설명</th>
			<td>
				<input type="text" name="prod_detail" value="<%=prod.getProd_detail()%>"/>
			</td>
		</tr>
		<tr>
			<th>사진</th>
			<td>
				<input type="file" name="prod_image"/>
			</td>
		</tr>
		<tr>
			<th>상세재고</th>
			<td>
				<input type="number" name="prod_totalstock" value="<%=prod.getProd_totalstock()%>"/>
			</td>
		</tr>
		<tr>
			<th>입고일</th>
			<td>
				<input type="date" name="prod_insdate" value="<%=prod.getProd_insdate()%>"/>
			</td>
		</tr>
		<tr>
			<th>적정재고</th>
			<td>
				<input type="number" name="prod_properstock" value="<%=prod.getProd_properstock()%>"/>
			</td>
		</tr>
		<tr>
			<th>사이즈</th>
			<td>
				<input type="text" name="prod_size" value="<%=prod.getProd_size()%>"/>
			</td>
		</tr>
		<tr>
			<th>색상</th>
			<td>
				<input type="text" name="prod_color" value="<%=prod.getProd_color()%>"/>
			</td>
		</tr>
		<tr>
			<th>주의사항</th>
			<td>
				<input type="text" name="prod_delivery" value="<%=prod.getProd_delivery()%>"/>
			</td>
		</tr>
		<tr>
			<th>단위</th>
			<td>
				<input type="text" value="EA" name="prod_unit" value="<%=prod.getProd_unit()%>"/>
			</td>
		</tr>
		<tr>
			<th>QTYIN</th>
			<td>
				<input type="number" name="prod_qtyin" value="<%=prod.getProd_qtyin()%>"/>
			</td>
		</tr>
		<tr>
			<th>QTYSALE</th>
			<td>
				<input type="number" name="prod_qtysale" value="<%=prod.getProd_qtysale()%>"/>
			</td>
		</tr>
		<tr>
			<th>마일리지</th>
			<td>
				<input type="number" name="prod_mileage" value="<%=prod.getProd_mileage()%>"/>
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

<script type="text/javascript">
	let prod_buyerTag = $("[name='prod_buyer']");
	$("[name='prod_lgu']").on("change", function(){
		let selectedLgu = $(this).val();
		if(selectedLgu){
			prod_buyerTag.find("option").hide();
			prod_buyerTag.find("option."+selectedLgu).show();
		}else{
			prod_buyerTag.find("option").show();
		}
		prod_buyerTag.find("option:first").show();
	});
</script>

</body>
</html>