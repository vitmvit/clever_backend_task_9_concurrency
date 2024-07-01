package org.example.server;

import org.example.model.Request;
import org.example.model.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ServerTest {

    private static final int REQUEST_VALUE = 42;

    private final Server server = new Server();

    @BeforeEach
    public void setUp() {
        server.getList().clear();
    }

    @Test
    public void processRequestTest() {
        Request request = new Request(REQUEST_VALUE);
        Response response = server.processRequest(request);
        Assertions.assertEquals(1, response.getSize());
    }

    @Test
    public void dataElementsTest() {
        server.processRequest(new Request(REQUEST_VALUE));
        Assertions.assertTrue(server.getList().contains(REQUEST_VALUE));
    }
}
