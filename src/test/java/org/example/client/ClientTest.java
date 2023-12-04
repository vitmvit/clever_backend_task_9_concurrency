package org.example.client;

import org.example.server.Server;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ClientTest {

    private Client client;
    private int sizeList;

    @BeforeEach
    public void setUp() {
        sizeList = 100;
        client = new Client(new Server(), sizeList);
    }

    @Test
    public void testRun() {
        client.run();
        Assertions.assertEquals(0, client.getList().size());
        Assertions.assertEquals((1 + sizeList) * (sizeList / 2), client.getAccumulator());
    }

    @Test
    public void testDataSize() {
        client.run();
        Assertions.assertEquals(0, client.getList().size());
    }
}
