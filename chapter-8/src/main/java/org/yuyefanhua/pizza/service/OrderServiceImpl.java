/**
 * @author 60417
 * @date 2022/2/4
 * @time 17:06
 * @todo
 */
package org.yuyefanhua.pizza.service;

import org.apache.log4j.Logger;
import org.yuyefanhua.pizza.domain.Order;

public class OrderServiceImpl implements OrderService {
    private static final Logger LOGGER =
            Logger.getLogger(OrderServiceImpl.class);
    public OrderServiceImpl() {}

    public void saveOrder(Order order) {
        //保存订单使用控制台打印出订单信息
        LOGGER.debug("保存订单:  " );
        LOGGER.debug("   顾客:  " + order.getCustomer().getName());
        LOGGER.debug("   # 订购 披萨:  " + order.getPizzas().size());
        LOGGER.debug("   支付:  " + order.getPayment());
    }
}
