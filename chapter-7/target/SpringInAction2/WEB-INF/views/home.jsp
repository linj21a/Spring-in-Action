<%--使用taglib对象，导入jstl标签库，别名为c --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page session="false" contentType="text/html;charset=UTF-8" %>
<html>
<head>
<title>Spittr</title>
<%--    引入外部样式文件--%>
    <link rel="stylesheet"
<%--          这里的value路径是以webapp下面的为准的，因为这些资源属于web--%>
          type="text/css" href="<c:url value="/CSS/style.css"/>" >
</head>
<body>
    <h1><spring:message code="spittr.welcome" /></h1>
      <a href="<c:url value="/" />" >
    <spring:message code="spittr.left" />
      </a>
    <a href="<c:url value="/spitter/register" />" >
        <spring:message code="spittr.right"/>
    </a>
</body>
</html>
