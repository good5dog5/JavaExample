package com.Jordan.Example.DesignPattern.SimpleFactoryExample;

public class Archer implements Adventurer {
    @Override
    public String getType() {
        System.out.println("I am Archer");
        return this.getClass().getSimpleName();
    }
}
