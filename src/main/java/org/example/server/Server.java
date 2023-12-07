package org.example.server;

import org.example.model.Request;
import org.example.model.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Server {
    private static final List<Integer> sharedList = new ArrayList<>();
    private final Lock lock;

    public Server() {
        this.lock = new ReentrantLock();
    }

    public Response processRequest(Request request) {
        int delay = getRandomDelay();
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int value = request.getValue();
        lock.lock();
        try {
            sharedList.add(value);
        } finally {
            lock.unlock();
        }
        return new Response(sharedList.size());
    }

    public List<Integer> getList() {
        return sharedList;
    }

    private int getRandomDelay() {
        Random random = new Random();
        return random.nextInt(901) + 100;
    }
}