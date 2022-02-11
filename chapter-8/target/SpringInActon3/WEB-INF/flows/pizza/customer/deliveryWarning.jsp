<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" contentType="text/html;UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Spring Pizza</title></head>
<body>
<h2>无法派送</h2>

<p>派送地址超出配送范围，需要到店自取！</p>

<a href="${flowExecutionUrl}&_eventId=accept">接受</a> |
<a href="${flowExecutionUrl}&_eventId=cancel">取消</a>
</body>
</html>