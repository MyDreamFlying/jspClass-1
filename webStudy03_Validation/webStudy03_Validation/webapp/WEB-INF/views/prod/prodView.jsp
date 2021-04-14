<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
table, th, td{
	border : 1px solid black
}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table>
	<tr>
		<th>상품코드</th>
		<td>${prod.prod_id}</td>
	</tr>
	<tr>
		<th>상품명</th>
		<td>${prod.prod_name}</td>
	</tr>
	<tr>
		<th>상품분류명</th>
		<td>${prod.lprod_nm}</td>
	</tr>
	<tr>
		<th>상품매입가격</th>
		<td>${prod.prod_cost}</td>
	</tr>
	<tr>
		<th>상품가격</th>
		<td>${prod.prod_price}</td>
	</tr>
	<tr>
		<th>상품이미지</th>
		<td><img src="${cPath}/prodImages/${prod.prod_img}"></td>
	</tr>
	<tr>
		<th>상품입고일</th>
		<td>${prod.prod_insdate}</td>
	</tr>
	<tr>
		<th>사이즈</th>
		<td>${prod.prod_size}</td>
	</tr>
	<tr>
		<th>색상</th>
		<td>${prod.prod_color}</td>
	</tr>
	<tr>
		<th>상품간단설명</th>
		<td>${prod.prod_outline}</td>
	</tr>
	<tr>
		<th>상품상세설명</th>
		<td>${prod.prod_detail}</td>
	</tr>
	<tr>
		<th>상품재고</th>
		<td>${prod.prod_totalstock}</td>
	</tr>
	<tr>
		<th>상품주의</th>
		<td>${prod.prod_delivery}</td>
	</tr>
	<tr>
		<th>거래처 정보</th>
		<td>
			<table>
				<thead>
					<tr>
						<th>거래처명</th>
						<th>담당자명</th>
						<th>연락처</th>
						<th>주소1</th>
					</tr>
					<tr>
						<td>${prod.buyer.buyer_name }</td>
						<td>${prod.buyer.buyer_charger }</td>
						<td>${prod.buyer.buyer_comtel }</td>
						<td>${prod.buyer.buyer_add1 }</td>
					</tr>
				</thead>
			</table>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<button type="button" onClick="window.location='${cPath}/prod/prodList.do'">상품목록으로</button>
			<button type="button" onClick="window.location='${cPath}/prod/prodUpdate.do?what=${prod.prod_id}'">상품수정</button>
		</td>
	</tr>
</table>
</body>
</html>