/**
 * @author 60417
 * @date 2022/2/4
 * @time 16:29
 * @todo
 */
package org.yuyefanhua.pizza.service;

import org.yuyefanhua.pizza.domain.Order;

public interface PricingEngine {
    /**
     * 计算订单总价
     * @param order 订单
     * @ Return float 订单总价
     */
    public float calculateOrderTotal(Order order);
}
