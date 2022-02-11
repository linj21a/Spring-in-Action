/**
 * @author 60417
 * @date 2022/2/4
 * @time 17:00
 * @todo
 */
package org.yuyefanhua.pizza.service;

import org.yuyefanhua.pizza.domain.Customer;
import org.yuyefanhua.pizza.service.CustomerNotFoundException;
import org.yuyefanhua.pizza.service.CustomerService;

public class CustomerServiceImpl implements CustomerService {
    public CustomerServiceImpl() {}

    /**
     * 查询顾客
     * @param phoneNumber 电话号码
     * @return customer
     * @throws CustomerNotFoundException 找不到customer
     */
    public Customer lookupCustomer(String phoneNumber) throws CustomerNotFoundException {
        //数据库实现
        //这里用假的实现：
        if("10086".equals(phoneNumber)||"13456729304".equals(phoneNumber)) {
            Customer customer = new Customer();
            customer.setId(123);
            customer.setName("YUYEFANHUA");
            customer.setAddress("广东省天河区");
            customer.setCity("广州");
            customer.setState("广州");
            customer.setZipCode("555442");
            customer.setPhoneNumber(phoneNumber);
            return customer;
        } else {
            throw new CustomerNotFoundException();
        }
    }
}
