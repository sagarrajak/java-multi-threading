package com.threadcreation.example.datarace;

public class DataraceExample {
    public static void main(String[] args) {
        SharedClass sharedObject = new SharedClass();
        Thread thread1 = new Thread(() -> {
            for (int i=0; i<Integer.MAX_VALUE; i++) {
                sharedObject.increment();
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i=0; i<Integer.MAX_VALUE; i++) {
                sharedObject.checkForDataRace();
            }
        });
        thread1.start();
        thread2.start();
    }
    public static class SharedClass {

        volatile  int x = 0;
        volatile int y = 0;

        public void increment() {
            x++;
            y++;
        }

        public void checkForDataRace() {
            if (y > x) {
                System.out.println("Data race detected");
            }
        }
    }
}
