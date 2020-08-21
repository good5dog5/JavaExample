package com.Jordan.Example.ThreadExample.CallableExample;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class FutureDemo {

    public static void main(String[] args) {
        Callable<int [] > primeCallable = new PrimeCallable(100000);

        FutureTask<int []> primeTask = new FutureTask<>(primeCallable);

        Thread t = new Thread(primeTask);
        t.start();


        try {
            Thread.sleep(5000);

            if (primeTask.isDone()) {
                int [] primes = primeTask.get();
                for(int prime : primes) {
                    System.out.print(prime + " ");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
