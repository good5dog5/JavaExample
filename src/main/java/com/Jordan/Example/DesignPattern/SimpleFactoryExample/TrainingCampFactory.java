package com.Jordan.Example.DesignPattern.SimpleFactoryExample;

public class TrainingCampFactory {
    public static Adventurer trainAdventurer(String type) {
        switch (type) {
            case "archer" : {
                System.out.println("Train a archer");
                return new Archer();
            }
            case "warrior" : {
                System.out.println("Train a warrior");
                return new Warrior();
            }
            default: return null;
        }
    }
}
