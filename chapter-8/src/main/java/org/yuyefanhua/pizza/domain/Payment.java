/**
 * @author 60417
 * @date 2022/2/4
 * @time 16:18
 * @todo
 */
package org.yuyefanhua.pizza.domain;

import java.io.Serializable;

/**
 * 交易支付类：
 * 需要支付的交易金额
 */
public abstract class Payment implements Serializable {
    private static final long serialVersionUID = 1L;

    private float amount;
    public void setAmount(float amount) {
        this.amount = amount;
    }
    public float getAmount() {
        return amount;
    }
}
