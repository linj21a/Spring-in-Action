<%--导入spring form--%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<head>
    <meta charset="UTF-8">
</head>
<div>
    <h2>创建披萨</h2>
<%--    这里，表单绑定pizza 这个pizza是在流程作用域内evaluate引用的方法 创建--%>
    <sf:form modelAttribute="pizza">
<%--        流程返回和结束的门票 --%>
        <input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}"/>
        <b>尺寸: </b><br/>
<%--        单选框选择尺寸 绑定到pizza.size--%>
        <sf:radiobutton path="size" label="小 (12-inch)" value="SMALL"/><br/>
        <sf:radiobutton path="size" label="中 (14-inch)" value="MEDIUM"/><br/>
        <sf:radiobutton path="size" label="大 (16-inch)" value="LARGE"/><br/>
        <sf:radiobutton path="size" label="超大 (20-inch)" value="GINORMOUS"/><br/>
        <br/>
<%-- 配料类型--%>
        <b>配料: </b><br/>
        <sf:checkboxes path="toppings" items="${toppingsList}" delimiter="<br/>"/><br/>
        <br/>
<%--        点击continue时，触发submit，触发addPizza方法
            它会和evaluate标签关联，然后在状态转移前创建Pizza放到流程作用域内 然后传递给addPizza方法--%>
        <input type="submit" class="button" name="_eventId_addPizza" value="Continue"/>
        <input type="submit" class="button" name="_eventId_cancel" value="Cancel"/>
    </sf:form>
</div>
