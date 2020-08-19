package com.Jordan.Example.DesignPattern.StrategyPattern;

public class UseSkill implements FlightStrategy {
    @Override
    public void execute() {
        System.out.println("[skill] Use super hard attack");
    }
}
