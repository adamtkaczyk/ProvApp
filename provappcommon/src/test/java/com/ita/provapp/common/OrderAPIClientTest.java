package com.ita.provapp.common;

import com.ita.provapp.common.exceptions.ServerException;
import com.ita.provapp.common.json.Order;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import static org.junit.Assert.*;

public class OrderAPIClientTest {

    private MockWebServer server;

    @Before
    public void setUp() throws Exception {
        server = new MockWebServer();
    }

    @Test
    public void createOrder() {
        try {
            MockResponse response = new MockResponse();
            response.setHeader("Location",  "/order/" + "112433434");
            server.enqueue(response);

            server.start();

            OrderAPIClient client = new OrderAPIClient(server.url("").toString());
            Order order = new Order(new Date());
            Integer orderId = client.createOrder("123456",order);
            assertEquals(orderId.intValue(), 112433434);

            server.shutdown();
        } catch (IOException | ServerException e) {
            fail(e.getMessage());
        }
    }
}