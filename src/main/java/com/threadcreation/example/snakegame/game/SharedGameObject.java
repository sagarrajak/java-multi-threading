package com.threadcreation.example.snakegame.game;

import java.util.concurrent.locks.ReentrantLock;

public class SharedGameObject {
    ReentrantLock lock = new ReentrantLock();
    private int gameCounter;

    public void increasePoint() {
        if (lock.tryLock()) {
            try {
                gameCounter++;
            }
            finally {
                lock.unlock();
            }
        }
    }
}
