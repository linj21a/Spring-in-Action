/**
 * @author 60417
 * @date 2022/1/17
 * @time 15:50
 * @todo
 */
package com.yuyefanhua.spittr.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.Date;

/**
 * 一个java bean--->spittle:用户发布的简短状态更新
 *  属性包括：
 *      Spittle提交的消息内容、
 *      时间戳、
 *      位置信息  ：Spittle发布时对应的经纬度
 */
@Entity
public class Spittle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;//Spittle Id
    @Column(name = "message")
    private  String message;//消息
    @Column(name = "time")
    private  Date time;//发布的时间戳
    @Column(name = "latitude")
    private Double latitude;//维度
    @Column(name = "longitude")
    private Double longitude;//经度
    @JoinColumn(name = "spitterId")
    private int spitterId;//所对应的用户id

    /**
     * 非空构造器，使用Entity注解，需要有一个非空构造器
     */
    public Spittle() {}
    public Spittle(String message, Date time) {
        this(message,time,null,null);//调用另外一个构造器
    }

    public Spittle(String message, Date time, Double latitude, Double longitude) {
       this(null,message,time,longitude,latitude);
    }

    public Spittle(Long id, String message, Date date, Double longitude, Double latitude) {
        this.id = id;
        this.message = message;
        this.time = date;
        this.latitude = latitude;
        this.longitude = longitude;
    }


    public int getSpitterId() {
        return spitterId;
    }

    public void setSpitterId(int spitterId) {
        this.spitterId = spitterId;
    }

    public Long getId() {
        return id;
    }
    public String getMessage() {
        return message;
    }
    public Date getTime() {
        return time;
    }
    public Double getLatitude() {
        return latitude;
    }
    public Double getLongitude() {
        return longitude;
    }
    @Override
    public boolean equals(Object o) {
        //只需要比较id 和time来判断两个对象是否相等
        return EqualsBuilder.reflectionEquals(this,o,"id","time");
    }

    @Override
    public int hashCode() {
        //只需要根据 id 和time来来生成 hashcode
        return HashCodeBuilder.reflectionHashCode(this,"id","time");
    }

    @Override
    public String toString() {
        return "Spittle{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", time=" + time +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
