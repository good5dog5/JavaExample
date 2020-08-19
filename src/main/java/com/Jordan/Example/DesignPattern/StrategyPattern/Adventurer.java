package com.Jordan.Example.DesignPattern.StrategyPattern;

public class Adventurer {
    FlightStrategy flightStrategy;

    public void attack() {
        if(flightStrategy == null) {
            flightStrategy = new NormalAttack();
        }
        flightStrategy.execute();
    }

    public void choiceStrategy(FlightStrategy strategy) {
        this.flightStrategy = strategy;
    }
}
