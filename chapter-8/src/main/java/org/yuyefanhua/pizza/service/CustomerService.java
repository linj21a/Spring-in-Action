/**
 * @author 60417
 * @date 2022/2/4
 * @time 17:00
 * @todo
 */
package org.yuyefanhua.pizza.service;

import org.yuyefanhua.pizza.domain.Customer;

public interface CustomerService {
    Customer lookupCustomer(String phoneNumber) throws CustomerNotFoundException;
}
