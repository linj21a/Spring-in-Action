/**
 * @author 60417
 * @date 2022/2/3
 * @time 22:31
 * @todo
 */
package org.yuyefanhua.pizza.domain;

import org.yuyefanhua.pizza.service.PricingEngine;
import org.yuyefanhua.pizza.service.PricingEngineImpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * pizza订单
 */
public class Order implements Serializable {
    private static final long serialVersionUID=1L;//序列化id
    private Customer customer;//顾客
    private List<Pizza> pizzas;//订购的pizza
    private Payment payment;//支付信息

    public Order() {
        pizzas = new ArrayList<Pizza>();//分配内存
        customer = new Customer();
    }
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public List<Pizza> getPizzas() {
        return pizzas;
    }
    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }
    public Payment getPayment() {
        return payment;
    }
    public void setPayment(Payment payment) {
        this.payment = payment;
    }
    public float getTotal(){
        if(pricingEngine==null){
            pricingEngine = new PricingEngineImpl();
        }
        return pricingEngine.calculateOrderTotal(this);
    }
    public void addPizza(Pizza pizza) {
        pizzas.add(pizza);
    }
//    计算价格的，这里使用注入的方式注入
    private PricingEngine pricingEngine;
    public void setPricingEngine(PricingEngine pricingEngine) {
      this.pricingEngine = pricingEngine;
    }
}
