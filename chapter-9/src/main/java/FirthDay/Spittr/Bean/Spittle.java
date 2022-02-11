/**
 * @author 60417
 * @date 2022/1/17
 * @time 15:50
 * @todo
 */
package FirthDay.Spittr.Bean;

import java.util.Date;
//使用Apache Commons Lang包来重写equals和hashcode方法
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * 一个java bean--->spittle:用户发布的简短状态更新
 *  属性包括：
 *      Spittle提交的消息内容、
 *      时间戳、
 *      位置信息  ：Spittle发布时对应的经纬度
 */
public class Spittle {
    private final Long id;//Spittle Id
    private final String message;//消息
    private final Date time;//发布的时间戳
    private Double latitude;//维度
    private Double longitude;//经度

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


//    //使用Apache Commons Lang包来重写equals和hashcode方法
//import org.apache.commons.lang3.builder.EqualsBuilder;
//import org.apache.commons.lang3.builder.HashCodeBuilder;
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
