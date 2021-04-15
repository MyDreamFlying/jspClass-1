<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:set var="importURL" value="${param.importURL}"/>
<c:set var="source" value="${param.source}"/>
<form>
	<input type="url" name="importURL" placeholder="http://playddit.net" value="${importURL}"/>
	<label><input type="checkbox" name="source" value="true" ${not empty source and source ? "checked" : ""}>소스보기</label>
	<input type="submit" value="가져오기"/>
</form>
<div>
<c:if test="not empty importURL"></c:if>

	${importURL}
	${param.source}
</div>
</body>
</html>