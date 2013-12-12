package com.github.desiresdesigner;

import java.io.*;
import java.net.Inet4Address;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;

/**
 * @author m-erofeev
 * @since 11.12.13
 */
public class Client {

  private final String host;
  private final int port;

  public Client(String host, int port) throws MalformedURLException {
    this.host = host;
    this.port = port;
  }

  public String sendMessageAndReceiveAnswer(String message) throws IOException {
    Socket socket = new Socket(host, port);
    final BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    final PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
    output.append(message);
    final String initMessage = inputToString(input);
    output.append(initMessage);
    final String ret = inputToString(input);
    return ret;
  }

  private String inputToString(BufferedReader input) throws IOException {
    StringBuilder inputStringBuilder = new StringBuilder();
    String s;
    while ((s = input.readLine()) != null) {
      inputStringBuilder.append(s).append("\n");
    }
    return inputStringBuilder.toString();
  }
}
