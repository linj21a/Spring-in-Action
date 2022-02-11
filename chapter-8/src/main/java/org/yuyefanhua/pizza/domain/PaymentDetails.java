/**
 * @author 60417
 * @date 2022/2/4
 * @time 16:11
 * @todo
 */
package org.yuyefanhua.pizza.domain;

import java.io.Serializable;

/**
 * 支付细节
 *  包含支付的类型
 *  信用卡号
 */
public class PaymentDetails implements Serializable {
    private static final long serialVersionUID = 1L;
    private PaymentType paymentType;//支付的类型
    private String creditCardNumber;//信用卡号码

    public PaymentType getPaymentType() {
        return paymentType;
    }
    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }
    public String getCreditCardNumber() {
        return creditCardNumber;
    }
    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }
}