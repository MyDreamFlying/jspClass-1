<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<link rel="stylesheet" type="text/css" href="js/semantic/semantic.css">

<script type=text/javascript src="${cPath }/js/jquery-3.6.0.min.js"></script>
<script type=text/javascript src="${cPath }/js/myJqueryPlugin.js"></script>
<script type=text/javascript src="${cPath }/js/jquery.form.min.js"></script>
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Poor+Story&display=swap" rel="stylesheet">
<style>
body{
	font-family: 'Poor Story', cursive;
	font-size : 1.2em;
}
</style>

<script type="text/javascript">
	$.getContextPath = function(){
		return "${cPath }";
	}
</script>