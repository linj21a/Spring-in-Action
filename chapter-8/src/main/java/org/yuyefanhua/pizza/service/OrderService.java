/**
 * @author 60417
 * @date 2022/2/4
 * @time 17:06
 * @todo
 */
package org.yuyefanhua.pizza.service;

import org.yuyefanhua.pizza.domain.Order;

public interface OrderService {
    /**
     * 保存订单
     * @param order
     */
    public void saveOrder(Order order);
}
