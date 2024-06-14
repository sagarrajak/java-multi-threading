package com.threadcreation.example.threadcoordination;

import java.math.BigInteger;
import java.util.Timer;
import java.util.TimerTask;

public class ThreadCoordination {

    public static void main(String[] args) {
        final Thread thread = new Thread(new CalculatePow(189238923, 18));
        thread.start();

        System.out.println("Timer started!");
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                thread.interrupt();
                System.out.println("Timer completed!");
            }
        }, 5000);
    }


    private static class CalculatePow implements Runnable {
        private final BigInteger x;
        private final BigInteger y;

        public CalculatePow(int x, int y) {
            this.x = BigInteger.valueOf(x);
            this.y = BigInteger.valueOf(y);
        }

        @Override
        public void run() {
            BigInteger pow = pow();
            System.out.println("Output from the big integer: " + x+"^^"+y+" = "+pow);
        }

        private BigInteger pow() {
            BigInteger result =  BigInteger.ONE;
            System.out.println("calculation started!");
            for (BigInteger i = BigInteger.ONE; i.compareTo(y) <= 0; i = i.add(BigInteger.ONE)) {
                if (!Thread.currentThread().isInterrupted())
                    result = result.multiply(x);
                else {
                    System.out.println("Calculation interrupted!");
                    return BigInteger.ONE;
                }
            }
            System.out.println("Calculation ended!");
            return result;
        }
    }
}
