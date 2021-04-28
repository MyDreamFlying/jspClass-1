<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<h4>PROD FORM</h4>
<form method="post" id="prodForm" enctype="multipart/form-data">
	<table>
		<tr>
			<th>제품명</th>
			<td>
				<input type="hidden" name="prod_id" value="${prod.prod_id}"/>
				<input type="text" name="prod_name" value="${prod.prod_name}"/>
			</td>
		</tr>
		<tr>
			<th>제품분류</th>
			<td>
				<select name="prod_lgu">
					<option value>상품분류</option>
					<c:forEach items="${lprodList }" var="lprod">
						<c:set var="selected" value='${lprod.lprod_gu eq prod.prod_lgu ? "selected" : ""}'></c:set>
						<option value="${lprod.lprod_gu }" ${selected }>
							${lprod.lprod_nm }
						</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<th>거래처</th>
			<td>
				<select name="prod_buyer">
					<option value>거래처 선택</option>
					<c:forEach items="${buyerList}" var="buyer">
						<c:set var="selected" value='${buyer.buyer_id eq prod.prod_buyer ? "selected" : "" }' ></c:set>
						<option class="${buyer.buyer_lgu }" value="${buyer.buyer_id }" ${selected }>
							${buyer.buyer_name }
						</option>
					</c:forEach>
				</select>
			</td>
		</tr>		
		<tr>
			<th>구매가격</th>
			<td>
				<input type="number" name="prod_cost" value="${prod.prod_cost}"/>
			</td>
		</tr>
		<tr>
			<th>판매가격</th>
			<td>
				<input type="number" name="prod_price" value="${prod.prod_price}"/>
			</td>
		</tr>
		<tr>
			<th>할인가격</th>
			<td>
				<input type="number" name="prod_sale" value="${prod.prod_sale}"/>
			</td>
		</tr>
		<tr>
			<th>간단설명</th>
			<td>
				<input type="text" name="prod_outline" value="${prod.prod_outline}"/>
			</td>
		</tr>
		<tr>
			<th>상세설명</th>
			<td>
				<input type="text" name="prod_detail" value="${prod.prod_detail}"/>
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
				<input type="number" name="prod_totalstock" value="${prod.prod_totalstock}"/>
			</td>
		</tr>
		<tr>
			<th>입고일</th>
			<td>
				<input type="date" name="prod_insdate" value="${prod.prod_insdate}"/>
			</td>
		</tr>
		<tr>
			<th>적정재고</th>
			<td>
				<input type="number" name="prod_properstock" value="${prod.prod_properstock}"/>
			</td>
		</tr>
		<tr>
			<th>사이즈</th>
			<td>
				<input type="text" name="prod_size" value="${prod.prod_size}"/>
			</td>
		</tr>
		<tr>
			<th>색상</th>
			<td>
				<input type="text" name="prod_color" value="${prod.prod_color}"/>
			</td>
		</tr>
		<tr>
			<th>주의사항</th>
			<td>
				<input type="text" name="prod_delivery" value="${prod.prod_delivery}"/>
			</td>
		</tr>
		<tr>
			<th>단위</th>
			<td>
				<input type="text" value="EA" name="prod_unit" value="${prod.prod_unit}"/>
			</td>
		</tr>
		<tr>
			<th>QTYIN</th>
			<td>
				<input type="number" name="prod_qtyin" value="${prod.prod_qtyin}"/>
			</td>
		</tr>
		<tr>
			<th>QTYSALE</th>
			<td>
				<input type="number" name="prod_qtysale" value="${prod.prod_qtysale}"/>
			</td>
		</tr>
		<tr>
			<th>마일리지</th>
			<td>
				<input type="number" name="prod_mileage" value="${prod.prod_mileage}"/>
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
