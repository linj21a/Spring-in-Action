<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>Spring 披萨</title></head>
<body>
  <h2>我的订单</h2>
  <h3>配送信息:</h3>
  <b>${order.customer.name}</b><br/>
  <b>${order.customer.address}</b><br/>
  <b>${order.customer.city}, ${order.customer.state}
    ${order.customer.zipCode}</b><br/>
  <b>${order.customer.phoneNumber}</b><br/>
  <hr/>
  <h3>价格: <fmt:formatNumber type="currency" value="${order.total}"/></h3>
  <hr/>
  <h3>披萨:</h3>
<%--  披萨数量=0 --%>
  <c:if test="${fn:length(order.pizzas) eq 0}">
    <b>你尚未订购披萨!</b>
  </c:if>
  <br/>
<%--  披萨数量!=0--%>
  <c:forEach items="${order.pizzas}" var="pizza">
    <li>${pizza.size} :
      <c:forEach items="${pizza.toppings}" var="topping">
        <c:out value="${topping}" />,
      </c:forEach>
    </li>
  </c:forEach>
<%--  订单状态的转移 --%>
  <form:form>
    <input type="hidden" name="_flowExecutionKey"
           value="${flowExecutionKey}"/>
    <input type="submit" name="_eventId_createPizza"
           value="Create Pizza" />
    <c:if test="${fn:length(order.pizzas) gt 0}">
      <input type="submit" name="_eventId_checkout"
             value="Checkout" />
    </c:if>
    <input type="submit" name="_eventId_cancel"
           value="Cancel" />
  </form:form>

</body>
</html>
