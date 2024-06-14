package com.threadcreation.example.policegame;

public class AscendingHacker extends  AbstractTheaf{
    public AscendingHacker(Vault vault) {
        super(vault);
    }

    @Override
    public void run() {
        for (int i=0; i < 10000; i++) {
            if(this.vault.isCorrectPassword(i)) {
                System.out.println(String.format("Thread %s have guess the password, %d", Thread.currentThread().getName(), i));
                System.exit(0);
            }
        }
    }
}
