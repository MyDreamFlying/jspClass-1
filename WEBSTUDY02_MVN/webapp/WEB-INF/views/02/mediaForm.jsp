<%@page import="java.util.Arrays"%>
<%@page import="com.fasterxml.jackson.databind.ObjectMapper"%>
<%@page import="java.io.Reader"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="java.io.FilenameFilter"%>
<%@page import="java.io.File"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<style>
img{
	width : 50%;
	height : 50%;
}
video{
	width : 50%;
	height : 50%;
}
</style>
	<script type ="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script type="text/javascript">
		$(function(){
			const SRCPTRN = "%A?%N=%V";
			const ImgAction = '<%=request.getContextPath()%>/01/image.do';
			const VideoAction = '<%=request.getContextPath()%>/01/video.do';
			
			$("#media").on('change',function(){
				loadMedia();
			});
			
			var loadMedia = function(){
				$('#mediaArea').empty();
				
				var name = $('#media').attr("name");
				var selecteds = $('#media :selected');
				
				var selectedsVals = $('#media').val();
				var jsonSelected = JSON.stringify(selectedsVals);
				
				// 선택된 목록 쿠키에 넣기 시작
				$.ajax({
					url : "<%=request.getContextPath()%>/02/MediaFormCookie.do",
					method : "get",
					data : {"data" : jsonSelected},
					dataType : "text",
					error : function(xhr, error, msg) {
						console.log(xhr);
						console.log(error);
						console.log(msg);
					}
				})
				// 선택된 목록 쿠키에 넣기 끝
				
				$(selecteds).each(function(idx, object){
					value = object.text;
					var mediaType = object.getAttribute('class');
					var src = SRCPTRN.replace("%N", name)
									.replace("%V", value);
					if(mediaType == 'image'){
						src = src.replace("%A",ImgAction)
						var img = $("<img>").attr("src",src);
						$('#mediaArea').append(img);
					}else if(mediaType == 'video'){
						src = src.replace("%A",VideoAction)
						var video = $("<video autoplay muted controls>").attr("src",src);
						$('#mediaArea').append(video);
					}
				})
			}
			
			loadMedia();
 		});
	</script>
</head>
<body>
<h4><%=new Date()%></h4>
<select multiple="multiple" name="media" id="media">
<%
	// 쿠키 저장된 값 불러오기
	Cookie[] cookies = request.getCookies();
	Cookie searched = null;
	String selectedCookie = null;
	if(cookies != null){
		for(Cookie tmp : cookies){
			String value = URLDecoder.decode(tmp.getValue(),"utf-8");
			if("selecteds".equals(tmp.getName())){
				selectedCookie = URLDecoder.decode(tmp.getValue(),"utf-8");
				break;
			}
		}
	}
	// 저장된 값 불러오기 끝
	
	ObjectMapper objectMapper = new ObjectMapper();
	String[] selecteds = new String[0];
	if(selectedCookie != null){
		selecteds = objectMapper.readValue(selectedCookie, String[].class);
	}
	
	String[] children = (String[])request.getAttribute("children");	
	Date today = new Date();
	StringBuffer options = new StringBuffer();
	for(String child : children){
		String selected = "";
		String mime = application.getMimeType(child);
		String type = mime.startsWith("image/")? "image" : "video";
		if(Arrays.asList(selecteds).contains(child)){
			selected = "selected";
		}
		options.append(String.format("<option %s class='%s'>%s</option>",selected,type,child));
	}
	out.println(options);
%>
</select>
<div id="mediaArea"></div>
<form>
	<input type="file" name="uploadImage"/>
	<input type="submit" value="업로드" />
</form>
</body>
</html>