package com.EffectiveJava.Chapter2.hierachicalBuilder;

public class PizzaTest {
    public static void main(String[] args) {
        NyPizza pizza = new NyPizza.Builder(NyPizza.Size.SMALL)
                .addTopping(Pizza.Topping.SAUSAGE)
                .addTopping(Pizza.Topping.ONION).build();

        System.out.println(pizza);
    }
}
