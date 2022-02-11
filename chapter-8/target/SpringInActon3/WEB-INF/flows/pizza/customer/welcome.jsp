<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page  contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<html>

<head>
    <meta charset="UTF-8">
    <title>Spring 披萨</title>
</head>
<body>
    <h2>欢迎来到Spring 披萨!!!</h2>
    <form:form>
<%--        flowExecutionKey是流程执行的key--%>
        <input type="hidden" name="_flowExecutionKey"
               value="${flowExecutionKey}"/>
<%--        电话号码 --%>
        <input type="text" name="phoneNumber"/><br/>
<%--        点击提交：触发_eventId_phoneEntered --%>
        <input type="submit" name="_eventId_phoneEntered" value="Lookup Customer" />
    </form:form>
</body>
</html>