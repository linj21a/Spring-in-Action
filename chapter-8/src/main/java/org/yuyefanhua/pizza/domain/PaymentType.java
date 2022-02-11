/**
 * @author 60417
 * @date 2022/2/4
 * @time 16:07
 * @todo
 */
package org.yuyefanhua.pizza.domain;

import java.util.Arrays;
import java.util.List;

import static org.apache.commons.lang3.text.WordUtils.capitalizeFully;
/**
 * 支付类型
 * 枚举型：
 * 现金cash、支票check、信用卡credit card
 */
public enum PaymentType {
    CASH,CHECK,CREDIT_CARD;
    public static List<PaymentType> asList(){
     //获取所有的支付类型,并转为list集合返回
        return Arrays.asList(PaymentType.values());
    }
    @Override
    public String toString() {
        return capitalizeFully(name().replace('_',' '));
    }
}
