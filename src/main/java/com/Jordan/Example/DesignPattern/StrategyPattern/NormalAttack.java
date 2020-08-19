package com.Jordan.Example.DesignPattern.StrategyPattern;

public class NormalAttack implements FlightStrategy {
    @Override
    public void execute() {
        System.out.println("Use normal attack");
    }
}
