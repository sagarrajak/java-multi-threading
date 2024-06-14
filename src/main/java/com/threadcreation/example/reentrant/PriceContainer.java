package com.threadcreation.example.reentrant;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PriceContainer {

    private Lock lockObject = new ReentrantLock();
    private double bitcoinPrice;
    private double litecoinPrice;
    private double etherPrice;

    private double bitcoinCashPrice;

    private double ripplePrice;

    public Lock getLockObject() {
        return lockObject;
    }

    public void setLockObject(Lock lockObject) {
        this.lockObject = lockObject;
    }

    public void setBitcoinPrice(double bitcoinPrice) {
        this.bitcoinPrice = bitcoinPrice;
    }

    public void setLitecoinPrice(double litecoinPrice) {
        this.litecoinPrice = litecoinPrice;
    }

    public void setEtherPrice(double etherPrice) {
        this.etherPrice = etherPrice;
    }

    public void setBitcoinCashPrice(double bitcoinCashPrice) {
        this.bitcoinCashPrice = bitcoinCashPrice;
    }

    public void setRipplePrice(double ripplePrice) {
        this.ripplePrice = ripplePrice;
    }

    public double getBitcoinPrice() {
        return bitcoinPrice;
    }

    public double getLitecoinPrice() {
        return litecoinPrice;
    }

    public double getEtherPrice() {
        return etherPrice;
    }

    public double getBitcoinCashPrice() {
        return bitcoinCashPrice;
    }

    public double getRipplePrice() {
        return ripplePrice;
    }
}
