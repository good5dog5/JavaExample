package com.Jordan.Example.ThreadExample;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class E7ReturnValuesUsingCallable {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        System.out.println("Thread main started");

        ExecutorService executorService = Executors.newFixedThreadPool(5);


        List<Future<Integer>> returnValues = executorService.invokeAll(Arrays.asList(
                new E7SumFirstNum(50),
                new E7SumFirstNum(50),
                new E7SumFirstNum(50),
                new E7SumFirstNum(50),
                new E7SumFirstNum(50),
                new E7SumFirstNum(50),
                new E7SumFirstNum(50),
                new E7SumFirstNum(50),
                new E7SumFirstNum(50),
                new E7SumFirstNum(50)));

        for (Future<Integer> value : returnValues) {
            System.out.println(value.get());;
        }

        executorService.shutdown();
        System.out.println("Thread main finished");
    }
}

@RequiredArgsConstructor
class E7SumFirstNum implements Callable<Integer> {
    private final int n;

    public Integer call() {
        int sum = 0;
        for (int i = 0; i <= n; i++) {
            System.out.println("[" + Thread.currentThread().getName() + "] Adding " + i);
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