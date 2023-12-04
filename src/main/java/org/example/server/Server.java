package org.example.server;

import org.example.model.Request;
import org.example.model.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Server {
    private final List<Integer> sharedList;
    private final AtomicInteger listSize;

    public Server() {
        this.sharedList = new ArrayList<>();
        this.listSize = new AtomicInteger(0);
    }

    public Response processRequest(Request request) {
        int delay = getRandomDelay();
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int value = request.getValue();
        synchronized (sharedList) {
            sharedList.add(value);
            listSize.incrementAndGet();
        }

        // Отправка ответа клиенту
        return new Response(listSize.get());
    }

    private int getRandomDelay() {
        Random random = new Random();
        return random.nextInt(901) + 100;
    }
}
