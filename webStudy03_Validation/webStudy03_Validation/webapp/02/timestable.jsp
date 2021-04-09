<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	td{
		width : 120px;
		height : 40px;
		text-align : center;
	}
	
	th,td{
		border : 1px solid black;
	}
	
	table{
		border-collapse : collapse;
	}
	.red{
		background-color : red;	
	}
	.green{
		background-color : green;
	}
</style>
</head>
<body>
<!-- 2단 부터 9단까지의 구구단을 table 태그를 이용해서 출력 -->
<!-- 하나의 row에 하나의 단이 출력되도록. -->
<%
	String minStr = request.getParameter("minDan");
	String maxStr = request.getParameter("maxDan");
	int minDan = 2;
	int maxDan = 9;
	if(minStr!= null && minStr.matches("[2-9]")){
		minDan = Integer.parseInt(minStr);
	}
	if(maxStr!= null && maxStr.matches("[2-9]")){
		maxDan = Integer.parseInt(maxStr);
	}
%>
<form>
	<input type="number" placeholder="최소단" name="minDan" min="2" max="9" value="<%=minDan%>">
	<select name="maxDan" required>
		<option value>최대단</option>
		<%
			String OPTPTRN = "<option %2$s value='%1$d'>%1$d단</option>";
			StringBuffer options = new StringBuffer();
			for(int dan=2; dan<=9; dan++){
				options.append(
					String.format(OPTPTRN, dan, dan==maxDan?"selected":"")
				);
			}
			out.println(options);
		%>
	</select>
	<input type="submit" value="전송"/>
</form>
<table>
	<%
		int rowcnt = 1;
		for(int i=minDan; i<=maxDan; i++){
			String clz = "normal";
			if(rowcnt++ == 3){
				clz = "red";
			}
			out.println(String.format("<tr class='%s'>",clz));
			for(int j=1; j<=9; j++){
				if(j == 4){
					clz = "green";
				}else{
					clz = "normal";
				}
				out.println(
					gugudanText(i, j, clz));
		
			}
			out.println("</tr>");
		}
	%>	
	
	<%!
		String gugudanText(int i, int j, String clz){
			return String.format("<td class='%s'>%d * %d = %d</td>",clz,i,j,i*j);
		}
	%>
</table>
</body>
</html>