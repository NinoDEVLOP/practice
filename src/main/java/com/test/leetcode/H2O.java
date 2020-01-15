package com.test.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * @author xiaqingchuan
 * @version 1.0
 * @date 2019/12/2019/12/17 16:40
 */
class H2O {

    volatile int weight = 0;
    volatile int count = 0;
    List<String> log = new LinkedList<>();

    public H2O() {

    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {

        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        synchronized (this) {
            while (weight == count && count >= 2) {
                this.wait();
            }
            weight += 1;
            count += 1;
            releaseHydrogen.run();
            log.add("count:" + count + ";" + "weight:" + weight);
            if (weight == 4 && count == 3) {
                weight = 0;
                count = 0;
            }

            this.notifyAll();
        }
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        // releaseOxygen.run() outputs "O". Do not change or remove this line.
        synchronized (this) {
            boolean existsOxygen = count == 1 && (weight + 2) == 4;
            boolean hO = count == 2 && weight == 3;
            while (existsOxygen || hO) {
                this.wait();
                existsOxygen = count == 1 && (weight + 2) == 4;
                hO = count == 2 && weight == 3;
            }
            releaseOxygen.run();
            weight += 2;
            count += 1;
            log.add("count:" + count + ";" + "weight:" + weight);
            if (weight == 4 && count == 3) {
                weight = 0;
                count = 0;
            }
            this.notifyAll();
        }
    }

    static class O2H extends H2O {

        Semaphore hSemaphore = new Semaphore(2);

        Semaphore oSemaphore = new Semaphore(0);


        public O2H() {

        }

        @Override
        public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
            hSemaphore.acquire(1);
            releaseHydrogen.run();
            oSemaphore.release(1);
        }

        @Override
        public void oxygen(Runnable releaseOxygen) throws InterruptedException {
            oSemaphore.acquire(2);
            releaseOxygen.run();
            hSemaphore.release(2);
        }

    }

    static class H2Ostreamline extends H2O {

        public H2Ostreamline() {

        }

        volatile int hWeight = 0;
        volatile int oWeight = 0;

        @Override
        public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
            synchronized (this) {
                while(hWeight == 2) {
                    this.wait();
                }
                hWeight += 1;
                releaseHydrogen.run();
                oWeight -= 1;
                this.notifyAll();
            }
        }

        @Override
        public void oxygen(Runnable releaseOxygen) throws InterruptedException {
            synchronized (this) {
                while(oWeight > 0) {
                    this.wait();
                }
                oWeight += 2;
                releaseOxygen.run();
                hWeight -= 2;
                this.notifyAll();
            }
        }

    }

    public static void main(String[] args) {
        String test = "OHHOOHHHH";
        String[] testArr = test.split("");
        List<Thread> threads = new ArrayList<>(testArr.length);
        H2O h2O = new H2Ostreamline();
        for (String str : testArr) {
            Thread thread = new Thread(controller(str, h2O));
            threads.add(thread);
        }
        for (Thread thread : threads) {
            thread.start();
        }
    }

    static Runnable createRunnable(final String atomic) {
        return () -> {
            System.out.print(atomic);
        };
    }

    static Runnable controller(final String atomic, H2O h2O) {
        if ("H".equals(atomic)) {
            return () -> {
                try {
                    h2O.hydrogen(createRunnable(atomic));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
        } else if ("O".equals(atomic)) {
            return () -> {
                try {
                    h2O.oxygen(createRunnable(atomic));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
        }
        return null;
    }
}
