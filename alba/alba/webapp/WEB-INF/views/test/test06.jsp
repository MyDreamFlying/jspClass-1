<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp" />
</head>
<body>
<h4>Test 06</h4>
<table class="table table-dark table-striped">
	<c:forEach items="${albaList }" var="alba" varStatus="vs" >
		<tr>
			<th>알바후보 ${vs.count}번</th>
			<td>${alba}</td>
		</tr>
	</c:forEach>
</table>
<jsp:include page="/includee/postScript.jsp"/>
</body>
</html>