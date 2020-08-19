package com.Jordan.Example.DesignPattern.SimpleFactoryExample;

public class Warrior implements Adventurer {
    @Override
    public String getType() {
        System.out.println("I am Warrior");
        return this.getClass().getSimpleName();

    }
}
