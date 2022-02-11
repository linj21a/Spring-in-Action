/**
 * @author 60417
 * @date 2022/2/4
 * @time 18:49
 * @todo
 */
package org.yuyefanhua.pizza.service;

/**
 * 简单的支付处理器实现
 * 拒绝支付超过 20 美元的任何金额。
 */
public class PaymentProcessor {
    public PaymentProcessor() {}

    /**
     * 判断是否有效
     * @param creditCardNumber
     * @param expMonth
     * @param expYear
     * @param amount
     * @throws PaymentException
     */
    public void approveCreditCard(String creditCardNumber, String expMonth,
                                  String expYear, float amount) throws PaymentException {
        if (amount > 20.00) {
            throw new PaymentException();
        }
    }
}