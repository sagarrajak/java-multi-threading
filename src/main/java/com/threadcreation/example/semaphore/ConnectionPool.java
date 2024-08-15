package com.threadcreation.example.semaphore;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;

class DatabaseConnection {
    private final int id;

    DatabaseConnection(int id) {
        this.id = id;
    }

    public void executeQuery(String query) {
        System.out.println("Executing query " + id + " "+query);
        long timeout = (long) (Math.random() * 1000);
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "DatabaseConnection{" +
                "id=" + id +
                '}';
    }
}

public class ConnectionPool {
    private final int poolsize;
    private final Semaphore semaphore;
    private final BlockingQueue<DatabaseConnection> pools;

    public ConnectionPool(int poolsize) {
        this.poolsize = poolsize;
        this.semaphore = new Semaphore(poolsize);
        this.pools = new LinkedBlockingQueue<>();
        for (int i=0; i<poolsize; i++) {
            pools.offer(new DatabaseConnection(i));
        }
    }

    public DatabaseConnection getConnection() throws InterruptedException {
        semaphore.acquire();
        return pools.take();
    }

    public void releaseConnection(DatabaseConnection connection) throws InterruptedException {
         pools.offer(connection);
         semaphore.release();
    }

    public int getAvailableConnection() {
        return semaphore.availablePermits();
    }
}


class DatabaseClient implements Runnable {
    private final ConnectionPool pool;
    private final int id;

    DatabaseClient(ConnectionPool pool, int id) {
        this.pool = pool;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            DatabaseConnection connection = pool.getConnection();
            connection.executeQuery("Select user from users");
            pool.releaseConnection(connection);
            System.out.println("Released connection for client "+id+" and connection "+connection);
            Thread.sleep((long)(Math.random()*1000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}