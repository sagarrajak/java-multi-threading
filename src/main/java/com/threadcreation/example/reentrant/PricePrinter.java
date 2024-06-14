package com.threadcreation.example.reentrant;

public class PricePrinter extends Thread {
    private final PriceContainer priceContainer;

    public PricePrinter(PriceContainer priceContainer) {
        this.priceContainer = priceContainer;
    }

    @Override
    public void run() {
        while (true) {
            if (this.priceContainer.getLockObject().tryLock()) {
                try {
                    System.out.println("New Bitcoin price "+this.priceContainer.getBitcoinPrice());
                    System.out.println("New ether price "+this.priceContainer.getEtherPrice());
                    System.out.println("New litecoin price "+ this.priceContainer.getLitecoinPrice());
                    System.out.println("New bitcoin cash price "+this.priceContainer.getBitcoinCashPrice());
                    System.out.println("New ripple price "+this.priceContainer.getRipplePrice());
                }
                finally {
                    priceContainer.getLockObject().unlock();
                }
            } else {
                System.out.println("Price is updating!");
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
