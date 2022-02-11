/**
 * @author 60417
 * @date 2022/2/4
 * @time 11:37
 * @todo
 */
package org.yuyefanhua.pizza.domain;

import java.io.Serializable;

/**
 * 顾客类：
 * 包括id、姓名、地址、城市、州、邮编、电话号码
 * 序列化 Serializable 所以成员都是类的实现
 */
public class Customer implements Serializable {
    private Integer id;//id
    private String name;// 姓名
    private String address; // 地址
    private String city;//城市
    private String state;//州
    private String zipCode;//邮编
    private String phoneNumber;//电话号码
    public Customer() {}
    public Customer(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getZipCode() {
        return zipCode;
    }
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}