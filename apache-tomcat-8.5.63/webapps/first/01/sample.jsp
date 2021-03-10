<%@page import="java.util.Date"%>
<html>
<body>
    <h2>Hello world<h2>
    <h4><span id="timearea"></span></h4>
    <h4><span id="timearea2"><%=new Date()%></span></h4>
<script>
    document.getElementById("timearea").innerHTML = new Date();
</script>
</body>
</html>