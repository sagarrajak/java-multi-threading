package com.threadcreation.example.reentrant.readwrite;

import java.util.ArrayList;
import java.util.NavigableMap;
import java.util.Random;
import java.util.TreeMap;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class InventoryDatabase {
    private final TreeMap<Integer, Integer> priceToMap = new TreeMap<>();
    public static final int HIGHEST_PRICE = 10000;
//    private ReentrantLock lock = new ReentrantLock();
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public static void main(String[] args) throws InterruptedException {
        InventoryDatabase database = new InventoryDatabase();
        Random random = new Random();
        for (int i=0; i<10000; i++) {
            int randomInt = random.nextInt(HIGHEST_PRICE);
            database.addItem(randomInt);
        }

        Thread writer = new Thread(() -> {
            while (true) {
                database.addItem(random.nextInt(HIGHEST_PRICE));
                database.removeItem(random.nextInt(HIGHEST_PRICE));
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {}
            }
        });
        writer.setDaemon(true);
        writer.start();

        ArrayList<Thread> readThread = getThreads(random, database);

        long startTime = System.currentTimeMillis();
        for (Thread thread: readThread) {
            thread.start();
        }
        for (Thread thread: readThread) {
            thread.join();
        }
        long endTime = System.currentTimeMillis();
        System.out.printf("Reading took %d ms%n", endTime - startTime);
    }

    private static ArrayList<Thread> getThreads(Random random, InventoryDatabase database) {
        int numberOfReaderThread = 7;
        ArrayList<Thread> readThread = new ArrayList<>();
        for (int i =0; i <numberOfReaderThread; i++) {
            Thread thread = new Thread(() -> {
                for (int j=0; j<100000; j++) {
                    int upperBoundPrice = random.nextInt(HIGHEST_PRICE);
                    int lowerBoundPrice =  upperBoundPrice > 0 ? random.nextInt(upperBoundPrice) : 0;
                    database.getNumberOfItemsOfTheRange(lowerBoundPrice, upperBoundPrice);
                }
            });
            thread.setDaemon(true);
            readThread.add(thread);
        }
        return readThread;
    }


    public int getNumberOfItemsOfTheRange(int lowerBound, int upperBound) {
        ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
        readLock.lock();
        try {
            Integer lowerEntry = priceToMap.ceilingKey(lowerBound);
            Integer upperEntry = priceToMap.floorKey(upperBound);

            if (lowerEntry == null || upperEntry == null || lowerBound > upperEntry)
                return 0;

            NavigableMap<Integer, Integer> rangeOfPrices = priceToMap.subMap(lowerEntry, true, upperEntry, true);
            return rangeOfPrices.values().stream().reduce(0, Integer::sum);
        }
        finally {
            readLock.unlock();
        }
    }

    public void addItem(Integer key) {
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
        writeLock.lock();
        try {
            priceToMap.put(key, priceToMap.getOrDefault(key, 0) + 1);
        } finally {
            writeLock.unlock();
        }
    }

    public void removeItem(Integer price) {
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
        writeLock.lock();
        try {
            Integer foundPrice = priceToMap.get(price);
            if (foundPrice != null)
                priceToMap.put(price, foundPrice - 1);
        }
        finally {
            writeLock.unlock();
        }
    }
}
