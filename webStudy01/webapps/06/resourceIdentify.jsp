<%@page import="java.nio.file.StandardCopyOption"%>
<%@page import="java.nio.file.Paths"%>
<%@page import="java.nio.file.Path"%>
<%@page import="java.nio.file.Files"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.File"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.net.URL"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>06/resourceIdentify.jsp</title>
</head>
<body>
<h4>자원의 식별</h4>
<pre>
	자원의 종류
	1. File System Resource : /Users/shane/Documents/GitHub/jspClass/contents/IMG_8537.JPG
	2. Class Path Resource : kr/or/ddit/servlet01/ImageFormServlet.class
	3. Web Resource : /images/IMG_8240.JPG
	<%
		String imageURL = "/images/IMG_8240.JPG";
		String folderURL = "/06";
		File folder = new File(application.getRealPath(folderURL));
		File saveFile = new File(folder, "IMG_8240.JPG");
		Path savePath = Paths.get(saveFile.getAbsolutePath());
		out.println(savePath);
		URL url = application.getResource(imageURL);
		out.println(url);
		try(
			InputStream is = application.getResourceAsStream(imageURL);
			FileOutputStream fos = new FileOutputStream(saveFile);
		){
			Files.copy(is, savePath, StandardCopyOption.REPLACE_EXISTING);
		}
	%>
</pre>
<img src="<%=request.getContextPath()+folderURL+"/"+saveFile.getName()%>">
</body>
</html>