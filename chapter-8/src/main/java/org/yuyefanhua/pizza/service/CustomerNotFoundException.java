/**
 * @author 60417
 * @date 2022/2/4
 * @time 11:43
 * @todo
 */
package org.yuyefanhua.pizza.service;

public class CustomerNotFoundException extends Exception{
    public CustomerNotFoundException() {}
    public CustomerNotFoundException(String message) {
        super(message);
    }
}
