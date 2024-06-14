package com.threadcreation.example.policegame;

public class AbstractTheaf extends Thread {
    protected final Vault vault;

    public AbstractTheaf(Vault vault) {
        this.vault = vault;
        this.setName(this.getClass().getSimpleName());
        this.setPriority(Thread.MAX_PRIORITY);
    }

    @Override
    public void start() {
        System.out.println(String.format("Starting current thread '%s' ", Thread.currentThread().getName()));
        super.start();
    }
}
