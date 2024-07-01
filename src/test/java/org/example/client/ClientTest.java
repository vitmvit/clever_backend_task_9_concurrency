package org.example.client;

import org.example.server.Server;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ClientTest {

    private static final int SIZE = 100;

    private final Client client = new Client(new Server(), SIZE);

    @BeforeEach
    public void setUp() {
        client.getServer().getList().clear();
        client.run();
    }

    @Test
    public void runTest() {
        Assertions.assertEquals((1 + SIZE) * (SIZE / 2), client.getAccumulator());
    }

    @Test
    public void dataSizeTest() {
        Assertions.assertEquals(0, client.getList().size());
    }
}
