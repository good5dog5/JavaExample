package com.Jordan.Example.ThreadExample;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;

public class E8NamingThreadUsingExecutors {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("Thread main started");

        ExecutorService executorService = Executors.newFixedThreadPool(5, new NamedThreadFactory());
        List<Future<Integer>> returnedValues = executorService.invokeAll(Arrays.asList(
                new E8SumFirstNumbers(50),
                new E8SumFirstNumbers(40),
                new E8SumFirstNumbers(30),
                new E8SumFirstNumbers(20),
                new E8SumFirstNumbers(10)));

        for (Future<Integer> value : returnedValues) {
            System.out.println(value.get());
        }

        executorService.shutdown();
        System.out.println("Thread main finished");
    }
}

@RequiredArgsConstructor
class E8SumFirstNumbers implements Callable<Integer> {
    private final int n;

    public Integer call() {
        int sum = 0;
        for(int i = 1; i <= n; i++) {
            System.out.println("[" + Thread.currentThread().getName() + "]" + "Adding " + i);
            sum += i;
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return sum;
    }
}

class NamedThreadFactory implements ThreadFactory {
    private String threadName = "xuxa-thread-";
    private static int count = 0;

    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        t.setName(threadName + count++);
        return t;
    }

}