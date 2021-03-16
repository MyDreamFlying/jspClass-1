<%@page import="java.io.FilenameFilter"%>
<%@page import="java.io.File"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<header>
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
				$('#mediaArea').empty();
				var mediaType = this.options[this.selectedIndex].getAttribute('class');
				var name = this.name;
				var value = $(this).val();
				var src = SRCPTRN.replace("%N", name)
								.replace("%V", value);
				if(mediaType == 'image'){
					src = src.replace("%A",ImgAction)
					var img = $("<img>").attr("src",src);
					$('#mediaArea').html(img);
				}else if(mediaType == 'video'){
					src = src.replace("%A",VideoAction)
					var video = $("<video autoplay controls>").attr("src",src);
					$('#mediaArea').html(video);
				}
				
			});
 		});
	</script>
</header>
<body>
<h4><%=new Date()%></h4>
<select name="media" id="media">
	<option disabled selected>choose a media to watch</option>
<%
	String[] children = (String[])request.getAttribute("children");	
	Date today = new Date();
	
	StringBuffer options = new StringBuffer();
	for(String child : children){
		String mime = application.getMimeType(child);
		String type = mime.startsWith("image/")? "image" : "video";
		options.append(String.format("<option class='%s'>%s</option>",type,child));
	}
	out.println(options);
%>
</select>
<div id="mediaArea"></div>
</body>
</html>