package com.threadcreation.example.threadcoordination;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ThreadJoinExample {
    public static void main(String[] args) throws InterruptedException {
        List<Long> list = Arrays.asList(4L, 234L, 84L, 64L, 97123L, 8L);
        List<CalFac> threads = list.stream().map(CalFac::new).collect(Collectors.toList());
        for (CalFac thread: threads) {
            thread.setDaemon(true);
            thread.start();
        }
        for (CalFac thread: threads) {
            thread.join(2000);
        }
        int i=0;
        for (CalFac thread: threads) {
            if(thread.isFinished()) {
                System.out.println("Out for value "+list.get(i)+" "+thread.getResult());
            }
            i++;
        }
    }

    private static class CalFac extends Thread {
        private final long valueToCal;
        private boolean isFinished = false;
        private BigInteger result = BigInteger.ONE;

        private CalFac(long valueToCal) {
            this.valueToCal = valueToCal;
        }

        @Override
        public void run() {
            calculateFac();
            this.isFinished = true;
        }

        private void calculateFac() {
            for (long i=1; i<=valueToCal; i++) {
                result = result.multiply(BigInteger.valueOf(i));
            }
        }

        public boolean isFinished() {
            if (!isFinished) {
                System.out.println("Still calculating for " + valueToCal);
            }
            return isFinished;
        }

        public BigInteger getResult() {
            return result;
        }
    }
}


