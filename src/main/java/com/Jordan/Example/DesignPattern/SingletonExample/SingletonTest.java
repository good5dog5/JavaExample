package com.Jordan.Example.DesignPattern.SingletonExample;

public class SingletonTest extends Thread{
    String myId;
    public SingletonTest(String id) {
        myId = id;
    }

    public void run() {
        Singleton singleton = Singleton.getInstance();
        if(singleton != null) {
            System.out.println(myId + " 產生 Singleton:" + singleton.hashCode());
        }
    }

    public static void main(String[] args) {
        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();

        System.out.println("s1: " + s1.hashCode() + " s2: " + s2.hashCode());
        System.out.println();

        Thread t1 = new SingletonTest("Thread 1");
        Thread t2 = new SingletonTest("Thread 2");
        t1.start();
        t2.start();
    }
}
