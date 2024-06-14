package com.threadcreation.example.policegame;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Vault {
    private final int random;

    public Vault(int random) {
        this.random = random;
    }

    public boolean isCorrectPassword(int password) {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return this.random == password;
    }

    public static void main(String[] args) {
        Random random1 = new Random();
        int i = random1.nextInt(10000);
        Vault vault = new Vault(i);
        AscendingHacker ascendingHacker = new AscendingHacker(vault);
        DeceningHackerThread deceningHackerThread = new DeceningHackerThread(vault);
        Police police = new Police();
        List<Thread> list = Arrays.asList(ascendingHacker, deceningHackerThread, police);
        for (Thread thread: list) {
            thread.start();
        }
    }
}
