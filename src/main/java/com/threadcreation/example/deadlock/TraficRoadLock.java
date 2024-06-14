package com.threadcreation.example.deadlock;

import java.util.Random;

public class TraficRoadLock {

    public static void main(String[] args) {
        Intersection intersection = new Intersection();
        Thread thread1 = new Thread(new TrainA(intersection));
        Thread thread2 = new Thread(new TrainB(intersection));
        thread1.start();
        thread2.start();
    }

    public static class TrainB implements Runnable {
        private final Intersection intersection;
        private final Random random = new Random();

        public TrainB(Intersection intersection) {
            this.intersection = intersection;
        }

        @Override
        public void run() {
            while (true) {
                int timeToSleep = random.nextInt(5);
                try {
                    Thread.sleep(timeToSleep);
                } catch (InterruptedException ignored) {
                }
                this.intersection.takeRoadB();
            }
        }
    }


    public static class TrainA implements Runnable {
        private final Intersection intersection;
        private final Random random = new Random();
        public TrainA(Intersection intersection) {
            this.intersection = intersection;
        }

        @Override
        public void run() {
            while (true) {
                int timeToSleep = random.nextInt(5);
                try {
                    Thread.sleep(timeToSleep);
                } catch (InterruptedException ignored) {
                }
                this.intersection.takeRoadA();
            }
        }
    }

    public static class Intersection {
        final Object roadA = new Object();
        final Object roadB = new Object();

        public void takeRoadA() {
            synchronized (roadA) {
                System.out.println("Road A lock has acquired by thread "+Thread.currentThread().getName());
                synchronized (roadB) {
                    System.out.println("Road b lock acquired by thread "+Thread.currentThread().getName());
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException ignored) {}
                }
            }
        }

        public void takeRoadB() {
            synchronized (roadB) {
                System.out.println("Road B lock has acquired by thread "+Thread.currentThread().getName());
                synchronized (roadA) {
                    System.out.println("Road A lock acquired by thread "+Thread.currentThread().getName());
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException ignored) {}
                }
            }
        }
    }
}
