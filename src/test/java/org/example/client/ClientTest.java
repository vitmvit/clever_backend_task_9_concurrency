package org.example.client;

import org.example.server.Server;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ClientTest {

    private Client client;

    @BeforeAll
    void setUp() {
        int size = 100;
        client = new Client(new Server(), size);
    }

    @Test
    public void testRun() {
        client.run();

//        Assertions.assertEquals(0, client.getData().size()); // Проверка, что список данных пуст после выполнения
//        Assertions.assertEquals((1 + 100) * (100 / 2), client.getAccumulator()); // Проверка, что сумма равна ожидаемому значению
    }

}
