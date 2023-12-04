package org.example.client;

import org.example.model.Request;
import org.example.model.Response;
import org.example.server.Server;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;


public class Client {
    private final Server server;
    private final int size;
    private final Random random = new Random();
    private List<Integer> data;
    private int accumulator;


    public Client(Server server, int size) {
        this.server = server;
        this.size = size;
        this.data = new ArrayList<>(size);
        for (int i = 1; i <= size; i++) {
            data.add(i);
        }
        this.accumulator = 0;
    }

    public void run() {
        ExecutorService executor = Executors.newCachedThreadPool();

        List<Future<Response>> futures = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            int index = getRandomIndex();
            int value = data.remove(index);
            Callable<Response> callable = new RequestTask(value);
            Future<Response> future = executor.submit(callable);
            futures.add(future);
        }

        executor.shutdown();

        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);

            for (Future<Response> future : futures) {
                Response response = future.get();
                accumulator += response.getSize();
            }

            if (data.isEmpty() && (accumulator == (1 + size) * (size / 2))) {
                System.out.println("Контроль пройден.");
            } else {
                System.out.println("Контроль не пройден.");
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private int getRandomIndex() {
        return random.nextInt(data.size());
    }


    private class RequestTask implements Callable<Response> {
        private int value;

        public RequestTask(int value) {
            this.value = value;
        }

        @Override
        public Response call() throws Exception {
            Thread.sleep(getRandomDelay());
            Request request = new Request(value);
            return sendRequest(request);
        }

        private int getRandomDelay() {
            return random.nextInt(401) + 100;
        }

        private Response sendRequest(Request request) {
            return server.processRequest(request);
        }
    }

}
