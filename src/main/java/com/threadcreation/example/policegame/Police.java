package com.threadcreation.example.policegame;

public class Police extends Thread {
    @Override
    public void run() {
        for (int i=0; i<10; i++) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Police is counting " + (i+1));
        }
        System.exit(0);
    }
}
