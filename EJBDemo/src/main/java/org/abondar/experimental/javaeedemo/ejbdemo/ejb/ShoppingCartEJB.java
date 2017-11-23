package org.abondar.experimental.javaeedemo.ejbdemo.ejb;


import org.abondar.experimental.javaeedemo.ejbdemo.model.Item;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Stateful
@StatefulTimeout(value = 20,unit = TimeUnit.SECONDS)
public class ShoppingCartEJB {

    private List<Item> cartItems = new ArrayList<>();

    public void addItem(Item item){
        if (!cartItems.contains(item)){
            cartItems.add(item);
        }
    }

    public void removeItem(Item item){
        if (cartItems.contains(item)){
            cartItems.remove(item);
        }
    }

    public Integer getNumberOfItems(){
        if (cartItems==null || cartItems.isEmpty()){
            return 0;
        }

        return cartItems.size();
    }

    public Float getTotal(){
        if (cartItems==null || cartItems.isEmpty()){
            return 0f;
        }

        Float total = 0f;
        for (Item cartItem : cartItems){
            total += cartItem.getPrice();
        }

        return total;
    }

    public void empty(){
        cartItems.clear();
    }

    @Remove
    public void checkout(){
        //some more stuff here
        cartItems.clear();
    }
}
