package com.threadcreation.example;

public class Main {
    public static void main(String[] args) {
        // first way to creating thread
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("Executing current tread " + Thread.currentThread().getName());
                    Thread.sleep(10000);
                    System.out.println("Ending current thread "+Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        // second way of creating thread
        Thread thread2 = new Thread(() -> {
            try {
                System.out.println("Executing current tread " + Thread.currentThread().getName());
                Thread.sleep(10000);
                System.out.println("Ending current thread "+Thread.currentThread().getName());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        thread.start();
        thread2.start();

        thread2.setName("Thread with lambda expression");
        thread2.setPriority(Thread.NORM_PRIORITY);
    }
}
