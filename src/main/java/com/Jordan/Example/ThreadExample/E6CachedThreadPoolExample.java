package com.Jordan.Example.ThreadExample;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class E6CachedThreadPoolExample {
    /*
     * Instantiates a thread pool that allocates as many threads as tasks are given
     *
     * Create more tasks and execute it again. Note that there are no queued tasks as the
     * pool grows when there are no available threads to run the tasks
     */
    public static void main(String[] args) {

        System.out.println("Thread main started");

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new E4MyTask());
        executorService.execute(new E4MyTask());
        executorService.execute(new E4MyTask());
        executorService.execute(new E4MyTask());
        executorService.execute(new E4MyTask());
        executorService.execute(new E4MyTask());
        executorService.execute(new E4MyTask());
        executorService.execute(new E4MyTask());
        executorService.execute(new E4MyTask());
        executorService.execute(new E4MyTask());
        executorService.execute(new E4MyTask());
        executorService.execute(new E4MyTask());
        executorService.execute(new E4MyTask());
        executorService.execute(new E4MyTask());
        executorService.execute(new E4MyTask());
        executorService.execute(new E4MyTask());
        executorService.execute(new E4MyTask());
        executorService.execute(new E4MyTask());
        executorService.execute(new E4MyTask());
        executorService.execute(new E4MyTask());
        executorService.execute(new E4MyTask());
        executorService.execute(new E4MyTask());
        executorService.execute(new E4MyTask());
        executorService.execute(new E4MyTask());
        executorService.execute(new E4MyTask());
        executorService.execute(new E4MyTask());
        executorService.execute(new E4MyTask());
        executorService.execute(new E4MyTask());
        executorService.execute(new E4MyTask());
        executorService.execute(new E4MyTask());

        executorService.shutdown();

        System.out.println("Thread main finished");
    }
}

class E6MyTask implements Runnable {
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
