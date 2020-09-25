package com.Jordan.Example.ThreadExample;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RequiredArgsConstructor
public class ThreadAndSynchronized implements Runnable {

    private final int id;

    @SneakyThrows
    @Override
    public synchronized void run() {

        for(int i=0; i<5; i++) {
            System.out.println("Thread: " + id);
            Thread.sleep(200);
        }

    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new ThreadAndSynchronized(1), "thread 1");
        Thread t2 = new Thread(new ThreadAndSynchronized(2), "thread 2");
        t1.start();
        t2.start();
    }
}
