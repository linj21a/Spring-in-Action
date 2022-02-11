/**
 * @author 60417
 * @date 2022/2/4
 * @time 11:51
 * @todo
 */
package org.yuyefanhua.pizza.domain;

import org.apache.commons.lang3.text.WordUtils;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Pizza类型
 */
public enum Topping implements Serializable {
    PEPPERONI,//意大利辣香肠
    SAUSAGE,//塘
    HAMBURGER,//汉堡包
    MUSHROOM,//蘑菇形的
    CANADIAN_BACON,//加拿大腌肉
    PINEAPPLE,//粉苹果
    GREEN_PEPPER,//青椒
    JALAPENO,//墨西哥胡椒，青辣椒
    TOMATO,//番茄
    ONION,//洋葱
    EXTRA_CHEESE;//奶酪

    public static List<Topping> asList() {
        Topping[] all = Topping.values();
        return Arrays.asList(all);
    }
    @Override
    public String toString() {
        //将其转化为字符串    //把下划线变为空格   //获取每个枚举常量： enum的name方法
        return WordUtils.capitalizeFully(name().replace('_', ' '));
    }
}
