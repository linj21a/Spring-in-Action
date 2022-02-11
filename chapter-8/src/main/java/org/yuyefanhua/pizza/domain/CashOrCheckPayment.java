/**
 * @author 60417
 * @date 2022/2/4
 * @time 16:24
 * @todo
 */
package org.yuyefanhua.pizza.domain;

/**
 * 现金或者支票支付类
 *
 */
public class CashOrCheckPayment extends Payment{
    public CashOrCheckPayment() {}

    public String toString() {
        return "CASH or CHECK:  $" + getAmount();
    }
}
