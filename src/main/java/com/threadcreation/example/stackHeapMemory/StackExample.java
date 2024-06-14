package com.threadcreation.example.stackHeapMemory;

import java.util.concurrent.Executors;

public class StackExample {
    public static void main(String[] args) {
        int x = 1;
        int y = 2;
        int result = sumNew(x, y);
        Executors.newSingleThreadExecutor().submit(() -> {
            int z = 12;
            int w = 200;
            return multiply(z, w);
        });
    }

    private static int multiply(int z, int w) {
        return z+w;
    }

    private static int sumNew(int x, int y) {
        return x + y;
    }
}
