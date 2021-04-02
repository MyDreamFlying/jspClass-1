<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>09/cookieDesc.jsp</title>
</head>
<body>
<h4>Cookie</h4>
<pre>
	: Http의 stateless 특성을 보완하고 위해 최소한의 상태 정보를 저장하는 개념.
		1. session : 상태 정보를 서버사이드에서 유지. 
		2. cookie : 상태 정보를 클라이언트 사이드에서 유지.
		
		** 쿠키 사용 방법
		<%
			Cookie sampleCookie = new Cookie("sampleCookie","CookieValue"); 
			response.addCookie(sampleCookie);
			
			String koreanValue = URLEncoder.encode("한글쿠키", "utf-8");
			Cookie koreanCookie = new Cookie("koreanCookie", koreanValue);
			
// 			Cookie allDomain = new Cookie("allDomainCookie", "all domain");
// 			response.addCookie(allDomain);
			
			Cookie allPathCookie = new Cookie("allPathCookie", "All~Path~");
			allPathCookie.setPath("/");
			response.addCookie(allPathCookie);
			
			Cookie longLiveCookie = new Cookie("longLiveCookie", "Long~~");
			longLiveCookie.setMaxAge(-1);
			response.addCookie(longLiveCookie);
			
		%>
		1. 쿠키 생성
		2. 응답에 쿠키를 포함하여 전송(header)
		
		3. 쿠키가 브라우저가 가진 저장소에 저장.
		4. 저장되어 있던 쿠키가 다음번 요청에 실려 재전송(header)
		
		5. 요청에 포함된 쿠키를 꺼내서 상태 복원 :
			<a href="cookieView.jsp">쿠키 확인하기(동일경로)</a>
			<a href="../08/cookieView.jsp">쿠키 확인하기(다른경로)</a>
		
		** 쿠키 속성의 종류
		
		1. name(required)
		2. value(required) : 특수문자를 포함하는 경우, RFC2396 규약에 따라 url encoding 방식을 사용함.
		3. domain(host) : 쿠키의 재전송을결정하는 조건.
			생략시, 쿠키 생성 도메인이 기본값으로 반영됨.
		4. path : 쿠키의 재전송을 결정하는 조건.
				다음번 요청이 설정된 경로 이하로 발생할때만 재전송.
				생략시, 쿠키 생성시의 경로가 기본적으로 반영됨.
		5. maxAge(expires) : 쿠키의 만료 시점
			생략시, 기본값으로 세션과 동일한 만료 시점이 사용.
			0 : 쿠키 삭제 (쿠키의 모든 조건이 동일한 경우)
			-1 : 브라우저 종료시 쿠키 바로 삭제
		6. httpOnly : http 프로토콜에서만 재전송
		7. secure : https 프로토콜에만 재전송
		
</pre>
</body>
</html>