package com.test.leetcode;

import java.util.function.IntConsumer;

/**
 * @author xiaqingchuan
 * @version 1.0
 * @date 2019/12/2019/12/16 15:56
 */
class ZeroEvenOdd {
    private int n;

    private volatile boolean zeroFlag = true;

    private volatile boolean oddFlag = false;

    private volatile int i = 0;

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        while (this.i < this.n) {
            while (!zeroFlag) {
                Thread.yield();
            }
            printNumber.accept(0);
            i += 1;
            zeroFlag = false;
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        while (this.i <= this.n) {
            while (zeroFlag || !oddFlag) {
                if (this.i > this.n || (this.i == this.n && !oddFlag)) {
                    return;
                }
                Thread.yield();
            }
            printNumber.accept(i);
            oddFlag = false;
            zeroFlag = true;
            if (this.i == this.n) {
                i += 1;
            }
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        while (this.i <= this.n) {
            while (zeroFlag || oddFlag) {
                if (this.i > this.n || (this.i == this.n && oddFlag)) {
                    return;
                }
                Thread.yield();
            }
            printNumber.accept(i);
            oddFlag = true;
            zeroFlag = true;
            if (this.i == this.n) {
                i += 1;
            }
        }
    }

    class OddEvenOdd2{
        private int n;

        public OddEvenOdd2(int n) {
            this.n = n;
        }

        //private Object zeroObj = new Onject();

        private  boolean isZeroPrinted = false;

        private boolean isEven = false;

        private boolean isOdd = false;

        // printNumber.accept(x) outputs "x", where x is an integer.
        public void zero(IntConsumer printNumber) throws InterruptedException {
            //printNumber.accept(0);
            for(int i = 1; i<= n; i ++ ){

                synchronized(this){
                    while(isZeroPrinted){
                        this.wait();
                    }
                    //第一条锁：打印0.
                    printNumber.accept(0);
                    //第二条锁： 如果这个数是偶数。
                    //第三条锁： 如果这数是基数。
                    isZeroPrinted = true;
                    if(i % 2 == 0){
                        isEven = true;
                    }else{
                        isOdd = true;
                    }
                    this.notifyAll();
                }
            }
        }

        public void even(IntConsumer printNumber) throws InterruptedException {
            for(int i = 2; i<= n; i +=2 ){
                synchronized(this){
                    while(!isZeroPrinted || !isEven){
                        this.wait();
                    }
                    //第一条锁：如果当前位数是基数.
                    printNumber.accept(i);
                    isZeroPrinted = false;
                    isEven = false;
                    //第二条锁： 如果这个数是偶数。
                    //第三条锁： 如果这数是基数。
                    notifyAll();
                }
            }
        }

        public void odd(IntConsumer printNumber) throws InterruptedException {
            for(int i = 1; i<= n; i +=2 ){
                synchronized(this){

                    while(!isZeroPrinted || !isOdd){
                        this.wait();
                    }
                    printNumber.accept(i);
                    isOdd = false;
                    isZeroPrinted = false;
                    notifyAll();
                }
            }
        }
    }

    public static void main(String[] args) {
        final ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(2);
        final IntConsumer intConsumer = new IntConsumer() {
            @Override
            public void accept(int value) {
                System.out.print(value);
            }
        };
        Thread a = new Thread(() -> {
            try {
                zeroEvenOdd.zero(intConsumer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread b = new Thread(() -> {
            try {
                zeroEvenOdd.even(intConsumer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread c = new Thread(() -> {
            try {
                zeroEvenOdd.odd(intConsumer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        a.start();
        c.start();
        b.start();
//        Thread.currentThread().getThreadGroup().toString();

//        while(Thread.activeCount() >= 2) {
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("i:" + zeroEvenOdd.i);
//        }
    }
}
