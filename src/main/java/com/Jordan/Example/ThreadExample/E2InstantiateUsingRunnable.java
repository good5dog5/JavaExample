package com.Jordan.Example.ThreadExample;

import lombok.AllArgsConstructor;

public class E2InstantiateUsingRunnable {
    public static void main(String[] args) {
        System.out.println("Thread main started");

        Runnable target;
        Thread t1 = new Thread(new MyTaskRunnable("any data that the thread may need to process")) ;
        Thread t2 = new Thread(new MyTaskRunnable("any data that the thread may need to process"));

        t1.start();
        t2.start();

        System.out.println("thread main fininshed");
    }
}

@AllArgsConstructor
class MyTaskRunnable implements Runnable {
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
