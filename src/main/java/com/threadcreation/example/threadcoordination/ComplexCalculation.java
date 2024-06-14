package com.threadcreation.example.threadcoordination;

import java.math.BigInteger;

public class ComplexCalculation {
    public BigInteger calculateResult(BigInteger base1, BigInteger power1, BigInteger base2, BigInteger power2) throws InterruptedException {
        PowerCalculatingThread powerCalculatingThread = new PowerCalculatingThread(base1, power1);
        PowerCalculatingThread powerCalculatingThread1 = new PowerCalculatingThread(base2, power2);

        powerCalculatingThread.start();
        powerCalculatingThread1.start();
        powerCalculatingThread.join();
        powerCalculatingThread.join();


        return powerCalculatingThread1.getResult().add(powerCalculatingThread.getResult());
    }

    private static class PowerCalculatingThread extends Thread {
        private BigInteger result = BigInteger.ONE;
        private BigInteger base;
        private BigInteger power;
        private  boolean isDone;

        public PowerCalculatingThread(BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
            this.isDone = false;
            this.result = BigInteger.ONE;
        }

        @Override
        public void run() {
          for (BigInteger i = BigInteger.ONE; i.compareTo(power) <= 0; i = i.add(BigInteger.ONE)) {
              result = result.multiply(base);
          }
          this.isDone = true;
        }

        public BigInteger getResult() { return result; }

        public boolean isDone() {
            return isDone;
        }
    }
}
