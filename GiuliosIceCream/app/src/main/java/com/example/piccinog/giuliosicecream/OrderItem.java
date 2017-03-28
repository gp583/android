package com.example.piccinog.giuliosicecream;

import java.io.Serializable;

/**
 * Created by giulio on 3/24/17.
 */

public class OrderItem implements Serializable {
    String amount;
    String date;
    String flavor;
    String size;
    String toppings;


    public OrderItem(String a, String d, String f, String s, String t) {
        this.amount = a;
        this.date = d;
        this.flavor = f;
        this.size = s;
        this.toppings = t;
    }

    @Override
    public String toString() {
        return "Date: " + date + "\n" +
                "Flavor: " + flavor + "\n" +
                "Size: " + size + "\n" +
                "Cost: " + amount + "\n" +
                "Toppings: " + toppings;
    }
}
