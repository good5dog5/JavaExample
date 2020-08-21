package com.Jordan.Example.ThreadExample;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class E5SingleThreadPoolExample {
    public static void main(String[] args) {

        System.out.println("Thread main started");

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new E4MyTask());
        executorService.execute(new E4MyTask());
        executorService.execute(new E4MyTask());
        executorService.execute(new E4MyTask());
        executorService.execute(new E4MyTask());

        executorService.shutdown();

        System.out.println("Thread main finished");
    }
}


class E5MyTask implements Runnable {
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("[" + Thread.currentThread().getName() + "] Message " + i);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}

