package com.threadcreation.example.policegame;

public class DeceningHackerThread extends AbstractTheaf {
    public DeceningHackerThread(Vault vault) {
        super(vault);
    }

    @Override
    public void run() {
        for (int i=9999; i >= 0; i--) {
            if(this.vault.isCorrectPassword(i)) {
                System.out.println(String.format("Thread %s have guess the password, %d", Thread.currentThread().getName(), i));
                System.exit(0);
            }
        }
    }
}
