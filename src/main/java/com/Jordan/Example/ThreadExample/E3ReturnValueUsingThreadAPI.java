package com.Jordan.Example.ThreadExample;

import lombok.RequiredArgsConstructor;

public class E3ReturnValueUsingThreadAPI {
    public static void main(String[] args) throws InterruptedException {
        E3MyTask task1 = new E3MyTask(400L);
        Thread t1 = new Thread(task1);
        t1.start();

        E3MyTask task2 = new E3MyTask(400L);
        Thread t2 = new Thread(task2);
        t2.start();

        // if task1 is still executing, then it puts the caller thread [main] to wait
        // when it finishes, then notify() is called to wake up the caller thread
        // same for task 2
        System.out.println("Sum: " + task1.getSum());
        System.out.println("Sum: " + task2.getSum());
    }
}


@RequiredArgsConstructor
class E3MyTask implements Runnable {
    private final Long sleep;
    private volatile int sum;
    private boolean done = false;

    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("[" + Thread.currentThread().getName() + "] Message " + i);

            this.sum += i;

            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        done = true;
        synchronized (this) {
            this.notifyAll();
        }
    }

    public int getSum() throws InterruptedException {
        synchronized (this) {
            if (!done) {
                System.out.println(Thread.currentThread().getName()+ " is waiting");
                this.wait();
            }
        }
        return this.sum;
    }

}