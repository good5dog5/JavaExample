package com.Jordan.Example.DesignPattern.StrategyPattern;

import org.junit.Test;

public class FlightTest {

    @Test
    public void test() {
        Adventurer adventurer = new Adventurer();

        System.out.println("New 史萊姆");
        adventurer.choiceStrategy(new NormalAttack());
        adventurer.attack();
        System.out.println();

        System.out.println("New huge 史萊姆");
        adventurer.choiceStrategy(new UseSkill());
        adventurer.attack();
        System.out.println();

        System.out.println("New Zombie");
        adventurer.choiceStrategy(new UseItem());
        adventurer.attack();

    }
}
