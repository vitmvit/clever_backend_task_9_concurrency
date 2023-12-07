package org.example.integration;

import org.example.client.Client;
import org.example.server.Server;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClientServerTest {

    private static final int SIZE = 100;

    private final Server server = new Server();
    private final Client client = new Client(server, SIZE);

    @Test
    void testClientServerTest() throws InterruptedException {

        Thread clientThread = new Thread(client::run);
        clientThread.start();

        TimeUnit.SECONDS.sleep(5);

        assertEquals(0, client.getList().size());
        assertEquals(SIZE, server.getList().size());
        assertEquals((1 + SIZE) * (SIZE / 2), client.getAccumulator());

        clientThread.interrupt();
        clientThread.join();
    }
}
