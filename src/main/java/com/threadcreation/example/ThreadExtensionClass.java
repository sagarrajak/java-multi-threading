package com.threadcreation.example;

public class ThreadExtensionClass extends Thread {
    @Override
    public void run() {
        System.out.println("Started thread run method " + "\"" + Thread.currentThread().getName() + "\"");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("End Thread run method "+ "\"" + Thread.currentThread().getName() + "\"");
    }

    public static void main(String[] args) {
        ThreadExtensionClass threadExtensionClass = new ThreadExtensionClass();
        threadExtensionClass.setName("Class based thread");
        threadExtensionClass.start();
    }
}
