package com.github.desiresdesigner;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.net.MalformedURLException;

import static org.junit.Assert.assertEquals;

/**
 * @author m-erofeev
 * @since 11.12.13
 */
@RunWith(JUnit4.class)
public class CommunicationTests {


  @Test
  public void testSocket() throws IOException {
    final int port = 8000;
    Server server = new Server(port);
    server.start();
    Client client = new Client("localhost", port);
    final String message = "Hello, Worlds";
    final String answer = client.sendMessageAndReceiveAnswer(message);
    assertEquals(message.toUpperCase(), answer);
  }
}
