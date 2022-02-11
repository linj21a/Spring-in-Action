<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<head>
  <meta charset="UTF-8">
</head>
<div>
  <script>
    function showCreditCardField() {
      //展示信用卡
      //获取name为paymentForm的表格 设置其creditCardNumber的框为显示
      var ccNumberStyle = document.paymentForm.creditCardNumber.style;
      ccNumberStyle.visibility = 'visible';
    }
    //隐藏信用卡
    function hideCreditCardField() {
      //获取name为paymentForm的表格 设置其creditCardNumber的框为隐藏
      var ccNumberStyle = document.paymentForm.creditCardNumber.style;
      ccNumberStyle.visibility = 'hidden';
    }
  </script>
  <h2>支付信息</h2>
  <sf:form modelAttribute="paymentDetails" name="paymentForm">
    <input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}"/>
<%--    上门支付现金时隐藏信用卡输入框--%>
    <sf:radiobutton path="paymentType"
                      value="CASH" label="现金 (上门时支付)"
                      onclick="hideCreditCardField()"/><br/>
    <sf:radiobutton path="paymentType"
                      value="CHECK" label="支票 (上门时支付)"
                      onclick="hideCreditCardField()"/><br/>
<%--    使用信用卡时展示信用卡输入框--%>
    <sf:radiobutton path="paymentType"
                      value="CREDIT_CARD" label="信用卡:"
                      onclick="showCreditCardField()"/>
    <sf:input path="creditCardNumber"
                cssStyle="visibility:hidden;"/>
    <br/><br/>
<%--    是否提交 paymentSubmitted为触发事件，其将转到verifyPayment认证支付是否有效--%>
    <input type="submit" class="button"
           name="_eventId_paymentSubmitted" value="Submit"/>
<%--    cancel触发事件，将转到Cancel状态 --%>
    <input type="submit" class="button"
           name="_eventId_cancel" value="Cancel"/>
  </sf:form>
</div>
