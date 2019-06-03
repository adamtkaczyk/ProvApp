package com.ita.provapp.common;

import com.ita.provapp.common.exceptions.ServerException;
import com.ita.provapp.common.json.Product;
import com.ita.provapp.common.json.User;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.beans.SamePropertyValuesAs.samePropertyValuesAs;
import static org.junit.Assert.*;

public class ProductsAPIClientTest {

    private MockWebServer server;

    @Before
    public void setUp() throws Exception {
        server = new MockWebServer();
    }

    @Test
    public void getProductExists() {
        try {
            server.enqueue(new MockResponse().setBody("{\n" +
                    "    \"name\": \"product\"\n" +
                    "}"));

            server.start();

            ProductsAPIClient client = new ProductsAPIClient(server.url("").toString());
            Product product = client.getProduct("123");
            assertThat(product, samePropertyValuesAs(new Product("product")));

            server.shutdown();
        } catch (IOException | ServerException e) {
            fail(e.getMessage());
        }
    }

    @Test(expected = ServerException.class)
    public void getProductNotExists() throws ServerException {
        try {
            MockResponse response = new MockResponse();
            response.setResponseCode(404);
            response.setBody("{\n" +
                    "    \"message\": \"Product: 123 not exists.\"\n" +
                    "}");
            server.enqueue(response);

            server.start();

            ProductsAPIClient client = new ProductsAPIClient(server.url("").toString());
            client.getProduct("123");
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test(expected = IOException.class)
    public void getProductServerInaccessible() throws IOException {
        try {
            ProductsAPIClient client = new ProductsAPIClient("http://192.168.10.100:8080");
            client.getProduct("123");
        } catch (ServerException e) {
            fail(e.getMessage());
        }
    }
}