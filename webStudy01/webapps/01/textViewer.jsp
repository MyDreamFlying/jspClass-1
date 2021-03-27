<%@page import="java.net.URL"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<body>
		<h4>Text file viewer</h4>
		<select id='textFile' name='textFile'>
			<option disabled selected>choose a file to read</option>
<%
	response.setContentType("text/html; charset=utf-8");
	ServletContext context = request.getServletContext();
	URL path = getClass().getResource("/datas");
	File contents = new File(path.getFile());
	ArrayList<String> children = getChildren(contents, context);
	
	for(String child : children){
		out.println(String.format("<option>%s</option>",child));
	}
%>
		</select>
		<br><br>
		<!-- src 속성의 주소값 : ex ) textView.do?textFile=filename(path?) -->
		<iframe id="viewer" src="" style="width:500px;height:500px"></iframe>
<script type="text/javascript">
	var select = document.querySelector("#textFile")
	select.onchange = function(event){
		var fileName = this.value;
		var frm = document.getElementById("viewer");
		frm.setAttribute("src","<%=request.getContextPath()%>/01/text.do?textFile="+fileName);
	}
</script>		
	</body>
</html>
<%!
private ArrayList<String> getChildren(File contents, ServletContext application) {
	ArrayList<String> list = new ArrayList<>();
	File[] children = contents.listFiles();
	
	for(File child : children) {
		if(child.isDirectory()) {
			list.addAll(getChildren(child, application));
		}else {
			String mime = application.getMimeType(child.getName());
			if(mime!=null && mime.startsWith("text/"))
				list.add(String.format("%s%s", getParent(child),child.getName()));
		}
	}
	return list;
}

private StringBuffer getParent(File file) {
	StringBuffer parent = new StringBuffer();
	
	if(!"datas".equals(file.getParentFile().getName())) {
		StringBuffer ancestors = getParent(file.getParentFile());
		String parentDir = file.getParentFile().getName();
		parent.insert(0,ancestors.append(parentDir).append("/"));
	}
	return parent;
}

%>