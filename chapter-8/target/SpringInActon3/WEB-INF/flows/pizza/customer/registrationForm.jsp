<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--使用spring form表单绑定--%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Spring 披萨</title>
</head>
<body>
    <h2>顾客注册</h2>
<%--    该表单绑定到order   model里面的key--%>
<%--    order具有成员customer --%>
    <sf:form modelAttribute="customer">
        <input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}"/>
        <b>号码：</b><sf:input path="phoneNumber"/><br/>
        <b>姓名：</b><sf:input path="name"/><br/>
        <b>地址：</b><sf:input path="address"/><br/>
        <b>城市：</b><sf:input path="city"/><br/>
        <b>&nbsp;&nbsp;州：</b><sf:input path="state"/><br/>
        <b>邮编：</b><sf:input path="zipCode"/><br/>
<%--        提交 确认--%>
        <input type="submit" name="_eventId_submit" value="Submit">
<%--        提交 取消--%>
        <input type="submit" name="_eventId_cancel" value="Cancel">
    </sf:form>
</body>
</html>
