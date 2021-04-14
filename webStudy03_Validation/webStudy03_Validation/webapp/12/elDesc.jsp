<%@page import="kr.or.ddit.utils.CookieUtils"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>EL(Expression Language : 표현언어)</h4>
<pre>
	: (속성)데이터를 표현(출력)할 목적으로 정의된 스크립트 언어
	
	1. 4개 영역의 속성들을 표현할 때 사용(*****).
	2. 연산자 지원
		- 산술연산자 : +-*/% (El에서 "+"는 concat 연산을 수행하지 않음. 무조건 산술연산만 한다.) ${2/3}, ${"2"+"3"}, ${"2"+3 }
					연산의 중심이 "연산자"다. (자바에서 "피연산자" 였던 것 과 반대)
		- 논리연산자 : &amp&amp(and), ||(or), !(not), ^(xor)
						${true and true }, ${true and "true"}, \${false or 3 }
		- 비교연산자 : >(gt), <(lt), >=(ge), <=(le), ==(eq), !=(ne)
						${not (4 le 3) } ${4 gt 3 }
		- 삼항연산자 : 조건식 ? 참 : 거짓
						
		- 단항연산자 : ++, -- (el 에서 원래는 증감 연산자를 쓸 수 없다. 이유: 할당연산자가 지원되지 않기 떄문. -> el의 목적은 값의 출력이지 변경이 아니기 때문.)
								(el 3.x 에서는 가능하도록 업데이트 되었다.)
						<%
							String test = " ";
							pageContext.setAttribute("test", test);
						%>
						empty ${empty test } : 4개의 Scope에서 test 라는 변수가 없는지 확인
							1) 속성의 존재 여부 체크
							2) null 여부 체크
							3) type check
								String 일 경우 legnth > 0 ? false : true
								Array :  length > 0 ?
								Collection : size > 0 ?
								위의 세가지에 해당하지 않음? 2) 에서 확인 끝.
	3. (속성으로 공유되고 있는) 자바 객체에 대한 접근 지원 지원. 
		1) 속성으로 공유되고 있는 객체만
		2) 메서드를 호출하지 않는다. java bean 규약으로 데이터에 접근한다.
	<%
		MemberVO member = new MemberVO("a001", "java");
		request.setAttribute("member", member);
	%>	
		${member.mem_id}, ${member.getTest()}, ${member.test }
		${member["mem_id"]}
	
	4. (속성) 집합객체에 따른 접근 기능 지원
	<%
		String[] array = new String[]{"value1", "value2"};
		session.setAttribute("array", array);
		List<?> list = Arrays.asList("value1", "value2");
		application.setAttribute("sampleList", list);
		
		List<?> list2 = Arrays.asList("tmp1", "tmp2");
		request.setAttribute("sampleList", list2);
		
		Set<String> set = new HashSet<>();
		set.add("value1");
		set.add("value2");
		pageContext.setAttribute("sampleSet", set);
		
		Map<String, Object> map = new HashMap<>();
		map.put("key1", "mapValue1");
		map.put("key-2", new Date());
		session.setAttribute("sampleMap", map);
	%>
		${array[1]} , <%--=array[2] --%> , ${array[2]}
		${sampleList.get(0)}, ${sampleList[0]}, ${sampleList[3]}, \${sampleList.get(3)} <- Out of bound exception
		그렇다면 콕 집어서 특정 스코프의 변수가 필요할때는 ? 기본객체 사용
		
		${sampleSet}
		
		<c:forEach items="${pageScope.sampleSet}" var="element" varStatus="vs"> 
			<c:if test="${not vs.last}">
				${element}
			</c:if>
		</c:forEach>
	
		${sampleMap.get("key-2")}, ${sampleMap.get("key-2").getTime()} 
		${sampleMap.key-2} , ${sampleMap["key-2"]}, ${sampleMap["key-2"].time},  ${sampleMap["key-2"]["time"]}
		 
	5. EL의 기본 객체 지원
		1) scope : pageScope, requestScope, sessionScope, applicationScope
		${applicationScope.sampleList[0] }
		
		2) parameter : param (Map&lt;String,String&gt;), paramValues (Map&lt;String,String[]&gt;)
		<a href="?param1=value1&param1=value2">파라미터 전달</a>
			<%=request.getParameter("param1") %>, ${param.param1}, ${param["param1"] }
			<%=Arrays.toString(request.getParameterValues("param1")) %>, ${paramValues["param1"]}
		
		3) header : header (Map&lt;String,String&gt;), 
					headerValues(Map&lt; String,String[]&gt;)
			user-agent 값
			<%=request.getHeader("user-agent") %>
			${header.user-agent} <- \${header.user-agent} 는 0이 나온다.
			${header["user-agent"]}
			${headerValues["user-agent"][0]}
		
		4) cookie :  cookie (Map&lt;String,Cookie&gt;)
		<%=new CookieUtils(request).getCookie("JSESSIONID").getValue() %>
		${cookie.JSESSIONID.value }
		${cookie["JSESSIONID"]["value"]}
		${pageContext.session.id }
		
		5) context parameter : initParam(Map&lt;String,String&gt;)  (application의 초기화 파라미터)
		<%=application.getInitParameter("contentFolder") %>
		${initParam.contentFolder}
		${initParam["contentFolder"]}
		
		6) pageContext
		${pageContext.request}
		${pageContext.request.contextPath}
						
</pre>
</body>
</html>