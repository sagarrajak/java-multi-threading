package com.threadcreation.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class StorePrime {
    public List<Double> getLst() {
        return lst;
    }

    List<Double> lst = new ArrayList<>();
    private  long limit;

    public StorePrime(long limit) {
        this.limit = limit;
    }

    public synchronized void addItem(double item) {
        lst.add(item);
    }

    public long getLimit() {
        return limit;
    }
}
public class PrimeCalculator {
    public static void main(String[] args) {
        long nx = 1000l;
        List<Thread> threads = new ArrayList<>();
        StorePrime storePrime = new StorePrime(nx);
        for (int i=1; i<=1; i++) {
            threads.add(new PrimeCalculatorExecute(1, i ,storePrime));
        }
        long l = System.currentTimeMillis();
        threads.forEach(Thread::start);
        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        long l1 = System.currentTimeMillis();
        System.out.println("Total time took "+((l1-l)));
        System.out.println(storePrime.getLst().toString());
    }
}



class PrimeCalculatorExecute extends Thread {
    private final StorePrime storePrime;
    int diff;

    long start;

    public PrimeCalculatorExecute(int diff, long start, StorePrime storePrime) {
        this.diff =  diff;
        this.storePrime = storePrime;
        this.start = start < 2 ? start + diff : start;

    }

    private boolean calculatePrimeHelper(long value) {
        if (value <= 1) return false;
        if (value == 2) return true;
        for (long i=start; i<=Math.sqrt(value); i++) {
            if (value % i == 0) {
                return false;
            }
        }
//        System.out.println(Thread.currentThread().getName()+ " "+value);
        return true;
    }

    @Override
    public void run() {
        for (long i=start; i<=storePrime.getLimit(); i+=diff) {
            if (calculatePrimeHelper(i)) {
                storePrime.addItem(i);
            }
        }
    }
}
