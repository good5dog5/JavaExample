package com.Jordan.Example.ThreadExample;

import lombok.AllArgsConstructor;

public class E1InstantiateUsingOnlyThread {
    public static void main(String[] args) {
        System.out.println("Thread main started");

        Thread t1 = new MyTask("any data that the thread may need to process");
        Thread t2 = new MyTask("any data that the thread may need to process");

        t1.start();
        t2.start();

        System.out.println("thread main fininshed");
    }
}

@AllArgsConstructor
class MyTask extends Thread {
    private String anyData;

    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("[" + Thread.currentThread().getName() + "] [data=" + this.anyData + "] Message" + i);

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
