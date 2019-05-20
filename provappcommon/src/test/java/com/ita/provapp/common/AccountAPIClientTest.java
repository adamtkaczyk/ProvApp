package com.ita.provapp.common;

import com.ita.provapp.common.exceptions.ServerException;
import com.ita.provapp.common.json.User;

import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import java.net.ConnectException;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import static org.hamcrest.beans.SamePropertyValuesAs.samePropertyValuesAs;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

public class AccountAPIClientTest {

    private MockWebServer server;

    @Before
    public void setUp() throws Exception {
        server = new MockWebServer();
    }

    @Test
    public void getUserExists() {
        try {
            //MockWebServer server = new MockWebServer();
            server.enqueue(new MockResponse().setBody("{\n" +
                    "    \"username\": \"pawel123\",\n" +
                    "    \"name\": \"Pawel\",\n" +
                    "    \"surname\": \"Nowak\",\n" +
                    "    \"email\": \"nowak@gmail.com\"\n" +
                    "}"));

            server.start();

            AccountAPIClient client = new AccountAPIClient(server.url("").toString());
            User user = client.getUser("123", "adam");
            //System.out.println(user.getName());
            assertThat(user, samePropertyValuesAs(new User("pawel123", "Pawel", "Nowak", "nowak@gmail.com")));

            server.shutdown();
        } catch (IOException | ServerException e) {
            fail(e.getMessage());
        }
    }

    @Test(expected = ServerException.class)
    public void getUserNotExists() throws ServerException {
        try {
            //MockWebServer server = new MockWebServer();
            MockResponse response = new MockResponse();
            response.setResponseCode(404);
            response.setBody("{\n" +
                    "    \"message\": \"User: adam1234 not exists.\"\n" +
                    "}");
            server.enqueue(response);

            server.start();

            AccountAPIClient client = new AccountAPIClient(server.url("").toString());
            User user = client.getUser("123", "adam123");
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test(expected = IOException.class)
    public void getUserServerInaccessible() throws IOException {
        try {
            AccountAPIClient client = new AccountAPIClient();
            User user = client.getUser("123", "adam");
        } catch (ServerException e) {
            fail(e.getMessage());
        }
    }
}