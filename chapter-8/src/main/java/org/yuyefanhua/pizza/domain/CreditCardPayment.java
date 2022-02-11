/**
 * @author 60417
 * @date 2022/2/4
 * @time 16:22
 * @todo
 */
package org.yuyefanhua.pizza.domain;

/**
 * 信用卡支付交易类：
 *
 */
public class CreditCardPayment extends Payment{
    public CreditCardPayment() {}

    private String authorization;//密码鉴权
    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }

    public String toString() {
        return "CREDIT:  $" + getAmount() + " ; AUTH: " + authorization;
    }
}
