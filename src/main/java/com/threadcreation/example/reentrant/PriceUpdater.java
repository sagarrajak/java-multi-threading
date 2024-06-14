package com.threadcreation.example.reentrant;

import java.util.Random;

public class PriceUpdater extends Thread{
    private final PriceContainer priceContainer;
    private final Random random = new Random();

    public PriceUpdater(PriceContainer priceContainer) {
        this.priceContainer = priceContainer;
    }

    @Override
    public void run() {
        while (true) {
            if(priceContainer.getLockObject().tryLock()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {}
                try {
                    priceContainer.setBitcoinPrice(random.nextInt(20000));
                    priceContainer.setEtherPrice(random.nextInt(2000));
                    priceContainer.setLitecoinPrice(random.nextInt(500));
                    priceContainer.setBitcoinCashPrice(random.nextInt(5000));
                    priceContainer.setRipplePrice(random.nextDouble());
                }
                finally {
                    priceContainer.getLockObject().unlock();
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {}
            } else {
                System.out.println("Price is printing");
            }
        }
    }
}
