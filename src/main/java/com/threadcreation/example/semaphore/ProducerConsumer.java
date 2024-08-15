package com.threadcreation.example.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;


public class ProducerConsumer {
    private static final int NUMBER_OF_CONSUMER = 1;
    private static final int NUMBER_OF_PRODUCER = 2;

    private Semaphore producerSemaphore = new Semaphore(NUMBER_OF_PRODUCER);
    private Semaphore consumerSemaphore = new Semaphore(NUMBER_OF_CONSUMER);

    private ReentrantLock lock = new ReentrantLock();






}
