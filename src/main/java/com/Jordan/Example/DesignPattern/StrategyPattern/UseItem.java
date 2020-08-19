package com.Jordan.Example.DesignPattern.StrategyPattern;

public class UseItem implements FlightStrategy {
    @Override
    public void execute() {
        System.out.println("[item] use fire band");
    }
}
