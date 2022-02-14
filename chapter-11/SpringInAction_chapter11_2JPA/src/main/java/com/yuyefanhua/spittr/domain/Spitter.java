/**
 * @author 60417
 * @date 2022/1/24
 * @time 20:22
 * @todo
 */
package com.yuyefanhua.spittr.domain;

import javax.persistence.*;

/**
 * 该spitter应用的用户
 * 包括基本信息：
 * 性，名、用户名、密码、对应uid
 * id  firstname lastname username password
 */
//@Entity表明是  ORM 映射
@Entity
public class Spitter {
    //id是数据库工具生成的
    @Id
//    生成策略：为无重复的值
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;//spitter的id
    @Column(name="firstname")
    private String firstName;
    @Column(name="lastName")
    private String lastName;
    @Column(name="username")
    private String username;
    @Column(name="password")
    private String password;
    @Column(name="email")
    private String email;

    public Spitter() {}//默认的构造器
    public Spitter(String firstName, String lastName, String username,
                   String password,String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
    }
    public Spitter(Long id, String firstName, String lastName, String username, String password,String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    @Override
    public String toString() {
        return "Spitter{" +
                "id=" + id +
                ", firstname='" + firstName + '\'' +
                ", lastname='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ",email='" + email + '\'' +
                '}';
    }
}
