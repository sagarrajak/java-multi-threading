package com.threadcreation.example.reentrant;

import java.lang.reflect.Array;

public class Main  {
    public static void main(String[] args) {
        PriceContainer priceContainer = new PriceContainer();;
        PriceUpdater priceUpdater = new PriceUpdater(priceContainer);
        PricePrinter pricePrinter = new PricePrinter(priceContainer);
        priceUpdater.start();
        pricePrinter.start();
    }
}
