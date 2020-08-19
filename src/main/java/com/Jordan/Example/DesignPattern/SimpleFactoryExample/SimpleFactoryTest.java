package com.Jordan.Example.DesignPattern.SimpleFactoryExample;

import org.junit.Assert;
import org.junit.Test;


public class SimpleFactoryTest {
    @Test
    public void test() {
        Adventurer adventurer1 = TrainingCampFactory.trainAdventurer("archer");
        Adventurer adventurer2 = TrainingCampFactory.trainAdventurer("warrior");

        assert adventurer1 != null;
        Assert.assertEquals(adventurer1.getType(), "Archer");
        assert adventurer2 != null;
        Assert.assertEquals(adventurer2.getType(), "Warrior");
    }
}
