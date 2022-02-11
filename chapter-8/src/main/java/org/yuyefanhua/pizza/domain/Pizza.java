/**
 * @author 60417
 * @date 2022/2/4
 * @time 11:47
 * @todo
 */
package org.yuyefanhua.pizza.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Pizza implements Serializable {
    private PizzaSize size;//尺寸
    private List<Topping> toppings;//类型

    public Pizza() {
        toppings = new ArrayList<Topping>();
        size = PizzaSize.LARGE;//默认尺寸为大
    }
    public PizzaSize getSize() {
        return size;
    }
    public void setSize(PizzaSize size) {
        this.size = size;
    }
    public void setSize(String sizeString) {
        this.size = PizzaSize.valueOf(sizeString);
    }
    public List<Topping> getToppings() {
        return toppings;
    }
    public void setToppings(List<Topping> toppings) {
        this.toppings = toppings;
    }
    public void setToppings(String[] toppingStrings) {
        for (int i = 0; i < toppingStrings.length; i++)
            toppings.add(Topping.valueOf(toppingStrings[i]));
    }
}
