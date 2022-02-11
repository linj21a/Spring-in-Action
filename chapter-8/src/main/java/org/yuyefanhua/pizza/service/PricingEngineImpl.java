/**
 * @author 60417
 * @date 2022/2/4
 * @time 16:29
 * @todo
 */
package org.yuyefanhua.pizza.service;

import org.apache.log4j.Logger;//区分开org.apache.logging.log4j.Logger
import org.yuyefanhua.pizza.service.PricingEngine;
import org.yuyefanhua.pizza.domain.Order;
import org.yuyefanhua.pizza.domain.Pizza;
import org.yuyefanhua.pizza.domain.PizzaSize;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
//忽略该警告
// The serializable class WmailCalendar does not declare a static final serialVersionUID field of type long
//使用这个注释将警告信息去掉。
@SuppressWarnings("serial")
public class PricingEngineImpl  implements PricingEngine, Serializable {
    private static final Logger LOGGER = Logger.getLogger(PricingEngine.class);
    private static Map<PizzaSize, Float> SIZE_PRICES;
    static {
        SIZE_PRICES = new HashMap<PizzaSize, Float>();
        SIZE_PRICES.put(PizzaSize.SMALL, 6.99f);
        SIZE_PRICES.put(PizzaSize.MEDIUM, 7.99f);
        SIZE_PRICES.put(PizzaSize.LARGE, 8.99f);
        SIZE_PRICES.put(PizzaSize.GINORMOUS, 9.99f);
    }
    private static float PRICE_PER_TOPPING = 0.20f;
    public PricingEngineImpl() {}

    public float calculateOrderTotal(Order order) {

        LOGGER.debug("Calculating order total");
        float total = 0.0f;
        if(order==null){
            return total;
        }
        for (Pizza pizza : order.getPizzas()) {
            float pizzaPrice = SIZE_PRICES.get(pizza.getSize());
            if(pizza.getToppings().size() > 2) {
                pizzaPrice += (pizza.getToppings().size() *
                        PRICE_PER_TOPPING);
            }
            total += pizzaPrice;
        }
        return total;
    }
}