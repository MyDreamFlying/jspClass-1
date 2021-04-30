<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<link rel="stylesheet" href="js/bootstrap-4.6.0-dist/css/bootstrap.min.css">

<script type=text/javascript src="js/jquery-3.6.0.min.js"></script>
<script type=text/javascript src="js/myJqueryPlugin.js"></script>
<script type=text/javascript src="js/jquery.form.min.js"></script>
<script type="text/javascript">
	$.getContextPath = function(){
		return "${cPath }";
	}
</script>